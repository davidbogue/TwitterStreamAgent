package com.surmize.twitterstream;

import com.surmize.models.Tweet;
import com.surmize.models.TweetPlace;
import com.surmize.models.TwitterUser;
import twitter4j.Place;
import twitter4j.Status;
import twitter4j.User;

public class TweetDataMapper {

    public static Tweet getTweetFromStatus(Status status){
        Tweet t = new Tweet();
        t.createdAt = status.getCreatedAt();
        t.favoriteCount = status.getFavoriteCount();
        t.id = status.getId();
        t.inReplyToScreenName = status.getInReplyToScreenName();
        t.inReplyToStatusId = status.getInReplyToStatusId();
        t.inReplyToUserId = status.getInReplyToUserId();
        t.isAnalyzed = false;
        t.isFavorited = status.isFavorited();
        t.isPossiblySensitive = status.isPossiblySensitive();
        t.isRetweet = status.isRetweet();
        t.isRetweeted = status.isRetweeted();
        t.isTruncated = status.isTruncated();
        t.lang =status.getLang();
        if(status.getGeoLocation() != null){
            t.latitude = status.getGeoLocation().getLatitude();
            t.longitude = status.getGeoLocation().getLongitude();
        }
        if(status.getPlace() != null){
            mapTweetPlace(t, status.getPlace());
        }
        t.retweetCount = status.getRetweetCount();
        t.text = status.getText();
        if(status.getUser() != null){
            mapTwitterUser(t, status.getUser());
        }
        return t;
    }

    private static void mapTwitterUser(Tweet t, User user) {
        t.user = new TwitterUser();
        t.user.id =user.getId();
        t.user.URL =user.getURL();
        t.user.biggerProfileImageURL = user.getBiggerProfileImageURL();
        t.user.biggerProfileImageURLHttps = user.getBiggerProfileImageURLHttps();
        t.user.createdAt = user.getCreatedAt();
        t.user.description = user.getDescription();
        t.user.favoritesCount = user.getFavouritesCount();
        t.user.followersCount = user.getFollowersCount();
        t.user.friendsCount = user.getFriendsCount();
        t.user.isGeoEnabled = user.isGeoEnabled();
        t.user.isProtected = user.isProtected();
        t.user.isTranslator = user.isTranslator();
        t.user.isVerified = user.isVerified();
        t.user.lang= user.getLang();
        t.user.listedCount = user.getListedCount();
        t.user.location = user.getLocation();
        t.user.miniProfileImageURL = user.getMiniProfileImageURL();
        t.user.miniProfileImageURLHttps = user.getMiniProfileImageURLHttps();
        t.user.name = user.getName();
        t.user.originalProfileImageURL = user.getOriginalProfileImageURL();
        t.user.originalProfileImageURLHttps = user.getOriginalProfileImageURLHttps();
        t.user.profileImageURL = user.getProfileImageURL();
        t.user.profileImageURLHttps = user.getProfileImageURLHttps();
        t.user.screenName = user.getScreenName();
        t.user.statusesCount = user.getStatusesCount();
        t.user.timeZone = user.getTimeZone();
        t.user.utcOffset = user.getUtcOffset();
        t.twitterUserId = t.user.id;
    }

    private static void mapTweetPlace(Tweet t, Place place) {
        t.place = new TweetPlace();
        t.place.URL = place.getURL();
        t.place.country = place.getCountry();
        t.place.countryCode = place.getCountryCode();
        t.place.fullName = place.getCountryCode();
        t.place.id = place.getId();
        t.place.name = place.getName();
        t.place.placeType = place.getPlaceType();
        t.place.streetAddress = place.getStreetAddress();
        t.tweetPlaceId = t.place.id;
    }
}
