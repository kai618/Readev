package com.kai.readev.chap9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.kai.readev.R;

public class WorkoutListActivity extends AppCompatActivity implements WorkoutListFragment.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);
    }

    @Override
    public void onItemClick(int id) {
        Intent intent = new Intent(this, WorkoutDetailActivity.class);
        intent.putExtra(WorkoutDetailActivity.WORKOUT_ID, id);
        startActivity(intent);
    }
}