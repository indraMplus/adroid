package com.accenter.com.accentermobile.member;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.widget.ListView;

import com.accenter.com.accentermobile.R;
import com.accenter.com.accentermobile.memberadapter.JakartaAdapter;
import com.accenter.com.accentermobile.memberdata.JakartaData;
import com.accenter.com.accentermobile.tool.AppController;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DkiActivity extends Activity {

    private static final String TAG = DkiActivity.class.getSimpleName();
    private static final String url = "https://accent-er.com/news/data_member/member_dki.php";
    private List<JakartaData> list = new ArrayList<>();
    private ListView listView;
    private JakartaAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dki);

        listView = (ListView) findViewById(R.id.list);
        adapter = new JakartaAdapter(this, list);
        listView.setAdapter(adapter);
        JsonArrayRequest arrReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                JakartaData dataSet = new JakartaData();
                                dataSet.setId(obj.getString("id_member"));
                                dataSet.setNamaMember(obj.getString("nama_member"));
                                dataSet.setNopungMember(obj.getString("nopung_member"));
                                dataSet.setRegionMember(obj.getString("chapter_member"));
                                dataSet.setStatusMember(obj.getString("status_member"));
                                list.add(dataSet);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder add = new AlertDialog.Builder(DkiActivity.this);
                add.setMessage(error.getMessage()).setCancelable(true);
                AlertDialog alert = add.create();
                alert.setTitle("Error!!!");
                alert.show();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(arrReq);
    }
}
