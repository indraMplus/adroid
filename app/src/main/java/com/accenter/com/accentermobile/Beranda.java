package com.accenter.com.accentermobile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.accenter.com.accentermobile.adapter.ArtikelAdapter;
import com.accenter.com.accentermobile.adapter.EventAdapter;
import com.accenter.com.accentermobile.admin.BuatArtikel;
import com.accenter.com.accentermobile.admin.LoginActivity;
import com.accenter.com.accentermobile.admin.UpdateEvent;
import com.accenter.com.accentermobile.admin.UpdateKopdar;
import com.accenter.com.accentermobile.admin.UpdateMember;
import com.accenter.com.accentermobile.data.ArtikelData;
import com.accenter.com.accentermobile.gallery.GaleriFotoMain;
import com.accenter.com.accentermobile.member.ListAllMemberActivity;
import com.accenter.com.accentermobile.tool.AppController;
import com.accenter.com.accentermobile.tool.Server;
import com.accenter.com.accentermobile.tool.SessionManager;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Beranda extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    ListView list;
    //parameter slider
    SliderLayout sliderLayout;
    HashMap<String,String> Hash_file_maps ;
    //end parameter slide
    ConnectivityManager conMgr;
    SwipeRefreshLayout swipe;
    Button btn_logout;
    TextView txt_id, txt_username;
    String username;
    SharedPreferences sharedpreferences;
    public static final String TAG_USERNAME = "email";
    //a list to store all the products
    List<ArtikelData> artikelList = new ArrayList<>();
    private static final String TAG = Beranda.class.getSimpleName();
    NavigationView navigationView;
    private int offSet = 0;
    //private AdView mAdView;
    int no;

    //parameter chat
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    ArtikelAdapter adapter;
    EventAdapter eventAdapter;
    //the recyclerview
    RecyclerView recyclerView;
    private SessionManager session;
    public static final String TAG_NO = "no";
    public static final String TAG_ID = "id";
    public static final String TAG_JUDUL = "judul";
    public static final String TAG_TGL = "tgl";
    public static final String TAG_ISI = "isi";
    public static final String TAG_GAMBAR = "gambar";
    Handler handler;
    Runnable runnable;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*//inisial iklan
        MobileAds.initialize(this, "ca-app-pub-1163823443997525~4444327801");
        mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //update checker
        //ForceUpdateChecker.with(this).onUpdateNeeded(this).check();

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "Tidak ada Jaringan Internet",
                        Toast.LENGTH_LONG).show();
            }
        }
//        Hash_file_maps = new HashMap<>();
//
//        sliderLayout = (SliderLayout)findViewById(R.id.slider);
//
//        Hash_file_maps.put("Ultah Accent-er ke 9", "https://accent-er.com/news/slider/sliderultah.jpg");
//        Hash_file_maps.put("Anniversary Regional PADANG", "https://accent-er.com/news/slider/sliderpadang.jpg");
//        Hash_file_maps.put("Anniversary Regional PEKANBARU", "https://accent-er.com/news/slider/sliderpekanbaru.jpg");
//        Hash_file_maps.put("Anniversary Regional JATIM", "https://accent-er.com/news/slider/sliderjatim.jpg");
//        Hash_file_maps.put("Deklarasi Chapter CIPASERA", "https://accent-er.com/news/slider/slidercipasera.jpg");
//        Hash_file_maps.put("Anniversary Regional JABAR", "https://accent-er.com/news/slider/sliderjabar.jpg");
//
//        for(String name : Hash_file_maps.keySet()){
//
//            TextSliderView textSliderView = new TextSliderView(Beranda.this);
//            textSliderView
//                    .description(name)
//                    .image(Hash_file_maps.get(name))
//                    .setScaleType(BaseSliderView.ScaleType.Fit)
//                    .setOnSliderClickListener(this);
//            textSliderView.bundle(new Bundle());
//            textSliderView.getBundle()
//                    .putString("extra",name);
//            sliderLayout.addSlider(textSliderView);
//        }
//        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
//        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//        sliderLayout.setCustomAnimation(new DescriptionAnimation());
//        sliderLayout.setDuration(6000);
//        sliderLayout.addOnPageChangeListener(this);


        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        username = getIntent().getStringExtra(TAG_USERNAME);
        session = new SessionManager(getApplicationContext());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.setDrawerListener(toggle);
        }
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
        if(sharedpreferences.contains(TAG_USERNAME)){
            if (navigationView != null) {
                navigationView.getMenu().clear();
            }
            assert navigationView != null;
            navigationView.inflateMenu(R.menu.activity_beranda_login);
        } else {
            assert navigationView != null;
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_beranda_drawer);
        }

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        list = (ListView) findViewById(R.id.list_artikel);

        //artikelList.clear();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Beranda.this, DetailArtikel.class);
                intent.putExtra(TAG_ID, artikelList.get(position).getId());
                startActivity(intent);
                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak ada Jaringan Internet",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
        adapter = new ArtikelAdapter(Beranda.this, artikelList);
        list.setAdapter(adapter);

        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           adapter.notifyDataSetChanged();
                           callNews(0);
                       }
                   }
        );

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
                            callNews(offSet);
                        }
                    };

                    handler.postDelayed(runnable, 3000);
                }
            }

        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
        adapter.notifyDataSetChanged();
        //sliderLayout.startAutoCycle();
    }


    @Override
    public void onRefresh() {
        swipe.setRefreshing(true);
        artikelList.clear();
        adapter.notifyDataSetChanged();
        callNews(0);
        swipe.setRefreshing(false);
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.beranda, menu);
        return true;
    }*/
    //variable untuk update firebase
