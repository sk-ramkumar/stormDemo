package server;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.TwitterServlet;

/**
 * Created with IntelliJ IDEA.
 * User: m1013128
 * Date: 6/6/13
 * Time: 12:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class server {

    private static final String APP_NAME = "tweetAnalysis";
    private static final String STATIC_RESOURCE_BASE = "webcontent";

    public static void main(String[] args) {
        Server server = new Server();
        ServerConnector http = new ServerConnector(server);
        http.setHost("localhost");
        http.setPort(8080);
        http.setIdleTimeout(30000);
        server.addConnector(http);

        ResourceHandler staticResourceHandler = new ResourceHandler();
        staticResourceHandler.setResourceBase("./" + STATIC_RESOURCE_BASE + "/");
        staticResourceHandler.setDirectoriesListed(true);

        ContextHandler staticContextHandler = new ContextHandler();
        staticContextHandler.setContextPath("/" + APP_NAME);
        staticContextHandler.setHandler(staticResourceHandler);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.setContextPath("/" + APP_NAME);
        servletContextHandler.addServlet(new ServletHolder(new TwitterServlet()), "/getTweets");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { staticContextHandler, servletContextHandler });

        // Add the handlers to the server and start jetty.
        server.setHandler(handlers);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            System.err.println("Error Starting Jetty Server...........");
            e.printStackTrace();
        }
    }
}
