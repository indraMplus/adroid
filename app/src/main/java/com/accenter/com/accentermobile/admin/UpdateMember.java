package com.accenter.com.accentermobile.admin;

//import android.app.ProgressDialog;
import android.content.DialogInterface;
        import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
        import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

        import com.accenter.com.accentermobile.R;
import com.accenter.com.accentermobile.adapter.MemberUpdateAdapter;
import com.accenter.com.accentermobile.data.MemberUpdateData;
import com.accenter.com.accentermobile.db.DBHelper;
import com.accenter.com.accentermobile.tool.AppController;
import com.accenter.com.accentermobile.tool.Server;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UpdateMember extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    DBHelper myDb;
    int success;

    ListView list;
    SwipeRefreshLayout swipe;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    //ProgressDialog pDialog;
    MemberUpdateAdapter memberUpdateAdapter;
    List<MemberUpdateData> memberUpdateList = new ArrayList<>();
    public static final String TAG = UpdateMember.class.getSimpleName();
    public static final String TAG_ID       = "id_member";
    public static final String TAG_NAMA     = "nama_member";
    public static final String TAG_NOPUNG   = "nopung_member";
    public static final String TAG_CHAPTER  = "chapter_member";
    public static final String TAG_STATUS   = "status_member";
    public static final String TAG_KETERANGAN = "keterangan_member";
    public static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    Handler handler;
    Runnable runnable;
    EditText txt_id, txt_nama,txt_nopung,txt_status,txt_region,txt_ket;
    Spinner Sstatus,region;
    String id, nama, chapter,nopung, status,keterangan;
    String tag_json_obj = "json_obj_req";
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_member);
        myDb = new DBHelper(this);

        //Initializing the ArrayList
        // untuk mengisi data dari JSON ke dalam adapter
        //callVolley();
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        // listview ditekan lama akan menampilkan dua pilihan edit atau delete data
        list = (ListView)findViewById(R.id.list_update);

        swipe.setOnRefreshListener(this);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                final String idx = memberUpdateList.get(position).getId();

                final CharSequence[] dialogitem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(UpdateMember.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                edit(idx);
                                break;
                            case 1:

                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
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
                            callVolley();
                        }
                    };

                    handler.postDelayed(runnable, 3000);
                }
            }

        });
        memberUpdateAdapter = new MemberUpdateAdapter(this, memberUpdateList);
        list.setAdapter(memberUpdateAdapter);
        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           callVolley();
                           memberUpdateList.clear();
                           memberUpdateAdapter.notifyDataSetChanged();
                       }
                   }
        );
    }

    private void simpankeSqlite(){
        String id = txt_id.getText().toString();
        String nama = txt_nama.getText().toString();
        String region = txt_region.getText().toString();
        String nopung = txt_nopung.getText().toString();
        String status = txt_status.getText().toString();
        boolean isInserted = myDb.updateData(id, nama, region, nopung, status);
        if (isInserted)
            Toast.makeText(UpdateMember.this, "Sukses", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(UpdateMember.this, "Gagal", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onRefresh() {
        memberUpdateList.clear();
        memberUpdateAdapter.notifyDataSetChanged();
        callVolley();
    }
    @Override
    public void onResume(){
        super.onResume();
        memberUpdateAdapter.notifyDataSetChanged();
    }
    // untuk mengosongi edittext pada form
    private void kosong(){
        txt_id.setText(null);
        txt_nama.setText(null);
        txt_nopung.setText(null);
        txt_region.setText(null);
        txt_status.setText(null);
    }

    // untuk menampilkan dialog from biodata

    private void DialogForm(String idx, String namax, String nopungx,String chapterx ,String statusx, String button) {
        dialog = new AlertDialog.Builder(UpdateMember.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_update, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.drawable.ic_build_black_36dp);
        dialog.setTitle("Form Update Member");

        txt_id          = (EditText) dialogView.findViewById(R.id.txt_id);
        txt_nama        = (EditText) dialogView.findViewById(R.id.txt_nama);
        txt_nopung      = (EditText) dialogView.findViewById(R.id.txt_nopung);
        txt_region      = (EditText) dialogView.findViewById(R.id.txt_chapter);
        txt_status      = (EditText) dialogView.findViewById(R.id.txt_status);

        if (!idx.isEmpty()){
            txt_id.setText(idx);
            txt_nama.setText(namax);
            txt_nopung.setText(nopungx);
            txt_region.setText(chapterx);
            txt_status.setText(statusx);

        } else {
            kosong();
        }

        dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                id = txt_id.getText().toString();
                nama = txt_nama.getText().toString();
                nopung = txt_nopung.getText().toString();
                chapter = txt_region.getText().toString();
                status = txt_status.getText().toString();

                simpan_update();
                //simpankeSqlite();
                dialog.dismiss();
            }
        });

        dialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                swipe.setRefreshing(true);
                memberUpdateList.clear();
                memberUpdateAdapter.notifyDataSetChanged();
                callVolley();
                swipe.setRefreshing(false);
            }
        });

        dialog.show();

    }

    // untuk menampilkan semua data pada listview
    private void callVolley(){
        swipe.setRefreshing(true);
        //memberUpdateList.clear();
        memberUpdateAdapter.notifyDataSetChanged();
        // membuat request JSON
        JsonArrayRequest jArr = new JsonArrayRequest(Server.url_select, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());

                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        MemberUpdateData item = new MemberUpdateData();

                        item.setId(obj.getString(TAG_ID));
                        item.setNama(obj.getString(TAG_NAMA));
                        item.setNopung(obj.getString(TAG_NOPUNG));
                        item.setChapter(obj.getString(TAG_CHAPTER));
                        item.setStatus(obj.getString(TAG_STATUS));
                        item.setKeterangan(obj.getString(TAG_KETERANGAN));

                        // menambah item ke array
                        memberUpdateList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // notifikasi adanya perubahan data pada adapter
                memberUpdateAdapter.notifyDataSetChanged();

                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                swipe.setRefreshing(false);
            }
        });

        // menambah request ke request queue
        AppController.getInstance().addToRequestQueue(jArr);
    }
    // fungsi untuk menyimpan atau update
    private void simpan_update() {
        // jika id kosong maka simpan, jika id ada nilainya maka update
        /*if (id.isEmpty()){*/
            url = Server.url_update;
        //}

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response);

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("Add/update", jObj.toString());

                        callVolley();
                        kosong();

                        Toast.makeText(UpdateMember.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        memberUpdateAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(UpdateMember.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(UpdateMember.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<>();
                // jika id kosong maka simpan, jika id ada nilainya maka update
                /*if (id.isEmpty()){
                    params.put("nama_member", nama);
                } else {*/
                    params.put(TAG_ID, id);
                    params.put(TAG_NAMA, nama);
                    params.put(TAG_NOPUNG, nopung);
                    params.put(TAG_CHAPTER, chapter);
                    params.put(TAG_STATUS, status);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    // fungsi untuk get edit data
    private void edit(final String idx){
        StringRequest strReq = new StringRequest(Request.Method.POST, Server.url_edit, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response);

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("get edit data", jObj.toString());
                        String idx      = jObj.getString(TAG_ID);
                        String namax    = jObj.getString(TAG_NAMA);
                        String nopungx  = jObj.getString(TAG_NOPUNG);
                        String chapterx    = jObj.getString(TAG_CHAPTER);
                        String statusx  = jObj.getString(TAG_STATUS);

                        DialogForm(idx, namax, nopungx, chapterx, statusx, "UPDATE");

                        memberUpdateList.clear();
                        memberUpdateAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(UpdateMember.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(UpdateMember.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<>();
                params.put(TAG_ID, idx);
                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    /*private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }*/

}
