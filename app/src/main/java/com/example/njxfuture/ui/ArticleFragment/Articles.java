package com.example.njxfuture.ui.ArticleFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.njxfuture.API.APIRequests;
import com.example.njxfuture.API.DataModels.Account;
import com.example.njxfuture.API.DataModels.ArticleData.ArticleData;
import com.example.njxfuture.API.DataModels.ArticleData.ArticleDataModel;
import com.example.njxfuture.MainActivity;
import com.example.njxfuture.R;
import com.example.njxfuture.Register;
import com.example.njxfuture.ViewPager.Adapters.NotificationPagerAdapter;
import com.example.njxfuture.databinding.FragmentArticlesBinding;
import com.example.njxfuture.databinding.FragmentLearnBinding;
import com.example.njxfuture.databinding.FragmentMoreBinding;
import com.example.njxfuture.ui.Adapters.NotificationAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Articles extends Fragment {
    ListView listView;
    private FragmentArticlesBinding binding;
    private ProgressBar progressBar;
    private static final int LOADER_DURATION_MS = 2000;
    private final int selectedNavItem = R.id.navigation_dashboard;
    private List<ArticleDataModel> items = new ArrayList<>();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentArticlesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = binding.articleList;
        progressBar = root.findViewById(R.id.progressBar);
       ArticleAdapter adapter = new ArticleAdapter(getContext(), new ArrayList<>()); // Initialize the adapter
        listView.setAdapter(adapter);
        showLoader();
        Call<List<ArticleDataModel>> call = APIRequests.fetchArticles("860114061759922");
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
        setCustomActionBar();
        return root;
    }

    public void setCustomActionBar() {
        // Inflate the custom ActionBar layout
        View actionBarView = LayoutInflater.from(requireContext()).inflate(R.layout.article_action_bar, null);

        // Set custom ActionBar layout as ActionBar
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        actionBar.setCustomView(actionBarView);
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

    private void hideLoader() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }
}