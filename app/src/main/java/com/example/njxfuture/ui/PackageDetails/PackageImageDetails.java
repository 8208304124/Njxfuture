package com.example.njxfuture.ui.PackageDetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.njxfuture.R;
import com.example.njxfuture.databinding.FragmentPackageDetailsBinding;
import com.example.njxfuture.databinding.FragmentPackageImageDetailsBinding;
import com.squareup.picasso.Picasso;

public class PackageImageDetails extends Fragment {
    String img;
    private FragmentPackageImageDetailsBinding binding;
    public PackageImageDetails(String img) {
        this.img = img;
    }
    public PackageImageDetails() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPackageImageDetailsBinding.inflate(inflater, container, false);
        ImageView packImage = binding.packImage;
        if(img!=null)
        {
            String imageUrl = "http://njx.revacg.in/" + img;
            Picasso.get().load(imageUrl).into(packImage);
        }
        return binding.getRoot();
    }
}