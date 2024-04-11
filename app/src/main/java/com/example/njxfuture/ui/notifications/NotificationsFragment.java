package com.example.njxfuture.ui.notifications;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.njxfuture.R;
import com.example.njxfuture.ViewPager.Adapters.NotificationPagerAdapter;
import com.example.njxfuture.WebView1;
import com.example.njxfuture.databinding.FragmentNotificationsBinding;
import com.google.android.material.tabs.TabLayout;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    ViewPager viewPager;
    TabLayout tabLayout;

    @SuppressLint("ResourceType")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        tabLayout = binding.tabs;
        tabLayout.setTabTextColors(ContextCompat.getColorStateList(getContext(), R.color.tab_color_selector));
        int nightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        // Set the default night mode for the app
        if (nightMode == Configuration.UI_MODE_NIGHT_YES) {
            tabLayout.setBackgroundColor(getResources().getColor(R.color.tabsDark));
        } else {
            tabLayout.setBackgroundColor(getResources().getColor(R.color.soft_grey));
        }
        FragmentManager fragmentManager = getFragmentManager();
        viewPager = binding.notificationViewpager;
        assert fragmentManager != null;
        NotificationPagerAdapter adapter = new NotificationPagerAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(adapter);
        int initialTabPosition = 0;
        viewPager.setOffscreenPageLimit(1);
        viewPager.setCurrentItem(initialTabPosition);
        tabLayout.setupWithViewPager(viewPager);
        setCustomActionBar();
        return root;
    }

    private void setCustomActionBar() {
        // Inflate the custom ActionBar layout
        View actionBarView = LayoutInflater.from(requireContext()).inflate(R.layout.notification_action_bar, null);

        // Set custom ActionBar layout as ActionBar
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        actionBar.setCustomView(actionBarView);
        actionBar.setDisplayShowCustomEnabled(true);
        TextView actionBarTitle = actionBarView.findViewById(R.id.notification_action_bar);

        actionBarTitle.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1));
        actionBarTitle.setGravity(android.view.Gravity.CENTER);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        actionBar.setCustomView(actionBarView, params);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        viewPager = null;
        tabLayout = null;
    }

    @SuppressLint("ResourceType")
    public void updateData(Context context) {
        tabLayout = binding.tabs;
        tabLayout.setTabTextColors(ContextCompat.getColorStateList(context, R.color.tab_color_selector));

        requireView().post(() -> {
            FragmentManager fragmentManager = getFragmentManager();
            viewPager = binding.notificationViewpager;
            assert fragmentManager != null;
            NotificationPagerAdapter adapter = new NotificationPagerAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            viewPager.setOffscreenPageLimit(1);
            viewPager.setAdapter(adapter);
            int initialTabPosition = 0;
            viewPager.setOffscreenPageLimit(1);
            viewPager.setCurrentItem(initialTabPosition);
            tabLayout.setupWithViewPager(viewPager);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (binding != null)
            updateData(requireContext());
    }

    public void someMethod(Context context, String url) {
        Intent intent = new Intent(context, WebView1.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
}