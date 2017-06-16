package org.redhat.qe.jaeger.jaxrs.exception.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.spi.UnhandledException;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
@Provider
public class UnhandledExceptionMapper implements ExceptionMapper<UnhandledException> {

    @Override
    public Response toResponse(UnhandledException exception) {
        return ExceptionMapperUtils.buildResponseWithCors(exception,
                Response.Status.INTERNAL_SERVER_ERROR);
    }
}
