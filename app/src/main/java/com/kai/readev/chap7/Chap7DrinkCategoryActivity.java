package com.kai.readev.chap7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.kai.readev.R;
import com.kai.readev.chap15.StarbuzzDatabaseHelper;

public class Chap7DrinkCategoryActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);

        SQLiteOpenHelper dbHelper = new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            @SuppressLint("Recycle") Cursor cursor = db.query(
                    "DRINK",
                    new String[]{"_id", "NAME"},
                    null, null, null, null, null);

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                    this,
                    android.R.layout.simple_expandable_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);

            ListView listView = findViewById(R.id.drink_cat_list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent = new Intent(this, Chap7DrinkActivity.class);
                intent.putExtra(Chap7DrinkActivity.DRINK_ID, (int) id);
                startActivity(intent);
            });
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cursor != null) cursor.close();
        if (db != null) db.close();
    }
}