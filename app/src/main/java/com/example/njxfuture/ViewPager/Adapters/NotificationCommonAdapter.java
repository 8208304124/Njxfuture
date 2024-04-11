package com.example.njxfuture.ViewPager.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.njxfuture.API.DataModels.ArticleData.ArticleDataModel;
import com.example.njxfuture.API.DataModels.MoneyControlDataModels.Item;
import com.example.njxfuture.R;
import com.example.njxfuture.WebView1;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NotificationCommonAdapter extends ArrayAdapter<Item> {

    private final Context context;
    private final List<Item> items;

    public NotificationCommonAdapter(Context context, List<Item> items) {
        super(context, R.layout.fragment_notification_tab_mtm_alerts, items);
        this.context = context;
        this.items = items;
    }
    // Method to update the data
    public void updateData(List<Item> newArticles) {
        items.clear();
        items.addAll(newArticles);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                view = inflater.inflate(R.layout.articles_row, parent, false);
            }
        }

        Item item = items.get(position);
        if (view != null) {
            TextView textView = view.findViewById(R.id.head_name);
            TextView textView1 = view.findViewById(R.id.desc);
            String imageUrl = item.getImageSrc(); // Assuming this is the URL for each article's image
            ImageView image = view.findViewById(R.id.icon_view);
            textView.setText(item.getTitle());
            textView1.setText(item.getDescription());
            Picasso.get().load(imageUrl).into(image);
        }
        int nightMode = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        // Set the default night mode for the app
        if (nightMode == Configuration.UI_MODE_NIGHT_YES) {
            int backgroundColor = (position % 2 == 0) ? R.color.dark : R.color.light_dark;
            view.setBackgroundResource(backgroundColor);
        } else {
            int backgroundColor = (position % 2 == 0) ? R.color.soft_grey : R.color.white;
            view.setBackgroundResource(backgroundColor);
        }
        // Set onClick listener for the item
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebView1.class);
                intent.putExtra("url", item.getLink());
                context.startActivity(intent);
            }
        });
        return view;

    }
}
