package com.example.njxfuture;
import android.app.Application;
import android.app.UiModeManager;
import android.content.res.Configuration;
import android.os.Build;
import android.widget.Toast;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Handle UI mode changes here
        checkCurrentUIMode();
    }
    private void checkCurrentUIMode() {
        int nightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (nightMode == Configuration.UI_MODE_NIGHT_YES) {
            // Dark mode is enabled
            Toast.makeText(this, "Dark mode is enabled", Toast.LENGTH_SHORT).show();
            setAppTheme(nightMode);
        } else {
            // Light mode is enabled
            setAppTheme(nightMode);
            Toast.makeText(this, "Light mode is enabled", Toast.LENGTH_SHORT).show();
        }
    }
    private void setAppTheme(int nightMode) {
        switch (nightMode) {
            case Configuration.UI_MODE_NIGHT_YES:
                setTheme(R.style.Theme_Dark);
                break;
            case Configuration.UI_MODE_NIGHT_NO:
            default:
                setTheme(R.style.Theme_DayMode);
                break;
        }
    }
}
