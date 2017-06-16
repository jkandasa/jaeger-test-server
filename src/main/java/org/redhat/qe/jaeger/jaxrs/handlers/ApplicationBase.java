package org.redhat.qe.jaeger.jaxrs.handlers;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.redhat.qe.jaeger.jaxrs.exception.mappers.ApplicationExceptionMapper;
import org.redhat.qe.jaeger.jaxrs.exception.mappers.BadRequestExceptionMapper;
import org.redhat.qe.jaeger.jaxrs.exception.mappers.DefaultOptionsMethodExceptionMapper;
import org.redhat.qe.jaeger.jaxrs.exception.mappers.ForbiddenExceptionMapper;
import org.redhat.qe.jaeger.jaxrs.exception.mappers.NotAcceptableExceptionMapper;
import org.redhat.qe.jaeger.jaxrs.exception.mappers.NotAllowedExceptionMapper;
import org.redhat.qe.jaeger.jaxrs.exception.mappers.NotAuthorizedExceptionMapper;
import org.redhat.qe.jaeger.jaxrs.exception.mappers.NotFoundExceptionMapper;
import org.redhat.qe.jaeger.jaxrs.exception.mappers.NotSupportedExceptionMapper;
import org.redhat.qe.jaeger.jaxrs.providers.McJacksonJson2Provider;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
public class ApplicationBase extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new LinkedHashSet<Class<?>>();

        //resources
        resources.add(PingHandler.class);
        resources.add(ExecuteTestHandler.class);

        //providers
        resources.add(ApplicationExceptionMapper.class);
        resources.add(BadRequestExceptionMapper.class);
        resources.add(DefaultOptionsMethodExceptionMapper.class);
        resources.add(ForbiddenExceptionMapper.class);
        resources.add(McJacksonJson2Provider.class); //Mixin provider
        resources.add(NotAcceptableExceptionMapper.class);
        resources.add(NotAllowedExceptionMapper.class);
        resources.add(NotAuthorizedExceptionMapper.class);
        resources.add(NotFoundExceptionMapper.class);
        resources.add(NotSupportedExceptionMapper.class);

        return resources;
    }
}
