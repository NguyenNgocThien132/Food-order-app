package com.example.myfoodapp;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.myfoodapp.activitis.LoginActivity;
import com.example.myfoodapp.activitis.RegisterActivity;
import com.example.myfoodapp.ui.history.HistoryFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfoodapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    ViewFlipper viewFlipper;
    Button btn_logout;
    TextView txtEmail, txtname;
    Menu menu;
    MenuItem itemToHide, itemHistory;

    private NavigationView navigationView;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        addControls();
        LoadActionViewFlipper();
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_monan, R.id.nav_yeuthich, R.id.nav_giohang, R.id.nav_lichsu,R.id.nav_admin)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Bạn có muốn thoát ứng dụng không?");
                builder.setCancelable(false);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    }
                });
                builder.setNegativeButton("Không", null);
                AlertDialog alert = builder.create();
                alert.show();


            }
        });
        showinfo();

        HideItem();
    }

    private void HideItem(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        menu = navigationView.getMenu();
        itemToHide = menu.findItem(R.id.nav_admin);
        itemHistory = menu.findItem(R.id.nav_lichsu);
        String email = user.getEmail().trim();
        String mail_user = "ngocthien@gmail.com";
        if (email.equalsIgnoreCase(mail_user)){
            itemToHide.setVisible(true);
            itemHistory.setVisible(false);
        }else{

            itemToHide.setVisible(false);
        }
    }

    private void addControls() {
        navigationView  = (NavigationView) findViewById(R.id.nav_view);
        viewFlipper = (ViewFlipper)findViewById(R.id.viewFilpper) ;
        btn_logout = (Button) findViewById(R.id.btn_dangxuat);
        txtEmail = navigationView.getHeaderView(0).findViewById(R.id.textView_email);
        txtname = navigationView.getHeaderView(0).findViewById(R.id.nameUser);

    }

    private void LoadActionViewFlipper() {
        List<String> quangcaomonan = new ArrayList<>();
        quangcaomonan.add("https://i.imgur.com/OStujSY.jpg");
        quangcaomonan.add("https://i.imgur.com/IIvvo2I.png");
        quangcaomonan.add("https://i.imgur.com/Mw7mezz.jpg");
        quangcaomonan.add("https://i.imgur.com/8N0g6AH.jpg");
        quangcaomonan.add("https://i.imgur.com/kY1sR0v.png");
        for (int i = 0; i< quangcaomonan.size();i++)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(quangcaomonan.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation animation_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.sile_in_right);
        Animation animation_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.sile_out_right);
        viewFlipper.setInAnimation(animation_in);
        viewFlipper.setOutAnimation(animation_out);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void showinfo()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null)
        {
            return;
        }

        String name = user.getDisplayName();
        String email = user.getEmail();

        if(name == null)
        {
            txtname.setVisibility(View.GONE);
        }
        else
        {
            txtname.setVisibility(View.VISIBLE);
            txtname.setText(name);
        }
        txtEmail.setText("" + email);
        txtname.setText("" + name);
    }
}