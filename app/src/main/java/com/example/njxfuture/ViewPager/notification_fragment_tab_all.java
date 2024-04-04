package com.example.njxfuture.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

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
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class notification_fragment_tab_all extends Fragment {
  private @NonNull FragmentNotificationTabAllBinding binding;


    ListView listView;
    private ProgressBar progressBar;

    @Override
    @SuppressLint("ResourceType")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentNotificationTabAllBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressBar = root.findViewById(R.id.progressBar);
        listView = binding.notificationList;

        showLoader();
        Call<List<NotificationDataModel>> call = APIRequests.fetchNotifications("79489f81-16dd-45d8-ab13-39d8635b0857");
        call.enqueue(new Callback<List<NotificationDataModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<NotificationDataModel>> call, @NonNull Response<List<NotificationDataModel>> response) {
                if (response.isSuccessful()) {
                    if(response.body()!=null)
                    {
                        hideLoader();
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
    private void showLoader() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }
    public String getDeviceIds(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String deviceId = sharedPreferences.getString("device_id", null);
        if (deviceId == null) {
            deviceId = UUID.randomUUID().toString();
            sharedPreferences.edit().putString("device_id", deviceId).apply();
        }
        return deviceId;
    }
    private void hideLoader() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }
}