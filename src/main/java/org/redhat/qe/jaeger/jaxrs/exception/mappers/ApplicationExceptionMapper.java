package org.redhat.qe.jaeger.jaxrs.exception.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.spi.ApplicationException;
import org.jboss.resteasy.spi.UnhandledException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
@Provider
@Slf4j
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {

    //Refer: https://issues.jboss.org/browse/RESTEASY-891
    // Any exception mappers for 'handled' exceptions
    UnhandledExceptionMapper unhandledExceptionMapper = new UnhandledExceptionMapper();

    @Override
    public Response toResponse(ApplicationException exception) {
        _logger.error("ApplicationException,", exception);
        // As this mapper will override all others, we need to manually delegate exception handling
        Throwable cause = exception.getCause();
        if (cause instanceof UnhandledException) {
            return unhandledExceptionMapper.toResponse((UnhandledException) cause);
        } else {
            return ExceptionMapperUtils.buildResponseWithCors(exception,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
