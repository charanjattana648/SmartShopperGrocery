package com.smartshopper.smartshoppergrocery;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class FruitAdapter extends ArrayAdapter<GroceryItems> {

    private Context context;
    private List<GroceryItems> fruitList=new ArrayList<>();
    private NumberFormat formatter;

    public FruitAdapter(Context context,ArrayList<GroceryItems> list) {
        super(context,0, list);
        this.context=context;
        this.fruitList=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.fruit_list_item,parent,false);
        }

        formatter = new DecimalFormat("$#,###.##");
        GroceryItems currentFruit=fruitList.get(position);
        ImageView fImg=convertView.findViewById(R.id.FruitImage);
        TextView fName=convertView.findViewById(R.id.FruitName);
        TextView fDesc=convertView.findViewById(R.id.FruitDesc);
        TextView fPrice=convertView.findViewById(R.id.FruitPrice);


        Glide.with(convertView).load(currentFruit.getImage()).into(fImg);
        fName.setText(currentFruit.getName());
        fDesc.setText(currentFruit.getDescription());
        fPrice.setText(formatter.format(currentFruit.getPrice()));

        return convertView;
    }
}
