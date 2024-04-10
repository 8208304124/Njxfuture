package com.example.njxfuture.ui.PackageDetails;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.njxfuture.API.APIRequests;
import com.example.njxfuture.API.DataModels.NotificationDataModel;
import com.example.njxfuture.API.DataModels.PackageDataModel.PackageDetailsDataModel;
import com.example.njxfuture.R;
import com.example.njxfuture.ViewPager.Adapters.NotificationPagerAdapter;
import com.example.njxfuture.databinding.FragmentPackageDetailsBinding;
import com.example.njxfuture.ui.Adapters.NotificationAdapter;
import com.example.njxfuture.ui.Adapters.PackageDetailsAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PackageDetails extends Fragment {

    private FragmentPackageDetailsBinding binding;
    TabLayout tabLayout;
    ViewPager viewPager;
    String id="";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPackageDetailsBinding.inflate(inflater, container, false);
        tabLayout = binding.packageTabs;
        int nightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        // Set the default night mode for the app
        if (nightMode == Configuration.UI_MODE_NIGHT_YES) {
            tabLayout.setBackgroundColor(getResources().getColor(R.color.tabsDark));
        } else {
           tabLayout.setBackgroundColor(getResources().getColor(R.color.white));
        }
        tabLayout.setTabTextColors(ContextCompat.getColorStateList(getContext(), R.color.tab_color_selector));
        FragmentManager fragmentManager = getFragmentManager();
        viewPager = binding.packageViewpager;
        assert fragmentManager != null;
        Bundle args = getArguments();

        if (args != null) {
             id = args.getString("pckid");
        }else id="1";

        Call<PackageDetailsDataModel> call = APIRequests.fetchPackages(getDeviceIds(getContext()),id,"18-03-2024");
        call.enqueue(new Callback<PackageDetailsDataModel>() {
            @Override
            public void onResponse(@NonNull Call<PackageDetailsDataModel> call, @NonNull Response<PackageDetailsDataModel> response) {
                if (response.isSuccessful()) {
                    if(response.body()!=null)
                    {
                        PackageDetailsAdapter adapter = new PackageDetailsAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,response.body());
                        viewPager.setAdapter(adapter);
                        viewPager.setOffscreenPageLimit(1);
                        int initialTabPosition = 0;
                        viewPager.setOffscreenPageLimit(1);
                        viewPager.setCurrentItem(initialTabPosition);
                        tabLayout.setupWithViewPager(viewPager);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<PackageDetailsDataModel> call, @NonNull Throwable t) {
                Log.e("API_CALL", "API call failed: " + t.getMessage());
            }
        });
        setHasOptionsMenu(true);
        return binding.getRoot();
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
        tabLayout = binding.packageTabs;
        tabLayout.setTabTextColors(ContextCompat.getColorStateList(context, R.color.tab_color_selector));
        requireView().post(() -> {
            tabLayout = binding.packageTabs;
            tabLayout.setTabTextColors(ContextCompat.getColorStateList(getContext(), R.color.tab_color_selector));
            FragmentManager fragmentManager = getFragmentManager();
            viewPager = binding.packageViewpager;
            assert fragmentManager != null;
            Call<PackageDetailsDataModel> call = APIRequests.fetchPackages(getDeviceIds(getContext()),id,"18-03-2024");
            call.enqueue(new Callback<PackageDetailsDataModel>() {
                @Override
                public void onResponse(@NonNull Call<PackageDetailsDataModel> call, @NonNull Response<PackageDetailsDataModel> response) {
                    if (response.isSuccessful()) {
                        if(response.body()!=null)
                        {
                            PackageDetailsAdapter adapter = new PackageDetailsAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,response.body());
                            viewPager.setAdapter(adapter);
                            viewPager.setOffscreenPageLimit(1);
                            int initialTabPosition = 0;
                            viewPager.setOffscreenPageLimit(1);
                            viewPager.setCurrentItem(initialTabPosition);
                            tabLayout.setupWithViewPager(viewPager);
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<PackageDetailsDataModel> call, @NonNull Throwable t) {
                    Log.e("API_CALL", "API call failed: " + t.getMessage());
                }
            });

        });
    }
    @Override
    public void onResume() {
        super.onResume();
        if (binding != null)
            updateData(requireContext());
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