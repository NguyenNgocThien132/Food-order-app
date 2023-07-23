package com.example.myfoodapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfoodapp.R;
import com.example.myfoodapp.SQLite.DailyHelper;
import com.example.myfoodapp.models.DetailCartModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class DetailCartAdapter extends RecyclerView.Adapter<DetailCartAdapter.ViewHolder> {

    Context context;
    ArrayList<DetailCartModel> arrayList;

    public DetailCartAdapter(Context context, ArrayList<DetailCartModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DetailCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_history_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull DetailCartAdapter.ViewHolder holder, int position) {
        DetailCartModel model = arrayList.get(position);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DailyHelper helper = new DailyHelper(context, "FoodApp.sqlite", null, 1);


        String email = user.getEmail();
        holder.cthoten.setText(model.getHoten());
        holder.ctsdt.setText(model.getSdt());
        holder.ctsoban.setText(model.getDiachi());
        holder.ctthucdon.setText(model.getTenthucan());
        holder.ctngaydat.setText(model.getNgaydat());
        holder.ctthanhtoan.setText(model.getPhuongthuc());
        holder.cttongtien.setText(model.getTongtien() + "00 VND");
        holder.ctmadonhang.setText(model.getId_donhang() + "");
        holder.email.setText(model.getEmail());

        if (model.getTrangthai() == 0) {
            holder.cttrangthai.setText("Đang chờ xác nhận");
        } else if(model.getTrangthai() == 1){
            holder.cttrangthai.setText("Đã xác nhận");
        }else{
            holder.cttrangthai.setText("Đã hủy");
        }

        if (email.equalsIgnoreCase("ngocthien@gmail.com")) {
            holder.xacnhan.setVisibility(View.VISIBLE);
            holder.huy.setVisibility(View.VISIBLE);

            holder.huy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Xác nhận");
                    builder.setMessage("Bạn có chắc muốn hủy?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            helper.QueryData("UPDATE ChiTietDonHang SET TrangThai = -1 WHERE ID_DonHang = "+model.getId_donhang()+"");
                            Toast.makeText(context, "Hủy đơn hàng thành công!", Toast.LENGTH_SHORT).show();
                            arrayList.remove(position);
                            notifyDataSetChanged();
                        }
                    });

                    builder.setNegativeButton("Không", null);

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
            holder.xacnhan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Xác nhận");
                    builder.setMessage("Bạn có chắc xác nhận đơn?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            helper.QueryData("UPDATE ChiTietDonHang SET TrangThai = 1 WHERE ID_DonHang = "+model.getId_donhang()+"");
                            Toast.makeText(context, "Xác nhận đơn hàng thành công!", Toast.LENGTH_SHORT).show();
                            arrayList.remove(position);
                            notifyDataSetChanged();
                        }
                    });

                    builder.setNegativeButton("Không", null);

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        } else {
            holder.xacnhan.setVisibility(View.GONE);
            holder.huy.setVisibility(View.GONE);


        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cthoten, ctsdt, ctsoban, ctthucdon, ctngaydat, cttongtien, ctthanhtoan, ctmadonhang, cttrangthai, email;
        Button xacnhan, huy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cthoten = itemView.findViewById(R.id.ctHoTen);
            ctsdt = itemView.findViewById(R.id.tcSdt);
            ctsoban = itemView.findViewById(R.id.ctSoBan);
            ctthucdon = itemView.findViewById(R.id.ctThucDon);
            ctngaydat = itemView.findViewById(R.id.tcNgayDatHang);
            cttongtien = itemView.findViewById(R.id.ctTongTien);
            ctthanhtoan = itemView.findViewById(R.id.tcPhuongThuc);
            ctmadonhang = itemView.findViewById(R.id.ctMaDonHang);
            cttrangthai = itemView.findViewById(R.id.tvtrangthai);
            email = itemView.findViewById(R.id.tcEmail);
            xacnhan = itemView.findViewById(R.id.btnXacNhan);
            huy = itemView.findViewById(R.id.btnHuy);
        }
    }
}
