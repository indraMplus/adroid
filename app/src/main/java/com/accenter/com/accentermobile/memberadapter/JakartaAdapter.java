package com.accenter.com.accentermobile.memberadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.accenter.com.accentermobile.R;
import com.accenter.com.accentermobile.memberdata.JakartaData;

import java.util.List;

/**
 * Created by indrablake on 23/01/2018.
 */
public class JakartaAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<JakartaData>jakartaDataList;


    public JakartaAdapter(Activity activity, List<JakartaData>jakartaDataList){
        this.activity = activity;
        this.jakartaDataList = jakartaDataList;
    }

    @Override
    public int getCount() {
        return jakartaDataList.size();
    }

    @Override
    public Object getItem(int location) {
        return jakartaDataList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row_member_jakarta, null);
        }

        TextView idJ = (TextView) convertView.findViewById(R.id.idJakarta);
        TextView namaJ = (TextView) convertView.findViewById(R.id.namamemberJakarta);
        TextView nopungJ = (TextView) convertView.findViewById(R.id.nopungmemberJakarta);
        TextView regionJ = (TextView) convertView.findViewById(R.id.regionmemberJakarta);
        TextView statusJ = (TextView) convertView.findViewById(R.id.statusmemberJakarta);

        JakartaData data = new JakartaData();

        idJ.setText(data.getItemId());
        namaJ.setText(data.getNamaMember());
        nopungJ.setText(data.getNopungMember());
        regionJ.setText(data.getRegionMember());
        statusJ.setText(data.getStatusMember());



        return convertView;
    }
}
