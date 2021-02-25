package com.kai.readev.chap7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kai.readev.R;

public class Chap7TopMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_menu);

        AdapterView.OnItemClickListener itemClickListener = (parent, view, position, id) -> {
            if (position == 0) {
                Intent intent = new Intent(Chap7TopMenuActivity.this, Chap7DrinkCategoryActivity.class);
                startActivity(intent);
            }
        };

        ListView listView = findViewById(R.id.option_list);
        listView.setOnItemClickListener(itemClickListener);
    }

}