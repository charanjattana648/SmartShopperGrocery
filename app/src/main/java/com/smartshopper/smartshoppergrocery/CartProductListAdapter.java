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
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CartProductListAdapter extends ArrayAdapter<SelectedcartItems> {

    private Context mContext;
    private List<SelectedcartItems> cartItemsList = new ArrayList<>();

    public CartProductListAdapter(@NonNull Context context, ArrayList<SelectedcartItems> list) {
        super(context, 0 , list);
        mContext = context;
        cartItemsList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.selected_cart_list,parent,false);

        SelectedcartItems currentItem = cartItemsList.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.cartItemImage);
        //image.setImageResource(currentItem.getProductImage());
        Glide.with(getContext()).load(currentItem.getProductImage()).into(image);

        TextView name = (TextView) listItem.findViewById(R.id.selectedItemname);
        name.setText(currentItem.getProductname());

        TextView price = (TextView) listItem.findViewById(R.id.selectedItemPrice);
        price.setText(String.valueOf(currentItem.getProductPrice()));

        TextView quantity = (TextView) listItem.findViewById(R.id.selectedItemQuantity);
        quantity.setText(String.valueOf(currentItem.getProductQuantity()));

        return listItem;
    }
}
