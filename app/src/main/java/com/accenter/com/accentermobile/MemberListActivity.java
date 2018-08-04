package com.accenter.com.accentermobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import com.accenter.com.accentermobile.adapter.ExpandMemberAdapter;
import com.accenter.com.accentermobile.member.bali.BaliActivity;
import com.accenter.com.accentermobile.member.banten.BantenActivity;
import com.accenter.com.accentermobile.member.banten.CilegonActivity;
import com.accenter.com.accentermobile.member.banten.PandeglangActivity;
import com.accenter.com.accentermobile.member.banten.RangkasActivity;
import com.accenter.com.accentermobile.member.banten.SerangActivity;
import com.accenter.com.accentermobile.member.jatim.SidoarjoActivity;
import com.accenter.com.accentermobile.member.MemberListInfo;
import com.accenter.com.accentermobile.member.jabar.BandungActivity;
import com.accenter.com.accentermobile.member.jabar.BekaciActivity;
import com.accenter.com.accentermobile.member.jabar.BogorActivity;
import com.accenter.com.accentermobile.member.jabar.CibuburActivity;
import com.accenter.com.accentermobile.member.jabar.CirebonActivity;
import com.accenter.com.accentermobile.member.jabar.DepokActivity;
import com.accenter.com.accentermobile.member.jabar.GarutActivity;
import com.accenter.com.accentermobile.member.JakartaActivity;
import com.accenter.com.accentermobile.member.jabar.KarawangActivity;
import com.accenter.com.accentermobile.member.jabar.KuninganActivity;
import com.accenter.com.accentermobile.member.jabar.SubangActivity;
import com.accenter.com.accentermobile.member.jabar.TasikActivity;
import com.accenter.com.accentermobile.member.jateng.AmbarawaActivity;
import com.accenter.com.accentermobile.member.jateng.JogjaActivity;
import com.accenter.com.accentermobile.member.jateng.PemalangActivity;
import com.accenter.com.accentermobile.member.jateng.SalatigaActivity;
import com.accenter.com.accentermobile.member.jateng.SemarangActivity;
import com.accenter.com.accentermobile.member.jateng.SoloActivity;
import com.accenter.com.accentermobile.member.jateng.UngaranActivity;
import com.accenter.com.accentermobile.member.kepriau.PekanbaruActivity;
import com.accenter.com.accentermobile.member.sumbar.JambiActivity;
import com.accenter.com.accentermobile.member.sumbar.PadangActivity;
import com.accenter.com.accentermobile.member.sumbar.PariamanActivity;
import com.accenter.com.accentermobile.member.sumsel.PalembangActivity;
import com.accenter.com.accentermobile.member.sumut.MedanActivity;

