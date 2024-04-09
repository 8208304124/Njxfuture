package com.example.njxfuture;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import java.util.regex.Pattern;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.njxfuture.API.APIRequests;
import com.example.njxfuture.API.DataModels.UpdateUserDataModel;
import com.example.njxfuture.API.DataModels.User;
import com.example.njxfuture.databinding.FragmentEditProfileBinding;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends Fragment implements View.OnTouchListener {

    private FragmentEditProfileBinding binding;
    String umail, uname, ugst, upwd, uno;
    TextView email, name, gst, pwd, no;
    Button update;
    private Drawable eyeIcon;
    private boolean isPasswordVisible = false;
    private ProgressBar progressBar;
    private static final String MOBILE_NUMBER_REGEX = "^[6-9]\\d{9}$";
    private static final Pattern pattern = Pattern.compile(MOBILE_NUMBER_REGEX);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressBar = root.findViewById(R.id.progressBar);
        showLoader();
        email = root.findViewById(R.id.user_email);
        name = root.findViewById(R.id.edit_user_name);
        gst = root.findViewById(R.id.edit_gst);
        pwd = root.findViewById(R.id.change_pwd);
        no = root.findViewById(R.id.edit_mobile_no);
        umail=email.getText().toString();
        uname=name.getText().toString();
        ugst=gst.getText().toString();
        upwd=pwd.getText().toString();
        uno=no.getText().toString();
        setHasOptionsMenu(true);
        eyeIcon = ContextCompat.getDrawable(getContext(), R.drawable.eye_outline_svgrepo_com);
        eyeIcon.setBounds(0, 0, eyeIcon.getIntrinsicWidth(), eyeIcon.getIntrinsicHeight());
        // Set touch listener to detect eye icon click
        pwd.setOnTouchListener(this);
        // Update the eye icon visibility initially
        updateEyeIconVisibility();
        Call<User> call = APIRequests.getUser(getDeviceIds(requireContext()));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        hideLoader();
                        email.setText(response.body().getEmail());
                        name.setText(response.body().getUser());
                        gst.setText(response.body().getGstin());
                        pwd.setText(response.body().getPas());
                        no.setText(response.body().getMob());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                Log.e("API_CALL", "API call failed: " + t.getMessage());
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before the text is changed.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                umail = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after the text has been changed.
            }
        });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before the text is changed.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                uname = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after the text has been changed.
            }
        });
        no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before the text is changed.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                uno = s.toString();
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
                ugst = s.toString();
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
                upwd = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after the text has been changed.
            }
        });
        binding.updateBtn.setOnClickListener(v -> {
            showLoader();
            if (!uname.isEmpty() && !umail.isEmpty() && !upwd.isEmpty() && !ugst.isEmpty() && !uno.isEmpty()) {
                if (!isValidEmail(umail)) {
                    Toast.makeText(getActivity().getApplicationContext(), "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
                } else {
                    if (!isValidGST(ugst)) {
                        Toast.makeText(getActivity().getApplicationContext(), "Please enter a valid GST Number.", Toast.LENGTH_SHORT).show();
                    } else {
                        if(!isValidPassword(upwd)){
                            Toast.makeText(getActivity().getApplicationContext(), "Password length should be 8-20 and contain One Uppercase letter, One Lowercase Letter, One Digit and One Special Character.", Toast.LENGTH_SHORT).show();
                        }else {
                            Call<UpdateUserDataModel> call1 = APIRequests.updateUserDetails(getDeviceIds(requireContext()), uname, uno, ugst, upwd, umail);
                            call1.enqueue(new Callback<UpdateUserDataModel>() {
                                @Override
                                public void onResponse(@NonNull Call<UpdateUserDataModel> call, @NonNull Response<UpdateUserDataModel> response) {
                                    if (response.isSuccessful()) {
                                        if (response.body() != null) {
                                            hideLoader();
                                            Toast.makeText(getContext(), "Update Successfully!!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(@NonNull Call<UpdateUserDataModel> call, @NonNull Throwable t) {
                                    Log.e("API_CALL", "API call failed: " + t.getMessage());
                                }
                            });
                        }}}

            }
        });
        setCustomActionBar();
        return root;
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
    private void setCustomActionBar() {
        // Inflate the custom ActionBar layout
        View actionBarView = LayoutInflater.from(requireContext()).inflate(R.layout.edit_profile_action_bar, null);

        // Set custom ActionBar layout as ActionBar
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        actionBar.setCustomView(actionBarView);
        actionBar.setDisplayShowCustomEnabled(true);
        TextView actionBarTitle = actionBarView.findViewById(R.id.edit_profile_action_bar_title);
        actionBarTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        actionBar.setCustomView(actionBarView, params);

    }

    private void showLoader() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void hideLoader() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // Check if the touch event is inside the eye icon bounds
        if (event.getAction() == MotionEvent.ACTION_UP &&
                event.getX() >= (pwd.getWidth() - pwd.getPaddingRight() - eyeIcon.getIntrinsicWidth())) {
            // Toggle password visibility
            togglePasswordVisibility();
            return true;
        }
        return false;
    }
    private void togglePasswordVisibility() {
        // Invert the password visibility flag
        isPasswordVisible = !isPasswordVisible;
        // Update the input type based on the visibility flag
        pwd.setInputType(isPasswordVisible ?
                android.text.InputType.TYPE_CLASS_TEXT :
                android.text.InputType.TYPE_CLASS_TEXT |
                        android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
        // Update the eye icon color and state
        updateEyeIconVisibility();
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
    private void updateEyeIconVisibility() {
        // Get the eye icon drawable
        Drawable wrappedEyeIcon = DrawableCompat.wrap(eyeIcon);
        // Update the eye icon tint color based on password visibility
        DrawableCompat.setTint(wrappedEyeIcon, isPasswordVisible ?
                ContextCompat.getColor(getContext(), R.color.eyeIconVisibleColor) :
                ContextCompat.getColor(getContext(), R.color.eyeIconHiddenColor));
        // Set the eye icon as a compound drawable
        pwd.setCompoundDrawables(null, null, isPasswordVisible ? wrappedEyeIcon : eyeIcon, null);
    }
    @SuppressWarnings("deprecation")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}