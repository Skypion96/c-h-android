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

public class ProcesseurAdapter extends ArrayAdapter<Processeur> {
    public ProcesseurAdapter(@NonNull Context context, int resource, @NonNull List<Processeur> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.item_processeur, null);
        }

        TextView letter = v.findViewById(R.id.tv_letter_proc);
        TextView title = v.findViewById(R.id.tv_title_proc);
        TextView url = v.findViewById(R.id.tv_url_proc);

        final Processeur proc = getItem(position);

        letter.setText(String.valueOf(proc.getMarque()));
        title.setText(proc.getNom());

        double prix = proc.getPrix();
        NumberFormat nm = NumberFormat.getNumberInstance();
        url.setText(nm.format(prix) + " €");

        return v;
    }


}
