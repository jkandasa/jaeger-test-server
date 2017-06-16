package org.redhat.qe.jaeger.jaxrs.exception.mappers;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException exception) {
        if (exception.getCause() instanceof NumberFormatException) {
            return ExceptionMapperUtils.buildResponseWithCors(exception,
                    Response.Status.BAD_REQUEST);
        }
        return ExceptionMapperUtils.buildResponse(exception,
                Response.Status.NOT_FOUND);
    }
}
