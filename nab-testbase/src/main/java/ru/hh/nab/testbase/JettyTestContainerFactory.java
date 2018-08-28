package ru.hh.nab.testbase;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import ru.hh.nab.common.properties.FileSettings;
import static ru.hh.nab.starter.NabApplicationContext.configureServletContext;
import static ru.hh.nab.starter.NabApplicationContext.createResourceConfig;
import ru.hh.nab.starter.server.ServerContext;
import ru.hh.nab.starter.server.jetty.JettyServer;
import ru.hh.nab.starter.server.jetty.JettyServerFactory;
import ru.hh.nab.starter.servlet.ServletConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class JettyTestContainerFactory {
  private static final Logger LOGGER = LoggerFactory.getLogger(JettyTestContainer.class);

  private static final ConcurrentMap<Class<? extends NabTestBase>, JettyTestContainer> INSTANCES = new ConcurrentHashMap<>();
  private static final String BASE_URI = "http://127.0.0.1";

  private final ApplicationContext applicationContext;
  private final ServletConfig servletConfig;
  private final Class<? extends NabTestBase> testClass;

  JettyTestContainerFactory(ApplicationContext applicationContext, ServletConfig servletConfig, Class<? extends NabTestBase> testClass) {
    this.applicationContext = applicationContext;
    this.servletConfig = servletConfig;
    this.testClass = testClass;
  }

  JettyTestContainer createTestContainer() {
    Class<? extends NabTestBase> baseClass = findMostGenericBaseClass(testClass);
    JettyTestContainer testContainer = INSTANCES.computeIfAbsent(baseClass,
        key -> new JettyTestContainer(servletConfig, applicationContext));
    testContainer.start();
    return testContainer;
  }

  private static Class<? extends NabTestBase> findMostGenericBaseClass(Class<? extends NabTestBase> clazz) {
    Class<? extends NabTestBase> current = clazz;
    while (true) {
      try {
        current.getDeclaredMethod("getServletConfig");
        return current;
      } catch (NoSuchMethodException e) {
        current = current.getSuperclass().asSubclass(NabTestBase.class);
      }
    }
  }

  static class JettyTestContainer {
    private final JettyServer jettyServer;
    private URI baseUri;

    JettyTestContainer(ServletConfig servletConfig, ApplicationContext applicationContext) {
      this.baseUri = UriBuilder.fromUri(BASE_URI).build();

      LOGGER.info("Creating JettyTestContainer...");

      final FileSettings fileSettings = applicationContext.getBean(FileSettings.class);
      final ServerContext jettyContext = new ServerContext(fileSettings);
      final ResourceConfig resourceConfig = createResourceConfig(applicationContext);

      jettyServer = JettyServerFactory.create(jettyContext, resourceConfig, servletConfig,
          (contextHandler) -> configureServletContext(contextHandler, applicationContext, servletConfig));
    }

    void start() {
      if (jettyServer.isRunning()) {
        LOGGER.warn("Ignoring start request - JettyTestContainer is already started.");
        return;
      }

      LOGGER.info("Starting JettyTestContainer...");

      jettyServer.start();
      baseUri = UriBuilder.fromUri(baseUri).port(jettyServer.getPort()).build();

      LOGGER.info("Started JettyTestContainer at the base URI " + baseUri);
    }

    String getBaseUrl() {
      return baseUri.toString();
    }

    int getPort() {
      return jettyServer.getPort();
    }
  }
}
