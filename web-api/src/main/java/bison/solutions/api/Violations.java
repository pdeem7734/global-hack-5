package bison.solutions.api;

import bison.solutions.domain.Citation;
import bison.solutions.domain.Violation;
import bison.solutions.hazelcast.HazelcastConnection;
import bison.solutions.mapper.CitationNumberMapper;
import bison.solutions.mapper.EndMapper;
import bison.solutions.mapper.ViolationCitationNumberMapper;
import bison.solutions.reducer.ListReducer;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;

import javax.ws.rs.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Path("Violation")
public class Violations {
    HazelcastConnection hazelcastConnection;


    @GET
    @Consumes("*/*")
    @Produces("application/json")
    @Path("CitationNumber/{citationNumber}")
    public List<Violation> getViolationsByCitationNumber(@PathParam("citationNumber") String citationNumber) throws ExecutionException, InterruptedException {
        hazelcastConnection = HazelcastConnection.hazelcastConnection;
        JobTracker jobTracker = hazelcastConnection.hazelcastInstance.getJobTracker(hazelcastConnection.ViolationNamespace);
        IMap<String, Violation> besterMap = hazelcastConnection.hazelcastInstance.getMap(hazelcastConnection.ViolationNamespace);
        KeyValueSource<String, Violation> source = KeyValueSource.fromMap(besterMap);
                Job < String, Violation > jobs = jobTracker.newJob(source);

        Map<String, List<Violation>> map = jobs.mapper(
                new ViolationCitationNumberMapper(Long.parseLong(citationNumber),
                        new EndMapper<String, Violation>()))
                .reducer(new ListReducer<String, Violation>())
                .submit().get();

        List<Violation> returnMe = new LinkedList<>();
        for (List<Violation> list : map.values()) {
            returnMe.addAll(returnMe);
        }
        return returnMe;
    }
}
