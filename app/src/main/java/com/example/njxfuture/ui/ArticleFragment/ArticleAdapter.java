package com.example.njxfuture.ui.ArticleFragment;

import android.content.Context;
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
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List<ArticleDataModel> items;

    public ArticleAdapter(Context context, List<ArticleDataModel> items) {
        super(context, R.layout.fragment_articles, items.size());
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.articles_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.head_name);
            viewHolder.imageView = convertView.findViewById(R.id.icon_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ArticleDataModel item = items.get(position);
        if (item != null) {
            viewHolder.textView.setText(item.getTitle());
            String imageUrl = "http://njx.revacg.in/ATIJxzwGS.jpeg"; // Assuming this is the URL for each article's image
            Picasso.get().load(imageUrl).into(viewHolder.imageView);
        }

        int backgroundColor = (position % 2 == 0) ? R.color.dark : R.color.light_dark;
        convertView.setBackgroundResource(backgroundColor);

        return convertView;
    }
    static class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
