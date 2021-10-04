package com.Model;

import java.util.ArrayList;
import java.util.Arrays;

import com.Model.Enum.Clas_formacion;

public class Capacidad {
    private Integer experiencia;
    private Clas_formacion formacion;
    private ArrayList<String> habilidades;

    public Capacidad() {
    }

    public Capacidad(Integer experiencia, Clas_formacion formacion, ArrayList<String> habilidades) {
        this.experiencia = experiencia;
        this.formacion = formacion;
        this.habilidades = habilidades;
    }

    public Integer getExperiencia() {
        return experiencia;
    }
    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
    }
    public Clas_formacion getFormacion() {
        return formacion;
    }
    public void setFormacion(Clas_formacion formacion) {
        this.formacion = formacion;
    }
    public ArrayList<String> getHabilidades() {
        return habilidades;
    }
    public void setHabilidades(ArrayList<String> habilidades) {
        this.habilidades = habilidades;
    }

    @Override
    public String toString() {
        return "Experiencia=" + experiencia + ", formacion=" + formacion + 
        ", habilidades=" + Arrays.toString(habilidades.toArray());
    }
    
}
