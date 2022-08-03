package com.example.midexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetail extends AppCompatActivity {

    DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        TextView detailTitle = (TextView) findViewById(R.id.detailTitle);
        TextView detailDesc = (TextView) findViewById(R.id.detailDesc);
        TextView detailPrice = (TextView) findViewById(R.id.detailPrice);
        ImageView detailImg = (ImageView) findViewById(R.id.detailImg);
        Button detailButton = (Button) findViewById(R.id.addToCart);

        Intent intent = getIntent();
        String name = intent.getStringExtra("proName");
        String desc = intent.getStringExtra("proDesc");
        int price = intent.getIntExtra("proPrice", 1);
        int img = intent.getIntExtra("proImg", 1);
        database = new DatabaseHelper(getApplicationContext());

        String priceHolder = String.valueOf(price);

        detailButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(database.insertData(ShoppingCartList.itemIdIncrement + 1, name, price)){
                    Toast.makeText(getApplicationContext(), "Item added to your shopping cart.", Toast.LENGTH_SHORT).show();
                    ShoppingCartList.cartId.clear();
                    ShoppingCartList.cartName.clear();
                    ShoppingCartList.cartPrice.clear();
                    ShoppingCartList.subtotal += price;
                    SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sh.edit();
                    ShoppingCartList.itemIdIncrement++;
                    ShoppingCartList.numberOfItems++;
                    editor.putInt("itemIdIncrement", ShoppingCartList.itemIdIncrement);
                    editor.putInt("totalItems", ShoppingCartList.numberOfItems);
                    editor.putInt("subtotal", ShoppingCartList.subtotal);
                    editor.commit();
                }
            }
        });

        detailTitle.setText(name);
        detailDesc.setText(desc);
        detailPrice.setText("$" + priceHolder);
        detailImg.setImageResource(img);

    }
}