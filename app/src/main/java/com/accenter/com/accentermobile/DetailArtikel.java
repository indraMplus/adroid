package com.accenter.com.accentermobile;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.accenter.com.accentermobile.tool.AppController;
import com.accenter.com.accentermobile.tool.Server;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.MobileAds;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DetailArtikel extends AppCompatActivity {

    NetworkImageView thumb_image;
    TextView judul, tgl, isi;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    SwipeRefreshLayout swipe;
    private String id_artikel;

    private static final String TAG = DetailArtikel.class.getSimpleName();
    //private AdView mAdView;
    public static final String TAG_ID       = "id";
    public static final String TAG_JUDUL    = "judul";
    public static final String TAG_TGL      = "tgl";
    public static final String TAG_ISI      = "isi";
    public static final String TAG_GAMBAR   = "gambar";

    private static final String url_detail  = Server.URL + "detail_artikel.php";
    String tag_json_obj = "json_obj_req";
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_artikel);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Detail Artikel");

        //inisial iklan
//        MobileAds.initialize(this, "ca-app-pub-1163823443997525~4444327801");
//        mAdView = (AdView)findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        thumb_image = (NetworkImageView) findViewById(R.id.gambar_artikel);
        judul       = (TextView) findViewById(R.id.judul_artikel);
        tgl         = (TextView) findViewById(R.id.tgl_artikel);
        isi         = (TextView) findViewById(R.id.isi_artikel);
        id_artikel = getIntent().getStringExtra(TAG_ID);
        callDetailNews(id_artikel);
    }
    private void callDetailNews(final String id){

        StringRequest strReq = new StringRequest(Request.Method.POST, url_detail, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response " + response);

                try {
                    JSONObject obj = new JSONObject(response);

                    String Judul    = obj.getString(TAG_JUDUL);
                    String Gambar   = obj.getString(TAG_GAMBAR);
                    String Tgl      = obj.getString(TAG_TGL);
                    String Isi      = obj.getString(TAG_ISI);

                    judul.setText(Judul);
                    tgl.setText(Tgl);
                    isi.setText(Isi);

                    if (obj.getString(TAG_GAMBAR)!=""){
                        thumb_image.setImageUrl(Gambar, imageLoader);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Detail News Error: " + error.getMessage());
                Toast.makeText(DetailArtikel.this,
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to post url
                Map<String, String> params = new HashMap<>();
                params.put("ids", id);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
