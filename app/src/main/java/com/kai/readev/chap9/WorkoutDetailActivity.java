package com.kai.readev.chap9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.kai.readev.R;

public class WorkoutDetailActivity extends AppCompatActivity {

    public static final String WORKOUT_ID = "workoutId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        Intent intent = getIntent();
        int id = intent.getIntExtra(WORKOUT_ID, 0);

        WorkoutDetailFragment frag = (WorkoutDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_workout_detail);
        frag.setWorkoutId(id);

    }
}