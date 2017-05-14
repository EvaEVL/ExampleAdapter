package com.example.a1.exampleadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BoxAdapter extends BaseAdapter{
    Context ctx;
    LayoutInflater lf;
    ArrayList<Product> obj;

    public BoxAdapter(Context con, ArrayList<Product> products){
        ctx = con;
        obj = products;
        lf = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return obj.size();
    }

    @Override
    public Object getItem(int i) {
        return obj.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View cview, ViewGroup viewGroup) {
        View view = cview;
        if(view == null){
            view = lf.inflate(R.layout.item_wat,viewGroup,false);
        }

        Product p = (Product) getItem(i);
        ((TextView) view.findViewById(R.id.tbD)).setText(p.name);
        ((TextView) view.findViewById(R.id.tvPrice)).setText(p.price);

        return view;
    }
}
