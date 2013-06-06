package poc;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * Created with IntelliJ IDEA.
 * User: ramkumar
 * Date: 06/06/13
 * Time: 10:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyEchoServlet extends WebSocketServlet {

    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.register(TestSocket.class);
    }
}
