package com.Controller;

import java.util.ArrayList;

import com.Model.Empleador;

public class ControllerEmpleador {

    //Se agrega un empleador a la base de datos, se le devuelve el codigo
    public String registrarEmpleador(String nombre, String pass){
        Empleador p = new Empleador(nombre, pass);
        BD base = new BD();
        return base.agregarEmpleador(p);
    }
    //Se define si el empleador esta conectado o no
    public boolean cambiarEstadoEmpleador(Empleador usuario){
        boolean prueba = false;
        BD base = new BD();

        if(base.modificarEstado(usuario))
            prueba = true;

        return prueba;
    }
    //Un empleador que se encuentre en la base de datos accede y pasa a estar conectado
    public Empleador iniciarSesion(String codigoE, String pass){
        BD base = new BD();
        Empleador usuario = base.verificarPass(codigoE, pass);
        if(usuario != null){
            System.out.println("Iniciar sesion");
            base.modificarEstado(usuario);
        }

        return usuario;
    }
    //Un empleador termina su conexion y cierra sesion
    public boolean cerrarSesion(Empleador usuario){
        boolean prueba = false;
        BD base = new BD();

        if(base.modificarEstado(usuario))
            prueba = true;

        return prueba;
    }
    //Un empleador elimina su cuenta
    public boolean eliminarEmpleador(Empleador usuario){
        boolean prueba = false;
        BD base = new BD();

        if(base.eliminarEmpleador(usuario))
            prueba = true;

        return prueba;
    }
    //Se retorna una cadena con todos los empleadores de la BD
    public ArrayList<String> consultarEmpleadores(){
        ArrayList<String> prueba = new ArrayList<>();
        BD base = new BD();

        ArrayList<Empleador> lista = base.cargarListaE();

        for (Empleador p : lista) {
            prueba.add(p.toString());
        }

        return prueba;
    }
}