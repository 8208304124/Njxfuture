package com.example.njxfuture;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.njxfuture.ViewPager.NotificationService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.njxfuture.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Start the NotificationService

        Intent serviceIntent = new Intent(this, NotificationService.class);
        startService(serviceIntent);
        com.example.njxfuture.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.fragment_more)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        Intent intents = getIntent();
        if(intents.hasExtra("notification_id") && intents.hasExtra("notification_tab")){
            navController.navigate(R.id.navigation_notifications);
        }
        //bottom navigation
        bottomNavigationView.setItemIconTintList(ContextCompat.getColorStateList(this, R.color.bottom_navigation_color));
        bottomNavigationView.setItemTextColor(ContextCompat.getColorStateList(this, R.color.bottom_navigation_color));
        bottomNavigationView.setOnNavigationItemSelectedListener(v -> {
            int itemId = v.getItemId();
            if (R.id.navigation_dashboard == itemId) {
                navController.navigate(R.id.navigation_dashboard);
            } else if (R.id.navigation_home == itemId) {
                navController.navigate(R.id.navigation_home);
            } else if (R.id.fragment_more == itemId) {
                navController.navigate(R.id.fragment_more);
            } else if (R.id.navigation_notifications == itemId) {
                navController.navigate(R.id.navigation_notifications);
            }
            return true;
        });
    }
}