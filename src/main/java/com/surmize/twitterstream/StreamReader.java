package com.surmize.twitterstream;

import com.surmize.dao.StockSymbolDAO;
import com.surmize.models.StockSymbol;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StreamReader {

    public static void main(String args[]) {
        StreamReader sr = new StreamReader();
        try {
            sr.openStream();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(StreamReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void openStream() throws InterruptedException, SQLException {
        /**
         * Set up your blocking queues: Be sure to size these properly based on
         * expected TPS of your stream
         */
        BlockingQueue<String> msgQueue = new LinkedBlockingQueue<>(100000);

        /**
         * Declare the host you want to connect to, the endpoint, and
         * authentication (basic auth or oauth)
         */
        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();

        // Set the stocks to follow
        hosebirdEndpoint.trackTerms( getStockTermList() );

        //OAuth1("consumerKey", "consumerSecret", "token", "secret");
        Authentication hosebirdAuth = new OAuth1(ApiProperties.getConsumerKey(),
                ApiProperties.getConsumerSecret(),
                ApiProperties.getToken(),
                ApiProperties.getSecret());

        // Create a new BasicClient. By default gzip is enabled.
        Client client = new ClientBuilder()
                .name("Hosebird-Client-01")
                .hosts(Constants.STREAM_HOST)
                .endpoint(hosebirdEndpoint)
                .authentication(hosebirdAuth)
                .processor(new StringDelimitedProcessor(msgQueue))
                .build();

        // Establish a connection
        client.connect();

        Thread processingThread = new Thread(new MsgThread(client, msgQueue));
        processingThread.start();
        
        Thread processingThread2 = new Thread(new MsgThread(client, msgQueue));
        processingThread2.start();
        
        Thread processingThread3 = new Thread(new MsgThread(client, msgQueue));
        processingThread3.start();
        
        System.out.println("Threads Started");
    }
    
    private List<String> getStockTermList() throws SQLException{
        List<String> terms = new ArrayList<>();
        StockSymbolDAO stockDao = new StockSymbolDAO();
        List<StockSymbol> symbols = stockDao.getTwitterStockSymbols();
        for (StockSymbol stockSymbol : symbols) {
            terms.add("$"+stockSymbol.symbol);
            System.out.println("$"+stockSymbol.symbol);
        }
        return terms;
    }

}
