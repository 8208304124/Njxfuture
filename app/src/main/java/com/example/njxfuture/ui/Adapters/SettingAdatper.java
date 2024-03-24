package com.example.njxfuture.ui.Adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.njxfuture.R;

import java.util.Arrays;
import java.util.List;

public class SettingAdatper extends ArrayAdapter<String> {

    private final Context context;
    private final List<String> items = Arrays.asList(new String[]{"VALID TILL NA", "Buy Subscriptions",
            "Quantsapp Web / Desktop", "Login With Touch ID", "My Tickets", "Refer & Earn", "Have a Referral Code", "Rewards & Transaction History"});
    private final int[] icons = new int[]{R.drawable.baseline_more_horiz_24, R.drawable.outline_shopping_cart_24, R.drawable.baseline_laptop_windows_24,
            R.drawable.baseline_fingerprint_24, R.drawable.baseline_airplane_ticket_24, R.drawable.baseline_wc_24, R.drawable.baseline_percent_24, R.drawable.baseline_star_24};

    public SettingAdatper(Context context) {
        super(context, R.layout.fragment_more);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                view = inflater.inflate(R.layout.setting_panel_row, parent, false);
            }
        }

        Log.d("my frag", "asadsads");
        if (view != null) {
            TextView textView = view.findViewById(R.id.setting_panel_name);
//            ImageView icon = view.findViewById(R.id.icon_view);
            textView.setText(items.get(position).toString());
//            icon.setImageResource(icons[position]);
        }
        return view;
    }
}
