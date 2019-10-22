package com.smartshopper.smartshoppergrocery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class FruitsCategoryActivity extends AppCompatActivity {

    private GridView gridView;
    private FruitAdapter fruitAdapter;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private ArrayList<GroceryItems> fruitItems=new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private NumberFormat formatter;


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits_category);

            formatter = new DecimalFormat("$#,###.##");
            sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
            editor=sharedPreferences.edit();
            gridView = findViewById(R.id.fruitGrid);
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Fruits");

new Thread(new Runnable() {
    @Override
    public void run() {



            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    showData(dataSnapshot);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
    }
}).start();
            FruitAsyn fruitAsynTask=new FruitAsyn();
            fruitAsynTask.execute();

    }

    private class FruitAsyn extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    editor.putString("Name","Fruits");

                    switch(position)
                    {
                        case 0:
                            startActivity(new Intent(FruitsCategoryActivity.this,SelectedGridItem.class));
                            editor.putString("child","Fruit1");
                            editor.commit();
                            break;
                        case 1:
                            startActivity(new Intent(FruitsCategoryActivity.this,SelectedGridItem.class));
                            editor.putString("child","Fruit2");
                            editor.commit();
                            break;
                        case 2:
                            startActivity(new Intent(FruitsCategoryActivity.this,SelectedGridItem.class));
                            editor.putString("child","Fruit3");
                            editor.commit();
                            break;
                        case 3:
                            startActivity(new Intent(FruitsCategoryActivity.this,SelectedGridItem.class));
                            editor.putString("child","Fruit4");
                            editor.commit();
                            break;
                        case 4:
                            startActivity(new Intent(FruitsCategoryActivity.this,SelectedGridItem.class));
                            editor.putString("child","Fruit5");
                            editor.commit();
                            break;
                        case 5:
                            startActivity(new Intent(FruitsCategoryActivity.this,SelectedGridItem.class));
                            editor.putString("child","Fruit6");
                            editor.commit();
                            break;
                    }

                }
            });
            return null;
        }
    }


    public void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds:dataSnapshot.getChildren() ) {
            GroceryItems fruits = ds.getValue(GroceryItems.class);

            fruitItems.add(new GroceryItems(fruits.getImage(), fruits.getName(), fruits.getDescription(), fruits.getPrice()));
            fruitAdapter = new FruitAdapter(this, fruitItems);
            gridView.setAdapter(fruitAdapter);
        }
    }

}
