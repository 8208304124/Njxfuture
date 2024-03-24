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

import com.example.njxfuture.R;
import com.example.njxfuture.databinding.FragmentNotificationTabAllBinding;
import com.example.njxfuture.databinding.FragmentNotificationsBinding;
import com.example.njxfuture.ui.Adapters.NotificationAdapter;

import java.util.Arrays;
import java.util.List;


public class notification_fragment_tab_all extends Fragment {
    List<String> arrname = Arrays.asList(new String[]{"Alis", "Naina", "Bob", "Julia", "Marry", "Tony Stark", "Chris", "Tom Holland", "Thor", "Elivis", "Leon", "Captain America",
            "Dr Strange", "Ant Man", "Charles", "Hulk"});

    List<String> arrmessage = Arrays.asList(new String[]{"Hi", "Hello", "How are You", "Hello,How are You", "I am fine", "hi,what about you", "your name is thor", "No ",
            "is you are a studen ", "yes i am ", "ok ", "Good night", "Good Morning", "Good AfterNoon", "hello, Sir", "Hi,Maam"});
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
        NotificationAdapter notify = new NotificationAdapter(getContext(), arrname);
        listView.setAdapter(notify);
        Log.d("YourFragment", "onCreateView");
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Log.d("YourFragment", "onViewCreated");
        // Your existing code here
    }

    @Override
    public void onResume() {
        super.onResume();
        NotificationAdapter notify = new NotificationAdapter(getContext(), arrname);
        listView.setAdapter(notify);
    }
}