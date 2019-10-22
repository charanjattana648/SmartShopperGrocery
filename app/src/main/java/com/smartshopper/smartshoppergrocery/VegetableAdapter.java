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

public class VegetableAdapter extends ArrayAdapter<GroceryItems> {
    private Context context;
    private List<GroceryItems> vegList=new ArrayList<>();
    private NumberFormat formatter;

    public VegetableAdapter(Context context,ArrayList<GroceryItems> list) {
        super(context,0, list);
        this.context=context;
        this.vegList=list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.veg_grid_items,parent,false);

        }
        formatter = new DecimalFormat("$#,###.##");
        GroceryItems currentVeg = vegList.get(position);
        ImageView vImg=convertView.findViewById(R.id.VegImage);
        TextView vName=convertView.findViewById(R.id.VegName);
        TextView vDesc=convertView.findViewById(R.id.VegDesc);
        TextView vPrice=convertView.findViewById(R.id.VegPrice);

        Glide.with(convertView).load(currentVeg.getImage()).into(vImg);
        vName.setText(currentVeg.getName());
        vDesc.setText(currentVeg.getDescription());
        vPrice.setText(formatter.format(currentVeg.getPrice()));

        return convertView;
    }
}
