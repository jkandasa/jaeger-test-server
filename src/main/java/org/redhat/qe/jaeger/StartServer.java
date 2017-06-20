package org.redhat.qe.jaeger;

import static io.undertow.Handlers.resource;

import java.nio.file.Paths;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.redhat.qe.jaeger.jaxrs.handlers.ApplicationBase;

import io.undertow.Undertow;
import io.undertow.server.handlers.resource.PathResourceManager;
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
            server.deploy(ApplicationBase.class, "/api");
            Undertow.Builder serverBuilder = Undertow.builder().addHttpListener(7000, "0.0.0.0");
            String wwwLocation = Utils.getWWW();
            _logger.debug("www location:{}", wwwLocation);
            server.addResourcePrefixPath("/", resource(new PathResourceManager(Paths.get(wwwLocation), 100))
                    .setDirectoryListingEnabled(false));
            server.start(serverBuilder);

        } catch (Exception ex) {
            _logger.error("Exception,", ex);
            server.stop();
        }
    }
}
