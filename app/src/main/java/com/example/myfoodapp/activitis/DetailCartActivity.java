package com.example.myfoodapp.activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.text.SimpleDateFormat;

import com.example.myfoodapp.R;
import com.example.myfoodapp.SQLite.DailyHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DetailCartActivity extends AppCompatActivity {
    TextView tvgiatien, tvname;
    EditText edtphuongthuc, edthoten, edtsdt, edtsoban;
    Button btndathang, btnhuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet_detail_cart);

        addControls();

        // Get the current date and time
        Calendar calendar = Calendar.getInstance();

        // Format the date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(calendar.getTime());

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String currentTime = timeFormat.format(calendar.getTime());
        DailyHelper helper = new DailyHelper(this, "FoodApp.sqlite", null, 1);

        Intent intent = getIntent();
        String giatien = intent.getStringExtra("giatien");
        String thucdon = intent.getStringExtra("thucdon");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();

        tvgiatien.setText(giatien);
        tvname.setText(thucdon);

        btndathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "INSERT INTO ChiTietDonHang VALUES (null, '" + tvname.getText() + "','"+edthoten.getText()+"', '" + edtsdt.getText() + "', '" + edtsoban.getText() + "', " + giatien + ", '" + currentDate + " - " + currentTime + "', '"+edtphuongthuc.getText()+"', '"+email+"', 0)";
                helper.QueryData(query);

                helper.QueryData("DELETE FROM GioHang");
                Toast.makeText(DetailCartActivity.this, "Thêm thành công đơn hàng", Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent(DetailCartActivity.this, ThanksActivity.class);
                startActivity(intent1);

            }
        });

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void addControls() {
        tvgiatien = findViewById(R.id.tvGiaTien);
        tvname = findViewById(R.id.tvName);
        edthoten = findViewById(R.id.edtHoTen);
        edtphuongthuc = findViewById(R.id.edtPhuongThuc);
        edtsdt = findViewById(R.id.edtSdt);
        edtsoban = findViewById(R.id.edtSoBan);
        btndathang = findViewById(R.id.btnDatHang);
        btnhuy = findViewById(R.id.btnHuyBo);
    }
}