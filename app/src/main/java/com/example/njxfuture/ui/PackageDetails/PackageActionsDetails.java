package com.example.njxfuture.ui.PackageDetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.njxfuture.API.DataModels.PackageDataModel.Point;
import com.example.njxfuture.R;
import com.example.njxfuture.databinding.FragmentPackageActionsDetailsBinding;
import com.example.njxfuture.databinding.FragmentPackageImageDetailsBinding;

import java.util.List;

public class PackageActionsDetails extends Fragment {

    List<Point> points;
    ListView listView;
    private FragmentPackageActionsDetailsBinding binding;

    public PackageActionsDetails(List<Point> points) {
        this.points = points;
    }
    public PackageActionsDetails() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPackageActionsDetailsBinding.inflate(inflater, container, false);
        listView = binding.actionList;
        if(points!=null) {
            PackageActionDetailsAdapter adapter = new PackageActionDetailsAdapter(getContext(), points);
            listView.setAdapter(adapter);
        }
        return binding.getRoot();
    }
}