public class MemberListActivity extends AppCompatActivity {
    ExpandMemberAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandMemberAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                if (groupPosition == 0) {
                    if (childPosition == 0) {
                        Intent child0Intent = new Intent(getApplicationContext(), JakartaActivity.class);
                        startActivityForResult(child0Intent,0);
                    }
                }
                if (groupPosition == 1) {
                    if (childPosition == 0) {
                        Intent child0Intent = new Intent(getBaseContext(), BandungActivity.class);
                        startActivity(child0Intent);
                    }
                    if (childPosition == 1) {
                        Intent child1Intent = new Intent(getBaseContext(), BekaciActivity.class);
                        startActivity(child1Intent);
                    }
                    if (childPosition == 2) {
                        Intent child2Intent = new Intent(getBaseContext(), KarawangActivity.class);
                        startActivity(child2Intent);
                    }
                    if (childPosition == 3) {
                        Intent child3Intent = new Intent(getBaseContext(), BogorActivity.class);
                        startActivity(child3Intent);
                    }
                    if (childPosition == 4) {
                        Intent child4Intent = new Intent(getBaseContext(), DepokActivity.class);
                        startActivity(child4Intent);
                    }
                    if (childPosition == 5) {
                        Intent child5Intent = new Intent(getBaseContext(), CibuburActivity.class);
                        startActivity(child5Intent);
                    }
                    if (childPosition == 6) {
                        Intent child6Intent = new Intent(getBaseContext(), SubangActivity.class);
                        startActivity(child6Intent);
                    }
                    if (childPosition == 7) {
                        Intent child7Intent = new Intent(getBaseContext(), TasikActivity.class);
                        startActivity(child7Intent);
                    }
                    if (childPosition == 8) {
                        Intent child8Intent = new Intent(getBaseContext(), CirebonActivity.class);
                        startActivity(child8Intent);
                    }
                    if (childPosition == 9) {
                        Intent child9Intent = new Intent(getBaseContext(), KuninganActivity.class);
                        startActivity(child9Intent);
                    }
                    if (childPosition == 10) {
                        Intent child10Intent = new Intent(getBaseContext(), GarutActivity.class);
                        startActivity(child10Intent);
                    }
                }
                    if (groupPosition == 2) { //jateng
                        if (childPosition == 0) {
                            Intent child0Intent = new Intent(getBaseContext(), JogjaActivity.class);
                            startActivity(child0Intent);
                        }
                        if (childPosition == 1) {
                            Intent child0Intent = new Intent(getBaseContext(), SemarangActivity.class);
                            startActivity(child0Intent);
                        }
                        if (childPosition == 2) {
                            Intent child0Intent = new Intent(getBaseContext(), UngaranActivity.class);
                            startActivity(child0Intent);
                        }
                        if (childPosition == 3) {
                            Intent child0Intent = new Intent(getBaseContext(), SalatigaActivity.class);
                            startActivity(child0Intent);
                        }
                        if (childPosition == 4) {
                            Intent child0Intent = new Intent(getBaseContext(), AmbarawaActivity.class);
                            startActivity(child0Intent);
                        }
                        if (childPosition == 5) {
                            Intent child0Intent = new Intent(getBaseContext(), SoloActivity.class);
                            startActivity(child0Intent);
                        }
                        if (childPosition == 6) {
                            Intent child0Intent = new Intent(getBaseContext(), PemalangActivity.class);
                            startActivity(child0Intent);
                        }
                    }
                    if (groupPosition == 3) { //jatim
                        if (childPosition == 0) {
                            Intent child0Intent = new Intent(getBaseContext(), SidoarjoActivity.class);
                            startActivity(child0Intent);
                        }
                        if (childPosition == 1) {
                            /*Intent child0Intent = new Intent(getBaseContext(), JakartaActivity.class);
                            startActivity(child0Intent);*/
                            Toast.makeText(getApplicationContext(),
                                    " Belum Di Update",
                                    Toast.LENGTH_SHORT).show();
                        }
                        if (childPosition == 2) {
                            /*Intent child0Intent = new Intent(getBaseContext(), JakartaActivity.class);
                            startActivity(child0Intent);*/
                            Toast.makeText(getApplicationContext(), " Belum Di Update",
                                    Toast.LENGTH_SHORT).show();
                        }
                        if (childPosition == 3) {
                            /*Intent child0Intent = new Intent(getBaseContext(), JakartaActivity.class);
                            startActivity(child0Intent);*/
                            Toast.makeText(getApplicationContext(),
                                    " Belum Di Update",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (groupPosition == 4) { //sumbar
                        if (childPosition == 0) {
                            Intent child0Intent = new Intent(getBaseContext(), PadangActivity.class);
                            startActivity(child0Intent);
                        }
                        if (childPosition == 1) {
                            Intent child0Intent = new Intent(getBaseContext(), PariamanActivity.class);
                            startActivity(child0Intent);
                        }
                        if (childPosition == 2) {
                            /*Intent child0Intent = new Intent(getBaseContext(), JambiActivity.class);
                            startActivity(child0Intent);*/
                            Toast.makeText(getApplicationContext(),
                                    " Belum Di Update",
                                    Toast.LENGTH_SHORT).show();
                        }
                        if (childPosition == 3) {
                            /*Intent child0Intent = new Intent(getBaseContext(), JakartaActivity.class);
                            startActivity(child0Intent);*/
                            Toast.makeText(getApplicationContext(),
                                    " Belum Di Update",
                                    Toast.LENGTH_SHORT).show();
                        }
                        if (childPosition == 4) {
                            Intent child0Intent = new Intent(getBaseContext(), JambiActivity.class);
                            startActivity(child0Intent);
                        }
                    }
                    if (groupPosition == 5) { //sumut
                        if (childPosition == 0) {
                            Intent child0Intent = new Intent(getBaseContext(), MedanActivity.class);
                            startActivity(child0Intent);
                        }
                    }
                if (groupPosition == 6) { //sumsel
                    if (childPosition == 0) {
                        Intent child0Intent = new Intent(getBaseContext(), PalembangActivity.class);
                        startActivity(child0Intent);
                    }
                }
                    if (groupPosition == 7) { //banten
                        if (childPosition == 0) {
                            Intent child0Intent = new Intent(getBaseContext(), BantenActivity.class);
                            startActivity(child0Intent);
                        }
                        if (childPosition == 1) {
                            Intent child0Intent = new Intent(getBaseContext(), CilegonActivity.class);
                            startActivity(child0Intent);
                        }
                        if (childPosition == 2) {
                            Intent child0Intent = new Intent(getBaseContext(), PandeglangActivity.class);
                            startActivity(child0Intent);
                        }
                        if (childPosition == 3) {
                            Intent child0Intent = new Intent(getBaseContext(), SerangActivity.class);
                            startActivity(child0Intent);
                        }
                        if (childPosition == 4) {
                            Intent child0Intent = new Intent(getBaseContext(), RangkasActivity.class);
                            startActivity(child0Intent);
                        }
                    }
                    if (groupPosition == 8) { //riau
                        if (childPosition == 0) {
                            Intent child0Intent = new Intent(getBaseContext(), PekanbaruActivity.class);
                            startActivity(child0Intent);
                        }
                    }
                     if (groupPosition == 9) { //bali
                        if (childPosition == 0) {
                            Intent child0Intent = new Intent(getBaseContext(), BaliActivity.class);
                            startActivity(child0Intent);
                    }
                }
                return false;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.memberdes, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.member_info) {
            Intent set = new Intent(MemberListActivity.this,MemberListInfo.class);
            startActivity(set);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding child data
        listDataHeader.add("DKI JAKARTA");
        listDataHeader.add("JAWA BARAT");
        listDataHeader.add("JAWA TENGAH");
        listDataHeader.add("JAWA TIMUR");
        listDataHeader.add("SUMATERA BARAT");
        listDataHeader.add("SUMATERA UTARA");
        listDataHeader.add("SUMATERA SELATAN");
        listDataHeader.add("BANTEN");
        listDataHeader.add("KEPULAUAN RIAU");
        listDataHeader.add("BALI");

        // Adding child data
        List<String> dki = new ArrayList<>();
        dki.add("Member List DKI JAKARTA");
        /*dki.add("The Godfather");
        dki.add("The Godfather: Part II");
        dki.add("Pulp Fiction");
        dki.add("The Good, the Bad and the Ugly");
        dki.add("The Dark Knight");
        dki.add("12 Angry Men");*/

        List<String> jabar = new ArrayList<>();
        jabar.add("Member Bandung");
        jabar.add("Member Bekasi");
        jabar.add("Member Karawang");
        jabar.add("Member Bogor");
        jabar.add("Member Depok");
        jabar.add("Member Cibubur");
        jabar.add("Member Subang");
        jabar.add("Member Tasikmalaya");
        jabar.add("Member Cirebon");
        jabar.add("Member Kuningan");
        jabar.add("Member Garut");


        List<String> jateng = new ArrayList<>();
        jateng.add("Member DIY");
        jateng.add("Member Semarang");
        jateng.add("Member Ungaran");
        jateng.add("Member Salatiga");
        jateng.add("Member Ambarawa");
        jateng.add("Member Solo");
        jateng.add("Member Pemalang");

        List<String> jatim = new ArrayList<>();
        jatim.add("Member Sidoarjo");
        jatim.add("Member Surabaya");
        jatim.add("Member Malang");
        jatim.add("Member Madiun");

        List<String> sumbar = new ArrayList<>();
        sumbar.add("Member Padang");
        sumbar.add("Member Pariaman");
        sumbar.add("Member Bukit Tinggi");
        sumbar.add("Member Payakumbuh");
        sumbar.add("Member Jambi");

        List<String> sumut = new ArrayList<>();
        sumut.add("Member Medan");
        /*sumut.add("Member List Pariaman");
        sumut.add("Member List Bukit Tinggi");
        sumut.add("Member List Payakumbuh");*/

        List<String> sumsel = new ArrayList<>();
        sumsel.add("Member Palembang");
        /*sumsel.add("Member List Palembang");
        sumsel.add("Member List Palembang");*/

        List<String> banten = new ArrayList<>();
        banten.add("Member Banten");
        banten.add("Member Cilegon");
        banten.add("Member Pandeglang");
        banten.add("Member Serang");
        banten.add("Member Rangkasbitung");

        List<String> riau = new ArrayList<>();
        riau.add("Member Pekanbaru");
        /*riau.add("Member List Jambi");
        riau.add("Member List Palembang");
        riau.add("Member List Palembang");*/

        List<String> bali = new ArrayList<>();
        bali.add("Member Bali");


        listDataChild.put(listDataHeader.get(0), dki); // Header, Child data
        listDataChild.put(listDataHeader.get(1), jabar);
        listDataChild.put(listDataHeader.get(2), jateng);
        listDataChild.put(listDataHeader.get(3), jatim);
        listDataChild.put(listDataHeader.get(4), sumbar);
        listDataChild.put(listDataHeader.get(5), sumut);
        listDataChild.put(listDataHeader.get(6), sumsel);
        listDataChild.put(listDataHeader.get(7), banten);
        listDataChild.put(listDataHeader.get(8), riau);
        listDataChild.put(listDataHeader.get(9), bali);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
