package com.example.practica_evaluacion_frecuente.Modelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Libro {
    private String title;
    private String volume;
    private String year;
    private String cover;

    public Libro(JSONObject jsondato) throws JSONException {
        title = jsondato.getString("title");
        volume = jsondato.getString("volume");
        year = jsondato.getString("year");
        cover = jsondato.getString("cover");
    }

    public static ArrayList<Libro> JsonObjectsBuild(JSONArray jsondato) throws JSONException {
        ArrayList<Libro> libros = new ArrayList<>();
        for (int i = 0; i < jsondato.length() && i<20; i++) {
            libros.add(new Libro(jsondato.getJSONObject(i)));
        }
        return libros;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
