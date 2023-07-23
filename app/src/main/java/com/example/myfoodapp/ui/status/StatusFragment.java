package com.example.myfoodapp.ui.status;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myfoodapp.R;
import com.example.myfoodapp.SQLite.DailyHelper;
import com.example.myfoodapp.adapters.DetailCartAdapter;
import com.example.myfoodapp.models.DetailCartModel;

import java.util.ArrayList;


public class StatusFragment extends Fragment {

    Button btnXacNhan, btnHuy;

    RecyclerView recyclerView;

    ArrayList<DetailCartModel> arrayList;

    DetailCartAdapter cartAdapter;

    public StatusFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_status, container, false);
        addControls(view);
        arrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DailyHelper helper = new DailyHelper(getContext(), "FoodApp.sqlite", null, 1);

        Cursor data = helper.GetData("SELECT * FROM ChiTietDonHang WHERE TrangThai = 0");
        while (data.moveToNext()){
            int idDonHang = data.getInt(0);
            String tenthucan = data.getString(1);
            String hoten = data.getString(2);
            String sdt = data.getString(3);
            String soban = data.getString(4);
            float tongtien = data.getFloat(5);
            String ngaydat = data.getString(6);
            String phuongthuc = data.getString(7);
            String email_user = data.getString(8);
            int trangthai = data.getInt(9);
            arrayList.add(new DetailCartModel(idDonHang, tenthucan, hoten, sdt, soban, tongtien, ngaydat, phuongthuc, email_user, trangthai));

        }


        cartAdapter = new DetailCartAdapter(getContext(), arrayList);
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
        return  view;
    }

    private void addControls(View view) {
        recyclerView = view.findViewById(R.id.status_rec);
    }

}