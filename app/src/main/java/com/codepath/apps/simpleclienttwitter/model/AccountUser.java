package com.codepath.apps.simpleclienttwitter.model;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

/**
 * Created by bhaskarjaiswal on 8/6/16.
 */
@Parcel(analyze={AccountUser.class})
public class AccountUser {
    public String username;
    public String screenname;
    public String profile_image_url;

    public AccountUser() {
    }

    public AccountUser(JSONObject jsonObject){
        try {
            username = jsonObject.getString("name");
            screenname = jsonObject.getString("screen_name");
            profile_image_url = jsonObject.getString("profile_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public String getScreenname() {
        return screenname;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }
}
