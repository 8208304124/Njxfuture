package com.example.njxfuture.ui.more;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.njxfuture.R;
import com.example.njxfuture.WebView1;
import com.example.njxfuture.databinding.FragmentMoreBinding;
import com.example.njxfuture.ui.Adapters.NotificationAdapter;
import com.example.njxfuture.ui.Adapters.SettingAdatper;

import java.util.Arrays;
import java.util.List;

public class more extends Fragment {

    LinearLayout editProfile, subscription, referandearn;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MoreViewModel moreViewModel =
                new ViewModelProvider(this).get(MoreViewModel.class);

        com.example.njxfuture.databinding.FragmentMoreBinding binding = FragmentMoreBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        editProfile = binding.editProfile;
        subscription = binding.subscription;
        referandearn = binding.referAndEarn;
        editProfile.setOnClickListener(View ->{
            CallEditProfile();
        });
        subscription.setOnClickListener(v->{
            Intent intent = new Intent(getContext(), WebView1.class);
            intent.putExtra("url", "http://njx.revacg.in/");
            startActivity(intent);
        });
        referandearn.setOnClickListener(v->{
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Here is the share content";
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        });
        setCustomActionBar();
        return root;
    }
    private void setCustomActionBar() {
        // Inflate the custom ActionBar layout
        View actionBarView = LayoutInflater.from(requireContext()).inflate(R.layout.more_action_bar, null);

        // Set custom ActionBar layout as ActionBar
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        actionBar.setCustomView(actionBarView);
        actionBar.setDisplayShowCustomEnabled(true);
        TextView actionBarTitle = actionBarView.findViewById(R.id.action_bar_title);
        actionBarTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        actionBar.setCustomView(actionBarView, params);

    }
    void CallEditProfile() {
        if (getActivity() != null) {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.fragment_editProfile); // Replace with the actual destination ID for Articles
        }
    }

}
