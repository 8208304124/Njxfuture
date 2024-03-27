package com.example.njxfuture.ui.PackageDetails;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.njxfuture.R;
import com.example.njxfuture.databinding.FragmentPackageImageDetailsBinding;
import com.squareup.picasso.Picasso;

public class PackageImageDetails extends Fragment {
    String img;
    private FragmentPackageImageDetailsBinding binding;
    private ProgressBar progressBar;
    public PackageImageDetails(String img) {
        this.img = img;
    }
    public PackageImageDetails() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPackageImageDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressBar = root.findViewById(R.id.progressBar);
        showLoader();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            ImageView packImage = binding.packImage;
            if(img!=null)
            {
                String imageUrl = "http://njx.revacg.in/" + img;
                hideLoader();
                Picasso.get().load(imageUrl).into(packImage);
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