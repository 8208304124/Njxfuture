package com.example.njxfuture;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.njxfuture.API.APIRequests;
import com.example.njxfuture.API.DataModels.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button login;
    private static final int REQUEST_READ_PHONE_STATE = 1;
    EditText uname, pass;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
            login = findViewById(R.id.login_btn);
            uname = findViewById(R.id.user_id);
            pass = findViewById(R.id.user_pass);
            register = findViewById(R.id.register_layout);
            login.setOnClickListener(View ->CallLogin(uname.getText().toString(), pass.getText().toString()));
            register.setOnClickListener(v->{
                Intent intent = new Intent(this,Register.class);
                startActivity(intent);
            });
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            getIMEINumber();
        }
    }
    void CallLogin(String uname, String pass){
//        Call<UserModel> call = APIRequests.fetchData(uname);
//        call.enqueue(new Callback<UserModel>() {
//            @Override
//            public void onResponse(@NonNull Call<UserModel> call, @NonNull Response<UserModel> response) {
//                if (response.isSuccessful()) {
//                    UserModel user = response.body();
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<UserModel> call, @NonNull Throwable t) {
//                // Handle failure
//            }
//        });
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public String getIMEINumber() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            String imei;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                imei = telephonyManager.getImei();
            } else {
                imei = telephonyManager.getDeviceId();
            }
            return imei;
        }
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ_PHONE_STATE) {
            if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                finish();
            }
        }
    }
}