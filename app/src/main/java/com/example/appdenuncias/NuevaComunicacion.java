package com.example.appdenuncias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class NuevaComunicacion extends AppCompatActivity {

    private TextInputLayout textInputAnadirComunicacion, textInputAnadirEmpleado;
    private SwitchCompat switchComunicacionAnonima;
    private Spinner spinnerDepartamentos, spinnerTipoComunicacion;
    private Button enviar, cancelar;
    private static String idUsuario;

    RequestQueue requestQueue;
    private static final String URL = "http://192.168.1.103/appdenunciasphp/insertar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_comunicacion);

        textInputAnadirComunicacion = findViewById(R.id.text_input_anadir);
        textInputAnadirEmpleado = findViewById(R.id.text_input_empleado);
        switchComunicacionAnonima = findViewById(R.id.switch_anonimo);
        spinnerDepartamentos = findViewById(R.id.spinner_departamento);
        spinnerTipoComunicacion = findViewById(R.id.spinner_tipo_comunucacion);
        enviar = findViewById(R.id.btnAnadirComunicacion);
        cancelar = findViewById(R.id.btnCancelarComunicacion);

        SharedPreferences sharedPreferences = this.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        idUsuario = sharedPreferences.getString("id", "16");
        Log.i("idUsuario", idUsuario);

        switchComunicacionAnonima.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textInputAnadirEmpleado.getEditText().setText("Anónimo");
                    Toast.makeText(getApplicationContext(),"El mensaje se enviará de forma anónima", Toast.LENGTH_LONG).show();
                } else {
                    String texto = textInputAnadirEmpleado.getEditText().getText().toString().trim();
                    if (texto.isEmpty()) {
                        textInputAnadirEmpleado.setError("El campo no puede estar vacío");
                    } else if (!texto.equals("Anónimo")){
                        Toast.makeText(getApplicationContext(),"El mensaje no será anónimo", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
        requestQueue = Volley.newRequestQueue(this);
    }

    //Botón aceptar
    public void aceptar (View v) {
        if (noActivo() | !validarComunicacion()) {
            Toast.makeText(getApplicationContext(),"El mensaje no será anónimo", Toast.LENGTH_LONG).show();
        }
        String empleado = textInputAnadirEmpleado.getEditText().getText().toString().trim();
        String tipoComunicacion = spinnerTipoComunicacion.getSelectedItem().toString();
        String departamento = spinnerDepartamentos.getSelectedItem().toString();
        String comunicacion = textInputAnadirComunicacion.getEditText().getText().toString().trim();

        insertarComunicacion(empleado, tipoComunicacion, departamento,comunicacion);
    }

    private void insertarComunicacion(final  String empleado, final String tipoComunicacion, final String departamento, final String comunicacion) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(NuevaComunicacion.this, "Comunicación enviada", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NuevaComunicacion.this, "La comunicación no se ha podido enviar", Toast.LENGTH_SHORT).show();
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap();
                params.put("empleado", empleado);
                params.put("tipo_comunicacion", tipoComunicacion);
                params.put("departamento", departamento );
                params.put("mensaje_comunicacion", comunicacion);
                params.put("id", idUsuario);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void cancelar (View v) {
        textInputAnadirComunicacion.getEditText().setText("");
        textInputAnadirEmpleado.getEditText().setText("");
        switchComunicacionAnonima.setChecked(false);
    }

    //Validaciones de los campos
    private boolean validarComunicacion() {
        String texto = textInputAnadirComunicacion.getEditText().getText().toString().trim();
        if (texto.isEmpty()) {
            textInputAnadirComunicacion.setError("El campo no puede estar vacío");
            return false;
        } else {
            textInputAnadirComunicacion.setError(null);
            return true;
        }
    }

    //Cuando el switch no esté activo debe tener un empleado ya que la comunicación no será anónima
    public boolean noActivo(){
        if (!switchComunicacionAnonima.isChecked()){
            textInputAnadirEmpleado.setError("El campo no puede estar vacío");
        }
        return true;
    }


}



