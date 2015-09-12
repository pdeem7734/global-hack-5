package bison.solutions.api;

import bison.soltuons.hazelcast.HazelcastConnection;
import bison.solutions.domain.Citation;
import bison.solutions.mapper.EndMapper;
import bison.solutions.mapper.FirstAndLastNameMapper;
import bison.solutions.reducer.ListReducer;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Path("Tickets")
public class Tickets {

    @EJB
    HazelcastConnection hazelcastConnection;

    @GET
    @Path("/FirstName/{firstName}/LastName/{lastName}")
    public List<Citation> getTicketsByName(@PathParam("firstName") String firstName,
                                         @PathParam("lastName") String lastName) throws ExecutionException, InterruptedException {
        JobTracker jobTracker = hazelcastConnection.hazelcastConnection.getJobTracker(hazelcastConnection.CitationNamespace);
        KeyValueSource<String, Citation> source = KeyValueSource.fromMap(hazelcastConnection.hazelcastConnection.getMap(hazelcastConnection.CitationNamespace));
        Job<String, Citation> jobs = jobTracker.newJob(source);

        Map<String, List<Citation>> map = jobs.mapper(new FirstAndLastNameMapper(firstName, lastName, new EndMapper<>()))
                .reducer(new ListReducer<>())
                .submit().get();

        List<Citation> returnMe = new LinkedList<>();
        map.values().stream().forEach(returnMe::addAll);
        return returnMe;
    }

    @GET
    @Path("/FirstName/{firstName}/LastName/{lastName}/DoB/{dateOfBirth}")
    public List<Citation> getTicketsByNameAndDoB(@PathParam("firstName") String firstName,
                                               @PathParam("lastName") String lastName,
                                               @PathParam("dateOfBirth") String dateOfBirth) {

        return null;
    }

    @GET
    @Path("/FirstName/{firstName}/LastName/{lastName}/DoB/{dateOfBirth}/License/{license}")
    public Citation getTicketByNameDoBAndLicenseNumber(@PathParam("firstName") String firstName,
                                                     @PathParam("lastName") String lastName,
                                                     @PathParam("dateOfBirth") String dateOfBirth,
                                                     @PathParam("license") String license) {
        return null;
    }
}
