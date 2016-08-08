package com.codepath.apps.simpleclienttwitter.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.codepath.apps.simpleclienttwitter.constant.Config;
import com.codepath.apps.simpleclienttwitter.twitterapi.TwitterClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjaiswal on 8/3/2016.
 */
@Table(name = "Tweet")
@Parcel(analyze={Tweet.class, User.class})
public class Tweet extends Model{

    @Column(name="body")
    public String body;

    @Column(name="uid")
    public long uid; // unique id for the tweet (database id)

    @Column(name="userid")
    public int userid;

    @Column(name="timeline")
    public String timeline;

    @Column(name="createdat")
    public String createdAt;

    @Column(name="source")
    public String source;

    @Column(name="user")
    public User user;

    @Column(name="tweetimage")
    public String tweetimage;

    public static Tweet fromJSON(JSONObject jsonObject){
        Tweet tweet = new Tweet();

        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
            tweet.source = jsonObject.getString("source");
            JSONArray jsonArray = jsonObject.getJSONObject("extended_entities").getJSONArray("media");
            if(jsonArray.length() > 0){
                tweet.tweetimage = jsonArray.getJSONObject(0).getString("media_url");
            }

            tweet.user.save();
            tweet.save();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tweet;
    }

    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray){
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        long max_id = Long.MAX_VALUE;
        for(int i=0;i<jsonArray.length();i++){
            try{
                JSONObject tweetJSON = jsonArray.getJSONObject(i);
                Tweet tweet = fromJSON(tweetJSON);
                tweets.add(tweet);
                max_id = Math.min(max_id,tweet.getUid());
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        TwitterClient.params_timeline.put(Config.MAX_ID,max_id);
        return tweets;
    }

    public static List<Tweet> getAllTweets() {
        return new Select()
                .from(Tweet.class)
                .orderBy("uid DESC")
                .execute();
    }


    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public String getTimeline() {
        return timeline;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public String getSource() {
        return source;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    public String getTweetimage() {
        return tweetimage;
    }
}
