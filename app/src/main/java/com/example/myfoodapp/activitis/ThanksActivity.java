package com.example.myfoodapp.activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myfoodapp.MainActivity;
import com.example.myfoodapp.R;
import com.example.myfoodapp.ui.MyCartFragment;
import com.example.myfoodapp.ui.home.HomeFragment;

public class ThanksActivity extends AppCompatActivity {

    Button btntieptuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);

        addControls();

        btntieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThanksActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        btntieptuc = findViewById(R.id.btnTiepTuc);
    }
}