package com.example.njxfuture.ui.PackageDetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.njxfuture.API.DataModels.PackageDataModel.Point;
import com.example.njxfuture.R;
import com.example.njxfuture.databinding.FragmentPackageActionsDetailsBinding;
import com.example.njxfuture.databinding.FragmentPackageImageDetailsBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PackageActionsDetails extends Fragment {

    List<Point> points;
    ListView listView;
    private FragmentPackageActionsDetailsBinding binding;
    private ProgressBar progressBar;

    public PackageActionsDetails(List<Point> points) {
        this.points = points;
    }
    public PackageActionsDetails() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPackageActionsDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = binding.actionList;
        progressBar = root.findViewById(R.id.progressBar);
        showLoader();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if(points!=null) {
                hideLoader();
                PackageActionDetailsAdapter adapter = new PackageActionDetailsAdapter(getContext(), points);
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