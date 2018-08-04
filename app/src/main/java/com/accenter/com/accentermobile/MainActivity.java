package com.accenter.com.accentermobile;


import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.accenter.com.accentermobile.adapter.ArtikelAdapter;
import com.accenter.com.accentermobile.data.ArtikelData;
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

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    ListView list;
    SwipeRefreshLayout swipe;

    //a list to store all the products
    List<ArtikelData> artikelList = new ArrayList<>();
    private static final String TAG = MainActivity.class.getSimpleName();

    private int offSet = 0;

    int no;

    ArtikelAdapter adapter;
    //the recyclerview
    RecyclerView recyclerView;
    public static final String TAG_NO       = "no";
    public static final String TAG_ID       = "id";
    public static final String TAG_JUDUL    = "judul";
    public static final String TAG_TGL      = "tgl";
    public static final String TAG_ISI      = "isi";
    public static final String TAG_GAMBAR   = "gambar";
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getting the recyclerview from xml
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        list = (ListView) findViewById(R.id.list_artikel);

        //initializing the productlist
        artikelList.clear();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, DetailArtikel.class);
                intent.putExtra(TAG_ID, artikelList.get(position).getId());
                startActivity(intent);
            }
        });

        adapter = new ArtikelAdapter(MainActivity.this,artikelList);
        list.setAdapter(adapter);

        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           artikelList.clear();
                           adapter.notifyDataSetChanged();
                           callNews(0);
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
                            callNews(offSet);
                        }
                    };

                    handler.postDelayed(runnable, 3000);
                }
            }

        });
    }
    @Override
    public void onRefresh() {
        artikelList.clear();
        adapter.notifyDataSetChanged();
        callNews(0);
    }

    private void callNews(int page){

        swipe.setRefreshing(true);

        // Creating volley request obj
        String url_list = Server.URL + "artikel.php?offset=";
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
                                    ArtikelData news = new ArtikelData();

                                    no = obj.getInt(TAG_NO);

                                    news.setId(obj.getString(TAG_ID));
                                    news.setJudulArtikel(obj.getString(TAG_JUDUL));

                                    if (obj.getString(TAG_GAMBAR) != "") {
                                        news.setImgArtikel(obj.getString(TAG_GAMBAR));
                                    }

                                    news.setTglArtikel(obj.getString(TAG_TGL));
                                    news.setIsiArtikel(obj.getString(TAG_ISI));

                                    // adding news to news array
                                    artikelList.add(news);

                                    if (no > offSet)
                                        offSet = no;

                                    Log.d(TAG, "offSet " + offSet);

                                } catch (JSONException e) {
                                    Log.e(TAG, "JSON Parsing error: " + e.getMessage());
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
}
