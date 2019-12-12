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

public class CarteGAdapter extends ArrayAdapter<CarteG> {
    public CarteGAdapter(@NonNull Context context, int resource, @NonNull List<CarteG> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.item_carte_graphique, null);
        }

        TextView letter = v.findViewById(R.id.tv_letter);
        TextView title = v.findViewById(R.id.tv_title);
        TextView url = v.findViewById(R.id.tv_url);

        final CarteG cg = getItem(position);

        letter.setText(String.valueOf(cg.getMarque()));
        title.setText(cg.getNom());

        double prix = cg.getPrix();
        NumberFormat nm = NumberFormat.getNumberInstance();
        url.setText(nm.format(prix) + " €");

        return v;
    }

}