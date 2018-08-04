package com.accenter.com.accentermobile.struktur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.accenter.com.accentermobile.R;

public class StrukturPusat extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struktur_pusat);

        imageView = (ImageView)findViewById(R.id.imageView2);
    }
}
