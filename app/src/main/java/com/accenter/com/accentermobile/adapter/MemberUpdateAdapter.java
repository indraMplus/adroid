package com.accenter.com.accentermobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.accenter.com.accentermobile.R;
import com.accenter.com.accentermobile.data.MemberUpdateData;

import java.util.List;

/**
 * Created by indrablake on 22/01/2018.
 */
public class MemberUpdateAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<MemberUpdateData> items;

    public MemberUpdateAdapter(Activity activity, List<MemberUpdateData> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) convertView = inflater.inflate(R.layout.list_item_update,null);

        TextView id = (TextView) convertView.findViewById(R.id.Lid);
        TextView nama = (TextView) convertView.findViewById(R.id.Lnama);
        TextView nopungs = (TextView) convertView.findViewById(R.id.Lnopung);
        TextView region = (TextView) convertView.findViewById(R.id.Lchapter);
        TextView status = (TextView) convertView.findViewById(R.id.Lstatus);
        TextView keterangan = (TextView)convertView.findViewById(R.id.Lketerangan);

        MemberUpdateData data = items.get(position);

        id.setText(data.getId());
        nama.setText(data.getNama());
        nopungs.setText(data.getNopung());
        region.setText(data.getChapter());
        status.setText(data.getStatus());
        keterangan.setText(data.getKeterangan());

        return convertView;
    }
}
