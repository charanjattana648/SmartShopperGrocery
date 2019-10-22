package com.smartshopper.smartshoppergrocery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class CandyCategoryActivity extends AppCompatActivity {

    private GridView canGridView;
    private CandyAdapter candyAdapter;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private ArrayList<GroceryItems> candyItems = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private NumberFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candy_category);

        formatter = new DecimalFormat("$#,###.##");
        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        editor=sharedPreferences.edit();

        canGridView = findViewById(R.id.canGrid);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Candies");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        canGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                editor.putString("Name","Candies");

                switch(position)
                {
                    case 0:
                        startActivity(new Intent(CandyCategoryActivity.this,SelectedGridItem.class));
                        editor.putString("child","Can1");
                        editor.commit();
                        break;
                    case 1:
                        startActivity(new Intent(CandyCategoryActivity.this,SelectedGridItem.class));
                        editor.putString("child","Can2");
                        editor.commit();
                        break;
                    case 2:
                        startActivity(new Intent(CandyCategoryActivity.this,SelectedGridItem.class));
                        editor.putString("child","Can3");
                        editor.commit();
                        break;
                    case 3:
                        startActivity(new Intent(CandyCategoryActivity.this,SelectedGridItem.class));
                        editor.putString("child","Can4");
                        editor.commit();
                        break;
                    case 4:
                        startActivity(new Intent(CandyCategoryActivity.this,SelectedGridItem.class));
                        editor.putString("child","Can5");
                        editor.commit();
                        break;
                    case 5:
                        startActivity(new Intent(CandyCategoryActivity.this,SelectedGridItem.class));
                        editor.putString("child","Can6");
                        editor.commit();
                        break;
                }

            }
        });
    }

    public void showData(DataSnapshot dataSnapshot) {

        for(DataSnapshot ds:dataSnapshot.getChildren()) {
            GroceryItems candies = ds.getValue(GroceryItems.class);
           // double price=Double.parseDouble(formatter.format(candies.getPrice()));
            candyItems.add(new GroceryItems(candies.getImage(), candies.getName(), candies.getDescription(), candies.getPrice()));


            candyAdapter = new CandyAdapter(this, candyItems);
            canGridView.setAdapter(candyAdapter);
        }

    }

}
