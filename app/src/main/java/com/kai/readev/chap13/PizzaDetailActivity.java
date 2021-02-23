package com.kai.readev.chap13;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.kai.readev.R;

public class PizzaDetailActivity extends AppCompatActivity {

    public final static String PIZZA_ID = "pizzaid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        int id = getIntent().getIntExtra(PIZZA_ID, 0);

        Pizza pizza = Pizza.pizzas[id];
        ImageView iv = findViewById(R.id.pizza_image);
        iv.setImageResource(pizza.getImageResourceId());
        TextView tv = findViewById(R.id.pizza_caption);
        tv.setText(pizza.getName());
    }
}