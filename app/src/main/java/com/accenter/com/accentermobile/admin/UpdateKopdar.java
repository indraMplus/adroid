package com.accenter.com.accentermobile.admin;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.accenter.com.accentermobile.Beranda;
import com.accenter.com.accentermobile.KopdarActivity;
import com.accenter.com.accentermobile.R;
import com.accenter.com.accentermobile.tool.AppController;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class UpdateKopdar extends AppCompatActivity {

    //segment notifikasi

    EditText tglKopdar,postBy;
    Button post;
    private SimpleDateFormat dateFormatter;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private String KEY_JUDUL = "judul";
    private String KEY_TGL = "tgl";
    private String KEY_ISI = "isi";
    private String KEY_REGION = "region";
    private String KEY_POSTER = "post";
    String UPLOAD_URL = "https://accent-er.com/news/buat_kopdar.php";
    private static final String TAG = UpdateKopdar.class.getSimpleName();
    //Deklararasi EditText
    EditText jdlKopdar,isiKopdar;
    Spinner chptKopdar;

    String tag_json_obj = "json_obj_req";
    int success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kopdar);

        //deklarasi tgl
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        //deklarasi edit text
        jdlKopdar = (EditText) findViewById(R.id.etJudulKopdar);
        chptKopdar = (Spinner) findViewById(R.id.etRegionKopdar);
        isiKopdar = (EditText) findViewById(R.id.etIsiKopdar);
        tglKopdar = (EditText) findViewById(R.id.etTglPostKopdar);
        postBy      = (EditText)findViewById(R.id.etPost);
        post        = (Button)findViewById(R.id.btnPostKopdar);
        //delklarasi array
        ArrayAdapter<String> regions = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.member_array));
        chptKopdar.setAdapter(regions);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postingKopdar();
                //sendNotification(v);
            }
        });

        tglKopdar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilTgl();
            }
        });
    }
    private void tampilTgl() {
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog tgls = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tglKopdar.setText("" + dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        tgls.show();
    }
    private void postingKopdar(){
//menampilkan progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Menyimpan...", "Silahkan Tunggu...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "Response: " + response);

                        try {
                            JSONObject jObj = new JSONObject(response);
                            success = jObj.getInt(TAG_SUCCESS);

                            if (success == 1) {
                                Log.e("v Add", jObj.toString());

                                Toast.makeText(UpdateKopdar.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                                verifikasiKopdar();
                                kosong();

                            } else {
                                Toast.makeText(UpdateKopdar.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //menghilangkan progress dialog
                        loading.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //menghilangkan progress dialog
                        loading.dismiss();

                        //menampilkan toast
                        Toast.makeText(UpdateKopdar.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e(TAG, error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                //membuat parameters
                Map<String, String> params = new HashMap<>();

                //menambah parameter yang di kirim ke web servis
                params.put(KEY_JUDUL, jdlKopdar.getText().toString().trim());
                params.put(KEY_TGL, tglKopdar.getText().toString().trim());
                params.put(KEY_ISI, isiKopdar.getText().toString().trim());
                params.put(KEY_REGION, chptKopdar.getSelectedItem().toString().trim());
                params.put(KEY_POSTER, postBy.getText().toString().trim());

                //kembali ke parameters
                Log.e(TAG, "" + params);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }

    private void kosong() {
        jdlKopdar.setText("");
        isiKopdar.setText("");
        tglKopdar.setText("");
        postBy.setText("");
    }
    private void verifikasiKopdar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("INFORMASI");
        builder.setMessage("Apakah anda akan membuat Posting Kopdar Lagi??!");
        builder.setCancelable(false);
        builder.setPositiveButton("Buat Lagi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //kosong
            }
        });

        builder.setNegativeButton("Tidak,Terima Kasih", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Kami tunggu anda di Postingan Kopdar selanjutnya!!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateKopdar.this,Beranda.class);
                startActivity(i);
                finish();
            }
        });
        builder.show();
    }
//    public void sendNotification(View view) {
//
//        NotificationCompat.Builder mBuilder =
//                new NotificationCompat.Builder(this);
//
//        //Create the intent that’ll fire when the user taps the notification//
//
//        Intent intent = new Intent(this, KopdarActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//
//        mBuilder.setContentIntent(pendingIntent);
//
//        mBuilder.setSmallIcon(R.drawable.ic_notifications_active_white_18dp);
//        mBuilder.setContentTitle("Pengumuman");
//        mBuilder.setContentText("Pemberitahuan Kopdar ! Silahkan Cek");
//
//        NotificationManager mNotificationManager =
//
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        mNotificationManager.notify(1, mBuilder.build());
//    }
}
