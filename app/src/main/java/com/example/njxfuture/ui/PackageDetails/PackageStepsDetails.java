package com.example.njxfuture.ui.PackageDetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.njxfuture.API.DataModels.PackageDataModel.Point;
import com.example.njxfuture.API.DataModels.PackageDataModel.Step;
import com.example.njxfuture.R;
import com.example.njxfuture.databinding.FragmentPackageActionsDetailsBinding;
import com.example.njxfuture.databinding.FragmentPackageStepsDetailsBinding;

import java.util.List;


public class PackageStepsDetails extends Fragment {

    List<Step> steps;
    ListView listView;
    private FragmentPackageStepsDetailsBinding binding;
    public PackageStepsDetails(List<Step> steps) {
        this.steps = steps;
    }
    // Default constructor
    public PackageStepsDetails() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPackageStepsDetailsBinding.inflate(inflater, container, false);

        listView = binding.stepsList;
        if(steps!=null)
        {
            PackageStepsDetailsAdapter adapter = new PackageStepsDetailsAdapter(getContext(), steps);
            listView.setAdapter(adapter);
        }
        return binding.getRoot();
    }
}