package com.accenter.com.accentermobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.accenter.com.accentermobile.adapter.GridViewAdapter;
import com.accenter.com.accentermobile.retrofit.PilihGambarActivity;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GaleriKopdar extends AppCompatActivity{

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton2;
    //parameter zoom

    //Web api url
    public static final String DATA_URL = "https://accent-er.com/news/foto_kopdar.php";
    //Tag values to read from json
    public static final String TAG_ID       = "id";
    public static final String TAG_IMAGE_URL = "image";
    public static final String TAG_NAME = "title";
    GridViewAdapter gridViewAdapter;
    //GridView Object
    private GridView gridView;

    //ArrayList for Storing image urls and titles
    ArrayList<String> images;
    ArrayList<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeri_kopdar);

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.ambilFoto);
        gridView = (GridView) findViewById(R.id.gridView);

        images = new ArrayList<>();
        titles = new ArrayList<>();

        getData();

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                Intent i = new Intent(GaleriKopdar.this, PilihGambarActivity.class);
                startActivity(i);
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(GaleriKopdar.this, FotoDetail.class);
//                intent.putExtra(TAG_ID, images.get(position)+ titles.get(position));
//                startActivity(intent);
            }
        });

        //parameter refresh

    }

    private void getData() {
        //Showing a progress dialog while our app fetches the data from url
        final ProgressDialog loading = ProgressDialog.show(this, "Tungguin Om ya Sabar...", "Ambil Data...", false, false);

        //Creating a json array request to get the json from our api
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(DATA_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Dismissing the progressdialog on response
                        loading.dismiss();

                        //Displaying our grid
                        showGrid(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Adding our request to the queue
        requestQueue.add(jsonArrayRequest);
    }
    private void showGrid(JSONArray jsonArray){
        //Looping through all the elements of json array
        for(int i = 0; i<jsonArray.length(); i++){
            //Creating a json object of the current index
            JSONObject obj = null;
            try {
                //getting json object from current index
                obj = jsonArray.getJSONObject(i);

                //getting image url and title from json object
                obj.getInt(TAG_ID);
                images.add(obj.getString(TAG_IMAGE_URL));
                titles.add(obj.getString(TAG_NAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Creating GridViewAdapter Object
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this,images,titles);
        //Adding adapter to gridview
        gridView.setAdapter(gridViewAdapter);
    }

}
