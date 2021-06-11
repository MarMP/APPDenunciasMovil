package com.example.appdenuncias;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
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


public class AnadirComunicacionFragment extends Fragment {
    private TextInputLayout textInputAnadirComunicacion, textInputAnadirEmpleado;
    private SwitchCompat switchComunicacionAnonima;
    private Spinner spinnerDepartamentos, spinnerTipoComunicacion;
    private Button enviar, cancelar;
    private static String idUsuario;

    RequestQueue requestQueue;
    private static final String URL = GlobalIp.IP+"appdenunciasphp/insertar.php";

    public AnadirComunicacionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_anadir_comunicacion, container, false);
        textInputAnadirComunicacion = v.findViewById(R.id.text_input_anadir);
        textInputAnadirEmpleado = v.findViewById(R.id.text_input_empleado);
        switchComunicacionAnonima = v.findViewById(R.id.switch_anonimo);
        spinnerDepartamentos = v.findViewById(R.id.spinner_departamento);
        spinnerTipoComunicacion = v.findViewById(R.id.spinner_tipo_comunucacion);
        enviar = v.findViewById(R.id.btnAnadirComunicacion);
        cancelar = v.findViewById(R.id.btnCancelarComunicacion);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        idUsuario = sharedPreferences.getString("id", "16");
        Log.i("idUsuario", idUsuario);

        switchComunicacionAnonima.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textInputAnadirEmpleado.getEditText().setText("Anónimo");
                    Toast.makeText(getContext(),"El mensaje se enviará de forma anónima", Toast.LENGTH_LONG).show();
                } else {
                    String texto = textInputAnadirEmpleado.getEditText().getText().toString().trim();
                    if (texto.isEmpty()) {
                        textInputAnadirEmpleado.setError("El campo no puede estar vacío");
                    } else if (!texto.equals("Anónimo")){
                        Toast.makeText(getContext(),"El mensaje no será anónimo", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noActivo() | !validarComunicacion()) {
                    Toast.makeText(getContext(),"El mensaje no será anónimo", Toast.LENGTH_LONG).show();
                }
                String empleado = textInputAnadirEmpleado.getEditText().getText().toString().trim();
                String tipoComunicacion = spinnerTipoComunicacion.getSelectedItem().toString();
                String departamento = spinnerDepartamentos.getSelectedItem().toString();
                String comunicacion = textInputAnadirComunicacion.getEditText().getText().toString().trim();

                insertarComunicacion(empleado, tipoComunicacion, departamento,comunicacion);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputAnadirComunicacion.getEditText().setText("");
                textInputAnadirEmpleado.getEditText().setText("");
                switchComunicacionAnonima.setChecked(false);
            }
        });

        requestQueue = Volley.newRequestQueue(getContext());
        return v;
    }

    private void insertarComunicacion(final  String empleado, final String tipoComunicacion, final String departamento, final String comunicacion) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), "Comunicación enviada", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "La comunicación no se ha podido enviar", Toast.LENGTH_SHORT).show();
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