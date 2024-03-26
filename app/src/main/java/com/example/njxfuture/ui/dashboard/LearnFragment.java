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

        articles.setOnClickListener(View -> {
            navController.navigate(R.id.article_activity);
        });
        binding.events.setOnClickListener(v -> {
            navController.navigate(R.id.event_learn_activity);
        });
        binding.webinars.setOnClickListener(v -> {
            navController.navigate(R.id.webinars_learn_activity);
        });
        binding.media.setOnClickListener(v -> {
            navController.navigate(R.id.media_learn_activity);
        });
        binding.strategies.setOnClickListener(v -> {
            navController.navigate(R.id.strategies_learn_activity);
        });
        binding.demoVideos.setOnClickListener(v -> {
            navController.navigate(R.id.demo_videos_learn_activity);
        });
        binding.courses.setOnClickListener(v -> {
            navController.navigate(R.id.courses_learn_activity);
        });
        binding.marketBrief.setOnClickListener(v -> {
            navController.navigate(R.id.market_brief_learn_activity);
        });
        binding.optionClub.setOnClickListener(v -> {
            navController.navigate(R.id.option_club_learn_activity);
        });
        binding.classroom.setOnClickListener(v -> {
            navController.navigate(R.id.classroom_learn_activity);
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