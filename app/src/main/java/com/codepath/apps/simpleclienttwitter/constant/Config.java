package com.codepath.apps.simpleclienttwitter.constant;

/**
 * Created by bhaskarjaiswal on 8/2/16.
 */
public class Config {
    public static final String CONSUMER_KEY="u0wR0lRZQNQmnqZxvLwSmfKz7";
    public static final String CONSUMER_SECRET="2a9JmfAG4tXnckJBDMVugWesSYeLQSOzzo6qmJGXN3KBVKzRbI";
    public static final String REST_URL = "https://api.twitter.com/1.1";
    public static final Integer COUNT=25;
    public static final Integer CHARACTER_COUNT = 140;
    public static final Integer BLUE = 130;
    public static final Integer GRAY = 110;
    public static final Integer SINCE_ID = 1;
    public static final int TWEET = 0;

    //Strings
    public static final String STRING_COUNT="count";
    public static final String STRING_SINCEID="since_id";
    public static final String STRING_TIMELINE = "statuses/home_timeline.json";
    public static final String STRING_USER_DETAIL = "account/verify_credentials.json";
    public static final String STRING_POST_STATUS = "statuses/update.json";
    public static final String STATUS = "status";
    public static final String REPLY_STATUS_ID = "in_reply_to_status_id";
    public static final Boolean SKIP_STATUS = false ;
    public static final String STRING_SKIP_STATUS = "skip_status";
    public static final String STRING_INCLUDE_ENTITIES="include_entities";
    public static final String MAX_ID = "max_id";
    public static final String DISCARD_MESSAGE = "Discard This Message ?";
    public static final String NETWORK_UNAVAILABLE= "Network Not Available";
    public static final String USER_INFORMATION_UNAVAILABLE="Unable to Retrieve User Information";
    public static final String STRING_TWEET = "TWEET";
    public static final String STRING_REPLY = "Reply";
    public static final String STRING_REPLY_TO = "Reply to ";
    public static final String YES = "Yes";
    public static final String NO = "No";

    public static final Boolean INCLUDE_ENTITIES=false;

    public static final String TWITTER_TIMESTAMP="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
}
