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

public class CandyAdapter extends ArrayAdapter<GroceryItems> {

    private Context context;
    private List<GroceryItems> canList=new ArrayList<>();
    private NumberFormat formatter;

    public CandyAdapter(Context context,ArrayList<GroceryItems> list) {
        super(context,0, list);
        this.context=context;
        this.canList=list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.can_grid_items,parent,false);

        }
        formatter = new DecimalFormat("$#,###.##");
        GroceryItems currentCandy = canList.get(position);
        ImageView cImg=convertView.findViewById(R.id.CanImage);
        TextView cName=convertView.findViewById(R.id.CanName);
        TextView cDesc=convertView.findViewById(R.id.CanDesc);
        TextView cPrice=convertView.findViewById(R.id.CanPrice);

        Glide.with(convertView).load(currentCandy.getImage()).into(cImg);
        cName.setText(currentCandy.getName());
        cDesc.setText(currentCandy.getDescription());
        cPrice.setText(formatter.format(currentCandy.getPrice()));

        return convertView;
    }
}
