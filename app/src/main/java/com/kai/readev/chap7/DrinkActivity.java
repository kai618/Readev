package com.kai.readev.chap7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.kai.readev.R;

public class DrinkActivity extends AppCompatActivity {

    public static final String DRINK_ID = "dId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        Intent intent = getIntent();
        int id = intent.getIntExtra(DRINK_ID, 0);

        Drink drink = DrinkManager.drinks[id];

        ImageView drinkImage = findViewById(R.id.drink_image);
        TextView drinkName = findViewById(R.id.drink_name);
        TextView drinkDescription = findViewById(R.id.drink_description);

        drinkImage.setImageResource(drink.getImageResId());
        drinkImage.setContentDescription(drink.getName());
        drinkName.setText(drink.getName());
        drinkDescription.setText(drink.getDescription());
    }
}