package com.example.njxfuture.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njxfuture.API.DataModels.MTMDataModel;
import com.example.njxfuture.API.MoneyControlsAPI;
import com.example.njxfuture.R;
import com.example.njxfuture.ViewPager.MTM_alerts.MTMAdapter;
import com.example.njxfuture.databinding.FragmentNotificationTabAllBinding;
import com.example.njxfuture.databinding.FragmentNotificationTabMtmAlertsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;

public class notification_fragment_tab_mtm_alerts extends Fragment {
    private @NonNull FragmentNotificationTabMtmAlertsBinding binding;
    public interface RSSService {
        @GET("MCtopnews.xml")
        Call<RSSFeed> getTopNews();
    }
    private RecyclerView recyclerView;
    private MTMAdapter adapter;
    private List<RSSItem> rssItems = new ArrayList<>();
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

