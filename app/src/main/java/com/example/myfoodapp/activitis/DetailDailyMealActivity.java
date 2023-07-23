package com.example.myfoodapp.activitis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.myfoodapp.R;
import com.example.myfoodapp.SQLite.DailyHelper;
import com.example.myfoodapp.adapters.DetailedDailyAdapter;
import com.example.myfoodapp.models.DetailedDailyModel;
import com.example.myfoodapp.ui.MyCartFragment;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DetailDailyMealActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    //    ListView listView;
    ArrayList<DetailedDailyModel> detailedDailyModels;
    DetailedDailyAdapter dailyAdapter;
    //    DetailBaseAdapter dailyAdapter;
    ImageView imageView;
    CollapsingToolbarLayout toolbarLayout;
    FloatingActionButton btnGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_daily_meal);


        addControls();
        String id_loaithucan = getIntent().getStringExtra("id_loaithucan");
        String name = getIntent().getStringExtra("name");
        int image = getIntent().getIntExtra("image", 0);

        recyclerView.setLayoutManager(new LinearLayoutManager(DetailDailyMealActivity.this));
        detailedDailyModels = new ArrayList<>();
        dailyAdapter = new DetailedDailyAdapter(this, detailedDailyModels);
        recyclerView.setAdapter(dailyAdapter);

        //tao co so du lieu
        DailyHelper helper = new DailyHelper(DetailDailyMealActivity.this, "FoodApp.sqlite", null, 1);

//        helper.QueryData("DROP TABLE ThucAnNoiBat");
//        helper.QueryData("CREATE TABLE IF NOT EXISTS ThucAnNoiBat (ID_Featured VARCHAR(10) PRIMARY KEY, Image_Featured INTEGER, Name_Featured NVARCHAR(200), Description_Featured NVARCHAR)");
//
//        try {
////        helper.QueryData("DELETE FROM ThucAnNoiBat");
//            helper.QueryData("INSERT INTO ThucAnNoiBat VALUES ('F01', " + R.drawable.fav1 + ", 'Breakfast', 'Description 1')");
//            helper.QueryData("INSERT INTO ThucAnNoiBat VALUES ('F02', " + R.drawable.fav2 + ", 'Lunch', 'Description 2')");
//            helper.QueryData("INSERT INTO ThucAnNoiBat VALUES ('F03', " + R.drawable.fav3 + ", 'Dinner', 'Description 3')");
//            helper.QueryData("INSERT INTO ThucAnNoiBat VALUES ('F04', " + R.drawable.fav2 + ", 'Sweet', 'Description 4')");
//            helper.QueryData("INSERT INTO ThucAnNoiBat VALUES ('F05', " + R.drawable.fav1 + ", 'Cofee', 'Description 5')");
//        } catch (Exception e) {
//            Log.e("err", "Đã có dữ liệu trong bảng ThucAnNoiBat");
//        }
        //tao bang
