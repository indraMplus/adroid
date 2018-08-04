package com.accenter.com.accentermobile.gallery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.accenter.com.accentermobile.R;
import com.accenter.com.accentermobile.retrofit.PilihGambarActivity;
import com.accenter.com.accentermobile.tool.AppController;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GaleriFotoMain extends AppCompatActivity {
    private String TAG = GaleriFotoMain.class.getSimpleName();
    private static final String endpoint = "https://accent-er.com/news/foto_kopdar.php";
    private ArrayList<Foto> images;
    private ProgressDialog pDialog;
    private GaleriAdapter mAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeri_foto_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        pDialog = new ProgressDialog(this);
        images = new ArrayList<>();
        mAdapter = new GaleriAdapter(GaleriFotoMain.this, images);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new GaleriAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new GaleriAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", images);
                bundle.putInt("position", position);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        fetchImages();
    }
    @Override
    public void onBackPressed() {
    super.onBackPressed();
    mAdapter.notifyDataSetChanged();
    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        images.clear();
        fetchImages();
        mAdapter.notifyDataSetChanged();
    }
    private void fetchImages() {

        pDialog.setMessage("Memuat Gambar...");
        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(endpoint,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        pDialog.hide();

                        //images.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                Foto image = new Foto();

                                object.getInt("id");
                                image.setName(object.getString("title"));
                                image.setMedium(object.getString("image"));


                                images.add(image);

                            } catch (JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                            }
                        }

                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_atas,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.ambilFotoGaleri:
                Intent ambilfoto = new Intent(GaleriFotoMain.this, PilihGambarActivity.class);
                startActivity(ambilfoto);
                return true;

        }
        return false;
    }
    @Override
    public void onResume(){
        super.onResume();
        images.clear();
        mAdapter.notifyDataSetChanged();
    }
}
