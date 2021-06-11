package com.example.appdenuncias;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class AlertasFragment extends Fragment {

    private RecyclerView rvListaAlertas;
    private RecyclerAdapterAlertas adapter;
    private List<AlertasItem> items;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_alertas, container, false);
        initViews();
        initValues();
        return v;
    }

    //Inicializa el recycler
    private void initViews(){
        rvListaAlertas = v.findViewById(R.id.rvListaAlertas);
    }

    private void initValues() {
        //Para mostrarlo en listas (se puede mostrar en Grid también)
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
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