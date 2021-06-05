package com.example.appdenuncias;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity  extends AppCompatActivity {

    private TextView tvTipoComunicacionDetail;
    private TextView tvDescripcionDetail;
    private TextView tvEstadoDetail;
    private TextView tvIdDetail;
    private ItemListComunicaciones itemDetailComunicaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido_leer_comunicacion);
        setTitle(getClass().getSimpleName());
        initViews();
        initValues();
    }
    private void initViews() {
        tvTipoComunicacionDetail = findViewById(R.id.tvTipoComunicacionDetail);
        tvDescripcionDetail = findViewById(R.id.tvDescripcionDetail);
        tvEstadoDetail = findViewById(R.id.tvEstadoDetail);
        tvIdDetail = findViewById(R.id.tvIdDetail);
    }
    private void initValues(){
        itemDetailComunicaciones = (ItemListComunicaciones) getIntent().getExtras().getSerializable("itemDetalles");
        tvTipoComunicacionDetail.setText(itemDetailComunicaciones.getTipo_comunicacion());
        tvDescripcionDetail.setText(itemDetailComunicaciones.getMensaje_comunicacion());
        tvEstadoDetail.setText(itemDetailComunicaciones.getEstado());
        tvIdDetail.setText(itemDetailComunicaciones.getId());
    }
}
