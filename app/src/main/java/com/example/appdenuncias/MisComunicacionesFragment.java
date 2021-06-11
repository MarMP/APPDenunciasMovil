package com.example.appdenuncias;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appdenuncias.retrofit_data.RetrofitApiService;
import com.example.appdenuncias.retrofit_data.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MisComunicacionesFragment extends Fragment implements RecyclerAdapter.RecyclerItemClick, SearchView.OnQueryTextListener{

    private RecyclerView rvLista;
    private RecyclerAdapter adapter;
    private List<ItemListComunicaciones> items;
    private SearchView buscador;
    private String idUsuario;
    private RetrofitApiService retrofitApiService;
    View v;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_mis_comunicaciones, container, false);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        idUsuario = sharedPreferences.getString("id", "16");

        initViews();
        initValues();
        initListener();

        return v;
    }

    //Inicializa el recycler y el searchview
    private void initViews(){
        rvLista = (RecyclerView)v.findViewById(R.id.rvLista);
        buscador = v.findViewById(R.id.svSearch);
    }

    private void initValues() {
        retrofitApiService = RetrofitClient.getApiService();

        //Para mostrarlo en listas (se puede mostrar en Grid también)
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvLista.setLayoutManager(manager);
        getItemsComunicaciones();
    }

    private void initListener() {
        buscador.setOnQueryTextListener(this);
    }

    private void getItemsComunicaciones() {
        retrofitApiService.getItemsComunicaciones(idUsuario).enqueue(new Callback<List<ItemListComunicaciones>>() {
            @Override
            public void onResponse(Call<List<ItemListComunicaciones>> call, Response<List<ItemListComunicaciones>> response) {
                items = response.body();
                adapter = new RecyclerAdapter(items, MisComunicacionesFragment.this::itemClick);
                rvLista.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<ItemListComunicaciones>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " +t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void itemClick(ItemListComunicaciones item) {
        Intent i = new Intent(getContext(), DetailsActivity.class);
        i.putExtra("itemDetalles", item);
        startActivity(i);
    }

    //Se ejecutará cuando pulsemos enter en nuestro móvil en la barra de búsqueda
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    //Escuchará lo que escribamos en la barra de búsqueda
    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.buscar(newText);
        return false;
    }
}