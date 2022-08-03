package com.example.midexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InfoForm extends AppCompatActivity {

    DatabaseHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_form);

        Button orderNow = (Button) findViewById(R.id.orderNow);
        String totalPrice = String.valueOf(ShoppingCartList.subtotal);
        orderNow.setText("Buy Now ($" + totalPrice + ")");

        database = new DatabaseHelper(getApplicationContext());

        orderNow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Items have been bought!", Toast.LENGTH_SHORT).show();
                ShoppingCartList.cartName.clear();
                ShoppingCartList.cartPrice.clear();
                ShoppingCartList.subtotal = 0;
                ShoppingCartList.numberOfItems = 0;
                SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sh.edit();
                database.deleteAllData();
                editor.putInt("subtotal", ShoppingCartList.subtotal);
                editor.putInt("totalItems", ShoppingCartList.numberOfItems);
                editor.commit();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
        });

    }
}