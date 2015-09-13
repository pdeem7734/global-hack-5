package bison.solutions.api;

import bison.solutions.domain.Court;
import bison.solutions.hazelcast.HazelcastConnection;

import javax.ws.rs.*;

@Path("Court")
public class Courts {
    HazelcastConnection hazelcastConnection;


    @POST
    @Consumes("*/*")
    @Produces("application/json")
    @Path("/Municipality")
    public Court getCourtByMunicipality(String municipality) {
        hazelcastConnection = HazelcastConnection.hazelcastConnection;
        return (Court) hazelcastConnection.hazelcastInstance.getMap(hazelcastConnection.CourtNamespace).get(municipality);
    }
}
