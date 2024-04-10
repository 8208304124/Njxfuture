package com.example.njxfuture;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


public class SpalshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        int nightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        // Set the default night mode for the app
        if (nightMode == Configuration.UI_MODE_NIGHT_YES) {
            setTheme(R.style.NoActionBarDark);
        } else {
            setTheme(R.style.NoActionBar);
        }
        setContentView(R.layout.activity_spalsh);
        // Get the system's night mode
        // Example: Navigate to the main activity after a delay
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SpalshActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Finish the splash activity to prevent going back to it
        }, 2000); // Delay for 2 seconds (2000 milliseconds)

    }
//    @Override
//    public void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
//        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
//        if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
//            resid = R.style.Theme_NoActionBarWithLight; // Change to your light theme
//
//        } else {
//            resid = R.style.Theme_NoActionBarWithDark; // Change to your dark theme
//        }
//        super.onApplyThemeResource(theme, resid, first);
//    }
}