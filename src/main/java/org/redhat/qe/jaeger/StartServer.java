package org.redhat.qe.jaeger;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.redhat.qe.jaeger.jaxrs.handlers.ApplicationBase;

import io.undertow.Undertow;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
@Slf4j
public class StartServer {
    public static void main(String[] args) {
        UndertowJaxrsServer server = new UndertowJaxrsServer();
        try {
            Undertow.Builder serverBuilder = Undertow.builder().addHttpListener(7000, "0.0.0.0");
            server.start(serverBuilder);
            server.deploy(ApplicationBase.class, "/rest");
        } catch (Exception ex) {
            _logger.error("Exception,", ex);
            server.stop();
        }

    }
}
