package com.example.appdenuncias;

import java.io.Serializable;

public class ItemListComunicaciones implements Serializable {

    private String tipo_comunicacion;
    private String mensaje_comunicacion;
    private String estado;
    private String id;
    private String id_empleado;
   // private String estadoEs = "Estado: ";

    public ItemListComunicaciones(String tipo_comunicacion, String mensaje_comunicacion, String estado, String id) {
        this.tipo_comunicacion = tipo_comunicacion;
        this.mensaje_comunicacion = mensaje_comunicacion;
        this.estado = estado;
        this.id = id;
    }

    public String getTipo_comunicacion() {
        return tipo_comunicacion;
    }
    public String getMensaje_comunicacion() {
        return mensaje_comunicacion;
    }
    public String getEstado() {
        return estado;
    }
    public String getId() {
        return id;
    }
    public String getId_empleado() {
        return id_empleado;
    }
}
