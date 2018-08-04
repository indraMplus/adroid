package com.accenter.com.accentermobile.member;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import com.accenter.com.accentermobile.memberadapter.JakartaAdapter;
import com.accenter.com.accentermobile.memberdata.JakartaData;
import com.accenter.com.accentermobile.tool.AppController;
import com.accenter.com.accentermobile.tool.Server;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.accenter.com.accentermobile.R;


public class JakartaActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{

    ListView list;
    SwipeRefreshLayout swipe;
    //a list to store all the products
    private static final String TAG = JakartaActivity.class.getSimpleName();
    //Creating Views
    List<JakartaData> jakartaDataList = new ArrayList<>();
    //The request counter to send ?page=1, ?page=2  requests
    int no;
    private int offSet = 0;
    Handler handler;
    Runnable runnable;
    JakartaAdapter Jadapter;

    public static final String TAG_NO               = "no";
    public static final String TAG_ID_JAKARTA       = "id";
    public static final String TAG_NAMA             = "nama";
    public static final String TAG_NOPUNG           = "nopung";
    public static final String TAG_CHAPTER          = "chapter";
    public static final String TAG_STATUS           = "status";
    /*private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jakarta);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        list = (ListView) findViewById(R.id.list_jakarta);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub

            }
        });
        Jadapter = new JakartaAdapter(JakartaActivity.this,jakartaDataList);
        list.setAdapter(Jadapter);
        swipe.setOnRefreshListener(this);
        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           jakartaDataList.clear();
                           Jadapter.notifyDataSetChanged();
                           callVolley(0);
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
                            callVolley(0);
                        }
                    };

                    handler.postDelayed(runnable, 3000);
                }
            }

        });
    }
    @Override
    public void onRefresh() {
        swipe.setRefreshing(true);
        jakartaDataList.clear();
        Jadapter.notifyDataSetChanged();
        callVolley(0);
        swipe.setRefreshing(false);
    }
    @Override
    public void onResume(){
        super.onResume();
        Jadapter.notifyDataSetChanged();
    }

    // untuk menampilkan semua data pada listview
    private void callVolley(int page){

        swipe.setRefreshing(true);

        // Creating volley request obj
        String url_list = Server.url_jakarta + "test.php?offset=";
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
                                    JakartaData datajakarta = new JakartaData();

                                    no = obj.getInt(TAG_NO);

                                    datajakarta.setId(obj.getString(TAG_ID_JAKARTA));
                                    datajakarta.setNamaMember(obj.getString(TAG_NAMA));
                                    datajakarta.setNopungMember(obj.getString(TAG_NOPUNG));
                                    datajakarta.setRegionMember(obj.getString(TAG_CHAPTER));
                                    datajakarta.setStatusMember(obj.getString(TAG_STATUS));

                                    // adding news to news array
                                    jakartaDataList.add(datajakarta);

                                    if (no > offSet)
                                        offSet = no;

                                    Log.d(TAG, "offSet " + offSet);

                                } catch (JSONException e) {
                                    Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                                }

                                // notifying list adapter about data changes
                                // so that it renders the list view with updated data
                                Jadapter.notifyDataSetChanged();
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
}
