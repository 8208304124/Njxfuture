package com.example.njxfuture;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.njxfuture.databinding.FragmentArticlesBinding;
import com.example.njxfuture.databinding.FragmentEditProfileBinding;

public class EditProfile extends Fragment {

    private FragmentEditProfileBinding binding;
    String umail, uname, ugst, upwd, uno;
    Button update;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.userEmail.addTextChangedListener(new TextWatcher() {
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
        binding.editUserName.addTextChangedListener(new TextWatcher() {
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
        binding.editMobileNo.addTextChangedListener(new TextWatcher() {
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
        binding.editGst.addTextChangedListener(new TextWatcher() {
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
        binding.changePwd.addTextChangedListener(new TextWatcher() {
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
        binding.updateBtn.setOnClickListener(v->{
            if(!uname.isEmpty() && !umail.isEmpty() && !upwd.isEmpty() && !ugst.isEmpty() && !uno.isEmpty()){
                
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
}