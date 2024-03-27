package com.example.njxfuture.ui.PackageDetails;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.example.njxfuture.API.DataModels.PackageDataModel.Step;
import com.example.njxfuture.R;
import com.example.njxfuture.databinding.FragmentPackageStepsDetailsBinding;

import java.util.List;


public class PackageStepsDetails extends Fragment {

    List<Step> steps;
    ListView listView;
    private FragmentPackageStepsDetailsBinding binding;
    private ProgressBar progressBar;
    public PackageStepsDetails(List<Step> steps) {
        this.steps = steps;
    }
    // Default constructor
    public PackageStepsDetails() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPackageStepsDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressBar = root.findViewById(R.id.progressBar);
        listView = binding.stepsList;
        showLoader();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if(steps!=null)
            {
                hideLoader();
                PackageStepsDetailsAdapter adapter = new PackageStepsDetailsAdapter(getContext(), steps);
                listView.setAdapter(adapter);
            }
        }, 2000);

        return root;
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
}