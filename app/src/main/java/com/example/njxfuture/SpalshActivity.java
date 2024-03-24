package com.example.njxfuture;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class SpalshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spalsh);


        // Example: Navigate to the main activity after a delay
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SpalshActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Finish the splash activity to prevent going back to it
        }, 2000); // Delay for 2 seconds (2000 milliseconds)
    }
}