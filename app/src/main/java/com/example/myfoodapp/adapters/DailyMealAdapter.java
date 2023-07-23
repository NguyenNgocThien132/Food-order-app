package com.example.myfoodapp.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfoodapp.R;
import com.example.myfoodapp.activitis.DetailDailyMealActivity;
import com.example.myfoodapp.models.DailyMealModel;
import com.example.myfoodapp.models.DetailedDailyModel;

import java.util.ArrayList;

public class DailyMealAdapter extends RecyclerView.Adapter<DailyMealAdapter.ViewHolder> {
    ArrayList<DailyMealModel> arrayList;
    Context context;


    public DailyMealAdapter( Context context,ArrayList<DailyMealModel> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DailyMealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_meal_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DailyMealAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(arrayList.get(position).getImage());
        holder.name.setText(arrayList.get(position).getName());
        holder.description.setText(arrayList.get(position).getDescription());
        holder.discount.setText(arrayList.get(position).getDiscount());


         DailyMealModel index = arrayList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailDailyMealActivity.class);
                intent.putExtra("id_loaithucan", index.getId_loaithucan());
                intent.putExtra("name", index.getName());
                intent.putExtra("image", index.getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, description, discount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_daily);
            name = itemView.findViewById(R.id.tvDinner);
            description = itemView.findViewById(R.id.tvDescription);
            discount = itemView.findViewById(R.id.tvDiscount);
        }
    }
}
