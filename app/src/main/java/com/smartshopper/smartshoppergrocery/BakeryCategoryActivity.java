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

public class BakeryCategoryActivity extends AppCompatActivity {

    private GridView bakGridView;
    private BakeryAdapter bakeryAdapter;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private ArrayList<GroceryItems> bakeryItems = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private NumberFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakery_category);

        formatter = new DecimalFormat("$#,###.##");

        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        editor=sharedPreferences.edit();

        bakGridView = findViewById(R.id.bakGrid);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Bakery");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        bakGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                editor.putString("Name","Bakery");

                switch(position)
                {
                    case 0:
                        startActivity(new Intent(BakeryCategoryActivity.this,SelectedGridItem.class));
                        editor.putString("child","Bak1");
                        editor.commit();
                        break;
                    case 1:
                        startActivity(new Intent(BakeryCategoryActivity.this,SelectedGridItem.class));
                        editor.putString("child","Bak2");
                        editor.commit();
                        break;
                    case 2:
                        startActivity(new Intent(BakeryCategoryActivity.this,SelectedGridItem.class));
                        editor.putString("child","Bak3");
                        editor.commit();
                        break;
                    case 3:
                        startActivity(new Intent(BakeryCategoryActivity.this,SelectedGridItem.class));
                        editor.putString("child","Bak4");
                        editor.commit();
                        break;
                    case 4:
                        startActivity(new Intent(BakeryCategoryActivity.this,SelectedGridItem.class));
                        editor.putString("child","Bak5");
                        editor.commit();
                        break;
                    case 5:
                        startActivity(new Intent(BakeryCategoryActivity.this,SelectedGridItem.class));
                        editor.putString("child","Bak6");
                        editor.commit();
                        break;
                }

            }
        });
    }

    public void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds:dataSnapshot.getChildren() ) {


            GroceryItems bak1 = ds.getValue(GroceryItems.class);
            bakeryItems.add(new GroceryItems(bak1.getImage(), bak1.getName(), bak1.getDescription(),bak1.getPrice()  ));
            bakeryAdapter = new BakeryAdapter(this, bakeryItems);
            bakGridView.setAdapter(bakeryAdapter);

        }
    }
}
