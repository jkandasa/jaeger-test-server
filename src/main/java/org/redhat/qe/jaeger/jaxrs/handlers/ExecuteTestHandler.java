package org.redhat.qe.jaeger.jaxrs.handlers;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.redhat.qe.jaeger.api.TestEngineApi;
import org.redhat.qe.jaeger.api.model.TestData;
import org.redhat.qe.jaeger.jaxrs.RestUtils;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
@Path("/test")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class ExecuteTestHandler {
    TestEngineApi engineApi = new TestEngineApi();

    @POST
    @Path("/simple")
    public Response executeSimpleTest(TestData testData) {
        return RestUtils.getResponse(Status.OK, engineApi.executeSimpleSpanTest(testData));
    }
}