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
import com.example.myfoodapp.adapters.FeaturedAdapter;
import com.example.myfoodapp.adapters.FeaturedVerAdapter;
import com.example.myfoodapp.models.FeaturedModel;
import com.example.myfoodapp.models.FeaturedVerModel;

import java.util.ArrayList;


public class FirstFragment extends Fragment {
    //==============================Featured Hor RecyclerView
    ArrayList<FeaturedModel> featuredModels;
    RecyclerView recyclerView;
    FeaturedAdapter featuredAdapter;

    //===============================Featured Ver RecyclerView
    ArrayList<FeaturedVerModel> featuredVerModels;
    RecyclerView recyclerView2;
    FeaturedVerAdapter featuredVerAdapter;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        //=============================================================================================================================
        //===============================Featured Ver RecyclerView
        recyclerView = view.findViewById(R.id.featured_hor_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        featuredModels = new ArrayList<>();

        DailyHelper helper = new DailyHelper(getContext(), "FoodApp.sqlite", null, 1);

        Cursor featuredData = helper.GetData("SELECT * FROM ThucAnNoiBat");
        while (featuredData.moveToNext()){
            String id_featured = featuredData.getString(0);
            int image = featuredData.getInt(1);
            String name = featuredData.getString(2);
            String desc = featuredData.getString(3);
            featuredModels.add(new FeaturedModel(id_featured, image, name, desc));
        }

        featuredAdapter = new FeaturedAdapter(featuredModels);
        recyclerView.setAdapter(featuredAdapter);
        //=============================================================================================================================
        //===============================Featured Ver RecyclerView




        recyclerView2 = view.findViewById(R.id.featured_ver_rec);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));

        featuredVerModels = new ArrayList<>();

        Cursor featuredVerData = helper.GetData("SELECT * FROM ThucAn");
        featuredVerModels.clear();
        while (featuredVerData.moveToNext()){
            String id_thucan = featuredVerData.getString(0);
            int image = featuredVerData.getInt(1);
            String name = featuredVerData.getString(2);
            String desc = featuredVerData.getString(3);
            String rating = featuredVerData.getString(4);
            String timing = featuredVerData.getString(5);
            featuredVerModels.add(new FeaturedVerModel(id_thucan, image, name, desc,rating, timing));
        }

        featuredVerAdapter = new FeaturedVerAdapter(featuredVerModels);
        recyclerView2.setAdapter(featuredVerAdapter);
        return view;
    }
}