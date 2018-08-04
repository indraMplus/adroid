package com.accenter.com.accentermobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.accenter.com.accentermobile.adapter.CustomGridViewAdapter;
import com.accenter.com.accentermobile.chat.ChatroomListActivity;
import com.accenter.com.accentermobile.chat.DaftarChat;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    GridView grid;
    String[] web = {
            "ARTIKEL",
            "EVENT",
            "KOPDAR",
            "MEMBER LIST",
            "BURSA JUAL-BELI",
            "GALERI KOPDAR",
            "CHATROOM",
            "STRUKTUR",
            "PENDAFTARAN"
    } ;
    int[] imageId = {
            R.mipmap.ic_text,
            R.mipmap.ic_calendar,
            R.mipmap.ic_car,
            R.mipmap.ic_management,
            R.mipmap.ic_shop,
            R.mipmap.ic_icon,
            R.mipmap.ic_group,
            R.mipmap.ic_diagram,
            R.mipmap.ic_registration
    };
    //parameter slider
    SliderLayout sliderLayout;
    HashMap<String,String> Hash_file_maps ;
    //end parameter slide
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        CustomGridViewAdapter adapter = new CustomGridViewAdapter(HomeActivity.this, web, imageId);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if(position==0) {
                    Intent intent = new Intent(HomeActivity.this, Beranda.class);
                    startActivity(intent);
                }
                else if (position ==1){

                }
                else if (position ==2) {
                }
                else if (position ==3) {
                }
                else if (position ==4) {
                }
                else if (position ==5) {
                }
                else if (position ==6) {
                }
                else if (position ==7) {
                }
                else if (position ==8) {
                }
            }
        });


        //parameter slider
        Hash_file_maps = new HashMap<>();

        sliderLayout = (SliderLayout)findViewById(R.id.slider);

        Hash_file_maps.put("Ultah Accent-er ke 9", "https://accent-er.com/news/slider/sliderultah.jpg");
        Hash_file_maps.put("Anniversary Regional PADANG", "https://accent-er.com/news/slider/sliderpadang.jpg");
        Hash_file_maps.put("Anniversary Regional PEKANBARU", "https://accent-er.com/news/slider/sliderpekanbaru.jpg");
        Hash_file_maps.put("Anniversary Regional JATIM", "https://accent-er.com/news/slider/sliderjatim.jpg");
        Hash_file_maps.put("Deklarasi Chapter CIPASERA", "https://accent-er.com/news/slider/slidercipasera.jpg");
        Hash_file_maps.put("Anniversary Regional JABAR", "https://accent-er.com/news/slider/sliderjabar.jpg");

        for(String name : Hash_file_maps.keySet()){

            TextSliderView textSliderView = new TextSliderView(HomeActivity.this);
            textSliderView
                    .description(name)
                    .image(Hash_file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(6000);
        sliderLayout.addOnPageChangeListener(this);


    }
    @Override
    public void onStop() {
        sliderLayout.stopAutoCycle();//parameter slider
        super.onStop();
    }
    @Override
    public void onResume(){
        super.onResume();
        sliderLayout.startAutoCycle();
    }
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
