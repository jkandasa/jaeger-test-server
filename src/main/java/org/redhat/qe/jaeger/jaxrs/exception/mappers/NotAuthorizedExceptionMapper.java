package org.redhat.qe.jaeger.jaxrs.exception.mappers;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
@Provider
public class NotAuthorizedExceptionMapper implements ExceptionMapper<NotAuthorizedException> {
    @Override
    public Response toResponse(NotAuthorizedException exception) {
        return ExceptionMapperUtils.buildResponseWithCors(exception,
                Response.Status.UNAUTHORIZED);
    }

}
