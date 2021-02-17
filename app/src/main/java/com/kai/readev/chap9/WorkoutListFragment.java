package com.kai.readev.chap9;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class WorkoutListFragment extends ListFragment {

    interface OnItemClickListener {
        void onItemClick(int id);
    }

    private OnItemClickListener listener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (OnItemClickListener) context;
        Log.d("frag", "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("frag", "onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayAdapter<Workout> adapter =
                new ArrayAdapter<>(
                        inflater.getContext(),
                        android.R.layout.simple_list_item_1,
                        WorkoutHandler.workouts
                );
        setListAdapter(adapter);
        Log.d("frag", "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("frag", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("frag", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("frag", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("frag", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("frag", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("frag", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("frag", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("frag", "onDetach");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("frag", "onSaveInstanceState");
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (listener != null) listener.onItemClick((int) id);
    }
}