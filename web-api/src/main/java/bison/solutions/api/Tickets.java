package bison.solutions.api;

import bison.solutions.hazelcast.HazelcastConnection;
import bison.solutions.domain.Citation;
import bison.solutions.mapper.*;
import bison.solutions.reducer.ListReducer;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;

import javax.ws.rs.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Path("Tickets")
public class Tickets {

    HazelcastConnection hazelcastConnection;

    @GET
    @Consumes("*/*")
    @Produces("application/json")
    @Path("/CitationNumber/{citationNumber}")
    public Citation getCitationByCitationNumber(@PathParam("citationNumber") String citationNumber) throws ExecutionException, InterruptedException {
        hazelcastConnection = HazelcastConnection.hazelcastConnection;
        JobTracker jobTracker = hazelcastConnection.hazelcastInstance.getJobTracker(hazelcastConnection.CitationNamespace);
        IMap<String, Citation> bestMap = hazelcastConnection.hazelcastInstance.getMap(hazelcastConnection.CitationNamespace);
        KeyValueSource<String, Citation> source = KeyValueSource.fromMap(bestMap);
        Job<String, Citation> jobs = jobTracker.newJob(source);

        Map<String, List<Citation>> map = jobs.mapper(
                new CitationNumberMapper(Long.parseLong(citationNumber),
                        new EndMapper<String, Citation>()))
                .reducer(new ListReducer<String, Citation>())
                .submit().get();

        List<Citation> returnMe = new LinkedList<>();
        for (List<Citation> list : map.values()) {
            returnMe.addAll(list);
        }
        if (returnMe.size() !=  0) return returnMe.get(0);
        return null;
    }


    @GET
    @Consumes("*/*")
    @Produces("application/json")
    @Path("/FirstName/{firstName}/LastName/{lastName}")
    public List<Citation> getTicketsByName(@PathParam("firstName") String firstName,
                                         @PathParam("lastName") String lastName) throws ExecutionException, InterruptedException {
        hazelcastConnection = HazelcastConnection.hazelcastConnection;
        JobTracker jobTracker = hazelcastConnection.hazelcastInstance.getJobTracker(hazelcastConnection.CitationNamespace);
        IMap<String, Citation> bestMap = hazelcastConnection.hazelcastInstance.getMap(hazelcastConnection.CitationNamespace);
        KeyValueSource<String, Citation> source = KeyValueSource.fromMap(bestMap);
        Job<String, Citation> jobs = jobTracker.newJob(source);

        Map<String, List<Citation>> map = jobs.mapper(
                new FirstAndLastNameMapper(firstName, lastName,
                        new EndMapper<String, Citation>()))
                .reducer(new ListReducer<String, Citation>())
                .submit().get();

        List<Citation> returnMe = new LinkedList<>();
        for (List<Citation> list : map.values()) {
            returnMe.addAll(list);
        }
        return returnMe;
    }

    @GET
    @Consumes("*/*")
    @Produces("application/json")
    @Path("/FirstName/{firstName}/LastName/{lastName}/DoB/{dateOfBirth}")
    public List<Citation> getTicketsByNameAndDoB(@PathParam("firstName") String firstName,
                                               @PathParam("lastName") String lastName,
                                               @PathParam("dateOfBirth") String dateOfBirth) throws ExecutionException, InterruptedException {

        hazelcastConnection = HazelcastConnection.hazelcastConnection;
        JobTracker jobTracker = hazelcastConnection.hazelcastInstance.getJobTracker(hazelcastConnection.CitationNamespace);
        IMap<String, Citation> bestMap = hazelcastConnection.hazelcastInstance.getMap(hazelcastConnection.CitationNamespace);
        KeyValueSource<String, Citation> source = KeyValueSource.fromMap(bestMap);
        Job<String, Citation> jobs = jobTracker.newJob(source);

        Map<String, List<Citation>> map = jobs.mapper(
                new FirstAndLastNameMapper(firstName, lastName,
                        new DoBMapper(new Date(Long.parseLong(dateOfBirth)),
                                new EndMapper<String, Citation>())))
                .reducer(new ListReducer<String, Citation>())
                .submit().get();

        List<Citation> returnMe = new LinkedList<>();
        for (List<Citation> list : map.values()) {
            returnMe.addAll(list);
        }
        return returnMe;
    }

    @GET
    @Consumes("*/*")
    @Produces("application/json")
    @Path("/FirstName/{firstName}/LastName/{lastName}/DoB/{dateOfBirth}/License/{license}")
    public Citation getTicketByNameDoBAndLicenseNumber(@PathParam("firstName") String firstName,
                                                     @PathParam("lastName") String lastName,
                                                     @PathParam("dateOfBirth") String dateOfBirth,
                                                     @PathParam("license") String license) throws ExecutionException, InterruptedException {
        hazelcastConnection = HazelcastConnection.hazelcastConnection;
        JobTracker jobTracker = hazelcastConnection.hazelcastInstance.getJobTracker(hazelcastConnection.CitationNamespace);
        IMap<String, Citation> bestMap = hazelcastConnection.hazelcastInstance.getMap(hazelcastConnection.CitationNamespace);
        KeyValueSource<String, Citation> source = KeyValueSource.fromMap(bestMap);
        Job<String, Citation> jobs = jobTracker.newJob(source);

        Map<String, List<Citation>> map = jobs.mapper(
                new FirstAndLastNameMapper(firstName, lastName,
                        new DoBMapper(new Date(Long.parseLong(dateOfBirth)),
                                new LicenseMapper(license,
                                        new EndMapper<String, Citation>()))))
                .reducer(new ListReducer<String, Citation>())
                .submit().get();

        List<Citation> returnMe = new LinkedList<>();
        for (List<Citation> list : map.values()) {
            returnMe.addAll(list);
        }
        return returnMe.get(0);
    }
}
