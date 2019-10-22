package com.smartshopper.smartshoppergrocery;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PurchaseRecordActivity extends AppCompatActivity {


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private long countItems;
    private TextView countTxt,costTxt,showItems;
    private NumberFormat formatter;
   private String TotalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_record);

        formatter = new DecimalFormat("$#,###.##");
        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        countItems=sharedPreferences.getLong("Count",0);
        TotalCost=sharedPreferences.getString("TotalCost","");

        costTxt=findViewById(R.id.totalItemCost);
        countTxt=findViewById(R.id.totalQuantity);
       // showItems=findViewById(R.id.displayItems);

        countTxt.setText(String.valueOf(countItems));
        costTxt.setText(formatter.format(Double.parseDouble(TotalCost)));





    }
}
