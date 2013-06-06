package handlers;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: m1013128
 * Date: 6/6/13
 * Time: 12:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleHandler extends AbstractHandler {
    @Override
    public void handle(String targetRequest, Request baseRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        System.out.println("SimpleHandler.handle");
        System.out.println(targetRequest);
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        httpServletResponse.getWriter().write("Jetty Embeded Server is running...........");

    }


}
