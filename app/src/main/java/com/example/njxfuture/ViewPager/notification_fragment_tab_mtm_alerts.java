package com.example.njxfuture.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.njxfuture.API.DataModels.MoneyControlDataModels.RSSFeed;
import com.example.njxfuture.API.RSSClient;
import com.example.njxfuture.R;
import com.example.njxfuture.ViewPager.Adapters.NotificationCommonAdapter;
import com.example.njxfuture.databinding.FragmentNotificationTabMtmAlertsBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


public class notification_fragment_tab_mtm_alerts extends Fragment {
    private @NonNull FragmentNotificationTabMtmAlertsBinding binding;
    ListView listView;
    private ProgressBar progressBar;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentNotificationTabMtmAlertsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressBar = root.findViewById(R.id.progressBar);
        showLoader();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.moneycontrol.com/rss/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        listView = binding.mtmList;
        RSSClient.RSSService service = retrofit.create(RSSClient.RSSService.class);
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

