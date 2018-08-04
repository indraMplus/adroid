package com.accenter.com.accentermobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.accenter.com.accentermobile.member.JakartaActivity;
import com.accenter.com.accentermobile.member.KaltimActivity;
import com.accenter.com.accentermobile.member.jatim.MadiunActivity;
import com.accenter.com.accentermobile.member.jatim.MaduraActivity;
import com.accenter.com.accentermobile.member.bali.BaliActivity;
import com.accenter.com.accentermobile.member.banten.BantenActivity;
import com.accenter.com.accentermobile.member.banten.CilegonActivity;
import com.accenter.com.accentermobile.member.banten.PandeglangActivity;
import com.accenter.com.accentermobile.member.banten.RangkasActivity;
import com.accenter.com.accentermobile.member.banten.SerangActivity;
import com.accenter.com.accentermobile.member.jabar.BandungActivity;
import com.accenter.com.accentermobile.member.jabar.BekaciActivity;
import com.accenter.com.accentermobile.member.jabar.BogorActivity;
import com.accenter.com.accentermobile.member.jabar.CibuburActivity;
import com.accenter.com.accentermobile.member.jabar.CirebonActivity;
import com.accenter.com.accentermobile.member.jabar.DepokActivity;
import com.accenter.com.accentermobile.member.jabar.GarutActivity;
import com.accenter.com.accentermobile.member.jabar.KarawangActivity;
import com.accenter.com.accentermobile.member.jabar.KuninganActivity;
import com.accenter.com.accentermobile.member.jabar.SubangActivity;
import com.accenter.com.accentermobile.member.jabar.TasikActivity;
import com.accenter.com.accentermobile.member.jateng.AmbarawaActivity;
import com.accenter.com.accentermobile.member.jateng.CilacapActivity;
import com.accenter.com.accentermobile.member.jateng.JogjaActivity;
import com.accenter.com.accentermobile.member.jateng.PemalangActivity;
import com.accenter.com.accentermobile.member.jateng.SalatigaActivity;
import com.accenter.com.accentermobile.member.jateng.SemarangActivity;
import com.accenter.com.accentermobile.member.jateng.SoloActivity;
import com.accenter.com.accentermobile.member.jateng.UngaranActivity;
import com.accenter.com.accentermobile.member.jatim.SidoarjoActivity;
import com.accenter.com.accentermobile.member.jatim.MalangActivity;
import com.accenter.com.accentermobile.member.jatim.SurabayaActivity;
import com.accenter.com.accentermobile.member.kepriau.PekanbaruActivity;
import com.accenter.com.accentermobile.member.sumbar.BukitTinggiActivity;
import com.accenter.com.accentermobile.member.sumbar.JambiActivity;
import com.accenter.com.accentermobile.member.sumbar.PadangActivity;
import com.accenter.com.accentermobile.member.sumbar.PariamanActivity;
import com.accenter.com.accentermobile.member.sumbar.PayakumbuhActivity;
import com.accenter.com.accentermobile.member.sumsel.PalembangActivity;
import com.accenter.com.accentermobile.member.sumut.MedanActivity;
import com.accenter.com.accentermobile.memberdata.JakartaData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemberRegionActivity extends AppCompatActivity {
    public static final String TAG_ID       = "id";
    private static final String TAG = MemberRegionActivity.class.getSimpleName();
    List<JakartaData>jData = new ArrayList<>();

    // Array of strings for ListView Title
    String[] listviewTitle = new String[]{
            //DKI & JABAR
            "DKI", "BEKASI", "BANDUNG", "BOGOR",
            "KARAWANG", "DEPOK", "CIBUBUR", "SUBANG",
            "TASIKMALAYA", "CIREBON", "KUNINGAN", "GARUT",
            //JATENG
            "YOGYAKARTA", "SEMARANG", "UNGARAN", "SALATIGA",
            "AMBARAWA", "SOLO", "PEMALANG", "CILACAP",
            //JATIM
            "SIDOARJO", "SURABAYA", "MALANG", "MADIUN","MADURA",
            //SUMBAR
            "PADANG", "PARIAMAN", "BUKIT TINGGI", "PAYAKUMBUH",
            "JAMBI",
            //SUMUT
            "MEDAN",
            //SUMSEL
            "PALEMBANG",
            //BANTEN
            "BANTEN", "CILEGON", "PANDEGLANG", "SERANG",
            "RANGKASBITUNG",
            //RIAU
            "PEKANBARU",
            //KALTIM
            "KALIMANTAN TIMUR",
            //BALI
            "BALI",
    };


    int[] listviewImage = new int[]{
            //DKI N JABAR
            R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.user,
            R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.user,
            R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.user,
            //JATENG
            R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.user,
            R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.user,
            //JATIM
            R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.user,
            R.drawable.user,
            //SUMBAR
            R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.user,
            R.drawable.user,
            //SUMUT
            R.drawable.user,
            //SUMSEL
            R.drawable.user,
            //BANTEN
            R.drawable.user, R.drawable.user, R.drawable.user, R.drawable.user,
            R.drawable.user,
            //RIAU
            R.drawable.user,
            //KALTIM
            R.drawable.user,
            //BALI
            R.drawable.user,

    };

    String[] listviewShortDescription = new String[]{
            "Member Accent-er Chapter DKI", "Member Accent-er Chapter BEKASI", "Member Accent-er Chapter BANDUNG", "Member Accent-er Chapter BOGOR",
            "Member Accent-er Chapter KARAWANG", "Member Accent-er Chapter DEPOK", "Member Accent-er Chapter CIBUBUR", "Member Accent-er Chapter SUBANG",
            "Member Accent-er Chapter TASIKMALAYA", "Member Accent-er Chapter CIREBON", "Member Accent-er Chapter KUNINGAN", "Member Accent-er Chapter GARUT",

            "Member Accent-er Chapter YOGYAKARTA", "Member Accent-er Chapter SEMARANG", "Member Accent-er Chapter UNGARAN", "Member Accent-er Chapter SALATIGA",
            "Member Accent-er Chapter AMBARAWA", "Member Accent-er Chapter SOLO", "Member Accent-er Chapter PEMALANG", "Member Accent-er Chapter CILACAP",

            "Member Accent-er Chapter SIDOARJO", "Member Accent-er Chapter SURABAYA", "Member Accent-er Chapter MALANG", "Member Accent-er Chapter MADIUN",
            "Member Accent-er Chapter MADURA",

            "Member Accent-er Chapter PADANG", "Member Accent-er Chapter PARIAMAN", "Member Accent-er Chapter BUKIT TINGGI", "Member Accent-er Chapter PAYAKUMBUH",
            "Member Accent-er Chapter JAMBI",

            "Member Accent-er Chapter MEDAN",

            "Member Accent-er Chapter PALEMBANG",

            "Member Accent-er Chapter BANTEN", "Member Accent-er Chapter CILEGON", "Member Accent-er Chapter PANDEGLANG", "Member Accent-er Chapter SERANG",
            "Member Accent-er Chapter RANGKASBITUNG",

            "Member Accent-er Chapter PEKANBARU",

            "Member Accent-er Chapter KALIMANTAN TIMUR",

            "Member Accent-er Chapter BALI",


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_region);
        List<HashMap<String, String>> aList = new ArrayList<>();

        for (int i = 0; i <40; i++) {
            HashMap<String, String> hm = new HashMap<>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_title, R.id.listview_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), aList, R.layout.list_row_region, from, to);
        ListView androidListView = (ListView) findViewById(R.id.listRegion);
        androidListView.setAdapter(simpleAdapter);

        androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                        Intent dki = new Intent(view.getContext(), JakartaActivity.class);
                        dki.putExtra(TAG_ID,jData.get(position).getItemId());
                        startActivity(dki);
                }
                if (position == 1) {
                    Intent bekasi = new Intent(getApplicationContext(), BekaciActivity.class);
                    startActivityForResult(bekasi, 1);
                }
                if (position == 2) {
                    Intent bandung = new Intent(getApplicationContext(), BandungActivity.class);
                    startActivityForResult(bandung, 2);
                }
                if (position == 3) {
                    Intent bgor = new Intent(getApplicationContext(), BogorActivity.class);
                    startActivityForResult(bgor, 3);
                }
                if (position == 4) {
                    Intent krw = new Intent(getApplicationContext(), KarawangActivity.class);
                    startActivityForResult(krw, 4);
                }
                if (position == 5) {
                    Intent bandung = new Intent(getApplicationContext(), DepokActivity.class);
                    startActivityForResult(bandung, 5);
                }
                if (position == 6) {
                    Intent bandung = new Intent(getApplicationContext(), CibuburActivity.class);
                    startActivityForResult(bandung, 6);
                }
                if (position == 7) {
                    Intent bandung = new Intent(getApplicationContext(), SubangActivity.class);
                    startActivityForResult(bandung, 7);
                }
                if (position == 8) {
                    Intent bandung = new Intent(getApplicationContext(), TasikActivity.class);
                    startActivityForResult(bandung, 8);
                }
                if (position == 9) {
                    Intent bandung = new Intent(getApplicationContext(), CirebonActivity.class);
                    startActivityForResult(bandung, 9);
                }
                if (position == 10) {
                    Intent bandung = new Intent(getApplicationContext(), KuninganActivity.class);
                    startActivityForResult(bandung, 10);
                }
                if (position == 11) {
                    Intent bandung = new Intent(getApplicationContext(), GarutActivity.class);
                    startActivityForResult(bandung, 11);
                }
                if (position == 12) {
                    Intent bandung = new Intent(getApplicationContext(), JogjaActivity.class);
                    startActivityForResult(bandung, 12);
                }
                if (position == 13) {
                    Intent bandung = new Intent(getApplicationContext(), SemarangActivity.class);
                    startActivityForResult(bandung, 13);
                }
                if (position == 14) {
                    Intent bandung = new Intent(getApplicationContext(), UngaranActivity.class);
                    startActivityForResult(bandung, 14);
                }
                if (position == 15) {
                    Intent bandung = new Intent(getApplicationContext(), SalatigaActivity.class);
                    startActivityForResult(bandung, 15);
                }
                if (position == 16) {
                    Intent bandung = new Intent(getApplicationContext(), AmbarawaActivity.class);
                    startActivityForResult(bandung, 16);
                }
                if (position == 17) {
                    Intent bandung = new Intent(getApplicationContext(), SoloActivity.class);
                    startActivityForResult(bandung, 17);
                }
                if (position == 18) {
                    Intent bandung = new Intent(getApplicationContext(), PemalangActivity.class);
                    startActivityForResult(bandung, 18);
                }
                if (position == 19) {
                    Intent bandung = new Intent(getApplicationContext(), CilacapActivity.class);
                    startActivityForResult(bandung, 19);
                }
                if (position == 20) {
                    Intent bandung = new Intent(getApplicationContext(), SidoarjoActivity.class);
                    startActivityForResult(bandung, 20);
                }
                if (position == 21) {
                    Intent bandung = new Intent(getApplicationContext(), SurabayaActivity.class);
                    startActivityForResult(bandung, 21);
                }
                if (position == 22) {
                    Intent bandung = new Intent(getApplicationContext(), MalangActivity.class);
                    startActivityForResult(bandung, 22);
                }
                if (position == 23) {
                    Intent bandung = new Intent(getApplicationContext(), MadiunActivity.class);
                    startActivityForResult(bandung, 23);
                }
                if (position == 24) {
                    Intent bandung = new Intent(getApplicationContext(), MaduraActivity.class);
                    startActivityForResult(bandung, 24);
                }
                if (position == 25) {
                    Intent bandung = new Intent(getApplicationContext(), PadangActivity.class);
                    startActivityForResult(bandung, 25);
                }
                if (position == 26) {
                    Intent bandung = new Intent(getApplicationContext(), PariamanActivity.class);
                    startActivityForResult(bandung, 26);
                }
                if (position == 27) {
                    Intent bandung = new Intent(getApplicationContext(), BukitTinggiActivity.class);
                    startActivityForResult(bandung, 27);
                }
                if (position == 28) {
                    Intent bandung = new Intent(getApplicationContext(), PayakumbuhActivity.class);
                    startActivityForResult(bandung, 28);
                }
                if (position == 29) {
                    Intent bandung = new Intent(getApplicationContext(), JambiActivity.class);
                    startActivityForResult(bandung, 29);
                }
                if (position == 30) {
                    Intent bandung = new Intent(getApplicationContext(), MedanActivity.class);
                    startActivityForResult(bandung, 30);
                }
                if (position == 31) {
                    Intent bandung = new Intent(getApplicationContext(), PalembangActivity.class);
                    startActivityForResult(bandung, 31);
                }
                if (position == 32) {
                    Intent bandung = new Intent(getApplicationContext(), BantenActivity.class);
                    startActivityForResult(bandung, 32);
                }
                if (position == 33) {
                    Intent bandung = new Intent(getApplicationContext(), CilegonActivity.class);
                    startActivityForResult(bandung, 33);
                }
                if (position == 34) {
                    Intent bandung = new Intent(getApplicationContext(), PandeglangActivity.class);
                    startActivityForResult(bandung, 34);
                }
                if (position == 35) {
                    Intent bandung = new Intent(getApplicationContext(), SerangActivity.class);
                    startActivityForResult(bandung, 35);
                }
                if (position == 36) {
                    Intent bandung = new Intent(getApplicationContext(), RangkasActivity.class);
                    startActivityForResult(bandung, 36);
                }
                if (position == 37) {
                    Intent bandung = new Intent(getApplicationContext(), PekanbaruActivity.class);
                    startActivityForResult(bandung, 37);
                }
                if (position == 38) {
                    Intent bandung = new Intent(getApplicationContext(), KaltimActivity.class);
                    startActivityForResult(bandung, 38);
                }
                if (position == 39) {
                    Intent bandung = new Intent(getApplicationContext(), BaliActivity.class);
                    startActivityForResult(bandung, 39);
                }
            }
        });
    }
}
