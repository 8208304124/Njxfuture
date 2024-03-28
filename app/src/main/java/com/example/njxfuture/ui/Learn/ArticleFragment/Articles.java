package com.example.njxfuture.ui.Learn.ArticleFragment;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.njxfuture.API.APIRequests;
import com.example.njxfuture.API.DataModels.ArticleData.ArticleDataModel;
import com.example.njxfuture.R;
import com.example.njxfuture.databinding.FragmentArticlesBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Articles extends Fragment {
    ListView listView;
    private FragmentArticlesBinding binding;
    private ProgressBar progressBar;
    int id;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentArticlesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressBar = root.findViewById(R.id.progressBar);
        showLoader();
        listView = binding.articleList;
        Bundle args = getArguments();

        if (args != null) {
            id = args.getInt("pckid");
        }else id=4;

        ArticleAdapter adapter = new ArticleAdapter(getContext(), new ArrayList<>()); // Initialize the adapter
        listView.setAdapter(adapter);
        Call<List<ArticleDataModel>> call = APIRequests.fetchArticles(getDeviceIds(requireContext()),id);
        call.enqueue(new Callback<List<ArticleDataModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<ArticleDataModel>> call, @NonNull Response<List<ArticleDataModel>> response) {
                if (response.isSuccessful()) {
                    if(response.body()!=null)
                        {
                            hideLoader();
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
        if(id==1)
        {
            setCustomActionBar(getResources().getString(R.string.events1));
        }
        else if(id==2){
            setCustomActionBar(getResources().getString(R.string.webinars1));
        }
        else if(id==3){
            setCustomActionBar(getResources().getString(R.string.media1));
        }
        else if(id==4){
            setCustomActionBar(getResources().getString(R.string.articles1));
        }
        else if(id==5){
            setCustomActionBar(getResources().getString(R.string.strategies));
        }
        else if(id==6){
            setCustomActionBar(getResources().getString(R.string.demo_videos1));
        }
        else if(id==7){
            setCustomActionBar(getResources().getString(R.string.courses1));
        }
        else if(id==8){
            setCustomActionBar(getResources().getString(R.string.market_brief1));
        }
        else if(id==9){
            setCustomActionBar(getResources().getString(R.string.option_club1));
        }
        else if(id==10){
            setCustomActionBar(getResources().getString(R.string.classroom1));
        }
        return root;
    }

    public void setCustomActionBar(String title) {
        // Inflate the custom ActionBar layout
        View actionBarView = LayoutInflater.from(requireContext()).inflate(R.layout.article_action_bar, null);

        // Set custom ActionBar layout as ActionBar
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setCustomView(actionBarView);
        TextView text= actionBarView.findViewById(R.id.learn_action_bar_title);
        text.setText(title);
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