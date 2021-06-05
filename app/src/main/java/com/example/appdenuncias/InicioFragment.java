package com.example.appdenuncias;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InicioFragment extends Fragment implements View.OnClickListener {

    CardView nuevaComunicacion;
    CardView leerComunicaciones;
    CardView perfil;
    CardView alertas;
    CardView noticias;
    CardView ajustes;

    public InicioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_inicio, container, false);

        nuevaComunicacion = v.findViewById(R.id.card_view_anadir);
        leerComunicaciones = v.findViewById(R.id.card_view_enviadas);
        perfil = v.findViewById(R.id.card_view_perfil);
        alertas = v.findViewById(R.id.card_view_alertas);
        noticias = v.findViewById(R.id.card_view_noticias);
        ajustes = v.findViewById(R.id.card_view_ajustes);

        nuevaComunicacion.setOnClickListener((View.OnClickListener) this);
        leerComunicaciones.setOnClickListener((View.OnClickListener) this);
        perfil.setOnClickListener((View.OnClickListener) this);
        alertas.setOnClickListener((View.OnClickListener) this);
        noticias.setOnClickListener((View.OnClickListener) this);
        ajustes.setOnClickListener((View.OnClickListener) this);
        return v;
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        switch (v.getId()){
            case R.id.card_view_anadir:
                i.setClass(getActivity(), NuevaComunicacion.class);
                startActivity(i);
                break;
            case R.id.card_view_enviadas:
                i.setClass(getActivity(), LeerComunicaciones.class);
                startActivity(i);
                break;
            case R.id.card_view_perfil:
                i.setClass(getActivity(), PerfilUsuario.class);
                startActivity(i);
                break;
            case R.id.card_view_alertas:
                i.setClass(getActivity(), Alertas.class);
                startActivity(i);
                break;
            case R.id.card_view_noticias:
                i.setClass(getActivity(), Noticias.class);
                startActivity(i);
                break;
            case R.id.card_view_ajustes:
                i.setClass(getActivity(), SettingsActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}