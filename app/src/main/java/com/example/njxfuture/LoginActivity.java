package com.example.njxfuture;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.njxfuture.API.APIRequests;
import com.example.njxfuture.API.DataModels.UpdateUserDataModel;

import java.util.Objects;
import java.util.UUID;


public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText uname, pass;
    TextView register,forgotPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
            login = findViewById(R.id.login_btn);
            uname = findViewById(R.id.user_id);
            pass = findViewById(R.id.user_pass);
            register = findViewById(R.id.register_layout);
            forgotPass=findViewById(R.id.forgot_pass);
            login.setOnClickListener(View ->CallLogin(uname.getText().toString(), pass.getText().toString()));
            register.setOnClickListener(v->{
                Intent intent = new Intent(this,Register.class);
                startActivity(intent);
            });
            forgotPass.setOnClickListener(v->{
                Intent intent = new Intent(getApplicationContext(), WebView1.class);
                intent.putExtra("url", "http://njx.revacg.in/");
                startActivity(intent);
            });

    }
    void CallLogin(String uname, String pass){
        Call<UpdateUserDataModel> call = APIRequests.getUserLogin(getDeviceIds(getApplicationContext()),uname,pass);
        call.enqueue(new Callback<UpdateUserDataModel>() {
            @Override
            public void onResponse(@NonNull Call<UpdateUserDataModel> call, @NonNull Response<UpdateUserDataModel> response) {
                if (response.isSuccessful()) {
                    UpdateUserDataModel user = response.body();
                    assert user != null;
                    if(!Objects.equals(user.getUser(), "UNKNOWN")){
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), user.getMsg(),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<UpdateUserDataModel> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_LONG).show();
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