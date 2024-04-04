package com.example.njxfuture;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.example.njxfuture.API.DataModels.OtpGenerateDataModel;
import com.example.njxfuture.API.DataModels.UpdateUserDataModel;
import com.example.njxfuture.API.UserCredentialsManager;

import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {


    EditText email, uname, mno, gst, pwd, otp;
    String mail = "", userName = "", mobile = "", GST = "", pass = "";
    TextView verify_text, number_text;
    private static final String PREF_KEY_DEVICE_ID = "device_id";
    Button register, verify;
    ImageView verify_mark;
    boolean otpCheck=false;
    private static final String MOBILE_NUMBER_REGEX = "^[6-9]\\d{9}$";
    int count = 1;
    // Pattern object for compiling the regular expression
    private static final Pattern pattern = Pattern.compile(MOBILE_NUMBER_REGEX);

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
        otp = findViewById(R.id.efit_otp);
        number_text = findViewById(R.id.number_text);
        register = findViewById(R.id.register_button);
        verify = findViewById(R.id.verify_button_via_sms);
        verify_text = findViewById(R.id.verify_text);
        verify_mark = findViewById(R.id.verify_mark);

        verify.setOnClickListener(v -> {
            if (count == 1) {
                if (isValidMobileNumber(mno.getText().toString())) {

                    Call<OtpGenerateDataModel> call = APIRequests.generateOtp(getDeviceIds(getApplicationContext()), mno.getText().toString());
                    call.enqueue(new Callback<OtpGenerateDataModel>() {
                        @Override
                        public void onResponse(@NonNull Call<OtpGenerateDataModel> call, @NonNull Response<OtpGenerateDataModel> response) {
                            if (response.isSuccessful()) {
                                assert response.body() != null;
                                if (response.body().getActy()) {
                                    verify.setText(R.string.verify);
                                    mno.setVisibility(View.GONE);
                                    number_text.setVisibility(View.GONE);
                                    otp.setVisibility(View.VISIBLE);
                                } else {
                                    Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<OtpGenerateDataModel> call, @NonNull Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "Mobile Number is incorrect!!", Toast.LENGTH_SHORT).show();
                }
            } else if (count > 2) {
                if (isValidMobileNumber(mno.getText().toString())) {
                    Call<UpdateUserDataModel> call = APIRequests.getOtpVerify(getDeviceIds(getApplicationContext()), otp.getText().toString(), mno.getText().toString());
                    call.enqueue(new Callback<UpdateUserDataModel>() {
                        @Override
                        public void onResponse(@NonNull Call<UpdateUserDataModel> call, @NonNull Response<UpdateUserDataModel> response) {
                            if (response.isSuccessful()) {
                                assert response.body() != null;
                                if (Objects.equals(response.body().getMsg(), "OTP Matched") && !Objects.equals(response.body().getUser(), "UNKNOWN")) {
                                    verify.setVisibility(View.GONE);
                                    mno.setVisibility(View.VISIBLE);
                                    number_text.setVisibility(View.VISIBLE);
                                    otp.setVisibility(View.GONE);
                                    verify_text.setVisibility(View.VISIBLE);
                                    verify_mark.setVisibility(View.VISIBLE);
                                    mno.setFocusable(false);
                                    otpCheck = true;
                                    mno.setClickable(false);
                                    mno.setEnabled(false);
                                } else {
                                    Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<UpdateUserDataModel> call, @NonNull Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "Mobile Number is incorrect!!", Toast.LENGTH_SHORT).show();
                }
                count = 2;
            }
            count = count + 1;
        });

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
                if(otpCheck){
                    Call<Account> call = APIRequests.creatAcc(getDeviceIds(getApplicationContext()), userName, mobile, GST, pass, mail);
                    call.enqueue(new Callback<Account>() {
                        @Override
                        public void onResponse(@NonNull Call<Account> call, @NonNull Response<Account> response) {
                            if (response.isSuccessful()) {
                                assert response.body() != null;
                                if (response.body().getRes()) {
                                    UserCredentialsManager credentialsManager = new UserCredentialsManager(getApplicationContext());
                                    credentialsManager.saveCredentials(userName, pass, getDeviceIds(getApplicationContext()));
                                    Toast.makeText(getApplicationContext(), "Account Created Successfully!!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Register.this, MainActivity.class);
                                    finishAffinity();
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<Account> call, @NonNull Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please verify Otp!!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please fill all the details!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static boolean isValidMobileNumber(String mobileNumber) {
        return pattern.matcher(mobileNumber).matches();
    }

    public String getDeviceIds(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String deviceId = sharedPreferences.getString(PREF_KEY_DEVICE_ID, null);
        if (deviceId == null) {
            deviceId = UUID.randomUUID().toString();
            sharedPreferences.edit().putString(PREF_KEY_DEVICE_ID, deviceId).apply();
        }
        return deviceId;
    }

}