package com.example.njxfuture.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.njxfuture.databinding.FragmentNotificationTabMtmAlertsBinding;



public class notification_fragment_tab_mtm_alerts extends Fragment {
    private @NonNull FragmentNotificationTabMtmAlertsBinding binding;

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentNotificationTabMtmAlertsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Log.d("my frag","calll");
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://www.moneycontrol.com/rss/")
//                .addConverterFactory(SimpleXmlConverterFactory.create())
//                .build();
//
//        RSSService service = retrofit.create(RSSService.class);
//        Call<RSSFeed> call = service.getTopNews();
//        call.enqueue(new Callback<RSSFeed>() {
//            @Override
//            public void onResponse(Call<RSSFeed> call, Response<RSSFeed> response) {
//                if (response.isSuccessful()) {
//                    RSSFeed feed = response.body();
//                    recyclerView = binding.recyclerView;
//                    adapter = new MTMAdapter(feed);
//                    recyclerView.setAdapter(adapter);
//                  Log.d("my frag","sadasasd"+response.body());
//                } else {
//                    // Handle unsuccessful response
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RSSFeed> call, Throwable t) {
//                // Handle network errors
//            }
//        });
        return root;
    }
}

