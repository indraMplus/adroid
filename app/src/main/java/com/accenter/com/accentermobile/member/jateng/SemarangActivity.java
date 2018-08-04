package com.accenter.com.accentermobile.member.jateng;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.accenter.com.accentermobile.R;

public class SemarangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semarang);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
