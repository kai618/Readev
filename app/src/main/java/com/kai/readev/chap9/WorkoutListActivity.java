package com.kai.readev.chap9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.kai.readev.R;

public class WorkoutListActivity extends AppCompatActivity implements WorkoutListFragment.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);
        Log.i("life", "act-onCreate");
    }

    @Override
    public void onItemClick(int id) {
        Intent intent = new Intent(this, WorkoutDetailActivity.class);
        intent.putExtra(WorkoutDetailActivity.WORKOUT_ID, id);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("life", "act-onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("life", "act-onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("life", "act-onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("life", "act-onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("life", "act-onStop");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("life", "act-onSaveInstanceState");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("life", "act-onDestroy");
    }
}