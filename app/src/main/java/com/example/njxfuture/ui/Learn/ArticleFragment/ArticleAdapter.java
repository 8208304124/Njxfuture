package com.example.njxfuture.ui.Learn.ArticleFragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.njxfuture.API.DataModels.ArticleData.ArticleDataModel;
import com.example.njxfuture.R;
import com.example.njxfuture.WebView1;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<ArticleDataModel> {

    private final Context context;
    private final List<ArticleDataModel> items;

    public ArticleAdapter(Context context, List<ArticleDataModel> items) {
        super(context, R.layout.fragment_articles, items);
        this.context = context;
        this.items = items;
    }
    // Method to update the data
    public void updateData(List<ArticleDataModel> newArticles) {
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

        ArticleDataModel item = items.get(position);
        if (view != null) {
            TextView textView = view.findViewById(R.id.head_name);
            TextView textView1 = view.findViewById(R.id.desc);
            String imageUrl = "http://njx.revacg.in/"+item.getImg(); // Assuming this is the URL for each article's image
            ImageView image = view.findViewById(R.id.icon_view);
            textView.setText(item.getTitle());
            textView1.setText(item.getArt());
            Picasso.get().load(imageUrl).into(image);
        }
//        int backgroundColor = (position % 2 == 0) ? R.color.dark : R.color.light_dark;
//        view.setBackgroundResource(backgroundColor);
//        // Set onClick listener for the item
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
