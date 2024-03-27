package com.example.njxfuture.ViewPager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.njxfuture.API.DataModels.MoneyControlDataModels.RSSFeed;
import com.example.njxfuture.API.RSSClient;
import com.example.njxfuture.R;
import com.example.njxfuture.ViewPager.Adapters.NotificationCommonAdapter;
import com.example.njxfuture.databinding.FragmentNotificationTabGeneralBinding;
import com.example.njxfuture.databinding.FragmentNotificationTabMtmAlertsBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


public class notification_fragment_tab_general extends Fragment {
    private @NonNull FragmentNotificationTabGeneralBinding binding;
    ListView listView;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotificationTabGeneralBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressBar = root.findViewById(R.id.progressBar);
        showLoader();
        listView = binding.mtmList;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.moneycontrol.com/rss/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        RSSClient.RSSService2 service = retrofit.create(RSSClient.RSSService2.class);
        Call<RSSFeed> call = service.getTopNews();
        call.enqueue(new Callback<RSSFeed>() {
            @Override
            public void onResponse(Call<RSSFeed> call, Response<RSSFeed> response) {
                if (response.isSuccessful()) {
                    RSSFeed feed = response.body();
                    hideLoader();
                    NotificationCommonAdapter notifyAdapter = new NotificationCommonAdapter(getContext(),feed.getChannels().get(0).getItems());
                    listView.setAdapter(notifyAdapter);
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<RSSFeed> call, Throwable t) {
                // Handle network errors
            }
        });

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