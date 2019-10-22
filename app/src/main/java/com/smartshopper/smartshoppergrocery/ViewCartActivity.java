package com.smartshopper.smartshoppergrocery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ViewCartActivity extends AppCompatActivity {

    private ListView listView;
    private CartProductListAdapter cartAdapter;
    private TextView resultCart,txtdata;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference,databaseReference2;
    private FirebaseUser user;
    private ArrayList<SelectedcartItems> cartList;
    private String userId;
    private Button purchaceOrder;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private long itemsCount;
    private double totalCost=0;
    private NumberFormat formatter;
    private String name="",rkey="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        formatter = new DecimalFormat("$#,###.##");
        listView = (ListView) findViewById(R.id.cart_list);
        cartList= new ArrayList<>();
        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();
        userId=user.getUid();
        firebaseDatabase=FirebaseDatabase.getInstance();
        //databaseReference2=firebaseDatabase.getReference().child(userId).orderByChild("productname").equalTo("name");

        databaseReference=firebaseDatabase.getReference().child(userId);
        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        editor=sharedPreferences.edit();
        txtdata=findViewById(R.id.resltTxt);
        //firebaseDatabase.getReference().child(userId).child("-LSISxv9FMyz0wi2m1sZ").removeValue();
        databaseReference.child("-LSISxv9FMyz0wi2m1sZ").removeValue();
        purchaceOrder=findViewById(R.id.checkout);
        purchaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewCartActivity.this,PurchaseRecordActivity.class));
            }
        });

        DataSync dataSync=new DataSync();
        dataSync.execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                name=cartList.get(position).getProductname();
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds:dataSnapshot.getChildren() )
                        {


                            SelectedcartItems selectedcartItems = ds.getValue(SelectedcartItems.class);

                            if(selectedcartItems.getProductname()==name)
                            {
                                rkey=ds.getKey();
                                databaseReference.child(rkey).removeValue();

                            }
                            txtdata.setText(rkey);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });


    }



    private class DataSync extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {


            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds:dataSnapshot.getChildren() )
                    {
                        itemsCount=dataSnapshot.getChildrenCount();
                        if(itemsCount==0)
                        {
                            Toast toast=Toast.makeText(ViewCartActivity.this,"Cart is Empty",Toast.LENGTH_SHORT);
                            toast.show();
                        }else {
                            SelectedcartItems selectedcartItems = ds.getValue(SelectedcartItems.class);
                            double price=Double.parseDouble(selectedcartItems.getProductPrice())*Integer.parseInt(selectedcartItems.getProductQuantity());

                            cartList.add(new SelectedcartItems(selectedcartItems.getProductImage(), selectedcartItems.getProductname(), formatter.format(price), selectedcartItems.getProductQuantity()));
                            cartAdapter = new CartProductListAdapter(getApplicationContext(), cartList);
                            listView.setAdapter(cartAdapter);
                            totalCost += price;
                        }
                    }
                    editor.putString("TotalCost",String.valueOf(totalCost));
                    editor.putLong("Count",itemsCount);
                    editor.commit();
                }



                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            return null;
        }


    }
}
