package com.accenter.com.accentermobile.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import com.accenter.com.accentermobile.R;
import com.accenter.com.accentermobile.data.Item;

/**
 * Created by indrablake on 06/04/2018.
 */

public class CustomGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] web;
    private final int[] Imageid;

    public CustomGridViewAdapter(Context c,String[] web,int[] Imageid ) {
        mContext = c;
        this.Imageid = Imageid;
        this.web = web;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return web.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            if (inflater != null) {
                grid = inflater.inflate(R.layout.gridview_custom, null);
            }
            TextView textView = grid.findViewById(R.id.gridview_text);
            ImageView imageView = grid.findViewById(R.id.gridview_image);
            textView.setText(web[position]);
            imageView.setImageResource(Imageid[position]);
        } else {
            grid = convertView;
        }

        return grid;
    }
}
