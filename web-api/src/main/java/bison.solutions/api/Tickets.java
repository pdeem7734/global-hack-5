package bison.solutions.api;

import jdk.nashorn.internal.runtime.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("Tickets")
public class Tickets {

    @GET
    @Path("/FirstName/{firstName}/LastName/{lastName}")
    public List<Object> getTicketsByName(@PathParam("firstName") String firstName,
                                         @PathParam("lastName") String lastName) {
        return null;
    }

    @GET
    @Path("/FirstName/{firstName}/LastName/{lastName}/DoB/{dateOfBirth}")
    public List<Object> getTicketsByNameAndDoB(@PathParam("firstName") String firstName,
                                               @PathParam("lastName") String lastName,
                                               @PathParam("dateOfBirth") String dateOfBirth) {

        return null;
    }

    @GET
    @Path("/FirstName/{firstName}/LastName/{lastName}/DoB/{dateOfBirth}/License/{license}")
    public Object getTicketByNameDoBAndLicenseNumber(@PathParam("firstName") String firstName,
                                                     @PathParam("lastName") String lastName,
                                                     @PathParam("dateOfBirth") String dateOfBirth) {
        return null;
    }
}
