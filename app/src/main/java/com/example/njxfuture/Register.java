package com.example.njxfuture;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.njxfuture.API.APIRequests;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.njxfuture.API.DataModels.Account;

public class Register extends AppCompatActivity {

    EditText email, uname, mno, gst, pwd;
    String mail, userName, mobile, GST, pass;
    TextView verify_text;
    private static final int REQUEST_READ_PHONE_STATE = 1;
    Button register, verify;
    ImageView verify_mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.register_edit_email);
        uname = findViewById(R.id.register_user_name);
        mno = findViewById(R.id.register_mobile_no);
        gst = findViewById(R.id.gst_no);
        pwd = findViewById(R.id.register_password);
        register = findViewById(R.id.register_button);
        verify = findViewById(R.id.verify_button_via_sms);
        verify_text = findViewById(R.id.verify_text);
        verify_mark = findViewById(R.id.verify_mark);

        verify.setOnClickListener(v -> {
            verify_text.setVisibility(View.VISIBLE);
            verify_mark.setVisibility(View.VISIBLE);
        });
        if (checkSelfPermission(android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            getIMEINumber();
        }
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before the text is changed.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mail = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after the text has been changed.
            }
        });
        uname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before the text is changed.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after the text has been changed.
            }
        });
        mno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before the text is changed.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mobile = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after the text has been changed.
            }
        });
        gst.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before the text is changed.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                GST = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after the text has been changed.
            }
        });
        pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before the text is changed.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pass = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after the text has been changed.
            }
        });

        register.setOnClickListener(v -> {
            if (!mail.isEmpty() && !userName.isEmpty() &&
                    !mobile.isEmpty() && !GST.isEmpty() &&
                    !pass.isEmpty()) {
                Call<Account> call = APIRequests.creatAcc(getIMEINumber(), userName, mobile, GST, pass, mail);
                call.enqueue(new Callback<Account>() {
                    @Override
                    public void onResponse(@NonNull Call<Account> call, @NonNull Response<Account> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Account Created Successfully!!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this, MainActivity.class);
//                            finishAffinity();
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Account> call, @NonNull Throwable t) {
                        // Handle failure
                    }
                });
            }
        });
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