package com.accenter.com.accentermobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.accenter.com.accentermobile.member.bali.BaliActivity;
import com.accenter.com.accentermobile.member.jabar.BandungActivity;
import com.accenter.com.accentermobile.member.banten.BantenActivity;
import com.accenter.com.accentermobile.member.jabar.BekaciActivity;
import com.accenter.com.accentermobile.member.jabar.BogorActivity;
import com.accenter.com.accentermobile.member.banten.CilegonActivity;
import com.accenter.com.accentermobile.member.jabar.CirebonActivity;
import com.accenter.com.accentermobile.member.JakartaActivity;
import com.accenter.com.accentermobile.member.sumbar.JambiActivity;
import com.accenter.com.accentermobile.member.jateng.JogjaActivity;
import com.accenter.com.accentermobile.member.jatim.SidoarjoActivity;
import com.accenter.com.accentermobile.member.jatim.MaduraActivity;
import com.accenter.com.accentermobile.member.sumut.MedanActivity;
import com.accenter.com.accentermobile.member.sumbar.PadangActivity;
import com.accenter.com.accentermobile.member.sumsel.PalembangActivity;
import com.accenter.com.accentermobile.member.kepriau.PekanbaruActivity;
import com.accenter.com.accentermobile.member.jabar.TasikActivity;


public class MemberActivity extends AppCompatActivity {
    ListView listView;
    private ArrayAdapter<CharSequence> madapter;
    public static final String MEMBER_LIST = "mlist";
    private static final int REQUEST_RESPONSE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        listView = (ListView) findViewById(R.id.memberList);
        madapter = ArrayAdapter.createFromResource(this, R.array.member_array, android.R.layout.simple_list_item_1);
        listView.setAdapter(madapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent myIntent = new Intent(view.getContext(), JakartaActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 1) {
                    Intent myIntent = new Intent(view.getContext(), BandungActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 2) {
                    Intent myIntent = new Intent(view.getContext(), BogorActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 3) {
                    Intent myIntent = new Intent(view.getContext(), BekaciActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 4) {
                    Intent myIntent = new Intent(view.getContext(), CilegonActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 5) {
                    Intent myIntent = new Intent(view.getContext(), JogjaActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 6) {
                    Intent myIntent = new Intent(view.getContext(), SidoarjoActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 7) {
                    Intent myIntent = new Intent(view.getContext(), PekanbaruActivity.class);
                    startActivityForResult(myIntent, 0);
                }
                if (position == 8) {
                    Intent myIntent = new Intent(view.getContext(), MedanActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 9) {
                    Intent myIntent = new Intent(view.getContext(), MaduraActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 10) {
                    Intent myIntent = new Intent(view.getContext(), PadangActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 11) {
                    Intent myIntent = new Intent(view.getContext(), TasikActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 12) {
                    Intent myIntent = new Intent(view.getContext(), CirebonActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 13) {
                    Intent myIntent = new Intent(view.getContext(), PalembangActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 14) {
                    Intent myIntent = new Intent(view.getContext(), JambiActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 15) {
                    Intent myIntent = new Intent(view.getContext(), BaliActivity.class);
                    startActivityForResult(myIntent, 0);
                }
                if (position == 16) {
                    Intent myIntent = new Intent(view.getContext(), BantenActivity.class);
                    startActivityForResult(myIntent, 0);
                }
            }
        });
    }
}
