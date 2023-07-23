package com.example.myfoodapp.adapters;

import android.content.Context;
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
import com.example.myfoodapp.models.HomeModel;
import com.example.myfoodapp.models.HomeVerModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class HomeVerAdapter extends RecyclerView.Adapter<HomeVerAdapter.ViewHolder> {

    private BottomSheetDialog bottomSheetDialog;
    Context context;
    ArrayList<HomeVerModel> list;

    public HomeVerAdapter(Context context, ArrayList<HomeVerModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_vertical_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final String mName = list.get(position).getName();
        final String mTiming = list.get(position).getTime();
        final String mRating = list.get(position).getRacking();
        final Float mPrice = list.get(position).getPrice();
        final int mImage = list.get(position).getImage();

        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.time.setText(list.get(position).getTime());
        holder.racing.setText(list.get(position).getRacking());
        holder.price.setText(list.get(position).getPrice() + "");

        HomeVerModel verModel = list.get(position);
        DailyHelper helper = new DailyHelper(context, "FoodApp.sqlite", null, 1);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomSheetDialog = new BottomSheetDialog(context, R.style.BottomSheetTheme);

                View sheetView = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_layout, null);

                Button btntang, btngiam, btnsoluong;
                btntang = sheetView.findViewById(R.id.btnTang);
                btngiam = sheetView.findViewById(R.id.btnGiam);
                btnsoluong = sheetView.findViewById(R.id.btnSoLuong);

                int[] temp = {1}; // Khai báo mảng để lưu trữ giá trị
                ArrayList<Integer> soluong = new ArrayList<>();
                soluong.add(1);

                btntang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        temp[0]++;
                        btnsoluong.setText(String.valueOf(temp[0]));
                        list.clear();
                        int i = temp[0];
                        soluong.add(i);
                    }
                });


                btngiam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (temp[0] > 1) {
                            temp[0]--;
                            btnsoluong.setText(String.valueOf(temp[0]));
                            list.clear();
                            int i = temp[0];
                            soluong.add(i);
                        }
                    }
                });
                int so_luong = temp[0];
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String email = user.getEmail();

                sheetView.findViewById(R.id.add_cart2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int dataSL = 0;
                        for (int i : soluong) {
                            dataSL = i;
                        }
                        Cursor data = helper.GetData("SELECT ID_ThucAn FROM ThucAn WHERE ID_ThucAn = '" + verModel.getId_thucan() + "'");
                        while (data.moveToNext()) {
                            String idthucan = data.getString(0);
                            helper.QueryData("INSERT INTO GioHang VALUES (null, '" + idthucan + "', " + dataSL + ", '" + email
                                    + "')");
                        }

                        Toast.makeText(context, "Thêm vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });

                ImageView bottomImg = sheetView.findViewById(R.id.bottom_img);
                TextView bottomName = sheetView.findViewById(R.id.bottom_name);
                TextView bottomPrice = sheetView.findViewById(R.id.bottom_price);
                TextView bottomRating = sheetView.findViewById(R.id.bottom_rating);

                bottomName.setText(mName);
                bottomPrice.setText(mPrice + "");
                bottomRating.setText(mRating);
                bottomImg.setImageResource(mImage);

                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, time, racing, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ver_img);
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            racing = itemView.findViewById(R.id.racing);
            price = itemView.findViewById(R.id.price);

        }
    }
}
