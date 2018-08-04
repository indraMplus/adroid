package com.accenter.com.accentermobile;

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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailEvent extends AppCompatActivity {

    NetworkImageView thumb_image;
    TextView judul, tgl, isi,poster,jam;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    SwipeRefreshLayout swipe;
    String id_event;

    private static final String TAG = DetailEvent.class.getSimpleName();

    public static final String TAG_ID       = "id";
    public static final String TAG_JUDUL    = "judul";
    public static final String TAG_TGL      = "tgl";
    public static final String TAG_ISI      = "isi";
    public static final String TAG_GAMBAR   = "gambar";
    public static final String TAG_JAM      = "jam";
    public static final String TAG_POST     = "post";

    private static final String url_detail  = Server.URL + "detail_event.php";
    String tag_json_obj = "json_obj_req";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_event);

        thumb_image = (NetworkImageView) findViewById(R.id.imageViewEvent);
        judul       = (TextView) findViewById(R.id.textViewTitle);
        tgl         = (TextView) findViewById(R.id.textViewTglEvent);
        isi         = (TextView) findViewById(R.id.textViewIsiEvent);
        jam       = (TextView) findViewById(R.id.textViewJamEvent);
        poster         = (TextView) findViewById(R.id.textViewPostEvent);
        id_event = getIntent().getStringExtra(TAG_ID);
        callDetailEvent(id_event);
    }
    private void callDetailEvent(final String id){

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
                    String Jam      = obj.getString(TAG_JAM);
                    String Poster      = obj.getString(TAG_POST);

                    judul.setText(Judul);
                    tgl.setText(Tgl);
                    isi.setText(Isi);
                    jam.setText(Jam);
                    poster.setText(Poster);

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
                Toast.makeText(DetailEvent.this,
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
