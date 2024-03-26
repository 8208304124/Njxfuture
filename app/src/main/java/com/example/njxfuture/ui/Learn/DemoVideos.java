package com.example.njxfuture.ui.Learn;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.njxfuture.API.APIRequests;
import com.example.njxfuture.API.DataModels.ArticleData.ArticleDataModel;
import com.example.njxfuture.R;
import com.example.njxfuture.databinding.FragmentDemoVideosBinding;
import com.example.njxfuture.databinding.FragmentStrategiesBinding;
import com.example.njxfuture.ui.Learn.ArticleFragment.ArticleAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DemoVideos extends Fragment {

    ListView listView;
    private FragmentDemoVideosBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDemoVideosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = binding.demoVideosList;
        ArticleAdapter adapter = new ArticleAdapter(getContext(), new ArrayList<>()); // Initialize the adapter
        listView.setAdapter(adapter);
        Call<List<ArticleDataModel>> call = APIRequests.fetchArticles("860114061759922",6);
        call.enqueue(new Callback<List<ArticleDataModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<ArticleDataModel>> call, @NonNull Response<List<ArticleDataModel>> response) {
                if (response.isSuccessful()) {
                    if(response.body()!=null)
                    {
                        adapter.updateData(response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ArticleDataModel>> call, @NonNull Throwable t) {
                Log.e("API_CALL", "API call failed: " + t.getMessage());
            }
        });
        setHasOptionsMenu(true);
        setCustomActionBar();
        return root;
    }
    public void setCustomActionBar() {
        // Inflate the custom ActionBar layout
        View actionBarView = LayoutInflater.from(requireContext()).inflate(R.layout.article_action_bar, null);

        // Set custom ActionBar layout as ActionBar
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        actionBar.setCustomView(actionBarView);
        TextView titleTextView = actionBarView.findViewById(R.id.learn_action_bar_title);
        titleTextView.setText(R.string.demo_videos1);
        actionBar.setDisplayShowCustomEnabled(true);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        actionBar.setCustomView(actionBarView, params);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}