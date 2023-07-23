package com.example.myfoodapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfoodapp.R;
import com.example.myfoodapp.SQLite.DailyHelper;
import com.example.myfoodapp.activitis.DetailCartActivity;
import com.example.myfoodapp.models.CartModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    ArrayList<CartModel> arrayList;
    private BottomSheetDialog bottomSheetDialog;
    TextView tvTongTien;


    public CartAdapter(Context context, ArrayList<CartModel> arrayList, TextView tvTongTien) {
        this.context = context;
        this.arrayList = arrayList;
        this.tvTongTien = tvTongTien;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        CartModel model = arrayList.get(position);
        holder.imageView.setImageResource(model.getImage());
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice() + "");


        int i = position;
        CartModel model1 = arrayList.get(position);
        DailyHelper helper = new DailyHelper(context, "FoodApp.sqlite", null, 1);

        // Retrieve the quantity from SQLite and set it to the btnsoluong button
        Cursor dataCart = helper.GetData("SELECT SoLuong FROM GioHang WHERE ID_GioHang = " + model1.getId_cart() + "");
        if (dataCart.moveToFirst()) {
            int quantity = dataCart.getInt(0);
            holder.btnsoluong.setText(String.valueOf(quantity));
        }


        holder.btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i1 = i;
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn có chắc muốn xóa thức ăn khỏi giỏ hàng?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Cursor dataCart = helper.GetData("SELECT ID_GioHang FROM GioHang WHERE ID_GioHang = " + model1.getId_cart() + "");
                        while (dataCart.moveToNext()) {
                            int idGioHang = dataCart.getInt(0);
                            helper.QueryData("DELETE FROM GioHang WHERE ID_GioHang = " + idGioHang + "");
                        }
                        Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();

                        arrayList.remove(i1);
                        // Thông báo cho adapter biết về sự thay đổi
                        notifyDataSetChanged();
                        updateTotalPrice();
                    }
                });

                builder.setNegativeButton("Hủy", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        holder.btntang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentQuantity = Integer.parseInt(holder.btnsoluong.getText().toString());
                int newQuantity = currentQuantity + 1;
                holder.btnsoluong.setText(String.valueOf(newQuantity));

                helper.QueryData("UPDATE GioHang SET SoLuong = " + newQuantity + " WHERE ID_GioHang = " + model1.getId_cart());
                model1.setSoluong(newQuantity);
                updateTotalPrice();

            }
        });
        holder.btngiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentQuantity = Integer.parseInt(holder.btnsoluong.getText().toString());
                if (currentQuantity > 1) {
                    int newQuantity = currentQuantity - 1;
                    holder.btnsoluong.setText(String.valueOf(newQuantity));

                    helper.QueryData("UPDATE GioHang SET SoLuong = " + newQuantity + " WHERE ID_GioHang = " + model1.getId_cart());
                    model1.setSoluong(newQuantity);
                }
                updateTotalPrice();
            }
        });
    }
    private void updateTotalPrice() {
        float totalPrice = 0;
        for (CartModel cartItem : arrayList) {
            float quantity = cartItem.getSoluong();
            float price = cartItem.getPrice();
            totalPrice += (quantity * price);
        }
        tvTongTien.setText(String.valueOf(totalPrice));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, price;
        Button btntang, btngiam, btnsoluong, btnxoa, btndathang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.detailed_img);
            name = itemView.findViewById(R.id.detailed_name);
            btntang = itemView.findViewById(R.id.btnTang);
            btngiam = itemView.findViewById(R.id.btnGiam);
            btnsoluong = itemView.findViewById(R.id.btnSoLuong);
            btnxoa = itemView.findViewById(R.id.btnXoa);
            price = itemView.findViewById(R.id.textView8);
            btndathang = itemView.findViewById(R.id.button_dathang);
        }
    }
}