//        helper.QueryData("DROP TABLE ThucAn");
        helper.QueryData("CREATE TABLE IF NOT EXISTS ThucAn (ID_ThucAn VARCHAR(10) PRIMARY KEY,Image INTEGER, Name NVARCHAR(200), DESCRIPTION NVARCHAR(200), RATING VARCHAR(100), PRICE VARCHAR(100), TIMING VARCHAR(100), ID_LoaiThucAn VARCHAR(4) REFERENCES LoaiThucAn(ID_LoaiThucAn), ID_Featured VARCHAR(10) REFERENCES ThucAnNoiBat(ID_Featured))");

        //them data Thức ăn

        try {
            if (id_loaithucan != null && id_loaithucan.equalsIgnoreCase("L001")) {
//            helper.QueryData("DELETE FROM ThucAn WHERE ID_LoaiThucAn = 'L001'");
//                helper.QueryData("INSERT INTO ThucAn VALUES ('BF01', " + R.drawable.fav1 + ", 'Breakfast', 'Description', '4.4', '40', '10am to 9pm', 'L001', 'F01')");
//                helper.QueryData("INSERT INTO ThucAn VALUES ('BF02', " + R.drawable.fav2 + ", 'Breakfast', 'Description', '4.4', '40', '10am to 9pm', 'L001', 'F01')");
//                helper.QueryData("INSERT INTO ThucAn VALUES ('BF03', " + R.drawable.fav3 + ", 'Breakfast', 'Description', '4.4', '40', '10am to 9pm', 'L001', 'F01')");
//
                showData(helper, "L001");
                imageView.setImageResource(image);
                toolbarLayout.setTitle(name);
            }
        } catch (Exception e) {
            Log.e("err", "Đã có dữ liệu trong bảng ThucAn");
        }

        try {
            if (id_loaithucan != null && id_loaithucan.equalsIgnoreCase("L002")) {
//            helper.QueryData("DELETE FROM ThucAn WHERE ID_LoaiThucAn = 'L002'");
//                helper.QueryData("INSERT INTO ThucAn VALUES ('L001', " + R.drawable.ver1 + ", 'Lunch', 'Description', '4.4', '40', '10am to 9pm', 'L002', 'F02')");
//                helper.QueryData("INSERT INTO ThucAn VALUES ('L002', " + R.drawable.ver2 + ", 'Lunch', 'Description', '4.4', '40', '10am to 9pm', 'L002', 'F02')");
//                helper.QueryData("INSERT INTO ThucAn VALUES ('L003', " + R.drawable.ver3 + ", 'Lunch', 'Description', '4.4', '40', '10am to 9pm', 'L002', 'F02')");
//                helper.QueryData("INSERT INTO ThucAn VALUES ('L004', " + R.drawable.ver3 + ", 'Lunch', 'Description', '4.4', '40', '10am to 9pm', 'L002', 'F02')");
//
                showData(helper, "L002");
                imageView.setImageResource(image);
                toolbarLayout.setTitle(name);
            }
        } catch (Exception e) {
            Log.e("err", "Đã có dữ liệu trong bảng ThucAn");
        }
        try {
            if (id_loaithucan != null && id_loaithucan.equalsIgnoreCase("L003")) {
//            helper.QueryData("DELETE FROM ThucAn WHERE ID_LoaiThucAn = 'L003'");
//                helper.QueryData("INSERT INTO ThucAn VALUES ('D001', " + R.drawable.fries1 + ", 'Dinner', 'Description', '4.4', '40', '10am to 9pm', 'L003', 'F03')");
//                helper.QueryData("INSERT INTO ThucAn VALUES ('D002', " + R.drawable.fries2 + ", 'Dinner', 'Description', '4.4', '40', '10am to 9pm', 'L003', 'F03')");
//                helper.QueryData("INSERT INTO ThucAn VALUES ('D003', " + R.drawable.fries3 + ", 'Dinner', 'Description', '4.4', '40', '10am to 9pm', 'L003', 'F03')");
//                helper.QueryData("INSERT INTO ThucAn VALUES ('D004', " + R.drawable.fries4 + ", 'Dinner', 'Description', '4.4', '40', '10am to 9pm', 'L003', 'F03')");
//
                showData(helper, "L003");
                imageView.setImageResource(image);
                toolbarLayout.setTitle(name);
            }
        } catch (Exception e) {
            Log.e("err", "Đã có dữ liệu trong bảng ThucAn");
        }

        try {
            if (id_loaithucan != null && id_loaithucan.equalsIgnoreCase("L004")) {
//            helper.QueryData("DELETE FROM ThucAn WHERE ID_LoaiThucAn = 'L004'");
//                helper.QueryData("INSERT INTO ThucAn VALUES ('S001', " + R.drawable.s1 + ", 'Sweet', 'Description', '4.4', '40', '10am to 9pm', 'L004', 'F04')");
//                helper.QueryData("INSERT INTO ThucAn VALUES ('S002', " + R.drawable.s2 + ", 'Sweet', 'Description', '4.4', '40', '10am to 9pm', 'L004', 'F04')");
//                helper.QueryData("INSERT INTO ThucAn VALUES ('S003', " + R.drawable.s3 + ", 'Sweet', 'Description', '4.4', '40', '10am to 9pm', 'L004', 'F04')");
//                helper.QueryData("INSERT INTO ThucAn VALUES ('S004', " + R.drawable.s4 + ", 'Sweet', 'Description', '4.4', '40', '10am to 9pm', 'L004', 'F04')");
//
                showData(helper, "L004");
                imageView.setImageResource(image);
                toolbarLayout.setTitle(name);
            }
        } catch (Exception e) {
            Log.e("err", "Đã có dữ liệu trong bảng ThucAn");
        }

        try {
            if (id_loaithucan != null && id_loaithucan.equalsIgnoreCase("L005")) {
                showData(helper, "L005");
                imageView.setImageResource(image);
                toolbarLayout.setTitle(name);
            }
        } catch (Exception e) {
            Log.e("err", "Đã có dữ liệu trong bảng ThucAn");
        }

        try {
            if (id_loaithucan != null && id_loaithucan.equalsIgnoreCase("L006")) {
                showData(helper, "L006");
                imageView.setImageResource(image);
                toolbarLayout.setTitle(name);
            }
        } catch (Exception e) {
            Log.e("err", "Đã có dữ liệu trong bảng ThucAn");
        }

        try {
            if (id_loaithucan != null && id_loaithucan.equalsIgnoreCase("L007")) {
                showData(helper, "L007");
                imageView.setImageResource(image);
                toolbarLayout.setTitle(name);
            }
        } catch (Exception e) {
            Log.e("err", "Đã có dữ liệu trong bảng ThucAn");
        }

        try {
            if (id_loaithucan != null && id_loaithucan.equalsIgnoreCase("L008")) {
                showData(helper, "L008");
                imageView.setImageResource(image);
                toolbarLayout.setTitle(name);
            }
        } catch (Exception e) {
            Log.e("err", "Đã có dữ liệu trong bảng ThucAn");
        }

        try {
            if (id_loaithucan != null && id_loaithucan.equalsIgnoreCase("L009")) {
                showData(helper, "L009");
                imageView.setImageResource(image);
                toolbarLayout.setTitle(name);
            }
        } catch (Exception e) {
            Log.e("err", "Đã có dữ liệu trong bảng ThucAn");
        }

        try {
            if (id_loaithucan != null && id_loaithucan.equalsIgnoreCase("L010")) {
                showData(helper, "L010");
                imageView.setImageResource(image);
                toolbarLayout.setTitle(name);
            }
        } catch (Exception e) {
            Log.e("err", "Đã có dữ liệu trong bảng ThucAn");
        }

        btnGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyCartFragment myCartFragment = new MyCartFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayoutContainer, myCartFragment);
                fragmentTransaction.commit();

                view.setVisibility(View.GONE);

            }
        });
        //=======================================================end=========================================================================
    }

    private void showData(DailyHelper helper, String id_loai) {
        Cursor thucAnData = helper.GetData("SELECT * FROM ThucAn WHERE ID_LoaiThucAn = '" + id_loai + "'");
        detailedDailyModels.clear();
        while (thucAnData.moveToNext()) {
            String id_thucan = thucAnData.getString(0);
            int image = thucAnData.getInt(1);
            String name = thucAnData.getString(2);
            String description = thucAnData.getString(3);
            String rating = thucAnData.getString(4);
            float price = thucAnData.getFloat(5);
            String timing = thucAnData.getString(6);
            String idLoaiThucAn = thucAnData.getString(7);
            String idFeatured = thucAnData.getString(8);
            detailedDailyModels.add(new DetailedDailyModel(id_thucan, image, name, description, rating, price, timing, idLoaiThucAn, idFeatured));
            dailyAdapter.notifyDataSetChanged();
        }
    }

    private void addControls() {
        recyclerView = findViewById(R.id.detailed_rec);
        imageView = findViewById(R.id.detailed_img);
        toolbarLayout = findViewById(R.id.Collapsing);
        btnGioHang = findViewById(R.id.floatingButtonGioHang);
    }
}