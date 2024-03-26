package com.example.njxfuture.ui.PackageDetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.njxfuture.API.DataModels.PackageDataModel.Step;
import com.example.njxfuture.R;

import java.util.List;


public class PackageStepsDetails extends Fragment {

    List<Step> steps;
    public PackageStepsDetails(List<Step> steps) {
        this.steps = steps;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_package_steps_details, container, false);
    }
}