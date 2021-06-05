package com.example.appdenuncias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import com.google.android.material.navigation.NavigationView;

public class MenuLateral extends AppCompatActivity {
    //Variables para el menú lateral
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;

    //Inicio
    CardView nuevaComunicacion;
    CardView leerComunicaciones;
    CardView perfil;
    CardView alertas;
    CardView noticias;
    CardView ajustes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lateral);

        //cardview
        nuevaComunicacion = findViewById(R.id.card_view_anadir);
        leerComunicaciones = findViewById(R.id.card_view_enviadas);
        perfil = findViewById(R.id.card_view_perfil);
        alertas = findViewById(R.id.card_view_alertas);
        noticias = findViewById(R.id.card_view_noticias);
        ajustes = findViewById(R.id.card_view_ajustes);

        //Botones onClick
        nuevaComunicacion.setOnClickListener(this::onClick);
        nuevaComunicacion.setOnClickListener(this::onClick);
        leerComunicaciones.setOnClickListener(this::onClick);
        perfil.setOnClickListener(this::onClick);
        alertas.setOnClickListener(this::onClick);
        noticias.setOnClickListener(this::onClick);
        ajustes.setOnClickListener(this::onClick);

        //gestión del menú
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = null;
                switch (id) {
                    case R.id.home:
                        fragment = new InicioFragment();
                        loadFragment (fragment);
                        break;
                    case R.id.profile:
                        fragment = new ProfileFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.settings:
                        fragment = new ConfiguracionFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.aboutUs:
                        fragment = new SobreNosotrosFragment();
                        loadFragment (fragment);
                        break;
                    case R.id.logout:
                        clickLogout();
                       // finish();
                        break;
                }
                return true;
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
    }

    //Manejo del logout del menú lateral de la aplicación
    private static void logout(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Cerrar sesión");
        builder.setMessage("¿Estás seguro de que deseas cerrar sesión?");
        //Botón aceptar
        builder.setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Finish activity
                activity.finishAffinity();
                System.exit(0);
            }
        });
        //Botón cancelar
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //mostrar diálogo
        builder.show();
    }

    public void clickLogout() {
        MenuLateral.logout(this);
    }

    //Botones del inicio cardview

    public void onClick(View v) {
        Intent i = new Intent();
        switch (v.getId()){
            case R.id.card_view_anadir:
                i.setClass(this, NuevaComunicacion.class);
                startActivity(i);
                break;
            case R.id.card_view_enviadas:
                i.setClass(this, LeerComunicaciones.class);
                startActivity(i);
                break;
            case R.id.card_view_perfil:
                i.setClass(this, PerfilUsuario.class);
                startActivity(i);
                break;
            case R.id.card_view_alertas:
                i.setClass(this, Alertas.class);
                startActivity(i);
                break;
            case R.id.card_view_noticias:
                i.setClass(this, Noticias.class);
                startActivity(i);
                break;
            case R.id.card_view_ajustes:
                i.setClass(this, SettingsActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }

}