package com.accenter.com.accentermobile.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.accenter.com.accentermobile.R;
import com.accenter.com.accentermobile.data.ArtikelData;
import com.accenter.com.accentermobile.tool.AppController;

/**
 * Created by indrablake on 20/12/2017.
 */
public class ArtikelAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ArtikelData> artikelDatas;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        public ArtikelAdapter(Activity activity, List<ArtikelData> artikelDatas) {
            this.activity = activity;
            this.artikelDatas = artikelDatas;
        }

    @Override
    public int getCount() {
        return  artikelDatas.size();
    }

    @Override
    public Object getItem(int location) {
        return  artikelDatas.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_artikel, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.imageArtikel);
        TextView judul = (TextView) convertView.findViewById(R.id.judul_artikel);
        TextView timestamp = (TextView) convertView.findViewById(R.id.tgl_artikel);
        TextView isi = (TextView) convertView.findViewById(R.id.isi_artikel);

        ArtikelData news = artikelDatas.get(position);

        thumbNail.setImageUrl(news.getImgArtikel(),imageLoader);
        judul.setText(news.getJudulArtikel());
        timestamp.setText(news.getTglArtikel());
        isi.setText(Html.fromHtml(news.getIsiArtikel()));

        return convertView;
    }
}
