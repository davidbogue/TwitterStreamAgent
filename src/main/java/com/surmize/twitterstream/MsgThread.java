package com.surmize.twitterstream;

import com.twitter.hbc.core.Client;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

public class MsgThread implements Runnable {

    private Client client;
    private BlockingQueue<String> msgQueue;

    public MsgThread(Client client, BlockingQueue<String> msgQueue) {
        this.client = client;
        this.msgQueue = msgQueue;
    }

    @Override
    public void run() {
        System.out.println("Inside Thread");
        while (!client.isDone()) {
            try {
                String msg = msgQueue.take();
                System.out.println(msg);
                Status tweet = getTweetFromJson(msg);
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
