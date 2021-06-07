package com.example.m1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Item> items;

    public MyAdapter(Context context , ArrayList<Item> items){
        this.mContext = context;
        this.items = items;
    }

    @Override
    public int getCount() {

        if(items==null ){
            return 0;
            }
        else{
            return items.size();
        }

    }

    @Override
    public Item getItem(int position) {
        if(items==null ){
            return null;
        }
        else{
            return items.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item1, parent, false);
        holder = new ViewHolder();

        holder.textview =  convertView.findViewById(R.id.textView);
        holder.textview.setText(items.get(position).placeName);

        holder.textView2 =  convertView.findViewById(R.id.textView2);
        holder.textView2.setText(items.get(position).address);

        holder.textView3 =  convertView.findViewById(R.id.textView3);
        holder.textView3.setText(items.get(position).managerUnit);

        convertView.setTag(holder);
        return convertView;

    }

    protected static class ViewHolder{
        TextView textview;
        TextView textView2;
        TextView textView3;
    }

}
