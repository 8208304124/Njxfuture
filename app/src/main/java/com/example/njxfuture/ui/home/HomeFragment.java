package com.example.njxfuture.ui.home;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.njxfuture.R;
import com.example.njxfuture.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Bundle bundle = new Bundle();
        binding.niftyPremium.setOnClickListener(v->{
            bundle.putString("pckid", "1");
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.package_details,bundle);
        });
        binding.bankNiftyPlatinum.setOnClickListener(v->{
            bundle.putString("pckid", "2");
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.package_details,bundle);
        });
        binding.optionPlatinums.setOnClickListener(v->{
            bundle.putString("pckid", "3");
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.package_details,bundle);
        });
        binding.equityFuture.setOnClickListener(v->{
            bundle.putString("pckid", "4");
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.package_details,bundle);
        });
        binding.equityPremium.setOnClickListener(v->{
            bundle.putString("pckid", "5");
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.package_details,bundle);
        });
        binding.commodityPremium.setOnClickListener(v->{
            bundle.putString("pckid", "6");
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.package_details,bundle);
        });
        binding.currencyPremium.setOnClickListener(v->{
            bundle.putString("pckid", "7");
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.package_details,bundle);
        });
        binding.hniPremium.setOnClickListener(v->{
            bundle.putString("pckid", "8");
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.package_details,bundle);
        });
        setCustomActionBar();
        return root;
    }

    private void setCustomActionBar() {
        // Inflate the custom ActionBar layout
        View actionBarView = LayoutInflater.from(requireContext()).inflate(R.layout.home_action_bar, null);

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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}