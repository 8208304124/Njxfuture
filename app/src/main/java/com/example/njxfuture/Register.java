package com.example.njxfuture;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    boolean otpCheck = false;
    private static final String MOBILE_NUMBER_REGEX = "^[6-9]\\d{9}$";
    int count = 1;
    LinearLayout l1;
    // Pattern object for compiling the regular expression
    private static final Pattern pattern = Pattern.compile(MOBILE_NUMBER_REGEX);

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
                if (!isValidEmail(mail)) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
                } else {
                    if (!isValidGST(GST)) {
                        Toast.makeText(getApplicationContext(), "Please enter a valid GST Number.", Toast.LENGTH_SHORT).show();
                    } else {
                        if(!isValidPassword(pass)){
                            Toast.makeText(getApplicationContext(), "Password length should be 8-20 and contain One Uppercase letter, One Lowercase Letter, One Digit and One Special Character.", Toast.LENGTH_SHORT).show();
                        }else {
                            if (otpCheck) {
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
                            } else {
                                Toast.makeText(getApplicationContext(), "Please verify Otp!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                }
            }
            } else {
                Toast.makeText(getApplicationContext(), "Please fill all the details!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static boolean isValidPassword(String password) {
        // Define your password validation rules here
          final int MIN_LENGTH = 8; // Minimum password length
          final int MAX_LENGTH = 20; // Maximum password length
          final String SPECIAL_CHARACTERS = "!@#$%^&*()_+\\-=[]{}|;:'\",.<>?"; // Special characters allowed

        // Check if the password is null or empty
        if (password == null || password.isEmpty()) {
            return false;
        }

        // Check if the password length is within the allowed range
        int length = password.length();
        if (length < MIN_LENGTH || length > MAX_LENGTH) {
            return false;
        }

        // Check if the password contains at least one uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        // Check if the password contains at least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        // Check if the password contains at least one digit
        if (!password.matches(".*\\d.*")) {
            return false;
        }

        // Check if the password contains at least one special character
        if (!password.matches(".*[" + escapeSpecialCharacters(SPECIAL_CHARACTERS) + "].*")) {
            return false;
        }


        // Password meets all validation criteria
        return true;
    }
    // Method to escape special characters for regex
    private static String escapeSpecialCharacters(String input) {
        return input.replaceAll("([\\\\\\[\\](){}+\\-?*.^$|])", "\\\\$1");
    }
    public static boolean isValidMobileNumber(String mobileNumber) {
        return pattern.matcher(mobileNumber).matches();
    }
    public static boolean isValidEmail(CharSequence email) {
        return (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    public static boolean isValidGST(String gstNumber) {
        // GST number should be 15 characters long
        if (gstNumber.length() != 15)
            return false;

        // GSTIN Format: first two characters should be alphabets
        String firstTwoChars = gstNumber.substring(0, 2);
        if (!firstTwoChars.matches("[A-Z]+"))
            return false;

        // GSTIN Format: Next 10 characters should be digits
        String middleChars = gstNumber.substring(2, 12);
        if (!middleChars.matches("[0-9]+"))
            return false;

        // GSTIN Format: 13th character should be the state code
        char stateCodeChar = gstNumber.charAt(12);
        if (!Character.isDigit(stateCodeChar))
            return false;

        // GSTIN Format: Last two characters should be alphabets or numerals
        String lastTwoChars = gstNumber.substring(13);
        if (!lastTwoChars.matches("[0-9A-Z]+"))
            return false;

        return true;
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