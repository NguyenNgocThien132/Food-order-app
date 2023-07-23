package com.example.myfoodapp.fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfoodapp.R;
import com.example.myfoodapp.SQLite.DailyHelper;
import com.example.myfoodapp.adapters.FeaturedVerAdapter1;
import com.example.myfoodapp.models.FeaturedVerModel1;

import java.util.ArrayList;


public class SecondFragment extends Fragment {

    ArrayList<FeaturedVerModel1> featuredVerModels1;
    RecyclerView recyclerView2;
    FeaturedVerAdapter1 featuredVerAdapter1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        DailyHelper helper = new DailyHelper(getContext(), "FoodApp.sqlite", null, 1);
        recyclerView2 = view.findViewById(R.id.rv_view);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));

        featuredVerModels1 = new ArrayList<>();

        Cursor featuredVerData = helper.GetData("SELECT * FROM ThucAn LIMIT 10");
        featuredVerModels1.clear();
        while (featuredVerData.moveToNext()){
            int image = featuredVerData.getInt(1);
            String name = featuredVerData.getString(2);
            featuredVerModels1.add(new FeaturedVerModel1(image, name));
        }

        featuredVerAdapter1 = new FeaturedVerAdapter1(featuredVerModels1);
        recyclerView2.setAdapter(featuredVerAdapter1);
        return view;
    }
}