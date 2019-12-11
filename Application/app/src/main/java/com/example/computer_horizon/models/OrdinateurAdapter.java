package com.example.computer_horizon.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.computer_horizon.R;

import java.text.NumberFormat;
import java.util.List;

public class OrdinateurAdapter extends ArrayAdapter<Ordinateur> {
    public OrdinateurAdapter(@NonNull Context context, int resource, @NonNull List<Ordinateur> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.item_ordinateur, null);
        }

        TextView letter = v.findViewById(R.id.tv_letter);
        TextView title = v.findViewById(R.id.tv_title);
        TextView url = v.findViewById(R.id.tv_url);

        final Ordinateur ordi = getItem(position);

        letter.setText(String.valueOf(ordi.getMarque()));
        title.setText(ordi.getNom());

        double prix = ordi.getPrix();
        NumberFormat nm = NumberFormat.getNumberInstance();
        url.setText(nm.format(prix) + " €");

        return v;
    }


}
