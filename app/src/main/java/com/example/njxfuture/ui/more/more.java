package com.example.njxfuture.ui.more;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
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

import com.example.njxfuture.API.UserCredentialsManager;
import com.example.njxfuture.R;
import com.example.njxfuture.WebView1;
import com.example.njxfuture.databinding.FragmentMoreBinding;
import com.example.njxfuture.ui.Adapters.NotificationAdapter;
import com.example.njxfuture.ui.Adapters.SettingAdatper;

import java.util.Arrays;
import java.util.List;

public class more extends Fragment {

    LinearLayout editProfile, subscription, whatsup, linkedin, instagram, twitter, writeEmail, helps, facebok,tickets,rewards;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MoreViewModel moreViewModel = new ViewModelProvider(this).get(MoreViewModel.class);
        UserCredentialsManager credentialsManager = new UserCredentialsManager(getContext());
        com.example.njxfuture.databinding.FragmentMoreBinding binding = FragmentMoreBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        editProfile = binding.editProfile;
        subscription = binding.subscription;
        whatsup = binding.whatsUp;
        tickets = binding.tickets;
        rewards = binding.rewards;
        linkedin = binding.lindin;
        instagram = binding.instagram;
        twitter = binding.twitter;
        writeEmail = binding.emails;
        helps = binding.helps;
        facebok = binding.facebook;
        int nightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        // Set the default night mode for the app
        if (nightMode == Configuration.UI_MODE_NIGHT_YES) {
           editProfile.setBackgroundColor(getResources().getColor(R.color.light_dark));
            subscription.setBackgroundColor(getResources().getColor(R.color.light_dark));
            whatsup.setBackgroundColor(getResources().getColor(R.color.light_dark));
            linkedin.setBackgroundColor(getResources().getColor(R.color.light_dark));
            instagram.setBackgroundColor(getResources().getColor(R.color.light_dark));
            twitter.setBackgroundColor(getResources().getColor(R.color.light_dark));
            writeEmail.setBackgroundColor(getResources().getColor(R.color.light_dark));
            helps.setBackgroundColor(getResources().getColor(R.color.light_dark));
            facebok.setBackgroundColor(getResources().getColor(R.color.light_dark));
            tickets.setBackgroundColor(getResources().getColor(R.color.light_dark));
            rewards.setBackgroundColor(getResources().getColor(R.color.light_dark));
        } else {
            editProfile.setBackgroundColor(getResources().getColor(R.color.soft_grey));
            subscription.setBackgroundColor(getResources().getColor(R.color.soft_grey));
            whatsup.setBackgroundColor(getResources().getColor(R.color.soft_grey));
            linkedin.setBackgroundColor(getResources().getColor(R.color.soft_grey));
            instagram.setBackgroundColor(getResources().getColor(R.color.soft_grey));
            twitter.setBackgroundColor(getResources().getColor(R.color.soft_grey));
            writeEmail.setBackgroundColor(getResources().getColor(R.color.soft_grey));
            helps.setBackgroundColor(getResources().getColor(R.color.soft_grey));
            facebok.setBackgroundColor(getResources().getColor(R.color.soft_grey));
            tickets.setBackgroundColor(getResources().getColor(R.color.soft_grey));
            rewards.setBackgroundColor(getResources().getColor(R.color.soft_grey));
        }
        editProfile.setOnClickListener(View -> {
            CallEditProfile();
        });
        subscription.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), WebView1.class);
            intent.putExtra("url", "http://njx.revacg.in/");
            startActivity(intent);
        });
        whatsup.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://api.whatsapp.com/send/?phone=%2B918788028670&text=Hello&type=phone_number&app_absent=0"));
            startActivity(intent);
        });
        linkedin.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), WebView1.class);
            intent.putExtra("url", "https://in.linkedin.com/company/bseindia");
            startActivity(intent);
        });
        instagram.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), WebView1.class);
            intent.putExtra("url", "https://www.instagram.com/bombaystockexchange/");
            startActivity(intent);
        });
        twitter.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), WebView1.class);
            intent.putExtra("url", "https://twitter.com/BSEIndia");
            startActivity(intent);
        });

        writeEmail.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:")); // Only email apps should handle this
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"sardarohan05@gmail.com"}); // Add recipient email
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Inquiry"); // Add email subject
            startActivity(emailIntent);
        });
        helps.setOnClickListener(v -> {

            String pwd = credentialsManager.getPassword();
            String pname = credentialsManager.getUsername();
            Intent intent = new Intent(getContext(), WebView1.class);
            intent.putExtra("url", "https://njx.revacg.in/help.php?u=" + pname + "&p=" + pwd);
            startActivity(intent);
        });
        facebok.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.facebook.com/BombayStockExchange"));
            startActivity(intent);
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
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        actionBar.setCustomView(actionBarView, params);

    }

    void CallEditProfile() {
        if (getActivity() != null) {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.fragment_editProfile); // Replace with the actual destination ID for Articles
        }
    }

}
