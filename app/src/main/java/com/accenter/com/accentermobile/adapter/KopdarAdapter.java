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

import com.accenter.com.accentermobile.data.KopdarData;
import com.android.volley.toolbox.ImageLoader;
import com.accenter.com.accentermobile.R;
import com.accenter.com.accentermobile.tool.AppController;
/**
 * Created by indrablake on 21/12/2017.
 */
public class KopdarAdapter extends BaseAdapter  {
    private Activity activity;
    private LayoutInflater inflater;
    private List<KopdarData> kopdarDatas;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public KopdarAdapter(Activity activity, List<KopdarData>kopdarDatas) {
        this.activity = activity;
        this.kopdarDatas = kopdarDatas;
    }
    @Override
    public int getCount() {
        return  kopdarDatas.size();
    }

    @Override
    public Object getItem(int location) {
        return  kopdarDatas.get(location);
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
            convertView = inflater.inflate(R.layout.list_kopdar, null);

        TextView judul = (TextView) convertView.findViewById(R.id.textJudulKopdar);
        TextView timestamp = (TextView) convertView.findViewById(R.id.textViewNopung);
        TextView isi = (TextView) convertView.findViewById(R.id.textViewIsiKopdar);
        TextView region = (TextView) convertView.findViewById(R.id.textViewRegionKopdar);
        TextView poster = (TextView) convertView.findViewById(R.id.textViewChapter);

        KopdarData kops = kopdarDatas.get(position);

        judul.setText(kops.getJudulKopdar());
        timestamp.setText(kops.getTglKopdar());
        isi.setText(Html.fromHtml(kops.getIsiKopdar()));
        region.setText(kops.getRegionKopdar());
        poster.setText(kops.getPostKopdar());

        return convertView;
    }
}
