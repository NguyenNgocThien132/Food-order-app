package com.example.myfoodapp.ui.history;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myfoodapp.MainActivity;
import com.example.myfoodapp.R;
import com.example.myfoodapp.SQLite.DailyHelper;
import com.example.myfoodapp.activitis.DetailDailyMealActivity;
import com.example.myfoodapp.adapters.DetailCartAdapter;
import com.example.myfoodapp.models.DetailCartModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class HistoryFragment extends Fragment {

    ArrayList<DetailCartModel> arrayList;
    RecyclerView recyclerView;
    DetailCartAdapter cartAdapter;

//    TextView cthoten, ctsdt, ctsoban, ctthucdon, ctngaydat, cttongtien, ctthanhtoan;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.history_rec);
        DailyHelper helper = new DailyHelper(getContext(), "FoodApp.sqlite", null, 1);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayList = new ArrayList<>();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null){
            String email = user.getEmail();
            Cursor dataDetailCart = helper.GetData("SELECT * FROM ChiTietDonHang WHERE Email = '" + email + "'");
            arrayList.clear();
            while (dataDetailCart.moveToNext()) {
                int idDonHang = dataDetailCart.getInt(0);
                String tenthucan = dataDetailCart.getString(1);
                String hoten = dataDetailCart.getString(2);
                String sdt = dataDetailCart.getString(3);
                String soban = dataDetailCart.getString(4);
                float tongtien = dataDetailCart.getFloat(5);
                String ngaydat = dataDetailCart.getString(6);
                String phuongthuc = dataDetailCart.getString(7);
                String email_user = dataDetailCart.getString(8);
                int trangthai = dataDetailCart.getInt(9);
                arrayList.add(new DetailCartModel(idDonHang, tenthucan, hoten, sdt, soban, tongtien, ngaydat, phuongthuc, email_user, trangthai));
            }
        }else{
            String email = null;
        }





        cartAdapter = new DetailCartAdapter(getContext(), arrayList);
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        return view;
    }
}