package com.surmize.twitterstream;

import com.surmize.dao.TweetDAO;
import com.surmize.models.Tweet;
import com.twitter.hbc.core.Client;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

public class MsgThread implements Runnable {

    private Client client;
    private BlockingQueue<String> msgQueue;
    private TweetDAO tweetDao;

    public MsgThread(Client client, BlockingQueue<String> msgQueue) {
        this.client = client;
        this.msgQueue = msgQueue;
        tweetDao = new TweetDAO();
    }

    @Override
    public void run() {
        while (!client.isDone()) {
            try {
                String msg = msgQueue.take();
                Status status = getTweetFromJson(msg);
                Tweet t = TweetDataMapper.getTweetFromStatus(status);
                try {
                    tweetDao.insertTweet(t);
                } catch (SQLException ex) {
                    Logger.getLogger(MsgThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(MsgThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected Status getTweetFromJson(String json) {
        try {
            return TwitterObjectFactory.createStatus(json);
        } catch (TwitterException ex) {
            Logger.getLogger(MsgThread.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    

}
