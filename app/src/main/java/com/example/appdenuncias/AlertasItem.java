package com.example.appdenuncias;

import java.io.Serializable;

public class AlertasItem implements Serializable {

    private int imagen;
    private String titulo;
    private String mensaje;

    public AlertasItem(int imagen, String titulo, String mensaje) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.mensaje = mensaje;
    }

    public int getImagen() {
        return imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }
}
