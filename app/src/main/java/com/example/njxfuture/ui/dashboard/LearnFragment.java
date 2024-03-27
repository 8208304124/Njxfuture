package com.example.njxfuture.ui.dashboard;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.njxfuture.R;
import com.example.njxfuture.databinding.FragmentLearnBinding;

public class LearnFragment extends Fragment {

    private FragmentLearnBinding binding;
    LinearLayout articles;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLearnBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        articles = binding.articles;
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
        Bundle bundle = new Bundle();
        articles.setOnClickListener(View -> {
            bundle.putInt("pckid", 4);
            navController.navigate(R.id.article_activity,bundle);
        });
        binding.events.setOnClickListener(v -> {
            bundle.putInt("pckid", 1);
            navController.navigate(R.id.article_activity,bundle);
        });
        binding.webinars.setOnClickListener(v -> {
            bundle.putInt("pckid", 2);
            navController.navigate(R.id.article_activity,bundle);
        });
        binding.media.setOnClickListener(v -> {
            bundle.putInt("pckid", 3);
            navController.navigate(R.id.article_activity,bundle);
        });
        binding.strategies.setOnClickListener(v -> {
            bundle.putInt("pckid", 5);
            navController.navigate(R.id.article_activity,bundle);
        });
        binding.demoVideos.setOnClickListener(v -> {
            bundle.putInt("pckid", 6);
            navController.navigate(R.id.article_activity,bundle);
        });
        binding.courses.setOnClickListener(v -> {
            bundle.putInt("pckid", 7);
            navController.navigate(R.id.article_activity,bundle);
        });
        binding.marketBrief.setOnClickListener(v -> {
            bundle.putInt("pckid", 8);
            navController.navigate(R.id.article_activity,bundle);
        });
        binding.optionClub.setOnClickListener(v -> {
            bundle.putInt("pckid", 9);
            navController.navigate(R.id.article_activity,bundle);
        });
        binding.classroom.setOnClickListener(v -> {
            bundle.putInt("pckid", 10);
            navController.navigate(R.id.article_activity,bundle);
        });
        setCustomActionBar();
        return root;
    }

    private void setCustomActionBar() {
        // Inflate the custom ActionBar layout
        View actionBarView = LayoutInflater.from(requireContext()).inflate(R.layout.learn_action_bar, null);

        // Set custom ActionBar layout as ActionBar
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setCustomView(actionBarView);
        actionBar.setDisplayShowCustomEnabled(true);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        actionBar.setCustomView(actionBarView, params);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}