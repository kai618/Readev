package com.kai.readev.chap7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.kai.readev.R;

public class Chap7DrinkActivity extends AppCompatActivity {

    public static final String DRINK_ID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        Intent intent = getIntent();
        int id = intent.getIntExtra(DRINK_ID, 0);

        Drink drink = DrinkManager.drinks[id];
        setTitle(drink.getName());

        ImageView drinkImage = findViewById(R.id.drink_image);
        drinkImage.setImageResource(drink.getImageResId());
        drinkImage.setContentDescription(drink.getName());
        TextView drinkName = findViewById(R.id.drink_name);
        drinkName.setText(drink.getName());
        TextView drinkDescription = findViewById(R.id.drink_description);
        drinkDescription.setText(drink.getDescription());
    }
}