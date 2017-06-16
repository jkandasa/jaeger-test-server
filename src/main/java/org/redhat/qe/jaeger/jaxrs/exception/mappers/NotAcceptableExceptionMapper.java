package org.redhat.qe.jaeger.jaxrs.exception.mappers;

import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
@Provider
public class NotAcceptableExceptionMapper implements ExceptionMapper<NotAcceptableException> {
    @Override
    public Response toResponse(NotAcceptableException exception) {
        return ExceptionMapperUtils.buildResponseWithCors(exception,
                Response.Status.NOT_ACCEPTABLE);
    }

}
