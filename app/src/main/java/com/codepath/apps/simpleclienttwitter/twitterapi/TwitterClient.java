package com.codepath.apps.simpleclienttwitter.twitterapi;

import android.content.Context;

import com.codepath.apps.simpleclienttwitter.constant.Config;
import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
    public static final String REST_URL = Config.REST_URL; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "u0wR0lRZQNQmnqZxvLwSmfKz7";      // Change this
    public static final String REST_CONSUMER_SECRET = "2a9JmfAG4tXnckJBDMVugWesSYeLQSOzzo6qmJGXN3KBVKzRbI"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://simpleclienttwitter"; // Change this (here and in manifest)

    public static final RequestParams params_timeline = new RequestParams();
    RequestParams params_user_details = new RequestParams();
    RequestParams params_post_user_status_or_reply = new RequestParams();

    String timelineUrl = getApiUrl(Config.STRING_TIMELINE);
    String userDetailUrl = getApiUrl(Config.STRING_USER_DETAIL);
    String postUserStatusUrl = getApiUrl(Config.STRING_POST_STATUS);

    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }

    public void getHomeTimeline(AsyncHttpResponseHandler handler) {
        params_timeline.put(Config.STRING_COUNT, Config.COUNT);
        getClient().get(timelineUrl, params_timeline, handler);
    }

    public void getUserDetails(AsyncHttpResponseHandler handler) {
        params_user_details.put(Config.STRING_SKIP_STATUS,Config.SKIP_STATUS);
        params_user_details.put(Config.STRING_INCLUDE_ENTITIES,Config.INCLUDE_ENTITIES);
        getClient().get(userDetailUrl,params_user_details, handler);
    }

    public void postUserStatus(AsyncHttpResponseHandler handler, String statusOrReply, Boolean status, String id) {
        if(!status){
            params_post_user_status_or_reply.put(Config.STATUS,statusOrReply);
            params_post_user_status_or_reply.put(Config.REPLY_STATUS_ID, id);
        }else{
            params_post_user_status_or_reply.remove(Config.REPLY_STATUS_ID);
            params_post_user_status_or_reply.put(Config.STATUS,statusOrReply);
        }

        getClient().post(postUserStatusUrl,params_post_user_status_or_reply, handler);
    }

	/* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
     * 	  i.e getApiUrl("statuses/home_timeline.json");
	 * 2. Define the parameters to pass to the request (query or body)
	 *    i.e RequestParams params = new RequestParams("foo", "bar");
	 * 3. Define the request method and make a call to the client
	 *    i.e client.get(apiUrl, params, handler);
	 *    i.e client.post(apiUrl, params, handler);
	 */
}