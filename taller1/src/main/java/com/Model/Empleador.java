package com.Model;

import java.io.Serializable;

public class Empleador implements Serializable{
    private String codigoE;
    private String nombre;
    private String pass;
    private boolean conectado;

    public Empleador() {
    }

    public Empleador(String codigoE, String nombre, String pass) {
        this.codigoE = codigoE;
        this.nombre = nombre;
        this.pass = pass;
        this.conectado = true;
    }

    public Empleador(String nombre, String pass) {
        this.nombre = nombre;
        this.pass = pass;
        this.conectado = true;
    }

    public String getCodigoE() {
        return codigoE;
    }
    public void setCodigoE(String codigoE) {
        this.codigoE = codigoE;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public boolean getConectado() {
        return conectado;
    }
    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    @Override
    public String toString() {
        return "CodigoE=" + codigoE + ", Nombre=" + nombre +
        ", Conectado = " + conectado;
    }

}