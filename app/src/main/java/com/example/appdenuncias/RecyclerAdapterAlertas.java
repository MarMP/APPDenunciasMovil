package com.example.appdenuncias;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecyclerAdapterAlertas extends RecyclerView.Adapter<RecyclerAdapterAlertas.RecyclerHolder> {

    //Una lista para poblar el recycler
    private List<AlertasItem> items;
    //Para gestionar el onClick
    //Lista para el search del estado original que no cambiará, el que cambiará será items
    private List<AlertasItem>estadoOriginal;

    public RecyclerAdapterAlertas(List<AlertasItem> items) {
        this.items = items;
        this.estadoOriginal = new ArrayList<>();
        estadoOriginal.addAll(items);
    }


    public static class RecyclerHolder extends RecyclerView.ViewHolder {
        private ImageView imagen;
        private TextView titulo;
        private TextView mensaje;

        public RecyclerHolder(@NonNull View itemView){
            super(itemView);
            imagen = itemView.findViewById(R.id.imgItem);
            titulo = itemView.findViewById(R.id.tvTituloAlerta);
            mensaje = itemView.findViewById(R.id.tvMensajeAlerta);
        }
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view_alertas, parent, false);
        return new RecyclerHolder(v);
    }

    //Se ejecutará tantas veces como elementos haya en la lista (según getItemCount)
    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        AlertasItem item = items.get(position);
        //rellenar cada item
        holder.imagen.setImageResource(item.getImagen());
        holder.titulo.setText(item.getTitulo());
        holder.mensaje.setText(item.getMensaje());
    }
    //Tamaño de la lista
    @Override
    public int getItemCount() {
        return items.size();
    }
}