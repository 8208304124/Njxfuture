package com.example.njxfuture.ui.Adapters;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.njxfuture.R;
import com.example.njxfuture.ui.notifications.NotificationsFragment;

import java.util.regex.Matcher;
import java.util.List;
import java.util.regex.Pattern;
public class NotificationAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List<String> items;

    public NotificationAdapter(Context context, List<String> items) {
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
            colorizeUrls(textView1, textView1.getText().toString());
               textView.setText(items.get(position));
        }
        return view;
    }
    private void colorizeUrls(TextView textView, String message) {
        Pattern pattern = Patterns.WEB_URL;
        Matcher matcher = pattern.matcher(message);
        int colorRes = R.color.faint_orange;
        ColorStateList colorStateList = ContextCompat.getColorStateList(context, colorRes);
        int defaultColor = colorStateList.getDefaultColor();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(message);

        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();

            // Make the link clickable
            spannableStringBuilder.setSpan(
                    createClickableSpan(message.substring(start, end)),
                    start,
                    end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );

            // Apply styling to the link
            spannableStringBuilder.setSpan(
                    new ForegroundColorSpan(defaultColor),
                    start,
                    end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );

        }

        // Set the modified text to the TextView
        textView.setText(spannableStringBuilder);

        // Enable movement method to make the link clickable
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
    private ClickableSpan createClickableSpan(final String url) {
        return new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                NotificationsFragment temp = new NotificationsFragment();
                temp.someMethod(context, url);
            }
        };
    }
}
