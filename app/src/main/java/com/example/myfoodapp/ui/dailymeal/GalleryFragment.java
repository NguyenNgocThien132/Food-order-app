package com.example.myfoodapp.ui.dailymeal;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfoodapp.R;
import com.example.myfoodapp.SQLite.DailyHelper;
import com.example.myfoodapp.adapters.DailyMealAdapter;
import com.example.myfoodapp.models.DailyMealModel;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<DailyMealModel> dailyMealModels;
    DailyMealAdapter dailyMealAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container,false);

        dailyMealModels = new ArrayList<>();

        recyclerView = root.findViewById(R.id.daily_meal_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DailyHelper helper = new DailyHelper(getContext(), "FoodApp.sqlite", null, 1);

        Cursor loaiThucAnData = helper.GetData("SELECT * FROM LoaiThucAn");
        dailyMealModels.clear();
        while (loaiThucAnData.moveToNext()){
            String id_loai = loaiThucAnData.getString(0);
            int image_loai = loaiThucAnData.getInt(1);
            String name = loaiThucAnData.getString(2);
            String discount = loaiThucAnData.getString(3);
            String description = loaiThucAnData.getString(4);
            dailyMealModels.add(new DailyMealModel(id_loai, image_loai, name, discount, description));
        }

        dailyMealAdapter = new DailyMealAdapter(getContext(), dailyMealModels);
        recyclerView.setAdapter(dailyMealAdapter);
        dailyMealAdapter.notifyDataSetChanged();
        return root;

    }

}