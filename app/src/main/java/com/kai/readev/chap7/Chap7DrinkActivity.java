package com.kai.readev.chap7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kai.readev.R;
import com.kai.readev.chap15.StarbuzzDatabaseHelper;

import java.lang.ref.WeakReference;

public class Chap7DrinkActivity extends AppCompatActivity {

    public static final String DRINK_ID = "drinkId";

    private static class UpdateDrinkTask extends AsyncTask<Integer, Void, Boolean> {
        private ContentValues values;
        private final WeakReference<Activity> activityWeakRef;

        UpdateDrinkTask(Activity activity) {
            this.activityWeakRef = new WeakReference<>(activity);
        }

        @Override
        protected void onPreExecute() {
            CheckBox checkBox = activityWeakRef.get().findViewById(R.id.checkbox);
            values = new ContentValues();
            values.put("FAVORITE", checkBox.isChecked());
        }

        @Override
        protected Boolean doInBackground(Integer... drinks) {
            int drinkId = drinks[0];
            SQLiteOpenHelper helper = new StarbuzzDatabaseHelper(activityWeakRef.get());
            try {
                SQLiteDatabase db = helper.getWritableDatabase();
                db.update("DRINK", values, "_id = ?", new String[]{Integer.toString(drinkId)});
                db.close();
                return true;
            } catch (SQLiteException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (!success) {
                Toast.makeText(activityWeakRef.get(), "Database unavailable!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkId = getIntent().getExtras().getInt(DRINK_ID);
        Drink drink = fetchDrinkFromDB(drinkId);
        if (drink == null) return;

        setTitle(drink.getName());

        ImageView drinkImage = findViewById(R.id.drink_image);
        drinkImage.setImageResource(drink.getImageResId());
        drinkImage.setContentDescription(drink.getName());

        TextView drinkName = findViewById(R.id.drink_name);
        drinkName.setText(drink.getName());

        TextView drinkDescription = findViewById(R.id.drink_description);
        drinkDescription.setText(drink.getDescription());

        CheckBox checkBox = findViewById(R.id.checkbox);
        checkBox.setChecked(drink.getFavorite());
    }

    @Nullable
    Drink fetchDrinkFromDB(int id) {
        Drink drink = null;
        SQLiteOpenHelper databaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();

            Cursor cursor = db.query(
                    "DRINK",
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"},
                    "_id = ?",
                    new String[]{Integer.toString(id)},
                    null,
                    null,
                    null);

            if (cursor.moveToFirst()) {
                drink = new Drink(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3) == 1);
                cursor.close();
                db.close();
            }
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable!", Toast.LENGTH_SHORT).show();
        }
        return drink;
    }

    public void onCheckFavorite(View view) {
        int drinkId = getIntent().getExtras().getInt(DRINK_ID);
        new UpdateDrinkTask(this).execute(drinkId);
    }
}