package com.kai.readev.chap7;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kai.readev.R;
import com.kai.readev.chap15.StarbuzzDatabaseHelper;

public class Chap7TopMenuActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_menu);
        setMenuView();
        setFavoriteListView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        ListView favListView = findViewById(R.id.fav_list);
        SimpleCursorAdapter adapter = (SimpleCursorAdapter) favListView.getAdapter();
        adapter.changeCursor(getFavoriteCursor(db));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cursor != null) cursor.close();
        if (db != null) db.close();
    }

    private void setMenuView() {
        ListView listView = findViewById(R.id.option_list);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (position == 0) {
                Intent intent = new Intent(this, Chap7DrinkCategoryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setFavoriteListView() {
        SQLiteOpenHelper helper = new StarbuzzDatabaseHelper(this);
        try {
            db = helper.getReadableDatabase();
            cursor = getFavoriteCursor(db);

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                    this,
                    android.R.layout.simple_expandable_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);

            ListView favListView = findViewById(R.id.fav_list);
            favListView.setAdapter(adapter);
            favListView.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent = new Intent(this, Chap7DrinkActivity.class);
                intent.putExtra(Chap7DrinkActivity.DRINK_ID, (int) id);
                startActivity(intent);
            });

        } catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable!", Toast.LENGTH_SHORT).show();
        }
    }

    private Cursor getFavoriteCursor(SQLiteDatabase db) {
        return db.query(
                "DRINK",
                new String[]{"_id", "NAME"},
                "FAVORITE = 1",
                null, null, null, null);
    }
}