package com.kundan.transportdemo;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.kundan.transportdemo.model.TransportModel;

import java.util.ArrayList;

/**
 * Created by Kundan on 30-03-2017.
 */

public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

    private final Context activity;
    ArrayList<TransportModel> transportList;

    public CustomSpinnerAdapter(Context context, ArrayList<TransportModel> list) {
        this.transportList=list;
        activity = context;
    }



    public int getCount()
    {
        return transportList.size();
    }

    public Object getItem(int i)
    {
        return transportList.get(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }



    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView txt = new TextView(activity);
        txt.setPadding(16, 16, 16, 16);
        txt.setTextSize(18);
        txt.setGravity(Gravity.LEFT);
        txt.setText(transportList.get(position).getName());
        txt.setTextColor(Color.parseColor("#000000"));
        return  txt;
    }

    public View getView(int i, View view, ViewGroup viewgroup) {
        TextView txt = new TextView(activity);
        txt.setGravity(Gravity.LEFT);
        txt.setPadding(16, 16, 16, 16);
        txt.setTextSize(16);
      //  txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_down_black_24dp, 0);
        txt.setText(transportList.get(i).getName());
        txt.setTextColor(Color.parseColor("#000000"));
        return  txt;
    }

}
