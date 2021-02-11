package com.kai.readev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class BeerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
    }

    public void onClickFindBeer(View view) {
        Spinner colorSpinner = (Spinner) findViewById(R.id.color_spinner);
        TextView brandView = (TextView) findViewById(R.id.brands);

        String color = colorSpinner.getSelectedItem().toString();
        BeerExpert expert = new BeerExpert();
        List<String> brands = expert.getBrands(color);

        StringBuilder builder = new StringBuilder();
        for (String brand : brands) {
            builder.append(brand).append('\n');
        }

        brandView.setText(builder);
    }
}