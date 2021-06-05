package com.example.appdenuncias;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.preference.PreferenceManager;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class PerfilUsuario extends AppCompatActivity {

    private TextView nombreTextView, ocupacionTextView, emailTextView, telefonoTextView, departamentoTextView;
    private ImageView usuarioImageView, emailImageView, telefonoImageView, departamentoImageView;
    private Button btnSubirImagen;
    private Uri foto;
    private static String idUsuario;

    private static final int REQUEST_PERMISSION_CODE = 100;
    private static final int REQUEST_IMAGE_GALLERY = 101;

    RequestQueue requestQueue;
    private static final String URL = "http://192.168.1.103/appdenunciasphp/perfil.php?id=" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        nombreTextView = findViewById(R.id.nombre_textview);
        ocupacionTextView = findViewById(R.id.ocupacion_textview);
        emailTextView = findViewById(R.id.email_textview);
        telefonoTextView = findViewById(R.id.phone_textview);
        departamentoTextView = findViewById(R.id.departamento_textview);
        usuarioImageView = findViewById(R.id.user_imageview);
        emailImageView = findViewById(R.id.email_imageview);
        telefonoImageView = findViewById(R.id.phone_imageview);
        departamentoImageView = findViewById(R.id.departamento_imageview);
        btnSubirImagen = findViewById(R.id.btnSubirImagen);

        SharedPreferences sharedPreferences = this.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        idUsuario = sharedPreferences.getString("id", "16");
        Log.i("idUsuario", idUsuario);

        btnSubirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //permisos para el uso de la galería
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //si están habilitados o no los permisos
                    if (ActivityCompat.checkSelfPermission(PerfilUsuario.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        abrirGaleria();
                    } else {
                        ActivityCompat.requestPermissions(PerfilUsuario.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
                    }
                } else {
                    abrirGaleria();
                }
            }
        });

        requestQueue = Volley.newRequestQueue(this);
        mostrarDatosPerfil(idUsuario);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_GALLERY) {
            //Para verificar si la activity es vacía o no, si se recuperó la imagen o no
            if (resultCode == Activity.RESULT_OK && data != null) {
                foto = data.getData();
                usuarioImageView.setImageURI(foto);
            } else {
                //si no se presiona nada
                Toast.makeText(this, "No ha seleccionado ninguna imagen", Toast.LENGTH_SHORT);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE_GALLERY);
    }

    //Si el usuario acepta o rechaza los permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                abrirGaleria();
            } else {
                Toast.makeText(this, "Necesitas habilitar los permisos", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void mostrarDatosPerfil(String id) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL+id,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String nombre, apellidos, puesto, email, telefono, departamento;
                try {
                    nombre = response.getString("nombre");
                    apellidos = response.getString("apellidos");
                    puesto = response.getString("puesto");
                    email = response.getString("email");
                    telefono = response.getString("telefono");
                    departamento = response.getString("departamento");

                    nombreTextView.setText(nombre +" "+ apellidos);
                    ocupacionTextView.setText(puesto);
                    emailTextView.setText(email);
                    telefonoTextView.setText(telefono);
                    departamentoTextView.setText(departamento);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueue.add(jsonObjectRequest);
    }
}