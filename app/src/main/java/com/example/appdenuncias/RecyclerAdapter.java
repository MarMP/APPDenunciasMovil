package com.example.appdenuncias;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

    //Una lista para poblar el recycler
    private List<ItemListComunicaciones> items;
    //Para gestionar el onClick
    private RecyclerItemClick itemClick;
    //Lista para el search del estado original que no cambiará, el que cambiará será items
    private List<ItemListComunicaciones>estadoOriginal;

    public RecyclerAdapter(List<ItemListComunicaciones> items, RecyclerItemClick itemClick) {
        this.items = items;
        this.itemClick = itemClick;
        this.estadoOriginal = new ArrayList<>();
        estadoOriginal.addAll(items);
    }

   public static class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView tipoComunicacion;
        private TextView descripcion;
        private TextView estado;
        private TextView id;

        public RecyclerHolder(@NonNull View itemView){
            super(itemView);
            tipoComunicacion = itemView.findViewById(R.id.tvTipoComunicacion);
            descripcion = itemView.findViewById(R.id.tvDescripcion);
            estado = itemView.findViewById(R.id.tvEstado);
            id = itemView.findViewById(R.id.tvId);
        }
   }

   //Se ejecutará tantas veces como elementos haya en la lista (según getItemCount)
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerHolder holder, int position) {
        ItemListComunicaciones item = items.get(position);
        //rellenar cada item
        holder.tipoComunicacion.setText(item.getTipo_comunicacion());
        holder.descripcion.setText(item.getMensaje_comunicacion());
        holder.estado.setText("Estado: " + item.getEstado());
        holder.id.setText("Nº de la comunicación: " + item.getId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(item);
            }
        });
    }

    //Tamaño de la lista
    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public RecyclerAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);
        return new RecyclerHolder(v);
    }
    //Gestiona el onClick
    public interface RecyclerItemClick {
        void itemClick(ItemListComunicaciones item);
    }

    //Metodo para gestionar la barra de búsqueda de las comunicaciones
    public void buscar(final String strBusqueda) {
        if (strBusqueda.length() == 0) {
            items.clear();
            items.addAll(estadoOriginal);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                items.clear();
                List<ItemListComunicaciones> collect = estadoOriginal.stream()
                        .filter(i -> i.getTipo_comunicacion().toLowerCase().contains(strBusqueda))
                        .collect(Collectors.toList());
                items.addAll(collect); //los items cambiarán estadoOriginal no
            }
            else {
                items.clear();
                for (ItemListComunicaciones i : estadoOriginal) {
                    if (i.getTipo_comunicacion().toLowerCase().contains(strBusqueda)) {
                        items.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
}
