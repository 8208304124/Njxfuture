package com.example.njxfuture;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.njxfuture.databinding.FragmentPackageDetailsBinding;
import com.google.android.material.tabs.TabLayout;


public class PackageDetails extends Fragment {

    private FragmentPackageDetailsBinding binding;
    TabLayout tabLayout;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPackageDetailsBinding.inflate(inflater, container, false);
        tabLayout = binding.packageTabs;
        tabLayout.setTabTextColors(ContextCompat.getColorStateList(getContext(), R.color.tab_color_selector));


        return binding.getRoot();
    }
}