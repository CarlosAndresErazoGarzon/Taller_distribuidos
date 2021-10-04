package com.Controller;

import java.util.ArrayList;

import com.Model.Enum.Clas_trabajo;
import com.Model.OfertaTrabajo;

public class ControllerOfertaTrabajo {
    //Se agrega una oferta de trabajo a la base de datos
    public boolean registrarOfertaTrabajo(Integer vacante, String nombre, long sueldo, Clas_trabajo clase, String capacidades, String codigoE){
        boolean prueba = false;
        OfertaTrabajo p = new OfertaTrabajo(vacante, nombre, sueldo, clase, capacidades, codigoE);
        BD base = new BD();

        if(base.agregarOfertaTrabajo(p))
            prueba = true;

        return prueba;
    }
    //Se retorna una cadena con todas las ofertas de trabajo de la BD
    public ArrayList<String> consultarOfertasRegistradas(){
        ArrayList<String> prueba = new ArrayList<>();
        BD base = new BD();

        ArrayList<OfertaTrabajo> lista = base.cargarListaOT();

        for (OfertaTrabajo p : lista) {
            prueba.add(p.toString());
        }

        return prueba;
    }
}