package com.example.practica_evaluacion_frecuente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.practica_evaluacion_frecuente.Adaptadores.AdaptadorLibro;
import com.example.practica_evaluacion_frecuente.Modelo.Libro;
import com.example.practica_evaluacion_frecuente.WebService.Asynchtask;
import com.example.practica_evaluacion_frecuente.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EdicionesActivity extends AppCompatActivity implements Asynchtask {

    private ListView lstOp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ediciones);

        //Recibe los datos de la Actividad que envia
        Bundle bundle = this.getIntent().getExtras();

        //Se crea una variable para la url del web service y se le concatena el parametro id requerido
        String url="https://revistas.uteq.edu.ec/ws/issues.php?j_id="+bundle.getString("id");

        //Se crea objeto Map para el envío de datos al web service y se inicializa el mismo.
        Map<String, String> datos = new HashMap<String, String>();
        WebService webService = new WebService(url, datos, EdicionesActivity.this,
                EdicionesActivity.this);
        webService.execute("GET");

        //Vista
        lstOp = (ListView) findViewById(R.id.lwEdiciones);
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray JSONlistalibro = new JSONArray(result);

        //MODELO O DATA
        ArrayList<Libro> lstlibro = Libro.JsonObjectsBuild(JSONlistalibro);

        //ADAPTADOR
        AdaptadorLibro adaptadorLibro = new AdaptadorLibro(this,lstlibro);
        lstOp.setAdapter(adaptadorLibro);
    }
}