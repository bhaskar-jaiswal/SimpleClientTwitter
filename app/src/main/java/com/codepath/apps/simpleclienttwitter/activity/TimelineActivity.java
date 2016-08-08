package com.codepath.apps.simpleclienttwitter.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codepath.apps.simpleclienttwitter.R;
import com.codepath.apps.simpleclienttwitter.adapter.TweetAdapter;
import com.codepath.apps.simpleclienttwitter.constant.Config;
import com.codepath.apps.simpleclienttwitter.dialog.ComposeTweetsDialog;
import com.codepath.apps.simpleclienttwitter.dialog.TweetDetailDialog;
import com.codepath.apps.simpleclienttwitter.listener.EndlessRecyclerViewScrollListener;
import com.codepath.apps.simpleclienttwitter.model.AccountUser;
import com.codepath.apps.simpleclienttwitter.model.Tweet;
import com.codepath.apps.simpleclienttwitter.twitterapi.TwitterApplication;
import com.codepath.apps.simpleclienttwitter.twitterapi.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {

    private TwitterClient client;
    private ArrayList<Tweet> tweetsList;
    private TweetAdapter tweetAdapter;
    private AccountUser accountUser;
    private LinearLayoutManager layoutManager;
    private ComposeTweetsDialog composeTweetsDialog;
    private TweetDetailDialog tweetDetailDialog;
    private Boolean networkAvailable;

    @BindView(R.id.rvTweets)
    RecyclerView rvTweets;

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    /*@BindView(R.id.fab)
    FloatingActionButton fab;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        tweetsList = new ArrayList<Tweet>();

        networkAvailable = isNetworkAvailable() && isOnline();

//        getSupportActionBar().hide();

        ButterKnife.bind(this);
        client = TwitterApplication.getRestClient();

        tweetAdapter = new TweetAdapter(this, tweetsList);
        rvTweets.setAdapter(tweetAdapter);

        rvTweets.setLayoutManager(layoutManager);
        rvTweets.setAdapter(tweetAdapter);
        layoutManager = new LinearLayoutManager(this);

        rvTweets.setLayoutManager(layoutManager);

        rvTweets.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager){
            public void onLoadMore(int page, int totalItemsCount){
//                Config.SINCE_ID=page;
//                Log.d("Debug", Config.SINCE_ID+"");
                if(networkAvailable){
                    populateTimeline();
                }else{
                    Toast.makeText(getApplicationContext(), Config.NETWORK_UNAVAILABLE,Toast.LENGTH_SHORT).show();
                }
            }
        });

        if(networkAvailable) {
            populateTimeline();
            retrieveUserDetails();
        }else{
            tweetsList.clear();
            tweetsList.addAll(Tweet.getAllTweets());
            tweetAdapter.notifyDataSetChanged();
        }

//        setSupportActionBar(toolbar);

        /*SearchView searchView = (SearchView) findViewById(R.id.ivSearchView);
        int searchImgId = android.support.v7.appcompat.R.id.search_button;
        ImageView v = (ImageView) searchView.findViewById(searchImgId);
        v.setImageResource(R.drawable.ic_twitter_search);*/

        composeTweetsDialog = new ComposeTweetsDialog();
        composeTweetsDialog.setCallSubmitTweet(new ComposeTweetsDialog.OnTweetSubmission() {
            @Override
            public void onTweetsubmitted(String tweet, String id) {
                TwitterClient.params_timeline.remove(Config.MAX_ID);
                postUserStatus(tweet,true, id);
            }
        });

        tweetDetailDialog = new TweetDetailDialog();
        tweetAdapter.setOnChooseOptionsActionListener(new TweetAdapter.OnItemClickListener() {
            @Override
            public void onTweetClick(View itemView, int position) {
                android.app.FragmentManager fragment = getFragmentManager();
                Tweet tweet = tweetsList.get(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable("tweet", Parcels.wrap(tweet));
                tweetDetailDialog.setArguments(bundle);
                tweetDetailDialog.show(fragment, "tweet_details");
            }
        });

        tweetDetailDialog.setOnReplyTweet(new TweetDetailDialog.OnReply() {
            @Override
            public void onReplyTweet(String tweet, String id) {
                TwitterClient.params_timeline.remove(Config.MAX_ID);
                postUserStatus(tweet,false, id);
            }
        });


        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                networkAvailable = isNetworkAvailable() && isOnline();

                if(networkAvailable){
                    swipeContainer.setRefreshing(true);
                    tweetsList.clear();
                    tweetAdapter.notifyDataSetChanged();
                    TwitterClient.params_timeline.remove(Config.MAX_ID);
                    populateTimeline();
                }else{
                    Toast.makeText(getApplicationContext(), Config.NETWORK_UNAVAILABLE,Toast.LENGTH_SHORT).show();
                    swipeContainer.setRefreshing(false);
                }

                // Delete database and update tables if network is available
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void populateTimeline() {
        if(isNetworkAvailable() && isOnline()) {
            client.getHomeTimeline(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d("Success Debug", response.toString());
                    ArrayList<Tweet> list = Tweet.fromJSONArray(response);
//                Config.SINCE_ID = list.get(list.size()-1).getUid();
                    tweetsList.addAll(list);
                    tweetAdapter.notifyDataSetChanged();
                    if (accountUser == null && networkAvailable) {
                        retrieveUserDetails();
                    }
                    swipeContainer.setRefreshing(false);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Log.d("getHomeTimeline", errorResponse + "");
                }
            });
        }
    }

    private void retrieveUserDetails() {
        client.getUserDetails(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                Log.d("Account User",response.toString());
                accountUser = new AccountUser(response);
//                Log.d("account user",accountUser.getScreenname()+" "+accountUser.getUsername());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("retrieveUserDetails", errorResponse.toString());
            }
        });
    }

    private void postUserStatus(String status, Boolean statusOrReply, String id) {

        if(networkAvailable){
            client.postUserStatus(new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    tweetsList.clear();
                    tweetAdapter.notifyDataSetChanged();
                    populateTimeline();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Log.d("postUserStatus", errorResponse.toString());
                }
            }, status, statusOrReply,id);
        }else{
            Toast.makeText(getApplicationContext(), Config.NETWORK_UNAVAILABLE,Toast.LENGTH_SHORT).show();
        }
    }

    public void onTweet(View view) {
        FragmentManager fragment = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        if (accountUser != null && accountUser.getUsername() != null) {
            bundle.putParcelable("accountUser", Parcels.wrap(accountUser));
            composeTweetsDialog.setArguments(bundle);
            composeTweetsDialog.show(fragment, "compose_tweets");
        } else {
            Toast.makeText(this, Config.USER_INFORMATION_UNAVAILABLE, Toast.LENGTH_LONG).show();
        }
    }


    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public void onResume(){
        super.onResume();
        networkAvailable = isNetworkAvailable() && isOnline();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(R.id.ivSearchView);
        int searchImgId = android.support.v7.appcompat.R.id.search_button;
        ImageView v = (ImageView) searchView.findViewById(searchImgId);
        v.setImageResource(R.drawable.ic_twitter_search);

        return true;

    }*/
}
