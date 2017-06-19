package org.redhat.qe.jaeger.jaxrs.handlers;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.redhat.qe.jaeger.api.model.Pong;
import org.redhat.qe.jaeger.jaxrs.RestUtils;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
@Path("/")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class PingHandler {

    @GET
    @Path("ping")
    public Response ping() throws UnknownHostException {
        return RestUtils.getResponse(Status.OK, Pong.builder()
                .hostname(InetAddress.getLocalHost().getHostName())
                .ip(InetAddress.getLocalHost().getHostAddress())
                .timestamp(System.currentTimeMillis()).build());
    }
}