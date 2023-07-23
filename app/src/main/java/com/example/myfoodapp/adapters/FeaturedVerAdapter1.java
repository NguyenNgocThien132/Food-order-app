package com.example.myfoodapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfoodapp.R;
import com.example.myfoodapp.models.FeaturedVerModel1;

import java.util.ArrayList;

public class FeaturedVerAdapter1 extends RecyclerView.Adapter<FeaturedVerAdapter1.ViewHolder> {
    ArrayList<FeaturedVerModel1> arrayList;

    public FeaturedVerAdapter1(ArrayList<FeaturedVerModel1> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_second, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeaturedVerModel1 model = arrayList.get(position);
        holder.imageView.setImageResource(model.getImage());
        holder.name.setText(model.getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_item_fragment_second);
            name = itemView.findViewById(R.id.txt_item_fragment_second);
        }
    }
}
