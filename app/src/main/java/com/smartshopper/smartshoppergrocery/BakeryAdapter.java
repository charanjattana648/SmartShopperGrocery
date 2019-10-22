package com.smartshopper.smartshoppergrocery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class BakeryAdapter extends ArrayAdapter<GroceryItems> {
    private Context context;
    private List<GroceryItems> bakList=new ArrayList<>();
    private NumberFormat formatter;


    public BakeryAdapter(Context context,ArrayList<GroceryItems> list) {
        super(context,0, list);
        this.context=context;
        this.bakList=list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.bak_grid_items,parent,false);

        }
        GroceryItems currentVeg = bakList.get(position);
        ImageView bImg=convertView.findViewById(R.id.BakImage);
        TextView bName=convertView.findViewById(R.id.BakName);
        TextView bDesc=convertView.findViewById(R.id.BakDesc);
        TextView bPrice=convertView.findViewById(R.id.BalPrice);
        formatter = new DecimalFormat("$#,###.##");

        Glide.with(convertView).load(currentVeg.getImage()).into(bImg);
        bName.setText(currentVeg.getName());
        bDesc.setText(currentVeg.getDescription());
        bPrice.setText(formatter.format(currentVeg.getPrice()));

        return convertView;
    }
}
