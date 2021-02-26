package com.kai.readev.chap7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kai.readev.R;
import com.kai.readev.chap15.StarbuzzDatabaseHelper;

public class Chap7DrinkActivity extends AppCompatActivity {

    public static final String DRINK_ID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        Intent intent = getIntent();
        int id = intent.getIntExtra(DRINK_ID, 0);


        Drink drink = fetchDrinkFromDB(id);
        if (drink == null) return;

        setTitle(drink.getName());

        ImageView drinkImage = findViewById(R.id.drink_image);
        drinkImage.setImageResource(drink.getImageResId());
        drinkImage.setContentDescription(drink.getName());

        TextView drinkName = findViewById(R.id.drink_name);
        drinkName.setText(drink.getName());

        TextView drinkDescription = findViewById(R.id.drink_description);
        drinkDescription.setText(drink.getDescription());
    }

    @Nullable
    Drink fetchDrinkFromDB(int id) {
        Drink drink = null;
        SQLiteOpenHelper databaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();

            Cursor cursor = db.query(
                    "DRINK",
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[]{Integer.toString(id)},
                    null,
                    null,
                    null);
            if (cursor.moveToFirst()) {
                drink = new Drink(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getInt(2));
                cursor.close();
                db.close();
            }
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable!", Toast.LENGTH_SHORT).show();
        }
        return drink;
    }
}