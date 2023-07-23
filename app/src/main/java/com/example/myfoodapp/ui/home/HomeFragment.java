package com.example.myfoodapp.ui.home;

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
import com.example.myfoodapp.adapters.HomeAdapter;
import com.example.myfoodapp.adapters.HomeVerAdapter;
import com.example.myfoodapp.adapters.UpdateVertical;
import com.example.myfoodapp.models.HomeModel;
import com.example.myfoodapp.models.HomeVerModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements UpdateVertical {


    RecyclerView home_rec, home_ver_rec;
    ArrayList<HomeModel> homeModelList;

    HomeAdapter homeAdapter;

    /////////////////////////////////vertical//////////////////////////////
    ArrayList<HomeVerModel> homeVerModels;
    HomeVerAdapter homeVerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        home_rec = root.findViewById(R.id.home_item);
        home_ver_rec = root.findViewById(R.id.home_ver);
        //-----------------------------------------------------------------------------------------------------------------------------
        //--------------------------------SQLite
        DailyHelper helper = new DailyHelper(getContext(), "FoodApp.sqlite", null, 1);
        helper.QueryData("CREATE TABLE IF NOT EXISTS LoaiThucAn (ID_LoaiThucAn VARCHAR(4) Primary Key, Image_Loai INTEGER, Name NVARCHAR(200), DISCOUNT VARCHAR(100), DESCRIPTION NVARCHAR(200))");

        try {
//            helper.QueryData("DELETE FROM ThucAn");
            helper.QueryData("DELETE FROM LoaiThucAn");
            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L001', " + R.drawable.breakfast + ", 'Breakfast', '30% OFF', 'Description Description Description')");
            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L002', " + R.drawable.lunch + ", 'Lunch', '20% OFF', 'Description Description Description')");
            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L003', " + R.drawable.dinner + ", 'Dinner', '50% OFF', 'Description Description Description')");
            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L004', " + R.drawable.sweets + ", 'Sweets', '39% OFF', 'Description Description Description')");
            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L005', " + R.drawable.coffe + ", 'Coffe', '20% OFF', 'Description Description Description')");

            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L006', " + R.drawable.pizza3 + ", 'pizza', '20% OFF', 'Description Description Description')");
            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L007', " + R.drawable.burger2 + ", 'hamburger', '20% OFF', 'Description Description Description')");
            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L008', " + R.drawable.fries1 + ", 'fries', '20% OFF', 'Description Description Description')");
            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L009', " + R.drawable.sandwich3 + ", 'sandwich', '20% OFF', 'Description Description Description')");
            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L010', " + R.drawable.icecream1 + ", 'Icecream', '20% OFF', 'Description Description Description')");
        } catch (Exception e) {
            Log.e("Lỗi", "Đã có dữ liệu trong bảng LoaiThucAn");
        }
        //--------------Thuc An Noi Bat
        helper.QueryData("CREATE TABLE IF NOT EXISTS ThucAnNoiBat (ID_Featured VARCHAR(10) PRIMARY KEY, Image_Featured INTEGER, Name_Featured NVARCHAR(200), Description_Featured NVARCHAR)");

        try {
            helper.QueryData("DELETE FROM ThucAnNoiBat");
            helper.QueryData("INSERT INTO ThucAnNoiBat VALUES ('F01', " + R.drawable.fav1 + ", 'Breakfast', 'Description 1')");
            helper.QueryData("INSERT INTO ThucAnNoiBat VALUES ('F02', " + R.drawable.fav2 + ", 'Lunch', 'Description 2')");
            helper.QueryData("INSERT INTO ThucAnNoiBat VALUES ('F03', " + R.drawable.fav3 + ", 'Dinner', 'Description 3')");
            helper.QueryData("INSERT INTO ThucAnNoiBat VALUES ('F04', " + R.drawable.fav2 + ", 'Sweet', 'Description 4')");
            helper.QueryData("INSERT INTO ThucAnNoiBat VALUES ('F05', " + R.drawable.fav1 + ", 'Cofee', 'Description 5')");

        } catch (Exception e) {
            Log.e("err", "Đã có dữ liệu trong bảng ThucAnNoiBat");
        }

        //tao bang Giỏ Hàng

//        helper.QueryData("DROP TABLE ThucAn");
        helper.QueryData("CREATE TABLE IF NOT EXISTS ThucAn (ID_ThucAn VARCHAR(10) PRIMARY KEY,Image INTEGER, Name NVARCHAR(200), DESCRIPTION NVARCHAR(200), RATING VARCHAR(100), PRICE FLOAT, TIMING VARCHAR(100), ID_LoaiThucAn VARCHAR(4) REFERENCES LoaiThucAn(ID_LoaiThucAn), ID_Featured VARCHAR(10) REFERENCES ThucAnNoiBat(ID_Featured))");


        helper.QueryData("DELETE FROM ThucAn");
        helper.QueryData("INSERT INTO ThucAn VALUES ('BF01', " + R.drawable.fav1 + ", 'Breakfast', 'Description', '4.4', 40, '10am to 9pm', 'L001', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('BF02', " + R.drawable.fav2 + ", 'Breakfast', 'Description', '4.4', 40, '10am to 9pm', 'L001', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('BF03', " + R.drawable.fav3 + ", 'Breakfast', 'Description', '4.4', 40, '10am to 9pm', 'L001', 'F01')");

        helper.QueryData("INSERT INTO ThucAn VALUES ('L001', " + R.drawable.ver1 + ", 'Lunch', 'Description', '4.4', 40, '10am to 9pm', 'L002', 'F02')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('L002', " + R.drawable.ver2 + ", 'Lunch', 'Description', '4.4', 40, '10am to 9pm', 'L002', 'F02')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('L003', " + R.drawable.ver3 + ", 'Lunch', 'Description', '4.4', 40, '10am to 9pm', 'L002', 'F02')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('L004', " + R.drawable.ver3 + ", 'Lunch', 'Description', '4.4', 40, '10am to 9pm', 'L002', 'F02')");

        helper.QueryData("INSERT INTO ThucAn VALUES ('D001', " + R.drawable.fries1 + ", 'Dinner', 'Description', '4.4', 40, '10am to 9pm', 'L003', 'F03')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('D002', " + R.drawable.fries2 + ", 'Dinner', 'Description', '4.4', 40, '10am to 9pm', 'L003', 'F03')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('D003', " + R.drawable.fries3 + ", 'Dinner', 'Description', '4.4', 40, '10am to 9pm', 'L003', 'F03')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('D004', " + R.drawable.fries4 + ", 'Dinner', 'Description', '4.4', 40, '10am to 9pm', 'L003', 'F03')");

        helper.QueryData("INSERT INTO ThucAn VALUES ('S001', " + R.drawable.s1 + ", 'Sweet', 'Description', '4.4', 40, '10am to 9pm', 'L004', 'F04')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('S002', " + R.drawable.s2 + ", 'Sweet', 'Description', '4.4', 40, '10am to 9pm', 'L004', 'F04')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('S003', " + R.drawable.s3 + ", 'Sweet', 'Description', '4.4', 40, '10am to 9pm', 'L004', 'F04')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('S004', " + R.drawable.s4 + ", 'Sweet', 'Description', '4.4', 40, '10am to 9pm', 'L004', 'F04')");

        helper.QueryData("INSERT INTO ThucAn VALUES ('C001', " + R.drawable.cf1 + ", 'Cafee', 'Description', '4.4', 40, '10am to 9pm', 'L005', 'F05')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('C002', " + R.drawable.cf2 + ", 'Cafee', 'Description', '4.4', 40, '10am to 9pm', 'L005', 'F05')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('C003', " + R.drawable.cf3 + ", 'Cafee', 'Description', '4.4', 40, '10am to 9pm', 'L005', 'F05')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('C004', " + R.drawable.cf4 + ", 'Cafee', 'Description', '4.4', 40, '10am to 9pm', 'L005', 'F05')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('C005', " + R.drawable.cf5 + ", 'Cafee', 'Description', '4.4', 40, '10am to 9pm', 'L005', 'F05')");

        helper.QueryData("INSERT INTO ThucAn VALUES ('PZ01', " + R.drawable.pizza1 + ", 'pizza', 'Description', '4.4', 40, '10am to 9pm', 'L006', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('PZ02', " + R.drawable.pizza2 + ", 'pizza', 'Description', '4.4', 40, '10am to 9pm', 'L006', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('PZ03', " + R.drawable.pizza3 + ", 'pizza', 'Description', '4.4', 40, '10am to 9pm', 'L006', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('PZ04', " + R.drawable.pizza4 + ", 'pizza', 'Description', '4.4', 40, '10am to 9pm', 'L006', 'F01')");

        helper.QueryData("INSERT INTO ThucAn VALUES ('HB01', " + R.drawable.burger1 + ", 'hamburger', 'Description', '4.4', 40, '10am to 9pm', 'L007', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('HB02', " + R.drawable.burger2 + ", 'hamburger', 'Description', '4.4', 40, '10am to 9pm', 'L007', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('HB03', " + R.drawable.burger4 + ", 'hamburger', 'Description', '4.4', 40, '10am to 9pm', 'L007', 'F01')");

        helper.QueryData("INSERT INTO ThucAn VALUES ('FR01', " + R.drawable.fries1 + ", 'fries', 'Description', '4.4', 40, '10am to 9pm', 'L008', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('FR02', " + R.drawable.fries2 + ", 'fries', 'Description', '4.4', 40, '10am to 9pm', 'L008', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('FR03', " + R.drawable.fries3 + ", 'fries', 'Description', '4.4', 40, '10am to 9pm', 'L008', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('FR04', " + R.drawable.fries4 + ", 'fries', 'Description', '4.4', 40, '10am to 9pm', 'L008', 'F01')");

        helper.QueryData("INSERT INTO ThucAn VALUES ('SW01', " + R.drawable.sandwich1 + ", 'sandwich', 'Description', '4.4', 40, '10am to 9pm', 'L009', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('SW02', " + R.drawable.sandwich2 + ", 'sandwich', 'Description', '4.4', 40, '10am to 9pm', 'L009', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('SW03', " + R.drawable.sandwich3 + ", 'sandwich', 'Description', '4.4', 40, '10am to 9pm', 'L009', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('SW04', " + R.drawable.sandwich4 + ", 'sandwich', 'Description', '4.4', 40, '10am to 9pm', 'L009', 'F01')");

        helper.QueryData("INSERT INTO ThucAn VALUES ('IC01', " + R.drawable.icecream1 + ", 'Icecream', 'Description', '4.4', 40, '10am to 9pm', 'L010', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('IC02', " + R.drawable.icecream2 + ", 'Icecream', 'Description', '4.4', 40, '10am to 9pm', 'L010', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('IC03', " + R.drawable.icecream3 + ", 'Icecream', 'Description', '4.4', 40, '10am to 9pm', 'L010', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('IC04', " + R.drawable.icecream4 + ", 'Icecream', 'Description', '4.4', 40, '10am to 9pm', 'L010', 'F01')");

//        helper.QueryData("DROP TABLE GioHang");
        helper.QueryData("CREATE TABLE IF NOT EXISTS GioHang (ID_GioHang INTEGER PRIMARY KEY AUTOINCREMENT , ID_ThucAn VARCHAR(10) REFERENCES ThucAn(ID_ThucAn), SoLuong INTEGER, Email VARCHAR(200))");

        //bang lich su dat hang
//        helper.QueryData("DROP TABLE ChiTietDonHang");
//        helper.QueryData("DELETE FROM ChiTietDonHang");
        helper.QueryData("CREATE TABLE IF NOT EXISTS ChiTietDonHang (ID_DonHang INTEGER PRIMARY KEY AUTOINCREMENT, TenThucAn NVARCHAR(200), HoTen NVARCHAR(200), SoDienThoai VARCHAR(200), DiaChi NVARCHAR(200), TongTien FLOAT, NgayDatHang VARCHAR(200), PhuongThuc NVARCHAR(200), Email VARCHAR(200), TrangThai INTEGER)");

        homeModelList = new ArrayList<>();
        homeModelList.add(new HomeModel(R.drawable.pizza, "Pizza"));
        homeModelList.add(new HomeModel(R.drawable.hamburger, "Hamburger"));
        homeModelList.add(new HomeModel(R.drawable.fried_potatoes, "Fries"));
        homeModelList.add(new HomeModel(R.drawable.ice_cream, "Ice Cream"));
        homeModelList.add(new HomeModel(R.drawable.sandwich, "Sandwich"));
        homeAdapter = new HomeAdapter(this, getActivity(), homeModelList, getContext());
        home_rec.setAdapter(homeAdapter);
        home_rec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        home_rec.setHasFixedSize(true);
        home_rec.setNestedScrollingEnabled(false);


        /////////////////////////////////vertical//////////////////////////////
        homeVerModels = new ArrayList<>();

        homeVerAdapter = new HomeVerAdapter(getActivity(), homeVerModels);
        home_ver_rec.setAdapter(homeVerAdapter);
        home_ver_rec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        return root;
    }

    @Override
    public void callback(int pos, ArrayList<HomeVerModel> list) {

        homeVerAdapter = new HomeVerAdapter(getContext(), list);
        homeVerAdapter.notifyDataSetChanged();
        home_ver_rec.setAdapter(homeVerAdapter);

    }
}