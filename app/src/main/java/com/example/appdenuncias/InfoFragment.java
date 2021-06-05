package com.example.appdenuncias;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class InfoFragment extends Fragment {

    ImageView iv;
    Button btn;
    int position = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.info_fragment, container, false);
        iv = v.findViewById(R.id.ivInfo);
        //btn = (Button) v.findViewById(R.id.btnBottonInfo);
        //Vistas que se irán viendo cuando pase de página
        switch (position) {
            case 1:
                iv.setImageResource(R.drawable.welcome);
                //btn.setText("Siguiente");
                break;
            case 2:
                iv.setImageResource(R.drawable.welcome_dos);
                //btn.setText("Siguiente");
                break;
            case 3:
                iv.setImageResource(R.drawable.welcome_tres);
                btn = (Button) v.findViewById(R.id.btnBottonInfo);
                btn.setText("Comienza");
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //llamar a menu lateral
                        Intent i = new Intent(getActivity(), MenuLateral.class);
                        startActivity(i);
                    }
                });
                break;
        }
        return v;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
