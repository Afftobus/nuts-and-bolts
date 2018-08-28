package ru.hh.nab.starter;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import ru.hh.nab.starter.jersey.DefaultResourceConfig;
import ru.hh.nab.starter.server.jetty.JettyServer;
import ru.hh.nab.starter.server.ServerContext;
import ru.hh.nab.starter.server.jetty.JettyServerFactory;
import ru.hh.nab.starter.servlet.ServletConfig;

public final class NabApplicationContext extends AnnotationConfigWebApplicationContext {

  private volatile JettyServer jettyServer;

  private final ServletConfig servletConfig;

  NabApplicationContext(ServletConfig servletConfig, Class<?>... primarySources) {
    this.servletConfig = servletConfig;
    register(primarySources);
    registerShutdownHook();
  }

  @Override
  protected void finishRefresh() {
    super.finishRefresh();
    startJettyServer();
  }

  private void startJettyServer() {
    JettyServer jettyServer = this.jettyServer;
    try {
      if (jettyServer == null) {
        final ResourceConfig resourceConfig = createResourceConfig(this);
        final ServerContext jettyContext = getBean(ServerContext.class);

        this.jettyServer = JettyServerFactory.create(jettyContext, resourceConfig, servletConfig, (contextHandler) -> {
          configureServletContext(contextHandler, this, servletConfig);
          setServletContext(contextHandler.getServletContext());
        });

        this.jettyServer.start();
      }
    } catch (Throwable t) {
      throw new ApplicationContextException("Unable to start application server", t);
    }
  }

  public static void configureServletContext(ServletContextHandler handler, ApplicationContext applicationContext, ServletConfig servletConfig) {
    handler.getServletContext().setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, applicationContext);
    servletConfig.configureServletContext(handler, applicationContext);
  }

  public static ResourceConfig createResourceConfig(ApplicationContext applicationContext) {
    ResourceConfig resourceConfig = new DefaultResourceConfig();
    applicationContext.getBeansWithAnnotation(javax.ws.rs.Path.class)
        .forEach((name, bean) -> resourceConfig.register(bean));
    return resourceConfig;
  }

  boolean isServerRunning() {
    return jettyServer.isRunning();
  }
}
