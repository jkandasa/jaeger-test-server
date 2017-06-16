package org.redhat.qe.jaeger.jaxrs.exception.mappers;

import javax.ws.rs.NotSupportedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
@Provider
public class NotSupportedExceptionMapper implements ExceptionMapper<NotSupportedException> {
    @Override
    public Response toResponse(NotSupportedException exception) {
        return ExceptionMapperUtils.buildResponseWithCors(exception,
                Response.Status.UNSUPPORTED_MEDIA_TYPE);
    }

}
