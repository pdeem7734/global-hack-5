package bison.soltuons.hazelcast;

import bison.solutions.domain.Citation;
import bison.solutions.domain.Violation;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

@Startup
@Singleton
public class HazelcastConnection {
    public HazelcastInstance hazelcastConnection;
    public final String CitationNamespace = "ciations";
    public final String ViolationNamespace = "violations";
    @PostConstruct
    private void makeMeAthing() {
        hazelcastConnection = HazelcastClient.newHazelcastClient();
        File citation = new File(System.getProperty("citations.csv"));
        File violations = new File(System.getProperty("violations.csv"));

        putViolationsInHazelcast(violations);
        putCitationsInHazelcast(citation);
    }


    private void putCitationsInHazelcast(File citation) {

        try(FileInputStream fileInputStream = new FileInputStream(citation);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            String line;
            bufferedReader.readLine();
            Citation citationToPutInHaz;
            String key = "";
            while((line = bufferedReader.readLine()) != null) {
                try {
                    String[] values = line.split(",");
                    citationToPutInHaz = new Citation();

                    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
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
                        citationToPutInHaz.setCitationDate(new Date(dtf.parseDateTime(values[2].split(" ")[0]).getMillis()));
                    } catch (IllegalArgumentException e) {/*literally cancer*/}
                    try {
                        citationToPutInHaz.setDateOfBirth(new Date(dtf.parseDateTime(values[5].split(" ")[0]).getMillis()));
                    } catch (IllegalArgumentException e) {/*literally cancer*/}
                    try {
                        citationToPutInHaz.setCourtDate(new Date(dtf.parseDateTime(values[10].split(" ")[0]).getMillis()));
                    } catch (IllegalArgumentException e) {/*literally cancer*/}

                    hazelcastConnection.getMap(CitationNamespace).put(key, citation);
                } catch (ArrayIndexOutOfBoundsException e) {
                    hazelcastConnection.getMap(CitationNamespace).put(key, citation);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void putViolationsInHazelcast(File violation) {
        try (FileInputStream fileInputStream = new FileInputStream(violation);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){

            String line;
            bufferedReader.readLine();
            while((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                Violation violationToPutInHaz = new Violation();
                DateFormat df = DateFormat.getDateInstance();

                String key = values[0];

                violationToPutInHaz.setCitationNumber(Long.parseLong(values[1]));
                violationToPutInHaz.setViolationNumber(values[2]);
                violationToPutInHaz.setViolationDescription(values[3]);
                violationToPutInHaz.setWarrantStatus(parseTOrF(values[4]));
                violationToPutInHaz.setWarrantNumber(values[5]);
                violationToPutInHaz.setStatus(Violation.Status.valueOf(values[6]));
                violationToPutInHaz.setStatusDate(df.parse(values[7]));
                violationToPutInHaz.setFineAmount(values[8]);
                violationToPutInHaz.setCourtCost(values[9]);

                hazelcastConnection.getMap(ViolationNamespace).put(key, violationToPutInHaz);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private boolean parseTOrF(String tOrF) {
        return tOrF.equals("TRUE");
    }
}
