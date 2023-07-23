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
import com.example.myfoodapp.adapters.FeaturedVerAdapter2;
import com.example.myfoodapp.models.FeaturedVerModel2;

import java.util.ArrayList;

public class ThirdFragment extends Fragment {

    ArrayList<FeaturedVerModel2> featuredVerModels2;
    RecyclerView recyclerView;
    FeaturedVerAdapter2 featuredVerAdapter2;
    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        DailyHelper helper = new DailyHelper(getContext(), "FoodApp.sqlite", null, 1);
        recyclerView = view.findViewById(R.id.rv_list3);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        featuredVerModels2 = new ArrayList<>();

        Cursor featuredVerData = helper.GetData("SELECT * FROM ThucAn ORDER BY ID_ThucAn DESC LIMIT 3");
        featuredVerModels2.clear();
        while (featuredVerData.moveToNext()){
            int image = featuredVerData.getInt(1);
            String name = featuredVerData.getString(2);
            featuredVerModels2.add(new FeaturedVerModel2(image, name));
        }

        featuredVerAdapter2 = new FeaturedVerAdapter2(featuredVerModels2);
        recyclerView.setAdapter(featuredVerAdapter2);
        return view;
    }
}