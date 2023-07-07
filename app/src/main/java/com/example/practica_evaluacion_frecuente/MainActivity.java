package com.example.practica_evaluacion_frecuente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.practica_evaluacion_frecuente.Adaptadores.AdaptadorRevista;
import com.example.practica_evaluacion_frecuente.Modelo.Revista;
import com.example.practica_evaluacion_frecuente.WebService.Asynchtask;
import com.example.practica_evaluacion_frecuente.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask, AdapterView.OnItemClickListener {

    private ListView lstOp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url="https://revistas.uteq.edu.ec/ws/journals.php";

        Map<String, String> datos = new HashMap<String, String>();
        WebService webService = new WebService(url, datos, MainActivity.this, MainActivity.this);
        webService.execute("GET");

        //VISTA
        lstOp = (ListView) findViewById(R.id.lwListaRevista);

        //Inicializacion del evento Onclick
        lstOp.setOnItemClickListener(this);

    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray JSONlistarevista = new JSONArray(result);

        //MODELO O DATA
        ArrayList<Revista> lstrevista = Revista.JsonObjectsBuild(JSONlistarevista);

        //ADAPTADOR
        AdaptadorRevista adaptadorRevista = new AdaptadorRevista(MainActivity.this,lstrevista);
        lstOp.setAdapter(adaptadorRevista);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Crea objeto revista y guarda la posición seleccionada en revista.
        Revista revista = (Revista) parent.getItemAtPosition(position);

        //Crea variable para almacenar el journal_id de la revista
        String identificador = revista.getJournal_id().toString();

        //Crea objeto intent para redirigir a la nueva actividad
        Intent intent = new Intent(MainActivity.this, EdicionesActivity.class);

        //Crea objeto bundle para enviar el parametro id a la nueva actividad
        Bundle bundle=new Bundle();
        bundle.putString("id",identificador);

        //Envia bundle a la nueva actividad y la inicializa
        intent.putExtras(bundle);
        startActivity(intent);

        Toast.makeText(this, "ID Seleccionado: " + identificador,Toast.LENGTH_SHORT).show();
    }
}