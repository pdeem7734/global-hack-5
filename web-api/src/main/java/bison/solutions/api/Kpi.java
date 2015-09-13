package bison.solutions.api;

import bison.solutions.domain.smaller.things.Feature;
import bison.solutions.hazelcast.HazelcastConnection;
import bison.solutions.mapper.kpi.*;
import bison.solutions.reducer.ListReducer;
import com.hazelcast.core.ISet;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Path("Kpi")
public class Kpi {
    HazelcastConnection hazelcastConnection;

    @GET
    @Consumes("*/*")
    @Produces("application/json")
    @Path("/Municipality/{municipality}/VehicleStops")
    public StringStringWrapperFacade getVehicleStopsDonut(String municipality) throws ExecutionException, InterruptedException {
        hazelcastConnection = HazelcastConnection.hazelcastConnection;
        JobTracker jobTracker = hazelcastConnection.hazelcastInstance.getJobTracker(hazelcastConnection.MuniReduceNamespace);
        ISet< Feature> bestestSet = hazelcastConnection.hazelcastInstance.getSet(hazelcastConnection.bigThingNamespace);
        KeyValueSource<String, Feature> source = KeyValueSource.fromSet(bestestSet);
        Job< String, Feature > jobs = jobTracker.newJob(source);

        Map<String, List<StringStringWrapperFacade>> map = jobs.mapper(
                new MunicipalityVehicleStopKpiMapper(municipality))
                .reducer(new ListReducer<String, StringStringWrapperFacade>())
                .submit().get();

        List<StringStringWrapperFacade> returnMe = new LinkedList<>();
        for (List<StringStringWrapperFacade> list : map.values()) {
            returnMe.addAll(list);
        }
        return returnMe.get(0);
    }

    @GET
    @Consumes("*/*")
    @Produces("application/json")
    @Path("/Municipality/{municipality}/Demographics")
    public Feature getDemographicsDonut(String municipality) throws ExecutionException, InterruptedException {
        hazelcastConnection = HazelcastConnection.hazelcastConnection;
        JobTracker jobTracker = hazelcastConnection.hazelcastInstance.getJobTracker(hazelcastConnection.MuniReduceNamespace);
        ISet< Feature> bestestSet = hazelcastConnection.hazelcastInstance.getSet(hazelcastConnection.bigThingNamespace);
        KeyValueSource<String, Feature> source = KeyValueSource.fromSet(bestestSet);
        Job< String, Feature > jobs = jobTracker.newJob(source);

        Map<String, List<Feature>> map = jobs.mapper(
                new MunicipalityDemographicKpiMapper(municipality))
                .reducer(new ListReducer<String, Feature>())
                .submit().get();

        List<Feature> returnMe = new LinkedList<>();
        for (List<Feature> list : map.values()) {
            returnMe.addAll(list);
        }
        return returnMe.get(0);
    }


    @GET
    @Consumes("*/*")
    @Produces("application/json")
    @Path("/Municipality/{municipality}/SearchRate")
    public StringStringWrapperFacade getSearchRateDonut(String municipality) throws ExecutionException, InterruptedException {
        hazelcastConnection = HazelcastConnection.hazelcastConnection;
        JobTracker jobTracker = hazelcastConnection.hazelcastInstance.getJobTracker(hazelcastConnection.MuniReduceNamespace);
        ISet< Feature> bestestSet = hazelcastConnection.hazelcastInstance.getSet(hazelcastConnection.bigThingNamespace);
        KeyValueSource<String, Feature> source = KeyValueSource.fromSet(bestestSet);
        Job< String, Feature > jobs = jobTracker.newJob(source);

        Map<String, List<StringStringWrapperFacade>> map = jobs.mapper(
                new MunicipalitySearchRateKpiMapper(municipality))
                .reducer(new ListReducer<String, StringStringWrapperFacade>())
                .submit().get();

        List<StringStringWrapperFacade> returnMe = new LinkedList<>();
        for (List<StringStringWrapperFacade> list : map.values()) {
            returnMe.addAll(list);
        }
        return returnMe.get(0);
    }

    @GET
    @Consumes("*/*")
    @Produces("application/json")
    @Path("/Municipality/{municipality}/ContrabandHitRate")
    public Feature getContrabandHitRateDonut(String municipality) throws ExecutionException, InterruptedException {
        hazelcastConnection = HazelcastConnection.hazelcastConnection;
        JobTracker jobTracker = hazelcastConnection.hazelcastInstance.getJobTracker(hazelcastConnection.MuniReduceNamespace);
        ISet< Feature> bestestSet = hazelcastConnection.hazelcastInstance.getSet(hazelcastConnection.bigThingNamespace);
        KeyValueSource<String, Feature> source = KeyValueSource.fromSet(bestestSet);
        Job< String, Feature > jobs = jobTracker.newJob(source);

        Map<String, List<Feature>> map = jobs.mapper(
                new MunicipalityContrabandHitRateKpiMapper(municipality))
                .reducer(new ListReducer<String, Feature>())
                .submit().get();

        List<Feature> returnMe = new LinkedList<>();
        for (List<Feature> list : map.values()) {
            returnMe.addAll(list);
        }
        return returnMe.get(0);
    }


    @GET
    @Consumes("*/*")
    @Produces("application/json")
    @Path("/Municipality/{municipality}/ArrestRate")
    public Feature getArrestRateDonut(String municipality) throws ExecutionException, InterruptedException {
        hazelcastConnection = HazelcastConnection.hazelcastConnection;
        JobTracker jobTracker = hazelcastConnection.hazelcastInstance.getJobTracker(hazelcastConnection.MuniReduceNamespace);
        ISet< Feature> bestestSet = hazelcastConnection.hazelcastInstance.getSet(hazelcastConnection.bigThingNamespace);
        KeyValueSource<String, Feature> source = KeyValueSource.fromSet(bestestSet);
        Job< String, Feature > jobs = jobTracker.newJob(source);

        Map<String, List<Feature>> map = jobs.mapper(
                new MunicipalityArrestRateKpiMapper(municipality))
                .reducer(new ListReducer<String, Feature>())
                .submit().get();

        List<Feature> returnMe = new LinkedList<>();
        for (List<Feature> list : map.values()) {
            returnMe.addAll(list);
        }
        return returnMe.get(0);
    }
}
