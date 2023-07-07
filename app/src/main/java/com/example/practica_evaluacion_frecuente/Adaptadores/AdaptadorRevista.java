package com.example.practica_evaluacion_frecuente.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.practica_evaluacion_frecuente.Modelo.Revista;
import com.example.practica_evaluacion_frecuente.R;

import java.util.ArrayList;

public class AdaptadorRevista extends ArrayAdapter<Revista> {

    public AdaptadorRevista(Context varcon, ArrayList<Revista> datos){
        super(varcon, R.layout.lyitemsderevista,datos);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.lyitemsderevista, null);

        TextView twNombreRevista = (TextView)item.findViewById(R.id.twNombreRevista);
        twNombreRevista.setText(getItem(position).getName());

        ImageView imageView = (ImageView)item.findViewById(R.id.imgPortadaRevista);
        Glide.with(this.getContext()).load(getItem(position).getPortada()).into(imageView);
        return(item);
    }
}
