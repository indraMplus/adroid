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
import com.accenter.com.accentermobile.data.EventData;
import com.accenter.com.accentermobile.tool.AppController;
/**
 * Created by indrablake on 21/12/2017.
 */
public class EventAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<EventData> eventDatas;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        public EventAdapter(Activity activity, List<EventData>eventDatas) {
            this.activity = activity;
            this.eventDatas = eventDatas;
        }
    @Override
    public int getCount() {
        return  eventDatas.size();
    }

    @Override
    public Object getItem(int location) {
        return  eventDatas.get(location);
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
            convertView = inflater.inflate(R.layout.list_event, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.imageEvent);
        TextView judul = (TextView) convertView.findViewById(R.id.textJudulEvent);
        TextView timestamp = (TextView) convertView.findViewById(R.id.textViewTglEvent);
        TextView isi = (TextView) convertView.findViewById(R.id.textViewIsiEvent);

        TextView poster = (TextView) convertView.findViewById(R.id.textViewPostEvent);

        EventData events = eventDatas.get(position);

        thumbNail.setImageUrl(events.getImgEvent(),imageLoader);
        judul.setText(events.getJudulEvent());
        timestamp.setText(events.getTglEvent());
        isi.setText(Html.fromHtml(events.getIsiEvent()));
        poster.setText(events.getPostEvent());

        // Converting timestamp into x ago format
        /*CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(events.getTglEvent()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        timestamp.setText(timeAgo);*/

        return convertView;
    }
}
