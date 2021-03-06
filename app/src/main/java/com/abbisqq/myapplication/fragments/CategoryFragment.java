package com.abbisqq.myapplication.fragments;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abbisqq.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.abbisqq.myapplication.activities.FishListContainer;
import com.abbisqq.myapplication.adapters.CategoryRecViewAdapter;
import com.abbisqq.myapplication.data.FishContract;
import com.abbisqq.myapplication.data.FishFamiliesData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements CategoryRecViewAdapter.ItemClickCallBack {

    RecyclerView recyclerView;
    CategoryRecViewAdapter adapter;
    private ArrayList listData;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.category_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        //resets the elevation
        changeElevation(0);

        listData = (ArrayList) FishFamiliesData.getListData();
        adapter = new CategoryRecViewAdapter(listData, getActivity());
        adapter.setItemClickCallBack(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_DOWN) {
                    changeElevation(0);
                    return false;
                } else {
                    changeElevation(15);
                    return false;

                }

            }
        });


        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onItemClick(int p) {
        Intent intent = new Intent(getContext(), FishListContainer.class);
        intent.putExtra("position",p);
        startActivity(intent);
    }




    void changeElevation(int elev) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setElevation(elev);
        }

    }
}