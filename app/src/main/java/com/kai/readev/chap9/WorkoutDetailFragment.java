package com.kai.readev.chap9;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kai.readev.R;

public class WorkoutDetailFragment extends Fragment {
    private int id;

    public void setWorkoutId(int id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();
        if (view == null) return;
        TextView titleView = view.findViewById(R.id.workout_title);
        titleView.setText(WorkoutHandler.workouts[id].getTitle());
        TextView descriptionView = view.findViewById(R.id.workout_description);
        descriptionView.setText(WorkoutHandler.workouts[id].getDescription());

    }
}