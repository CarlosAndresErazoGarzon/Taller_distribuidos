package com.Model;

import com.Model.Enum.Clas_trabajo;

public class OfertaTrabajo {
    private String codigoOT;
    private Integer vacante;
    private String nombre;
    private Long sueldo;
    private Clas_trabajo clase;

    private String capacidades; //Aun no esta implementada la clase Capacidad
    private String codigoE;

    public OfertaTrabajo() {
    }

    public OfertaTrabajo(Integer vacante, String nombre, Long sueldo, Clas_trabajo clase,
            String capacidades, String codigoE) {
        this.vacante = vacante;
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.clase = clase;
        this.capacidades = capacidades;
        this.codigoE = codigoE;
    }

    public String getCodigoOT() {
        return codigoOT;
    }

    public void setCodigoOT(String codigoOT) {
        this.codigoOT = codigoOT;
    }

    public Integer getVacante() {
        return vacante;
    }

    public void setVacante(Integer vacante) {
        this.vacante = vacante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getSueldo() {
        return sueldo;
    }

    public void setSueldo(Long sueldo) {
        this.sueldo = sueldo;
    }

    public Clas_trabajo getClase() {
        return clase;
    }

    public void setClase(Clas_trabajo clase) {
        this.clase = clase;
    }

    public String getCapacidades() {
        return capacidades;
    }

    public void setCapacidades(String capacidades) {
        this.capacidades = capacidades;
    }

    public String getCodigoE() {
        return codigoE;
    }

    public void setCodigoE(String codigoE) {
        this.codigoE = codigoE;
    }

    @Override
    public String toString() {
        return "Codigo oferta = " + codigoOT + ", Nombre = "+ nombre +
        ", Clase = " + clase + ", Sueldo = " + sueldo +
        ", Capacidades = " + capacidades + 
        ", vacante = " + vacante + ", codigo empleado = " + codigoE;
    }
}
