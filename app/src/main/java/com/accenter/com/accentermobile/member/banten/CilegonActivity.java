package com.accenter.com.accentermobile.member.banten;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.accenter.com.accentermobile.R;

public class CilegonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cipasera);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
