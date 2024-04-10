package com.example.njxfuture;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.example.njxfuture.API.APIRequests;
import com.example.njxfuture.API.DataModels.UpdateUserDataModel;
import com.example.njxfuture.API.UserCredentialsManager;

import java.util.Objects;
import java.util.UUID;

import androidx.core.content.ContextCompat;


public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText uname, pass;
    TextView register, forgotPass, help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int nightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        // Set the default night mode for the app
        if (nightMode == Configuration.UI_MODE_NIGHT_YES) {
            setTheme(R.style.NoActionBarDark);
        } else {
            setTheme(R.style.NoActionBar);
        }
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login_btn);
        uname = findViewById(R.id.user_id);
        pass = findViewById(R.id.user_pass);
        register = findViewById(R.id.register_layout);
        forgotPass = findViewById(R.id.forgot_pass);
        help = findViewById(R.id.help);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }
        login.setOnClickListener(View -> CallLogin(uname.getText().toString(), pass.getText().toString()));
        register.setOnClickListener(v -> {
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        });
        forgotPass.setOnClickListener(v -> {
            UserCredentialsManager credentialsManager = new UserCredentialsManager(getApplicationContext());
            String pwd = credentialsManager.getPassword();
            String pname = credentialsManager.getUsername();
            Intent intent = new Intent(getApplicationContext(), WebView1.class);
            String url = "https://njx.revacg.in/fpass.php?u=" + pname + "&p=" + pwd;
            intent.putExtra("url", url);
            startActivity(intent);
        });
        help.setOnClickListener(v -> {
            UserCredentialsManager credentialsManager = new UserCredentialsManager(getApplicationContext());
            String pwd = credentialsManager.getPassword();
            String pname = credentialsManager.getUsername();
            Intent intent = new Intent(getApplicationContext(), WebView1.class);
            intent.putExtra("url", "https://njx.revacg.in/help.php?u=" + pname + "&p=" + pwd);
            startActivity(intent);
        });
    }

    void CallLogin(String name, String passs) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        Call<UpdateUserDataModel> call = APIRequests.getUserLogin(getDeviceIds(getApplicationContext()), name, passs);
        call.enqueue(new Callback<UpdateUserDataModel>() {
            @Override
            public void onResponse(@NonNull Call<UpdateUserDataModel> call, @NonNull Response<UpdateUserDataModel> response) {

                if (response.isSuccessful()) {
                    UpdateUserDataModel user = response.body();
                    assert user != null;
                    if (!Objects.equals(user.getUser(), "UNKNOWN")) {
                        // Save credentials
                        UserCredentialsManager credentialsManager = new UserCredentialsManager(getApplicationContext());
                        credentialsManager.saveCredentials(name, passs, getDeviceIds(getApplicationContext()));
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), user.getMsg(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<UpdateUserDataModel> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public String getDeviceIds(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String deviceId = sharedPreferences.getString("device_id", null);
        if (deviceId == null) {
            deviceId = UUID.randomUUID().toString();
            sharedPreferences.edit().putString("device_id", deviceId).apply();
        }
        return deviceId;
    }
}