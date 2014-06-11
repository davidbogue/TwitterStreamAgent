package com.surmize.twitterstream;

import org.junit.Test;
import static org.junit.Assert.*;
import twitter4j.Status;

/**
 *
 * @author davidbogue
 */
public class MsgThreadTest {
    
    public MsgThreadTest() {
    }
    
    @Test
    public void testGetTweetFromJson() {
        System.out.println("getTweetFromJson");
        MsgThread mt = new MsgThread(null, null);
        String json = "{\"created_at\":\"Sun Jun 08 03:24:49 +0000 2014\",\"id\":475478525613723648,\"id_str\":\"475478525613723648\",\"text\":\"Are you Bullish on these #stocks $MWV $DATA $HRL $AAPL View now http:\\/\\/t.co\\/l7n6crzlZE\",\"source\":\"\\u003ca href=\\\"https:\\/\\/mobile.twitter.com\\\" rel=\\\"nofollow\\\"\\u003eMobile Web (M2)\\u003c\\/a\\u003e\",\"truncated\":false,\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":2496229530,\"id_str\":\"2496229530\",\"name\":\"Rodger Reinhardtt\",\"screen_name\":\"RodgerReinhard2\",\"location\":\"Martinsville, Illinois\",\"url\":null,\"description\":\"In America, anybody can be president. Thats one of the risks you take. - Adlai Stevenson\",\"protected\":false,\"verified\":false,\"followers_count\":5,\"friends_count\":0,\"listed_count\":0,\"favourites_count\":0,\"statuses_count\":1074,\"created_at\":\"Thu May 15 12:10:32 +0000 2014\",\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":false,\"lang\":\"en\",\"contributors_enabled\":false,\"is_translator\":false,\"profile_background_color\":\"C0DEED\",\"profile_background_image_url\":\"http:\\/\\/abs.twimg.com\\/images\\/themes\\/theme1\\/bg.png\",\"profile_background_image_url_https\":\"https:\\/\\/abs.twimg.com\\/images\\/themes\\/theme1\\/bg.png\",\"profile_background_tile\":false,\"profile_link_color\":\"0084B4\",\"profile_sidebar_border_color\":\"C0DEED\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"profile_image_url\":\"http:\\/\\/abs.twimg.com\\/sticky\\/default_profile_images\\/default_profile_4_normal.png\",\"profile_image_url_https\":\"https:\\/\\/abs.twimg.com\\/sticky\\/default_profile_images\\/default_profile_4_normal.png\",\"default_profile\":true,\"default_profile_image\":true,\"following\":null,\"follow_request_sent\":null,\"notifications\":null},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"retweet_count\":0,\"favorite_count\":0,\"entities\":{\"hashtags\":[{\"text\":\"stocks\",\"indices\":[25,32]}],\"trends\":[],\"urls\":[{\"url\":\"http:\\/\\/t.co\\/l7n6crzlZE\",\"expanded_url\":\"http:\\/\\/bit.ly\\/1kPmoYl\",\"display_url\":\"bit.ly\\/1kPmoYl\",\"indices\":[64,86]}],\"user_mentions\":[],\"symbols\":[{\"text\":\"MWV\",\"indices\":[33,37]},{\"text\":\"DATA\",\"indices\":[38,43]},{\"text\":\"HRL\",\"indices\":[44,48]},{\"text\":\"AAPL\",\"indices\":[49,54]}]},\"favorited\":false,\"retweeted\":false,\"possibly_sensitive\":false,\"filter_level\":\"medium\",\"lang\":\"en\"}";
        Status tweet = mt.getTweetFromJson(json);
        System.out.println(tweet.getText());
        assertEquals("Are you Bullish on these #stocks $MWV $DATA $HRL $AAPL View now http://t.co/l7n6crzlZE", tweet.getText());
    }
    
}
