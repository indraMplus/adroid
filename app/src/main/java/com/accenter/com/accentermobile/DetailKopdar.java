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
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class DetailKopdar extends AppCompatActivity {

    TextView judul, tgl, isi,poster,region;
    SwipeRefreshLayout swipe;
    String id_kopdar;

    private static final String TAG = DetailKopdar.class.getSimpleName();

    public static final String TAG_ID       = "id";
    public static final String TAG_JUDUL    = "judul";
    public static final String TAG_TGL      = "tgl";
    public static final String TAG_ISI      = "isi";
    public static final String TAG_REGION   = "region";
    public static final String TAG_POST     = "post";
    private static final String url_detail  = Server.URL + "detail_kopdar.php";
    String tag_json_obj = "json_obj_req";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_kopdar);

        judul       = (TextView) findViewById(R.id.textViewJudulKopdar);
        tgl         = (TextView) findViewById(R.id.textViewNopung);
        isi         = (TextView) findViewById(R.id.textViewIsiKopdar);
        region       = (TextView) findViewById(R.id.textViewRegionKopdar);
        poster         = (TextView) findViewById(R.id.textViewChapter);
        id_kopdar = getIntent().getStringExtra(TAG_ID);
        callDetailKopdar(id_kopdar);
    }
    private void callDetailKopdar(final String id){

        StringRequest strReq = new StringRequest(Request.Method.POST, url_detail, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response " + response);

                try {
                    JSONObject obj = new JSONObject(response);

                    String Judul    = obj.getString(TAG_JUDUL);
                    String Region   = obj.getString(TAG_REGION);
                    String Tgl      = obj.getString(TAG_TGL);
                    String Isi      = obj.getString(TAG_ISI);
                    String Poster   = obj.getString(TAG_POST);

                    judul.setText(Judul);
                    tgl.setText(Tgl);
                    isi.setText(Isi);
                    region.setText(Region);
                    poster.setText(Poster);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Detail Kopdar Error: " + error.getMessage());
                Toast.makeText(DetailKopdar.this,
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
