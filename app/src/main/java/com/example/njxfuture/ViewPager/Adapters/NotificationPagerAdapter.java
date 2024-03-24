package com.example.njxfuture.ViewPager.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.njxfuture.ViewPager.notification_fragment_tab_all;

public class NotificationPagerAdapter extends FragmentPagerAdapter {

    public NotificationPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new notification_fragment_tab_all();
            case 1:
                return new com.example.njxfuture.ViewPager.Adapters.notification_fragment_tab_advisory();
            case 2:
                return new com.example.njxfuture.ViewPager.Adapters.notification_fragment_tab_general();
            case 3:
                return new com.example.njxfuture.ViewPager.Adapters.notification_fragment_tab_mtm_alerts();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4; // Number of tabs
    }

    @NonNull
    @Override
    public CharSequence getPageTitle(int position) {
        // Set tab titles
        switch (position) {
            case 0:
                return "All";
            case 1:
                return "Advisory";
            case 2:
                return "General";
            case 3:
                return "MTM Alerts";
            default:
                return null;
        }
    }
}
