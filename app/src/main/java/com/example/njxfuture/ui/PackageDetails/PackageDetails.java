package com.example.njxfuture.ui.PackageDetails;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PackageDetails extends Fragment {

    private FragmentPackageDetailsBinding binding;
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPackageDetailsBinding.inflate(inflater, container, false);
        tabLayout = binding.packageTabs;
        tabLayout.setTabTextColors(ContextCompat.getColorStateList(getContext(), R.color.tab_color_selector));
        FragmentManager fragmentManager = getFragmentManager();
        viewPager = binding.packageViewpager;
        assert fragmentManager != null;

        Call<PackageDetailsDataModel> call = APIRequests.fetchPackages("123456789","1","18-03-2024");
        call.enqueue(new Callback<PackageDetailsDataModel>() {
            @Override
            public void onResponse(@NonNull Call<PackageDetailsDataModel> call, @NonNull Response<PackageDetailsDataModel> response) {
                if (response.isSuccessful()) {
                    if(response.body()!=null)
                    {
                        PackageDetailsAdapter adapter = new PackageDetailsAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,response.body());
                        viewPager.setOffscreenPageLimit(1);
                        viewPager.setAdapter(adapter);
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

        return binding.getRoot();
    }
}