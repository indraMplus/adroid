package com.accenter.com.accentermobile;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.app.NotificationCompat;
import android.widget.ArrayAdapter;
import android.widget.RemoteViews;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.accenter.com.accentermobile.db.DBHelper;
import com.accenter.com.accentermobile.tool.DaftarAPI;
import com.accenter.com.accentermobile.tool.NotifView;
import com.accenter.com.accentermobile.tool.Server;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class DaftarMember extends AppCompatActivity {

    public static final String NAMA_DATABASE = "adroid";
    SQLiteDatabase mDatabase;
    DBHelper myDb;

    private PendingIntent pendingIntent;
    private NotificationManager notificationManager;
    private android.support.v4.app.NotificationCompat.Builder notificationBuilder;
    ProgressDialog pDialog;
    Button btn_daftar_baru;
    Spinner regional;
    private EditText tNama;
    private EditText tAlamat;
    private EditText tEmail;
    private EditText tTelp;
    private EditText tNorang;
    private EditText tThnMobil;
    private EditText tSim;
    private EditText tKet;
    Context context;
    Intent intent;

    //int success;
    ConnectivityManager conMgr;

//    private static final String url = Server.URL_DAFTAR_MEMBER + "daftar_baru.php";
//
//    private static final String TAG = DaftarMember.class.getSimpleName();
//
//
//    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_member);
        context = DaftarMember.this;
        myDb = new DBHelper(this);

        btn_daftar_baru = (Button) findViewById(R.id.btnDaftarMember);
        tNama = (EditText) findViewById(R.id.etNamaMemberDaftar);
        tAlamat = (EditText) findViewById(R.id.etAlamatMember);
        tEmail = (EditText) findViewById(R.id.etEmailMember);
        tTelp = (EditText) findViewById(R.id.etNotelpDaftar);
        tNorang = (EditText) findViewById(R.id.etRangkaMobilDaftar);
        tThnMobil = (EditText) findViewById(R.id.etTahunMobilDaftar);
        tSim = (EditText) findViewById(R.id.etNoSimDaftar);
        tKet    = (EditText)findViewById(R.id.etKeterangan);
        regional = (Spinner) findViewById(R.id.etRegionDaftarMember);
        ArrayAdapter<String> regionList = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.region));
        regional.setAdapter(regionList);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() == null
                    || !conMgr.getActiveNetworkInfo().isAvailable()
                    || !conMgr.getActiveNetworkInfo().isConnected()) {
                        Toast.makeText(getApplicationContext(), "Tidak ada Jaringan Internet",
                                Toast.LENGTH_LONG).show();
                    }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("INFORMASI");
        builder.setMessage("Dengan mengakses fitur pendaftaran member (HASCI) Hyundai Accent Series Club Indonesia (Accent-er) Bahwa anda akan mengikuti peraturan yang berlaku!");
        builder.setCancelable(false);
        builder.setPositiveButton("Setuju", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //kosong
            }
        });

        builder.setNegativeButton("Batal Daftar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Kami tunggu anda di Pendaftaran yang selanjutnya!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.show();
        btn_daftar_baru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasi();
                //CustomNotification();
                AddDataMemberSqlite();
            }
        });
    }
    //dept does not need validation as it is a spinner and it cannot be empty
    private boolean inputsAreCorrect(String nama, String email) {
        if (nama.isEmpty()) {
            tNama.setError("Nama Belum di isi");
            tNama.requestFocus();
            return false;
        }

        if (email.isEmpty()) {
            tEmail.setError("Email blm di isi");
            tEmail.requestFocus();
            return false;
        }
        return true;
    }
    public void AddDataMemberSqlite() {
        //getting the current time for joining date
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String joiningDate = sdf.format(cal.getTime());
        String nama     = tNama.getText().toString();
        String alamat   = tAlamat.getText().toString();
        String email    = tEmail.getText().toString();
        String region   = regional.getSelectedItem().toString();
        String telp     = tTelp.getText().toString();
        String norang   = tNorang.getText().toString();
        String tahun    = tThnMobil.getText().toString();
        String nosim   = tSim.getText().toString();
        String ket      = tKet.getText().toString();
        String npung    = toString();


        boolean isInserted = myDb.insertData(nama, alamat, email, region, telp, norang, tahun, nosim, npung, ket, joiningDate);
        if (isInserted)
            Toast.makeText(DaftarMember.this, "Sukses", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(DaftarMember.this, "Gagal", Toast.LENGTH_LONG).show();

    }

    private void daftarbaru(){

        //Here we will handle the http request to insert user to mysql db
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(Server.URL_DAFTAR_MEMBER) //Setting the Root URL
                .build(); //Finally building the adapter
        pDialog = new ProgressDialog(DaftarMember.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Silahkan Tunggu...");
        showDialog();
        //Creating object for our interface
        DaftarAPI api = adapter.create(DaftarAPI.class);
        //Defining the method insertuser of our interface
        api.insertUser(
                //Passing the values by getting it from editTexts
                tNama.getText().toString(),
                tAlamat.getText().toString(),
                tEmail.getText().toString(),
                regional.getSelectedItem().toString(),
                tTelp.getText().toString(),
                tNorang.getText().toString(),
                tThnMobil.getText().toString(),
                tSim.getText().toString(),
                tKet.getText().toString(),

                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader;
                        //An string to store output from the server
                        String output = "";
                        //simpan ke sqlite
                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
//                            tNama.setText("");
//                            tAlamat.setText("");
//                            tEmail.setText("");
//                            tTelp.setText("");
//                            tNorang.setText("");
//                            tThnMobil.setText("");
//                            tSim.setText("");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(DaftarMember.this, output, Toast.LENGTH_LONG).show();
                        gotoCourseActivity();
                        hideDialog();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(DaftarMember.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    private void CustomNotification() {
        // Using RemoteViews to bind custom layouts into Notification
        RemoteViews remoteViews = new RemoteViews(getPackageName(),
                R.layout.notif);

        // Set Notification Title
        String strtitle = getString(R.string.judul);
        // Set Notification Text
        String strtext = getString(R.string.isi);

        // Open NotificationView Class on Notification Click
        Intent intent = new Intent(this, NotifView.class);
        // Send data to NotificationView Class
        intent.putExtra("title", strtitle);
        intent.putExtra("text", strtext);
        // Open NotificationView.java Activity
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                // Set Icon
                .setSmallIcon(R.drawable.kim_yongii256)
                        // Set Ticker Message
                .setTicker(getString(R.string.customnotificationticker))
                        // Dismiss Notification
                .setAutoCancel(true)
                        // Set PendingIntent into Notification
                .setContentIntent(pIntent)
                        // Set RemoteViews into Notification
                .setContent(remoteViews);
        Notification notification = builder.build();
        //notification.sound = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.salam);
        notification.defaults = Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE;

        // Locate and set the Images into customnotificationtext.xml ImageViews
        remoteViews.setImageViewResource(R.id.imagenotileft,R.drawable.childish_thumb_up);
        remoteViews.setImageViewResource(R.id.imagenotiright,R.drawable.kim_yongii256);

        // Locate and set the Text into customnotificationtext.xml TextViews
        remoteViews.setTextViewText(R.id.title,getString(R.string.judul));
        remoteViews.setTextViewText(R.id.text,getString(R.string.isi));

        // Create Notification Manager
        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Build Notification with Notification Manager
        notificationmanager.notify(0, builder.build());

    }
    private boolean validasi() {
        if (tNama.getText().toString().trim().equals("")) {
            tNama.setError("Nama Belum di isi");
            return false;
        }
        if (tAlamat.getText().toString().trim().length() == 0) {
            tAlamat.setError("Alamat Belum di isi");
            return false;
        }
        if (tEmail.getText().toString().trim().length() == 0) {
            tEmail.setError("Email Belum di isi");
            return false;
        }
        if (tTelp.getText().toString().trim().length() == 0) {
            tTelp.setError("No Telepon Belum di isi");
            return false;
        }
        if (regional.getSelectedItem().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Regional / Chapter belum dipilih", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tNorang.getText().toString().trim().length() == 0) {
            tNorang.setError("No Rangka Belum di isi");
            return false;
        }
        if (tThnMobil.getText().toString().trim().length() == 0) {
            tThnMobil.setError("Tahun Mobil Belum di isi");
            return false;
        }
        if (tSim.getText().toString().trim().length() == 0) {
            tSim.setError("No SIM Belum di isi");
            return false;
        }
        daftarbaru();
        return true;
    }
    private void cekKoneksi(){
        //event buat cek koneksi
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {
        } else {
            Toast.makeText(getApplicationContext(), "Tidak ada Jaringan Internet", Toast.LENGTH_SHORT).show();
        }
    }
    private void batal(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Perhatian");
        builder.setMessage("Anda akan keluar dari pendaftaran dan belum menyelesaikan nya!!");
        builder.setCancelable(false);
        builder.setPositiveButton("Tetap Keluar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intent = new Intent(DaftarMember.this, Beranda.class);
                finish();
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Batal Keluar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*Toast.makeText(getApplicationContext(), "Kami tunggu anda di Pendaftaran yang selanjutnya!!", Toast.LENGTH_SHORT).show();
                finish();*/
            }
        });
        builder.show();
    }
    private void gotoCourseActivity() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("TERIMA KASIH");
        builder.setMessage("Anda telah mendaftar di H.A.S.C.I (Accent-er)!!");
        builder.setCancelable(false);
        builder.setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, Beranda.class);
                startActivity(intent);
                finish();
            }
        });
//
//        builder.setNegativeButton("Batal Keluar", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getApplicationContext(), "Kami tunggu anda di Pendaftaran yang selanjutnya!!", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        });
        builder.show();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        batal();
    }
}
