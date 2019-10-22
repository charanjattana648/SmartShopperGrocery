package com.smartshopper.smartshoppergrocery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeScreenMain extends AppCompatActivity {

    ImageButton fruitsBtn, vegetablesBtn, bakeryBtn, candyBtn;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_main);

        fruitsBtn = findViewById(R.id.fruits);
        vegetablesBtn = findViewById(R.id.vegetables);
        bakeryBtn = findViewById(R.id.bakery);
        candyBtn = findViewById(R.id.candies);

        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();

        fruitsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenMain.this, FruitsCategoryActivity.class));
            }
        });

        vegetablesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenMain.this, VegetablesCategoryActivity.class));
            }
        });

        bakeryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenMain.this, BakeryCategoryActivity.class));
            }
        });

        candyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenMain.this, CandyCategoryActivity.class));
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.cart:
                if(user!=null)
                {
                    startActivity(new Intent(HomeScreenMain.this,ViewCartActivity.class));
                }else {
                    Toast toast=Toast.makeText(HomeScreenMain.this,"Please login First",Toast.LENGTH_SHORT);
                    toast.show();
                    startActivity(new Intent(HomeScreenMain.this,LoginActivity.class));
                    finish();
                }

                return true;
            case R.id.login:
                startActivity(new Intent(HomeScreenMain.this, LoginActivity.class));
                return(true);
            case R.id.register:
                startActivity(new Intent(HomeScreenMain.this, RegisterActivity.class));
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }
}
