package org.redhat.qe.jaeger.jaxrs.exception.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.jboss.resteasy.spi.DefaultOptionsMethodException;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
public class DefaultOptionsMethodExceptionMapper implements ExceptionMapper<DefaultOptionsMethodException> {

    @Override
    public Response toResponse(DefaultOptionsMethodException exception) {
        return ExceptionMapperUtils.buildResponseWithCors(exception, Status.OK);
    }

}
