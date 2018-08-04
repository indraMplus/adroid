package com.accenter.com.accentermobile.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.accenter.com.accentermobile.R;
import com.accenter.com.accentermobile.data.MemberData;

import java.util.List;

/**
 * Created by indrablake on 23/01/2018.
 */
public class MemberAdapter extends BaseAdapter {

    private Context mCtx;
    private List<MemberData>memberDataList;

    public MemberAdapter(Context mCtx, List<MemberData> memberDataList){
        this.mCtx = mCtx;
        this.memberDataList = memberDataList;
    }

    @Override
    public int getCount() {
        return memberDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return memberDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(mCtx).inflate(R.layout.list_row_member_jakarta,null);

        TextView idJ    = (TextView) convertView.findViewById(R.id.idJakarta);
        TextView namaJ  = (TextView) convertView.findViewById(R.id.namamemberJakarta);
        TextView nopungJ = (TextView) convertView.findViewById(R.id.nopungmemberJakarta);
        TextView regionJ = (TextView) convertView.findViewById(R.id.regionmemberJakarta);
        TextView statusJ = (TextView) convertView.findViewById(R.id.statusmemberJakarta);

        MemberData data = (MemberData)getItem(position);

        idJ.setText(data.getId());
        namaJ.setText(data.getNamaMember());
        nopungJ.setText(data.getNopung());
        regionJ.setText(data.getChapter());
        statusJ.setText(data.getStatus());

        return convertView;
    }
}
