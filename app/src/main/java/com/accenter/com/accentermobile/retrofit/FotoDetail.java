package com.accenter.com.accentermobile.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.accenter.com.accentermobile.R;
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
import java.util.Objects;

public class FotoDetail extends AppCompatActivity {

    NetworkImageView thumb_image;
    TextView title;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    String id_galeri;
    private static final String TAG = FotoDetail.class.getSimpleName();

    public static final String TAG_ID       = "id";
    public static final String TAG_TITLE    = "title";
    public static final String TAG_GAMBAR   = "image";
    private static final String url_detail  = Server.URL + "detail_galeri.php";
    String tag_json_obj = "json_obj_req";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_detail);
        thumb_image = (NetworkImageView) findViewById(R.id.imageViewGaleri);
        title      = (TextView) findViewById(R.id.textViewTitle);
        id_galeri = getIntent().getStringExtra(TAG_ID);
        callDetailGaleri(id_galeri);
    }
    private void callDetailGaleri(final String id){

        StringRequest strReq = new StringRequest(Request.Method.POST, url_detail, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response " + response);

                try {
                    JSONObject obj = new JSONObject(response);

                    String titles    = obj.getString(TAG_TITLE);
                    String Gambar   = obj.getString(TAG_GAMBAR);

                    title.setText(titles);

                    if (!Objects.equals(obj.getString(TAG_GAMBAR), "")){
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
                Toast.makeText(FotoDetail.this,
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
