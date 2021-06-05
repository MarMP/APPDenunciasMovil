package com.example.appdenuncias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appdenuncias.retrofit_data.RetrofitApiService;
import com.example.appdenuncias.retrofit_data.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeerComunicaciones extends AppCompatActivity implements RecyclerAdapter.RecyclerItemClick, SearchView.OnQueryTextListener {
    private RecyclerView rvLista;
    private RecyclerAdapter adapter;
    private List<ItemListComunicaciones> items;
    private SearchView buscador;
    private String idUsuario;
    private RetrofitApiService retrofitApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_comunicaciones);

        SharedPreferences sharedPreferences = this.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        idUsuario = sharedPreferences.getString("id", "16");

        initViews();
        initValues();
        initListener();
    }

    //Inicializa el recycler y el searchview
    private void initViews(){
        rvLista = (RecyclerView)findViewById(R.id.rvLista);
        buscador = findViewById(R.id.svSearch);
    }

    private void initValues() {
        retrofitApiService = RetrofitClient.getApiService();

        //Para mostrarlo en listas (se puede mostrar en Grid también)
        LinearLayoutManager manager = new LinearLayoutManager(this);
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
                adapter = new RecyclerAdapter(items,LeerComunicaciones.this);
                rvLista.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<ItemListComunicaciones>> call, Throwable t) {
                Toast.makeText(LeerComunicaciones.this, "Error: " +t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void itemClick(ItemListComunicaciones item) {
        Intent i = new Intent(this, DetailsActivity.class);
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