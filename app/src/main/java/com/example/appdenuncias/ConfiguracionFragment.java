package com.example.appdenuncias;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class ConfiguracionFragment extends Fragment {

    private TextInputLayout oldPassword, newPassword, repitPassword;
    private Button changePassword;
    private static String idUsuario;

    RequestQueue requestQueue;
    private static final String URL = "http://192.168.1.103/appdenunciasphp/actualizarContrasena.php";

    public ConfiguracionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_configuracion, container, false);

        oldPassword = v.findViewById(R.id.passwordOld);
        newPassword = v.findViewById(R.id.passwordNew);
        repitPassword = v.findViewById(R.id.passwordNewRepit);
        changePassword = v.findViewById(R.id.btnCambiarContrasena);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        idUsuario = sharedPreferences.getString("id", "16");
        Log.i("idUsuario", idUsuario);

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = oldPassword.getEditText().getText().toString().trim();
                String newPass = newPassword.getEditText().getText().toString().trim();
                String repitPass = repitPassword.getEditText().getText().toString().trim();

                if (validarLongitudCampo() == false) {
                    Toast.makeText(getActivity(),"No se puede enviar", Toast.LENGTH_LONG).show();
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getActivity(), "Contraseña actualizada correctamente", Toast.LENGTH_SHORT).show();
                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getActivity(), "La contraseña no se ha podido actualizar", Toast.LENGTH_SHORT).show();
                                }
                            }
                    ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap();
                            params.put("oldpassword", oldPass);
                            params.put("newpassword", newPass);
                            params.put("repitpassword", repitPass );
                            params.put("id", idUsuario);

                            return params;
                        }
                    };
                    requestQueue = Volley.newRequestQueue(getActivity());
                    requestQueue.add(stringRequest);
                }
            }
        });

        return v;
    }

    //Validaciones de los campos
    private boolean validarCamposVacios() {
        String oldPass = oldPassword.getEditText().getText().toString().trim();
        String newPass = newPassword.getEditText().getText().toString().trim();
        String repitPass = repitPassword.getEditText().getText().toString().trim();
        if (oldPass.isEmpty() & oldPass.length() < 8  || newPass.isEmpty() & newPass.length() < 8 || repitPass.isEmpty() & repitPass.length() < 8) {
            oldPassword.setError("El campo no puede estar vacío");
            newPassword.setError("El campo no puede estar vacío");
            repitPassword.setError("El campo no puede estar vacío");
            return false;
        } else {
            oldPassword.setError(null);
            newPassword.setError(null);
            repitPassword.setError(null);
            return true;
        }
    }

   private boolean validarLongitudCampo() {
        String oldPass = oldPassword.getEditText().getText().toString().trim();
        String newPass = newPassword.getEditText().getText().toString().trim();
        String repitPass = repitPassword.getEditText().getText().toString().trim();
        if (oldPass.length() < 8 || newPass.length() < 8 || repitPass.length() < 8) {
            oldPassword.setError("La contraseña debe tener una longitud mínima de 8 caracteres.");
            newPassword.setError("La contraseña debe tener una longitud mínima de 8 caracteres.");
            repitPassword.setError("La contraseña debe tener una longitud mínima de 8 caracteres.");
            return false;
        } else {
            oldPassword.setError(null);
            newPassword.setError(null);
            repitPassword.setError(null);
            return true;
        }
    }
}