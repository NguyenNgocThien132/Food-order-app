package com.example.myfoodapp.adapters;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfoodapp.R;
import com.example.myfoodapp.SQLite.DailyHelper;
import com.example.myfoodapp.activitis.DetailDailyMealActivity;
import com.example.myfoodapp.models.DetailedDailyModel;
import com.example.myfoodapp.models.HomeModel;
import com.example.myfoodapp.models.HomeVerModel;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    UpdateVertical updateVertical;
    Activity activity;
    ArrayList<HomeModel> list;


    boolean check = true;
    boolean select = true;
    int row_index = -1;

    Context context;


    public HomeAdapter(UpdateVertical updateVertical, Activity activity, ArrayList<HomeModel> list, Context context) {
        this.updateVertical = updateVertical;
        this.activity = activity;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());

        DailyHelper helper = new DailyHelper(context, "FoodApp.sqlite", null, 1);
        if (check) {
            ArrayList<HomeVerModel> homeVerModelArrayList = new ArrayList<>();
            showData(helper, "L006", homeVerModelArrayList);

            updateVertical.callback(position, homeVerModelArrayList);
            check = false;
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();


                if (position == 0) {
                    ArrayList<HomeVerModel> homeVerModelArrayList = new ArrayList<>();
                    showData(helper, "L006", homeVerModelArrayList);
                    updateVertical.callback(position, homeVerModelArrayList);

                } else if (position == 1) {
                    ArrayList<HomeVerModel> homeVerModelArrayList = new ArrayList<>();
                    showData(helper, "L007", homeVerModelArrayList);
                    updateVertical.callback(position, homeVerModelArrayList);

                } else if (position == 2) {
                    ArrayList<HomeVerModel> homeVerModelArrayList = new ArrayList<>();
                    showData(helper, "L008", homeVerModelArrayList);
                    updateVertical.callback(position, homeVerModelArrayList);

                } else if (position == 3) {
                    ArrayList<HomeVerModel> homeVerModelArrayList = new ArrayList<>();
                    showData(helper, "L010", homeVerModelArrayList);
                    updateVertical.callback(position, homeVerModelArrayList);

                } else if (position == 4) {
                    ArrayList<HomeVerModel> homeVerModelArrayList = new ArrayList<>();
                    showData(helper, "L009", homeVerModelArrayList);
                    updateVertical.callback(position, homeVerModelArrayList);

                }
            }
        });
        if (select) {
            if (position == 0) {
                holder.cardView.setBackgroundResource(R.drawable.change_bg);
                select = false;
            }
        } else {
            if (row_index == position) {
                holder.cardView.setBackgroundResource(R.drawable.change_bg);
            } else {
                holder.cardView.setBackgroundResource(R.drawable.default_bg);
            }
        }
    }

    private void showData(DailyHelper helper, String id_loai, ArrayList<HomeVerModel> list) {
        Cursor data = helper.GetData("SELECT ID_ThucAn, Image, Name, TIMING, RATING, PRICE FROM ThucAn WHERE ID_LoaiThucAn = '" + id_loai + "'");
        list.clear();
        while (data.moveToNext()) {
            String idThucAn = data.getString(0);
            int image = data.getInt(1);
            String name = data.getString(2);
            String timing = data.getString(3);
            String rating = data.getString(4);
            float price = data.getFloat(5);
            list.add(new HomeVerModel(idThucAn, image, name, timing, rating, price));
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.item_img);
            name = itemView.findViewById(R.id.item_text);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
