package org.redhat.qe.jaeger.jaxrs.handlers;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.redhat.qe.jaeger.jaxrs.RestUtils;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.2
 */
@Path("/utils")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class UtilsHandler {
    @GET
    @Path("/environment")
    public Response environment() {
        return RestUtils.getResponse(Status.OK, System.getenv());
    }
    
    @GET
    @Path("/properties")
    public Response properties() {
        return RestUtils.getResponse(Status.OK, System.getProperties());
    }

}
