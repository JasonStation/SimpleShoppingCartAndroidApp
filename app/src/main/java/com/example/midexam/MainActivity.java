package com.example.midexam;

//2440051574 - Jason Leonardo Sutioso - Nomor 3

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Integer> proId = new ArrayList<Integer>();
    public static ArrayList<Integer> proImg = new ArrayList<Integer>();
    public static ArrayList<String> proName = new ArrayList<String>();
    public static ArrayList<String> proDesc = new ArrayList<String>();
    public static ArrayList<Integer> proPrice = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button shoppingCart = (Button) findViewById(R.id.shoppingButton);
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        int keepNumberOfItems = sh.getInt("totalItems", ShoppingCartList.numberOfItems);
        shoppingCart.setText("View Shopping Cart (" + String.valueOf(keepNumberOfItems) + ")");

        shoppingCart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ShoppingCartList.cartId.clear();
                ShoppingCartList.cartName.clear();
                ShoppingCartList.cartPrice.clear();
                Intent i = new Intent(getApplicationContext(), ShoppingCartList.class);
                startActivity(i);
            }
        });

        if(proId.isEmpty()){
            proId.add(1);
            proImg.add(R.drawable.hyperxkeyboard);
            proName.add("HyperX Keyboard");
            proDesc.add("Key switches are specially designed to balance response and accuracy, featuring short travel times and low actuation forces. They are also reliable, rated for 80 million keystrokes with no loss of quality.");
            proPrice.add(100);

            proId.add(2);
            proImg.add(R.drawable.engamemouse);
            proName.add("Endgame Mouse");
            proDesc.add("Thanks to 14 RGB LEDs the mouse wheel, logo and the RGB ring at the bottom of the mouse can be illuminated with up to 16.7 million colours. A total of five lighting modes with different effects and four brightness levels are available.");
            proPrice.add(80);

            proId.add(3);
            proImg.add(R.drawable.headphonescool);
            proName.add("Storm Gaming Headset");
            proDesc.add("A gaming headset that has 50mm speakers and there are RGB lights on the side of the headset and is equipped with a microphone on the left side of the headset which makes the headset look more elegant and cool.\n" +
                    "With a soft foam design, bass sound, and comfortable for long-term use.");
            proPrice.add(20);

            proId.add(4);
            proImg.add(R.drawable.pccasetempered);
            proName.add("Metallic Gear PC Case");
            proDesc.add("A slick case made out of metal for your PC. It is one of th4e most top quality PC cases you can buy on the market right now for a good price. This PC comes 7 expansion slots, 2 USB Ports, and more!");
            proPrice.add(95);

            proId.add(5);
            proImg.add(R.drawable.rexusmousepad);
            proName.add("Rexus RGB Mousepad");
            proDesc.add("The Rexus KVLAR TR2 mousepad uses a textured surface and is equipped with an ideal thickness so as to maximize precise mouse movement. Mouse control becomes easier because the mouse sensor maps the mousepad surface in detail.\n" +
                    "This mousepad will give you perfect control and enhance your performance in every game.");
            proPrice.add(20);

        }

        ListView items = (ListView) findViewById(R.id.mainView);

        CustomAdapter ca = new CustomAdapter();

        items.setAdapter(ca);

        items.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),"You selected : " + proName.get(position),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ProductDetail.class);
                intent.putExtra("proImg", proImg.get(position));
                intent.putExtra("proName", proName.get(position));
                intent.putExtra("proPrice", proPrice.get(position));
                intent.putExtra("proDesc", proDesc.get(position));
                startActivity(intent);
            }
        });
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return proId.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.listviewpro, null);

            TextView title = (TextView) view.findViewById(R.id.name);
            TextView price = (TextView) view.findViewById(R.id.price);
            ImageView thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

            title.setText(proName.get(i));
            price.setText("$" + proPrice.get(i).toString());
            thumbnail.setImageResource(proImg.get(i));

            return view;
        }
    }
}