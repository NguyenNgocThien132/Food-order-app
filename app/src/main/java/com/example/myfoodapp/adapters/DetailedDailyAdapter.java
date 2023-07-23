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
import com.example.myfoodapp.models.DetailedDailyModel;
import com.example.myfoodapp.models.HomeVerModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class DetailedDailyAdapter extends RecyclerView.Adapter<DetailedDailyAdapter.ViewHolder> {
    Context context;
    ArrayList<DetailedDailyModel> arrayList;

    private BottomSheetDialog bottomSheetDialog;


    public DetailedDailyAdapter(Context context, ArrayList<DetailedDailyModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public DetailedDailyAdapter() {
    }

    @NonNull
    @Override
    public DetailedDailyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detailed_daily_meal_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DetailedDailyAdapter.ViewHolder holder, int position) {
        DetailedDailyModel model = arrayList.get(position);

        final String mName = model.getName();
        final String mRating = model.getRating();
        final Float mPrice = model.getPrice();
        final int mImage = model.getImage();

        holder.imageView.setImageResource(model.getImage());
        holder.name.setText(model.getName());
        holder.description.setText(model.getDescription());
        holder.price.setText(model.getPrice() + "");
        holder.rating.setText(model.getRating());
        holder.timing.setText(model.getTiming());

        DailyHelper helper = new DailyHelper(context, "FoodApp.sqlite", null, 1);
        DetailedDailyModel dailyModel = arrayList.get(position);
        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog = new BottomSheetDialog(context, R.style.BottomSheetTheme);

                View sheetView = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_add_to_cart, null);

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
                        arrayList.clear();
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
                            arrayList.clear();
                            int i = temp[0];
                            soluong.add(i);
                        }
                    }
                });
                int so_luong = temp[0];
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String email = user.getEmail();
                sheetView.findViewById(R.id.add_to_cart2).setOnClickListener(new View.OnClickListener() {
                    int i = so_luong;

                    @Override
                    public void onClick(View view) {
                        int dataSL = 0;
                        for (int i : soluong) {
                            dataSL = i;
                        }
                        Cursor data = helper.GetData("SELECT ID_ThucAn FROM ThucAn WHERE ID_ThucAn = '" + dailyModel.getId_thucan() + "'");
                        while (data.moveToNext()) {
                            String idthucan = data.getString(0);
                            helper.QueryData("INSERT INTO GioHang VALUES (null, '" + idthucan + "', " + dataSL + ", '" + email + "')");
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
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, price, description, rating, timing;

        Button addtocart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.detailed_img);
            name = itemView.findViewById(R.id.detailed_name);
            price = itemView.findViewById(R.id.detailed_price);
            description = itemView.findViewById(R.id.detailed_description);
            rating = itemView.findViewById(R.id.detailed_rating);
            timing = itemView.findViewById(R.id.detailed_timing);
            addtocart = itemView.findViewById(R.id.addToCart);
        }
    }
}
