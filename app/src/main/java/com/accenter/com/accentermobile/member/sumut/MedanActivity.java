package com.accenter.com.accentermobile.member.sumut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.accenter.com.accentermobile.R;

public class MedanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medan);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
