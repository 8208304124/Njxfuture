package com.example.njxfuture.ui.PackageDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.njxfuture.API.DataModels.PackageDataModel.Point;
import com.example.njxfuture.R;

import java.util.List;

public class PackageActionDetailsAdapter extends ArrayAdapter<Point> {

    private final Context context;
    List<Point> packageActions;
    public PackageActionDetailsAdapter(Context context, List<Point> packageActions) {
        super(context, R.layout.fragment_package_actions_details, packageActions);
        this.context = context;
        this.packageActions=packageActions;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                view = inflater.inflate(R.layout.package_action_steps_row, parent, false);
            }
        }

        if (view != null) {
            TextView textView = view.findViewById(R.id.action_message);
            textView.setText(packageActions.get(position).getInfo());
        }
        return view;
    }
}
