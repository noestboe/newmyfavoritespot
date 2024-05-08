package com.example.spot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ListData> {
    public ListAdapter(@NonNull Context context, ArrayList<ListData> dataArrayList) {
        super(context, R.layout.list_item_spot, dataArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListData listData = getItem(position);
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_spot, parent, false);
        }
        TextView listName = view.findViewById(R.id.listName);
        TextView listTime = view.findViewById(R.id.listTime);
        listName.setText(listData.name);
        listTime.setText(listData.raiting + "/10");
        return view;
    }
}
