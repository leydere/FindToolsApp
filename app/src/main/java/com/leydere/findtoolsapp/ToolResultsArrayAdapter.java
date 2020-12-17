package com.leydere.findtoolsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ToolResultsArrayAdapter extends ArrayAdapter<ToolModel> {
    public ToolResultsArrayAdapter(@NonNull Context context, List<ToolModel> toolList) {
        super(context, 0, toolList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.tool_results_list_view, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        ToolModel currentToolPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        // Commented out ImageView is image has not been implemented yet
        /*ImageView numbersImage = currentItemView.findViewById(R.id.imageView);
        assert currentToolPosition != null;
        numbersImage.setImageResource(currentToolPosition.getNumbersImageId());*/

        // then according to the position of the view assign the desired toolNameText TextView for the same
        TextView toolNameText = currentItemView.findViewById(R.id.toolNameText);
        toolNameText.setText(currentToolPosition.getToolName());

        // then according to the position of the view assign the desired TextView 2 for the same
        // code used for second TextView in example
        /*TextView textView2 = currentItemView.findViewById(R.id.textView2);
        textView2.setText(currentToolPosition.getNumbersInText());*/

        // then return the recyclable view
        return currentItemView;
    }
}
