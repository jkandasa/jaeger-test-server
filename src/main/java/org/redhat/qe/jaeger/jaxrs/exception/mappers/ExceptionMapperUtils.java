package org.redhat.qe.jaeger.jaxrs.exception.mappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.redhat.qe.jaeger.api.model.ApiError;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
@Slf4j
public class ExceptionMapperUtils {
    private ExceptionMapperUtils() {

    }

    public static Response buildResponse(Throwable exception, Status status) {
        _logger.trace("RestEasy exception,", exception);
        return Response.status(status)
                .entity(ApiError.builder().error(exception.getMessage()).build())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    public static Response buildResponseWithCors(Throwable exception, Status status) {
        _logger.trace("RestEasy exception,", exception);
        return Response
                .status(status)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(ApiError.builder().error(exception.getMessage()).build())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
