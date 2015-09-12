package bison.soltuons.hazelcast;

import bison.solutions.domain.Citation;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;

@Startup
@Singleton
public class HazelcastConnection {
    public HazelcastInstance hazelcastConnection;
    public final String CitationNamespace = "ciations";
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
            while((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                Citation citationToPutInHaz = new Citation();

                DateFormat df = DateFormat.getDateInstance();

                String key = values[0];
                System.out.println("I'm Working!");
                citationToPutInHaz.setCitationNumber(Integer.parseInt(values[1]));
                citationToPutInHaz.setCitationDate(df.parse(values[2]));
                citationToPutInHaz.setFirstName(values[3]);
                citationToPutInHaz.setLastName(values[4]);
                citationToPutInHaz.setDateOfBirth(df.parse(values[5]));
                citationToPutInHaz.setDefendentAddress(values[6]);
                citationToPutInHaz.setDefendentCity(values[7]);
                citationToPutInHaz.setDefendentState(values[8]);
                citationToPutInHaz.setDriversLicenseNumber(values[9]);
                citationToPutInHaz.setCourtDate(df.parse(values[10]));
                citationToPutInHaz.setCourtLocation(values[11]);
                citationToPutInHaz.setCourtAddress(values[12]);

                hazelcastConnection.getMap(CitationNamespace).put(key, citation);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void putViolationsInHazelcast(File citation) {

    }
}
