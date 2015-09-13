package bison.solutions.hazelcast;

import bison.solutions.domain.Citation;
import bison.solutions.domain.Court;
import bison.solutions.domain.ReallyBigThing;
import bison.solutions.domain.Violation;
import bison.solutions.domain.smaller.things.Feature;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.*;
import java.util.Date;
import java.util.Set;

@Startup
@Singleton
public class HazelcastConnection {
    public static HazelcastConnection hazelcastConnection;
    public HazelcastInstance hazelcastInstance;

    public final String CitationNamespace = "ciations";
    public final String ViolationNamespace = "violations";
    public final String CourtNamespace = "court";
    public final String bigThingNamespace = "bigthing";
    public final String MuniReduceNamespace = "muniReduce";

    @PostConstruct
    private void makeMeAthing() {
        hazelcastInstance = Hazelcast.newHazelcastInstance();
        InputStream citation = null;
        InputStream violations = null;
        InputStream court = null;
        InputStream reallybigthing = null;
        try {
            citation = HazelcastConnection.class.getResource("citations.csv").openStream();
            violations = HazelcastConnection.class.getResource("violations.csv").openStream();
            court = HazelcastConnection.class.getResource("courts.csv").openStream();

            putCourtsInHazelcast(court);
            putViolationsInHazelcast(violations);
            putCitationsInHazelcast(citation);
            System.out.println("starting the huge file");
            reallybigthing = HazelcastConnection.class.getResource("reallybigthing.js").openStream();
            injectbigthingIntoHazelcast(reallybigthing);
        hazelcastConnection = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void injectbigthingIntoHazelcast(InputStream reallybigthing) {
        ObjectMapper op = new ObjectMapper();
        try {
            ReallyBigThing object = op.readValue(reallybigthing, ReallyBigThing.class);
            Set<Feature> bigSet = hazelcastInstance.getSet(bigThingNamespace);
            System.out.println("###################");
            System.out.println("# Had this much " + object.features.size());
            System.out.println("###################");

            bigSet.addAll(object.features);
            System.out.println("###################");
            System.out.println("# Got This Much " + bigSet.size());
            System.out.println("###################");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void putCourtsInHazelcast(InputStream stream) {
        try(InputStreamReader inputStreamReader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            String line;
            bufferedReader.readLine();
            Court court = null;
            String key = "";
            while((line = bufferedReader.readLine()) != null) {
                try {
                    String[] values = line.split(",");
                    court = new Court();
                    DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");

                    key = values[0];
                    key = key.toUpperCase();
                    court.setMunicipality(key);
                    court.setMunicipalCourt(values[1]);
                    court.setMunicipalWebsite(values[2]);
                    court.setMunicipalCourtWebsite(values[3]);
                    court.setClerkPhoneNumber(values[5]);
                    if (!values[6].equals("N/A") && !values[6].equals("")) court.setOnlinePayment(true);
                    else court.setOnlinePayment(false);

                    hazelcastInstance.getMap(CourtNamespace).put(key, court);
                } catch (ArrayIndexOutOfBoundsException e) {
                    hazelcastInstance.getMap(CourtNamespace).put(key, court);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void putCitationsInHazelcast(InputStream citation) {

        try(InputStreamReader inputStreamReader = new InputStreamReader(citation);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            String line;
            bufferedReader.readLine();
            Citation citationToPutInHaz = null;
            String key = "";
            while((line = bufferedReader.readLine()) != null) {
                try {
                    String[] values = line.split(",");
                    citationToPutInHaz = new Citation();
                    DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");

                    key = values[0];
                    citationToPutInHaz.setCitationNumber(Integer.parseInt(values[1]));
                    citationToPutInHaz.setFirstName(values[3]);
                    citationToPutInHaz.setLastName(values[4]);
                    citationToPutInHaz.setDefendentAddress(values[6]);
                    citationToPutInHaz.setDefendentCity(values[7]);
                    citationToPutInHaz.setDefendentState(values[8]);
                    citationToPutInHaz.setDriversLicenseNumber(values[9]);
                    citationToPutInHaz.setCourtLocation(values[11]);
                    citationToPutInHaz.setCourtAddress(values[12]);

                    try {
                        citationToPutInHaz.setCitationDate(new Date(dtf.parseMillis(values[2].split(" ")[0])));
                    } catch (IllegalArgumentException e) {/*literally cancer*/}
                    try {
                        citationToPutInHaz.setDateOfBirth(new Date(dtf.parseMillis(values[5].split(" ")[0])));
                    } catch (IllegalArgumentException e) {/*literally cancer*/}
                    try {
                        citationToPutInHaz.setCourtDate(new Date(dtf.parseMillis(values[10].split(" ")[0])));
                    } catch (IllegalArgumentException e) {/*literally cancer*/}

                    hazelcastInstance.getMap(CitationNamespace).put(key, citationToPutInHaz);
                } catch (ArrayIndexOutOfBoundsException e) {
                    hazelcastInstance.getMap(CitationNamespace).put(key, citationToPutInHaz);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void putViolationsInHazelcast(InputStream violation) {
        try (InputStreamReader inputStreamReader = new InputStreamReader(violation);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){

            String line;
            bufferedReader.readLine();
            while((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                Violation violationToPutInHaz = new Violation();
                DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");

                String key = values[0];
                try {
                    violationToPutInHaz.setCitationNumber(Long.parseLong(values[1]));
                    violationToPutInHaz.setViolationNumber(values[2]);
                    violationToPutInHaz.setViolationDescription(values[3]);
                    violationToPutInHaz.setWarrantStatus(parseTOrF(values[4]));
                    violationToPutInHaz.setWarrantNumber(values[5]);
                    try {
                        violationToPutInHaz.setStatus(Violation.Status.valueOf(values[6]));
                    } catch (IllegalArgumentException ex) { /* */ }
                    violationToPutInHaz.setStatusDate(new Date(dtf.parseMillis(values[7])));
                    violationToPutInHaz.setFineAmount(values[8]);
                    violationToPutInHaz.setCourtCost(values[9]);
                } catch (ArrayIndexOutOfBoundsException e) { }

                hazelcastInstance.getMap(ViolationNamespace).put(key, violationToPutInHaz);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean parseTOrF(String tOrF) {
        return tOrF.equals("TRUE");
    }
}
