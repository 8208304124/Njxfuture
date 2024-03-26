package com.example.njxfuture.ui.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.njxfuture.API.DataModels.PackageDataModel.PackageDetailsDataModel;
import com.example.njxfuture.ui.PackageDetails.PackageActionsDetails;
import com.example.njxfuture.ui.PackageDetails.PackageImageDetails;
import com.example.njxfuture.ui.PackageDetails.PackageStepsDetails;

public class PackageDetailsAdapter extends FragmentPagerAdapter {

    PackageDetailsDataModel Data;
    public PackageDetailsAdapter(@NonNull FragmentManager fm, int behavior,PackageDetailsDataModel data) {
        super(fm, behavior);
        this.Data = data;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PackageImageDetails(Data.getImg());
            case 1:
                return new PackageActionsDetails(Data.getPoints());
            case 2:
                return new PackageStepsDetails(Data.getSteps());
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3; // Number of tabs
    }

    @NonNull
    @Override
    public CharSequence getPageTitle(int position) {
        // Set tab titles
        switch (position) {
            case 0:
                return "Image";
            case 1:
                return "Actions";
            case 2:
                return "Steps";
            default:
                return null;
        }
    }
}
