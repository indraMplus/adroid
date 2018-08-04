package com.accenter.com.accentermobile.admin;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.accenter.com.accentermobile.Beranda;
import com.accenter.com.accentermobile.R;
import com.accenter.com.accentermobile.tool.AppController;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class UpdateEvent extends AppCompatActivity {


    ImageView imgEvent;
    EditText jdlEvent,tglevent,isiEvent,postEvent;
    Button btnPostEvent,btnPilihGbrEvent;
    Bitmap bitmap, decoded;
    int success;
    private SimpleDateFormat dateFormatter;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60; // range 1 - 100
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private String KEY_IMAGE = "gambar";
    private String KEY_JUDUL = "judul";
    private String KEY_ISI = "isi";
    private String KEY_TGL = "tgl";
    private String KEY_POSTER ="post";
    String UPLOAD_URL = "https://accent-er.com/news/buat_event.php";
    private static final String TAG = UpdateEvent.class.getSimpleName();

    String tag_json_obj = "json_obj_req";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_event);

        //deklarasi tgl
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        jdlEvent = (EditText) findViewById(R.id.etJudulEvent);
        isiEvent = (EditText) findViewById(R.id.etEventIsi);
        imgEvent = (ImageView)findViewById(R.id.imageViewEvent);
        postEvent = (EditText) findViewById(R.id.etPostEvent);
        tglevent = (EditText) findViewById(R.id.etTglEvent);
        btnPilihGbrEvent = (Button) findViewById(R.id.btnPilihGbrEvent);
        btnPostEvent = (Button) findViewById(R.id.buttonPostEvent);

        btnPilihGbrEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihGambar();
            }
        });
        btnPostEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postingEvent();
            }
        });

        tglevent.setOnClickListener(new View.OnClickListener() {
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
                tglevent.setText("" + dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        tgls.show();
    }
    private void postingEvent() {
        //menampilkan progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Silahkan Tunggu...", false, false);
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

                                Toast.makeText(UpdateEvent.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                                verifikasiKopdar();
                                kosong();

                            } else {
                                Toast.makeText(UpdateEvent.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                        Toast.makeText(UpdateEvent.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e(TAG, error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                //membuat parameters
                Map<String, String> params = new HashMap<>();

                //menambah parameter yang di kirim ke web servis
                params.put(KEY_IMAGE, getStringImage(decoded));
                params.put(KEY_JUDUL, jdlEvent.getText().toString().trim());
                params.put(KEY_ISI, isiEvent.getText().toString().trim());
                params.put(KEY_TGL, tglevent.getText().toString().trim());
                params.put(KEY_POSTER, postEvent.getText().toString().trim());

                //kembali ke parameters
                Log.e(TAG, "" + params);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }

    private void kosong() {
        imgEvent.setImageResource(0);
        isiEvent.setText(null);
        jdlEvent.setText(null);
        tglevent.setText(null);
        postEvent.setText(null);
    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    private void pilihGambar() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih Gambar Artikel"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //mengambil fambar dari Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                setToImageView(getResizedBitmap(bitmap, 512));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imgEvent.setImageBitmap(decoded);
    }
    // fungsi resize image
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
    private void verifikasiKopdar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("INFORMASI");
        builder.setMessage("Apakah anda akan membuat Agenda Event Lagi??!");
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
                Toast.makeText(getApplicationContext(), "Kami tunggu anda di Postingan Event selanjutnya!!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateEvent.this,Beranda.class);
                startActivity(i);
                finish();
            }
        });
        builder.show();
    }
}
