package com.kai.readev.chap7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kai.readev.R;

public class Chap7DrinkCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);

        ArrayAdapter<Drink> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, DrinkManager.drinks
        );
        AdapterView.OnItemClickListener itemClickListener = (parent, view, position, id) -> {
            Intent intent = new Intent(this, Chap7DrinkActivity.class);
            intent.putExtra(Chap7DrinkActivity.DRINK_ID, (int) id);
            startActivity(intent);
        };

        ListView listView = findViewById(R.id.drink_cat_list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(itemClickListener);
    }
}