package com.example.njxfuture.ui.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.njxfuture.API.DataModels.NotificationDataModel;
import com.example.njxfuture.R;
import com.example.njxfuture.ui.notifications.NotificationsFragment;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.List;
import java.util.regex.Pattern;

public class NotificationAdapter extends ArrayAdapter<NotificationDataModel> {

    private final Context context;
    private final List<NotificationDataModel> items;

    public NotificationAdapter(Context context, List<NotificationDataModel> items) {
        super(context, R.layout.fragment_notifications, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                view = inflater.inflate(R.layout.notification_card_row, parent, false);
            }
        }

        if (view != null) {
            TextView textView = view.findViewById(R.id.name);
            TextView textView1 = view.findViewById(R.id.message);
            ImageView image = view.findViewById(R.id.emails);
//            colorizeUrls(textView1, textView1.getText().toString());
            textView1.setText(items.get(position).getArt());
            textView.setText(items.get(position).gettip());
            String imageUrl = "http://njx.revacg.in/" + items.get(position).getpckimg(); // Assuming this is the URL for each article's image
            Picasso.get().load(imageUrl).into(image);
            TextView dates = view.findViewById(R.id.date);
            dates.setText(formatDateTime(items.get(position).getPdt()));
        }
        view.setOnClickListener(v->{
            Bundle bundle = new Bundle();
            bundle.putString("pckid", items.get(position).getpckid());
            NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.package_details,bundle);
        });
        return view;
    }

    public static String formatDateTime(String inputDateTime) {
        try {
            // Parse the input string into a Date object
            SimpleDateFormat inputFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
            Date date = inputFormat.parse(inputDateTime);

            // Define the desired output format
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMMM-yyyy hh:mma", Locale.ENGLISH);

            // Format the Date object into a string with the desired format
            return outputFormat.format(date);
        } catch (ParseException e) {
            return ""; // Return empty string if parsing fails
        }
    }
//    private void colorizeUrls(TextView textView, String message) {
//        Pattern pattern = Patterns.WEB_URL;
//        Matcher matcher = pattern.matcher(message);
//        int colorRes = R.color.faint_orange;
//        ColorStateList colorStateList = ContextCompat.getColorStateList(context, colorRes);
//        int defaultColor = colorStateList.getDefaultColor();
//        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(message);
//
//        while (matcher.find()) {
//            int start = matcher.start();
//            int end = matcher.end();
//
//            // Make the link clickable
//            spannableStringBuilder.setSpan(
//                    createClickableSpan(message.substring(start, end)),
//                    start,
//                    end,
//                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
//            );
//
//            // Apply styling to the link
//            spannableStringBuilder.setSpan(
//                    new ForegroundColorSpan(defaultColor),
//                    start,
//                    end,
//                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
//            );
//
//        }
//
//        // Set the modified text to the TextView
//        textView.setText(spannableStringBuilder);
//
//        // Enable movement method to make the link clickable
//        textView.setMovementMethod(LinkMovementMethod.getInstance());
//    }
//    private ClickableSpan createClickableSpan(final String url) {
//        return new ClickableSpan() {
//            @Override
//            public void onClick(View widget) {
//                NotificationsFragment temp = new NotificationsFragment();
//                temp.someMethod(context, url);
//            }
//        };
//    }
}
