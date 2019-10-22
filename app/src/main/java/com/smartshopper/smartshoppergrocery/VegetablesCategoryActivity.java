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

public class VegetablesCategoryActivity extends AppCompatActivity {

    private GridView vegGridView;
    private VegetableAdapter vegetableAdapter;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private ArrayList<GroceryItems> vegetableItems=new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private NumberFormat formatter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetables_category);

        formatter = new DecimalFormat("$#,###.##");
        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        editor=sharedPreferences.edit();

        vegGridView = findViewById(R.id.vegGrid);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Vegetables");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        vegGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                editor.putString("Name","Vegetables");

                switch(position)
                {
                    case 0:
                        startActivity(new Intent(VegetablesCategoryActivity.this,SelectedGridItem.class));
                        editor.putString("child","Veg1");
                        editor.commit();
                        break;
                    case 1:
                        startActivity(new Intent(VegetablesCategoryActivity.this,SelectedGridItem.class));
                        editor.putString("child","Veg2");
                        editor.commit();
                        break;
                    case 2:
                        startActivity(new Intent(VegetablesCategoryActivity.this,SelectedGridItem.class));
                        editor.putString("child","Veg3");
                        editor.commit();
                        break;
                    case 3:
                        startActivity(new Intent(VegetablesCategoryActivity.this,SelectedGridItem.class));
                        editor.putString("child","Veg4");
                        editor.commit();
                        break;
                    case 4:
                        startActivity(new Intent(VegetablesCategoryActivity.this,SelectedGridItem.class));
                        editor.putString("child","Veg5");
                        editor.commit();
                        break;
                    case 5:
                        startActivity(new Intent(VegetablesCategoryActivity.this,SelectedGridItem.class));
                        editor.putString("child","Veg6");
                        editor.commit();
                        break;
                }

            }
        });

    }
    public void showData(DataSnapshot dataSnapshot) {

        for(DataSnapshot ds: dataSnapshot.getChildren()) {
            GroceryItems vegetables = ds.getValue(GroceryItems.class);

            vegetableItems.add(new GroceryItems(vegetables.getImage(), vegetables.getName(), vegetables.getDescription(), vegetables.getPrice() ));

            vegetableAdapter = new VegetableAdapter(this, vegetableItems);
            vegGridView.setAdapter(vegetableAdapter);
        }

    }

}
