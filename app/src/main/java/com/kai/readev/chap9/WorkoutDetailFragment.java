package com.kai.readev.chap9;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kai.readev.R;
import com.kai.readev.chap11.StopwatchFragment;

public class WorkoutDetailFragment extends Fragment {
    private int id;

    private static final String ID = "id";

    public WorkoutDetailFragment() {
    }

    public WorkoutDetailFragment(int id) {
        this.id = id;
    }


    public void setWorkoutId(int id) {
        this.id = id;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            id = savedInstanceState.getInt(ID);
        } else {
            StopwatchFragment stopwatchFrag = new StopwatchFragment();
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            ft.replace(R.id.stopwatch_container, stopwatchFrag);
            ft.commit();
        }
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ID, id);
    }
}