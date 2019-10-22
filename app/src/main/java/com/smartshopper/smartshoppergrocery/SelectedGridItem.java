package com.smartshopper.smartshoppergrocery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

public class SelectedGridItem extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,mdatbaseref;
    private SharedPreferences sharedPreferences;
    private String Name,Child,ItemImage,ItemName;
    private double ItemPrice;
    private ImageView imgView;
    private TextView name,price;
    private EditText Quantity;
    private Button addToCartBtn;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private SharedPreferences.Editor editor;
    private String QuantityNum;
private NumberFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_grid_item);

        formatter = new DecimalFormat("$#,###.##");
        imgView=findViewById(R.id.selectedFruitImage);
        name=findViewById(R.id.fruitItemName);
        price=findViewById(R.id.fruitItemPrice);
        Quantity=findViewById(R.id.quantity);
        addToCartBtn = findViewById(R.id.addToCart);
        firebaseAuth= FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();

        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);


        Name=sharedPreferences.getString("Name","");
        Child=sharedPreferences.getString("child","");
        gridAsyn gridAsynTask=new gridAsyn();
        gridAsynTask.execute();
        mdatbaseref=FirebaseDatabase.getInstance().getReference().child(firebaseAuth.getUid());
        new Thread(new Runnable() {
            @Override
            public void run() {
                addToCartBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(user!=null)
                        {
                            QuantityNum= Quantity.getText().toString().trim();
                            if(QuantityNum.isEmpty())
                            {
                                Toast toast=Toast.makeText(SelectedGridItem.this,"Please Insert Quantity",Toast.LENGTH_SHORT);
                                toast.show();

                            }else {



                                HashMap<String,String> dataMap=new HashMap<>();
                                dataMap.put("productImage",ItemImage);
                                dataMap.put("productname",ItemName);
                                dataMap.put("productPrice",String.valueOf(ItemPrice));
                                dataMap.put("productQuantity",QuantityNum);

                                mdatbaseref.push().setValue(dataMap);
                                Toast toast=Toast.makeText(SelectedGridItem.this,"Data Inserted ",Toast.LENGTH_SHORT);
                                toast.show();
                                finish();
                            }}else{
                            Toast toast=Toast.makeText(SelectedGridItem.this,"Please login First",Toast.LENGTH_SHORT);
                            toast.show();
                            startActivity(new Intent(SelectedGridItem.this, LoginActivity.class));
                            finish();


                        }


                    }
                });

            }
        }).start();




    }


    private class gridSelectedAsyn extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {

            addToCartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(user!=null)
                    {
                        QuantityNum= Quantity.getText().toString().trim();
                        if(QuantityNum.isEmpty())
                        {
                            Toast toast=Toast.makeText(SelectedGridItem.this,"Please Insert Quantity",Toast.LENGTH_SHORT);
                            toast.show();

                        }else {
                          //  mdatbaseref=FirebaseDatabase.getInstance().getReference().child(firebaseAuth.getUid());


                            HashMap<String,String> dataMap=new HashMap<>();
                            dataMap.put("productImage",ItemImage);
                            dataMap.put("productname",ItemName);
                            dataMap.put("productPrice",String.valueOf(ItemPrice));
                            dataMap.put("productQuantity",QuantityNum);

                            mdatbaseref.push().setValue(dataMap);
                            Toast toast=Toast.makeText(SelectedGridItem.this,"Data Inserted ",Toast.LENGTH_SHORT);
                            toast.show();
                            finish();
                        }}else{
                        Toast toast=Toast.makeText(SelectedGridItem.this,"Please login First",Toast.LENGTH_SHORT);
                        toast.show();
                        startActivity(new Intent(SelectedGridItem.this, LoginActivity.class));
                        finish();


                    }


                }
            });
            return null;
        }
    }

    private class gridAsyn extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    GroceryItems item1=dataSnapshot.child(Name).child(Child).getValue(GroceryItems.class);
                    ItemName= item1.getName();
                    ItemImage=item1.getImage();
                    ItemPrice=item1.getPrice();

                    Glide.with(getApplicationContext()).load(ItemImage).into(imgView);
                    name.setText(ItemName);
                    price.setText(formatter.format(ItemPrice));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            return null;
        }
    }
}
