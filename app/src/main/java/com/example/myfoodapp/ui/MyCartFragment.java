package com.example.myfoodapp.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myfoodapp.R;
import com.example.myfoodapp.SQLite.DailyHelper;
import com.example.myfoodapp.activitis.DetailCartActivity;
import com.example.myfoodapp.adapters.CartAdapter;
import com.example.myfoodapp.models.CartModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class MyCartFragment extends Fragment {

    ArrayList<CartModel> arrayList;
    CartAdapter cartAdapter;
    RecyclerView recyclerView;

    TextView tong_tien;
    Button dathang;

    public MyCartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);
        addControls(view);
        recyclerView = view.findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayList = new ArrayList<>();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            String email = user.getEmail();
            DailyHelper helper = new DailyHelper(getContext(), "FoodApp.sqlite", null, 1);
            arrayList.clear();
            try {

                Cursor dataGioHang = helper.GetData("SELECT ID_GioHang, Image, Name, PRICE, SoLuong FROM GioHang, ThucAn WHERE GioHang.ID_ThucAn = ThucAn.ID_ThucAn AND Email = '"+email+"'");
                while (dataGioHang.moveToNext()) {
                    int ID_GioHang = dataGioHang.getInt(0);
                    int image = dataGioHang.getInt(1);
                    String name = dataGioHang.getString(2);
                    float price = dataGioHang.getFloat(3);
                    int soluong = dataGioHang.getInt(4);
                    arrayList.add(new CartModel(ID_GioHang, image, name, price, soluong));
                }
            } catch (Exception e) {
                Log.e("Err", "Chưa có order trong giỏ hàng");
            }
        }else{
            String email = null;
        }


        float tong = 0;
        if (arrayList != null) {

            for (CartModel model : arrayList) {
                tong += model.getGiatien();
            }
            tong_tien.setText(tong+"");
        } else {
            tong_tien.setText("0.");
        }

        cartAdapter = new CartAdapter(getContext(), arrayList, tong_tien);
        recyclerView.setAdapter(cartAdapter);

        dathang.setOnClickListener(new View.OnClickListener() {
            String thucdon = "";
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DetailCartActivity.class);
                intent.putExtra("giatien", tong_tien.getText());
                for (CartModel model: arrayList){
                    thucdon+= "- "+model.getName()+"("+model.getPrice()+"00 VND) - Số lượng:"+model.getSoluong()+"\n";
                }
                intent.putExtra("thucdon", thucdon);
                startActivity(intent);
            }
        });

        return view;
    }

    private void addControls(View view) {

        tong_tien = view.findViewById(R.id.tvTongTien);
        dathang = view.findViewById(R.id.button_dathang);
    }
}