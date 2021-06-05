package com.example.appdenuncias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;


import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

public class Alertas extends AppCompatActivity {
private RecyclerView rvListaAlertas;
private RecyclerAdapterAlertas adapter;
private List<AlertasItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertas);
        initViews();
        initValues();
    }

    //Inicializa el recycler
    private void initViews(){
        rvListaAlertas = findViewById(R.id.rvListaAlertas);
    }

    private void initValues() {
        //Para mostrarlo en listas (se puede mostrar en Grid también)
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvListaAlertas.setLayoutManager(manager);
        items = getItems();
        adapter = new RecyclerAdapterAlertas(items);
        rvListaAlertas.setAdapter(adapter);
    }

    //Muestra los datos de las alertas en el recycler
    private List<AlertasItem> getItems() {
        List<AlertasItem> itemLists = new ArrayList<>();
        itemLists.add(new AlertasItem(R.drawable.campana_notificacion, "El estado de su comunicación con Nº 28 ha cambiado.", "Estado: Cerrada"));
        itemLists.add(new AlertasItem(R.drawable.campana_notificacion, "El estado de su comunicación con Nº 28  ha cambiado.", "Estado: En proceso"));
        itemLists.add(new AlertasItem(R.drawable.campana_notificacion, "El estado de su comunicación con Nº 28  ha cambiado.", "Estado: Abierta"));
        itemLists.add(new AlertasItem(R.drawable.campana_notificacion, "El estado de su comunicación con Nº 17 ha cambiado.", "Estado: Cerrada"));
        itemLists.add(new AlertasItem(R.drawable.campana_notificacion, "El estado de su comunicación con Nº 17 ha cambiado.", "Estado: En proceso"));
        itemLists.add(new AlertasItem(R.drawable.campana_notificacion, "El estado de su comunicación con Nº 17  ha cambiado.", "Estado: Abierta"));
        itemLists.add(new AlertasItem(R.drawable.campana_notificacion, "El estado de su comunicación con Nº 3 ha cambiado.", "Estado: Cerrada"));
        itemLists.add(new AlertasItem(R.drawable.campana_notificacion, "El estado de su comunicación con Nº 3 ha cambiado.", "Estado: En proceso"));
        itemLists.add(new AlertasItem(R.drawable.campana_notificacion, "El estado de su comunicación con Nº 3 ha cambiado.", "Estado: Abierta"));
        return itemLists;
    }


}