package com.accenter.com.accentermobile.member;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import com.accenter.com.accentermobile.R;
import com.accenter.com.accentermobile.adapter.MemberAdapter;
import com.accenter.com.accentermobile.data.MemberData;
import com.accenter.com.accentermobile.tool.AppController;
import com.accenter.com.accentermobile.tool.Server;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.MobileAds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ListAllMemberActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private int offSet = 0;
    int no;
    ListView list;
    SwipeRefreshLayout swipe;
    MemberAdapter adapter;
    //a list to store all the products
    List<MemberData> memberList = new ArrayList<>();
    //private AdView mAdView;
    private static final String TAG = ListAllMemberActivity.class.getSimpleName();
    public static final String TAG_NO               = "no";
    public static final String TAG_ID               = "id";
    public static final String TAG_NAMA             = "nama";
    public static final String TAG_NOPUNG           = "nopung";
    public static final String TAG_CHAPTER          = "chapter";
    public static final String TAG_STATUS           = "status";
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_member);

        //inisial iklan
//        MobileAds.initialize(this, "ca-app-pub-1163823443997525~4444327801");
//        mAdView = (AdView)findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        list = (ListView) findViewById(R.id.listMember);

        adapter = new MemberAdapter(this,memberList);
        list.setAdapter(adapter);

        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           memberList.clear();
                           adapter.notifyDataSetChanged();
                           callMember(0);
                       }
                   }
        );

        list.setOnScrollListener(new AbsListView.OnScrollListener() {

            private int currentVisibleItemCount;
            private int currentScrollState;
            private int currentFirstVisibleItem;
            private int totalItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.currentScrollState = scrollState;
                this.isScrollCompleted();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                this.currentFirstVisibleItem = firstVisibleItem;
                this.currentVisibleItemCount = visibleItemCount;
                this.totalItem = totalItemCount;
            }

            private void isScrollCompleted() {
                if (totalItem - currentFirstVisibleItem == currentVisibleItemCount
                        && this.currentScrollState == SCROLL_STATE_IDLE) {

                    swipe.setRefreshing(true);
                    handler = new Handler();

                    runnable = new Runnable() {
                        public void run() {
                            callMember(offSet);
                        }
                    };

                    handler.postDelayed(runnable, 3000);
                }
            }

        });
    }
    @Override
    public void onRefresh() {
        memberList.clear();
        adapter.notifyDataSetChanged();
        callMember(0);
    }
    @Override
    public void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private void callMember(int page){

        swipe.setRefreshing(true);

        // Creating volley request obj
        String url_list = Server.DATA_MEMBER + "test.php?offset=";
        JsonArrayRequest arrReq = new JsonArrayRequest(url_list + page,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        if (response.length() > 0) {
                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    MemberData memberData = new MemberData();

                                    no = obj.getInt(TAG_NO);

                                    memberData.setId(obj.getString(TAG_ID));
                                    memberData.setNamaMember(new String(obj.getString(TAG_NAMA).getBytes("UTF-8")));
                                    memberData.setNopung(obj.getString(TAG_NOPUNG));
                                    memberData.setChapter(obj.getString(TAG_CHAPTER));
                                    memberData.setStatus(obj.getString(TAG_STATUS));

                                    if (no > offSet)
                                        offSet = no;

                                    Log.d(TAG, "offSet " + offSet);
                                    // adding news to news array
                                    memberList.add(memberData);
                                } catch (JSONException e) {
                                    Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }

                                // notifying list adapter about data changes
                                // so that it renders the list view with updated data
                                adapter.notifyDataSetChanged();
                            }
                        }
                        swipe.setRefreshing(false);
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                swipe.setRefreshing(false);
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(arrReq);
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.memberdes,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.member_info:
                Intent meminfo = new Intent(ListAllMemberActivity.this, MemberListInfo.class);
                startActivity(meminfo);

                return true;
        }
        return false;
    }
}
