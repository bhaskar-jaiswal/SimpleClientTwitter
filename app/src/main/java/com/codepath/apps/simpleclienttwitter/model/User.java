package com.codepath.apps.simpleclienttwitter.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

/**
 * Created by bjaiswal on 8/3/2016.
 */
@Table(name = "User")
@Parcel(analyze = {User.class})
public class User extends Model{

    @Column(name = "name")
    public String name;

    @Column(name = "uid")
    public String uid;

    @Column(name = "screenName")
    public String screenName;

    @Column(name = "profileImageUrl")
    public String profileImageUrl;

    @Column(name = "background_image")
    public String background_image;

    public static User fromJSON(JSONObject jsonObject){
        User user = new User();

        try {
            user.screenName = jsonObject.getString("screen_name");
            user.name = jsonObject.getString("name");
            user.uid = jsonObject.getString("id");
            user.profileImageUrl = jsonObject.getString("profile_image_url");
            user.background_image=jsonObject.getString("profile_background_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
    public String getBackground_image() {
        return background_image;
    }
}