//    @Override
//    public void onUpdateNeeded(final String updateUrl) {
//        AlertDialog dialog = new AlertDialog.Builder(this)
//                .setTitle("Versi Baru Tersedia")
//                .setMessage("Silahkan Download Versi terbaru Adroid")
//                .setPositiveButton("Update",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                redirectStore(updateUrl);
//                            }
//                        }).setNegativeButton("Tidak, Mungkin Nanti",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                finish();
//                            }
//                        }).create();
//        dialog.show();
//    }
//
//    private void redirectStore(String updateUrl) {
//        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl));
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//    }
//    @Override
//    public void onResume(){
//        super.onResume();
//        adapter.notifyDataSetChanged();
//        sliderLayout.startAutoCycle();
//    }


   /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Keluar) {
            signOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void signOut() {
        auth.signOut();
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Keluar Admin Panel Aplikasi");
            builder.setMessage("Anda akan keluar dari mode Admin Panel Aplikasi Adroid ??");
            builder.setCancelable(false);
            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    logout();//keluar dari panel admin
                    finish();
                }
            });

            builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Masih Betah di Admin Panel om??", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();

        } else if (id == R.id.nav_event) {
            Intent event = new Intent(Beranda.this, EventActivity.class);
            //list.setAdapter(eventAdapter);
            startActivity(event);

        } else if (id == R.id.nav_kopdar) {
            Intent kopdar = new Intent(Beranda.this, KopdarActivity.class);
            startActivity(kopdar);
        } else if (id == R.id.nav_galeri_kopdar) {
            Intent kopdar = new Intent(Beranda.this, GaleriFotoMain.class);
            startActivity(kopdar);

        } else if (id == R.id.nav_member) {
            Intent member = new Intent(Beranda.this, ListAllMemberActivity.class);
            startActivity(member);
        } else if (id == R.id.nav_struktur) {
            Intent struktur = new Intent(Beranda.this, StrukturOrganisasi.class);
            startActivity(struktur);
        } else if (id == R.id.nav_daftar) {
            Intent member = new Intent(Beranda.this, DaftarMember.class);
            startActivity(member);
        } else if (id == R.id.nav_info) {
            Intent infor = new Intent(Beranda.this, AboutPage.class);
            startActivity(infor);
        } else if (id == R.id.nav_login) {
            Intent login = new Intent(Beranda.this, LoginActivity.class);
            startActivity(login);
        } else if (id == R.id.nav_artikel) {
            Intent login = new Intent(Beranda.this, BuatArtikel.class);
            startActivity(login);
        } else if (id == R.id.nav_update_member) {
            Intent login = new Intent(Beranda.this, UpdateMember.class);
            startActivity(login);
        } else if (id == R.id.nav_update_kopdar) {
            Intent login = new Intent(Beranda.this, UpdateKopdar.class);
            startActivity(login);
        } else if (id == R.id.nav_update_event) {
            Intent login = new Intent(Beranda.this, UpdateEvent.class);
            startActivity(login);
        /*} else if (id == R.id.nav_update_struktur) {
            Intent us = new Intent(Beranda.this, UpdatePengurus.class);
            startActivity(us);*/
        } else if (id == R.id.nav_bagikan) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Accent-er Mobile");
            String sAux = "DOWNLOAD Aplikasi Accent-er versi Android Terbaru \n\n" +
                    "Informasi Kegiatan Accent-er kini ada dalam genggaman anda\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=com.accenter.com.adroid \n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "Bagikan"));

        } else if (id == R.id.nav_share) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Accent-er Mobile");
            String sAux = "DOWNLOAD Aplikasi Accent-er versi Android Terbaru \n\n" +
                    "Informasi Kegiatan Accent-er kini ada dalam genggaman anda\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=com.accenter.com.adroid \n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "Bagikan"));
        } else if (id == R.id.nav_send) {
            Intent saran = new Intent(Beranda.this, SaranActivity.class);
            startActivity(saran);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    private void logout(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(LoginActivity.session_status, false);
        editor.putString(TAG_USERNAME, null);
        editor.apply();


        Intent intent = new Intent(this, Beranda.class);
        finish();
        startActivity(intent);
    }


    private void callNews(int page) {

        swipe.setRefreshing(true);

        // Creating volley request obj
        String url_list = Server.URL + "artikel.php?offset=";
        JsonArrayRequest arrReq = new JsonArrayRequest(url_list + page,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        if (response.length() > 0) {
                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    ArtikelData news = new ArtikelData();

                                    no = obj.getInt(TAG_NO);

                                    news.setId(obj.getString(TAG_ID));
                                    news.setJudulArtikel(obj.getString(TAG_JUDUL));

                                    if (obj.getString(TAG_GAMBAR) != "") {
                                        news.setImgArtikel(obj.getString(TAG_GAMBAR));
                                    }

                                    news.setTglArtikel(obj.getString(TAG_TGL));
                                    news.setIsiArtikel(obj.getString(String.valueOf(Html.fromHtml(TAG_ISI))));

                                    // adding news to news array
                                    artikelList.add(news);

                                    if (no > offSet)
                                        offSet = no;

                                    Log.d(TAG, "offSet " + offSet);

                                } catch (JSONException e) {
                                    Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                                }

                                // notifying list adapter about data changes
                                // so that it renders the list view with updated data
                                adapter.notifyDataSetChanged();
                            }
                        }
                        swipe.setRefreshing(false);
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                swipe.setRefreshing(false);
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(arrReq);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Beranda Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.accenter.com.accentermobile/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
//    }

//    @Override
//    public void onStop() {
//        sliderLayout.stopAutoCycle();//parameter slider
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Beranda Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.accenter.com.accentermobile/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
//    }
    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
    // starting the service to register with GCM

}
