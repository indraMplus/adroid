package com.accenter.com.accentermobile.member.jabar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.accenter.com.accentermobile.R;

public class GarutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garut);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
