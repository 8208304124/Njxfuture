package com.example.njxfuture.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njxfuture.API.APIRequests;
import com.example.njxfuture.API.DataModels.NotificationDataModel;
import com.example.njxfuture.R;
import com.example.njxfuture.databinding.FragmentNotificationTabAllBinding;
import com.example.njxfuture.databinding.FragmentNotificationsBinding;
import com.example.njxfuture.ui.Adapters.NotificationAdapter;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class notification_fragment_tab_all extends Fragment {
  private @NonNull FragmentNotificationTabAllBinding binding;


    ListView listView;

    @Override
    @SuppressLint("ResourceType")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentNotificationTabAllBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = binding.notificationList;
        Call<List<NotificationDataModel>> call = APIRequests.fetchNotifications("860114061759922");
        call.enqueue(new Callback<List<NotificationDataModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<NotificationDataModel>> call, @NonNull Response<List<NotificationDataModel>> response) {
                if (response.isSuccessful()) {
                    if(response.body()!=null)
                    {
                        NotificationAdapter notify = new NotificationAdapter(getContext(), response.body());
                        listView.setAdapter(notify);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<NotificationDataModel>> call, @NonNull Throwable t) {
                Log.e("API_CALL", "API call failed: " + t.getMessage());
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    }

    @Override
    public void onResume() {
        super.onResume();
//        NotificationAdapter notify = new NotificationAdapter(getContext(), arrname);
//        listView.setAdapter(notify);
    }
}