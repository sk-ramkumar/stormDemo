package twitter;

import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created with IntelliJ IDEA.
 * User: ramkumar
 * Date: 06/06/13
 * Time: 9:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class TweetStream {
    private static final LinkedBlockingDeque<String> tweets = new LinkedBlockingDeque<String>();
    private static String [] filters;
    private static TweetStream tweetStream = new TweetStream();
    TwitterStream twitterStream;
    private boolean isConnected = false;

    private TweetStream() {
    }

    public static TweetStream getInstance(){
        return tweetStream;
    }

    public void connectToTwitter(){
        if(isConnected){
            return;
        }
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                /*System.getProperties().put("proxySet", "true");
                System.getProperties().put("proxyHost", "115.112.231.106");
                System.getProperties().put("proxyPort", "80");*/
                twitterStream = new TwitterStreamFactory().getInstance();
                twitterStream.setOAuthConsumer("FsqmyZhKOK5ZzX5VuFAZOw", "sP8dm9sMhF28rBM4WuJ62Mht7xpyzxTC8NgzTqOvyI");
                AccessToken accessToken = new AccessToken("74695937-Jm4EQFeZQTOqLXJKUm4xVXWwaTWGTfGastdAEz9AX", "UKULYpntfTKlOi0f9B2Ga8eJEvc8mblWGbHKSCgiCM8");
                twitterStream.setOAuthAccessToken(accessToken);
                twitterStream.addListener(new StatusListener() {

                    @Override
                    public void onStatus(Status status) {
                        tweets.push(status.getText());
                        System.out.println(status.getText());
                    }

                    @Override
                    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }

                    @Override
                    public void onTrackLimitationNotice(int i) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }

                    @Override
                    public void onScrubGeo(long l, long l2) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }

                    @Override
                    public void onStallWarning(StallWarning stallWarning) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }

                    @Override
                    public void onException(Exception e) {
                        e.printStackTrace();
                        twitterStream.shutdown();
                        isConnected = false;
                    }
                });

            }
        });
        t.run();
        isConnected = true;
    }

    public static LinkedBlockingDeque<String> getTweets() {
        return tweets;
    }

    public String[] getFilters() {
        return filters;
    }

    public void setFilters(String[] filters) {
        TweetStream.filters = filters;
    }

    public void updateFilters(){
        FilterQuery query = new FilterQuery();
        query.track(filters);
        twitterStream.cleanUp();
        twitterStream.filter(query);
    }
}
