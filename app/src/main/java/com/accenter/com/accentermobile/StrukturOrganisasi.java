package com.accenter.com.accentermobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.accenter.com.accentermobile.struktur.StrukturPusat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StrukturOrganisasi extends AppCompatActivity {
    String[] listviewTitle = new String[]{
            //Pusat
            "KEPENGURUSAN ACCENT-ER NUSANTARA", "KEPENGURUSAN ACCENT-ER DKI JAKARTA", "KEPENGURUSAN ACCENT-ER JABAR", "KEPENGURUSAN ACCENT-ER JATENG",
            "KEPENGURUSAN ACCENT-ER JATIM", "KEPENGURUSAN ACCENT-ER BALI", "KEPENGURUSAN ACCENT-ER JAMBI", "KEPENGURUSAN ACCENT-ER SUMBAR",
            "KEPENGURUSAN ACCENT-ER BANTEN", "KEPENGURUSAN ACCENT-ER CHAPTER CIPASERA",

    };
    String[] listviewShortDescription = new String[]{
            "Struktural kepengurusan accent-er nusantara serta regional", "Struktural kepengurusan accent-er Dki Jakarta", "Struktural kepengurusan accent-er Jawabarat", "Struktural kepengurusan accent-er Jawatengah",
            "Struktural kepengurusan accent-er Jawatimur", "Struktural kepengurusan accent-er Bali", "Struktural kepengurusan accent-er Jambi", "Struktural kepengurusan accent-er Sumatera Barat",
            "Struktural kepengurusan accent-er Banten", "Struktural kepengurusan accent-er chapter Cipasera",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struktur_organisasi);// Array of strings for ListView Title
        List<HashMap<String, String>> aList = new ArrayList<>();
        for (int i = 0; i <9; i++) {
            HashMap<String, String> hm = new HashMap<>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            aList.add(hm);
        }
        String[] from = {"listview_title", "listview_discription"};
        int[] to = {R.id.listview_title, R.id.listview_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), aList, R.layout.list_row_struktur, from, to);
        ListView androidListView = (ListView) findViewById(R.id.listRegion);
        androidListView.setAdapter(simpleAdapter);

        androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent pusat = new Intent(getBaseContext(), StrukturPusat.class);
                    startActivity(pusat);
                }
                if (position == 1) {
                    /*Intent bekasi = new Intent(getApplicationContext(), StrukturDki.class);
                    startActivityForResult(bekasi, 1);*/
                    Toast.makeText(StrukturOrganisasi.this, "Belum Di Update Om!Nanti yaa", Toast.LENGTH_SHORT).show();
                }
                if (position == 2) {
                    /*Intent bandung = new Intent(getApplicationContext(), StrukturJabar.class);
                    startActivityForResult(bandung, 2);*/
                    Toast.makeText(StrukturOrganisasi.this, "Belum Di Update Om!Nanti yaa", Toast.LENGTH_SHORT).show();
                }
                if (position == 3) {
                    /*Intent bgor = new Intent(getApplicationContext(), StrukturJateng.class);
                    startActivityForResult(bgor, 3);*/
                    Toast.makeText(StrukturOrganisasi.this, "Belum Di Update Om!Nanti yaa", Toast.LENGTH_SHORT).show();
                }
                if (position == 4) {
                    /*Intent krw = new Intent(getApplicationContext(), StrukturJatim.class);
                    startActivityForResult(krw, 4);*/
                    Toast.makeText(StrukturOrganisasi.this, "Belum Di Update Om!Nanti yaa", Toast.LENGTH_SHORT).show();
                }
                if (position == 5) {
                    /*Intent bandung = new Intent(getApplicationContext(), StrukturBali.class);
                    startActivityForResult(bandung, 5);*/
                    Toast.makeText(StrukturOrganisasi.this, "Belum Di Update Om!Nanti yaa", Toast.LENGTH_SHORT).show();
                }
                if (position == 6) {
                    /*Intent bandung = new Intent(getApplicationContext(), StrukturJambi.class);
                    startActivityForResult(bandung, 6);*/
                    Toast.makeText(StrukturOrganisasi.this, "Belum Di Update Om!Nanti yaa", Toast.LENGTH_SHORT).show();
                }
                if (position == 7) {
                    /*Intent bandung = new Intent(getApplicationContext(), StrukturPadang.class);
                    startActivityForResult(bandung, 7);*/
                    Toast.makeText(StrukturOrganisasi.this, "Belum Di Update Om!Nanti yaa", Toast.LENGTH_SHORT).show();
                }
                if (position == 8) {
                    /*Intent bandung = new Intent(getApplicationContext(), StrukturBanten.class);
                    startActivityForResult(bandung, 8);*/
                    Toast.makeText(StrukturOrganisasi.this, "Belum Di Update Om!Nanti yaa", Toast.LENGTH_SHORT).show();
                }
                if (position == 9) {
                    /*Intent bandung = new Intent(getApplicationContext(), StrukturCipasera.class);
                    startActivityForResult(bandung, 9);*/
                    Toast.makeText(StrukturOrganisasi.this, "Belum Di Update Om!Nanti yaa", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
