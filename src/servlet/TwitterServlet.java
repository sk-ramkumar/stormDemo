package servlet;

import twitter.TweetStream;
import twitter4j.TwitterStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: m1013128
 * Date: 6/6/13
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class TwitterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String topic1 = request.getParameter("topic1");
        String topic2 = request.getParameter("topic2");
        String topic3 = request.getParameter("topic3");
        String topic4 = request.getParameter("topic4");
        System.out.println(request.getParameterMap());
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(topic1 + "-" + topic2 + "-" + topic3 + "-" + topic4);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String topic1 = request.getParameter("topic1");
        String topic2 = request.getParameter("topic2");
        String topic3 = request.getParameter("topic3");
        String topic4 = request.getParameter("topic4");
        List<String> filters = new ArrayList<String>();
        if(topic1 != null){
            filters.add(topic1);
        }
        if(topic2 != null){
            filters.add(topic2);
        }
        if(topic3 != null){
            filters.add(topic3);
        }
        if(topic4 != null){
            filters.add(topic4);
        }
        TweetStream tweetStream = TweetStream.getInstance();
        tweetStream.connectToTwitter();
        tweetStream.setFilters(filters.toArray(new String[filters.size()]));
        tweetStream.updateFilters();
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(topic1 + "-" + topic2 + "-" + topic3 + "-" + topic4);
    }
}
