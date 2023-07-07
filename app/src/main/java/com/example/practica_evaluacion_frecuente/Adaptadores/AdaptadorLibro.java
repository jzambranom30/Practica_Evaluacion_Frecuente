package com.example.practica_evaluacion_frecuente.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.practica_evaluacion_frecuente.Modelo.Libro;
import com.example.practica_evaluacion_frecuente.R;

import java.util.ArrayList;


public class AdaptadorLibro extends ArrayAdapter<Libro> {

    public AdaptadorLibro(Context context, ArrayList<Libro> dato){
        super(context, R.layout.lyitemsdeedicion,dato);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.lyitemsdeedicion, null);

        TextView twNombreEdicion = (TextView)item.findViewById(R.id.twNombreEdicion);
        twNombreEdicion.setText(getItem(position).getTitle());

        TextView twVolumen = (TextView)item.findViewById(R.id.twVolumen);
        twVolumen.setText(getItem(position).getVolume());

        TextView twYear = (TextView)item.findViewById(R.id.twYear);
        twYear.setText(getItem(position).getYear());

        ImageView imageView = (ImageView)item.findViewById(R.id.imgPortadaEdicion);
        Glide.with(this.getContext()).load(getItem(position).getCover()).into(imageView);
        return(item);
    }
}
