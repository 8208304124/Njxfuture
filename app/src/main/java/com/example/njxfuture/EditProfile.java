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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.njxfuture.API.APIRequests;
import com.example.njxfuture.API.DataModels.ArticleData.ArticleDataModel;
import com.example.njxfuture.API.DataModels.UpdateUserDataModel;
import com.example.njxfuture.API.DataModels.User;
import com.example.njxfuture.databinding.FragmentArticlesBinding;
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
                Call<UpdateUserDataModel> call1 = APIRequests.updateUserDetails(getDeviceIds(requireContext()),uname,uno,ugst,upwd,umail);
                call1.enqueue(new Callback<UpdateUserDataModel>() {
                    @Override
                    public void onResponse(@NonNull Call<UpdateUserDataModel> call, @NonNull Response<UpdateUserDataModel> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                hideLoader();
                                Toast.makeText(getContext(),"Update Successfully!!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UpdateUserDataModel> call, @NonNull Throwable t) {
                        Log.e("API_CALL", "API call failed: " + t.getMessage());
                    }
                });
            }
        });
        setCustomActionBar();
        return root;
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