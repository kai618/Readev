package com.kai.readev.chap12;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kai.readev.R;
import com.kai.readev.chap13.CaptionedImagesAdapter;
import com.kai.readev.chap13.Pizza;
import com.kai.readev.chap13.PizzaDetailActivity;
import com.kai.readev.chap8.OrderActivity;

public class PizzaFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.fragment_pizza, container, false);

        String[] captions = new String[15];
        int[] imageIds = new int[15];

        for (int i = 0; i < 15; i++) {
            captions[i] = Pizza.pizzas[i % 2].getName();
            imageIds[i] = Pizza.pizzas[i % 2].getImageResourceId();
        }

        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(captions, imageIds);
        adapter.setOnTileClickListener(position -> {
            Intent intent = new Intent(getContext(), PizzaDetailActivity.class);
            intent.putExtra(PizzaDetailActivity.PIZZA_ID, position % 2);
            startActivity(intent);
        });
        rv.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rv.setLayoutManager(layoutManager);
        return rv;
    }
}