package com.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileWriter;
import java.io.FileReader;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.Model.Empleador;
import com.Model.OfertaTrabajo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BD {

    public String agregarEmpleador(Empleador p) {
        ArrayList<Empleador> listaEmpleadores = cargarListaE();
        String codigo = "";

        if (listaEmpleadores.isEmpty()) {
            codigo = "EM0";
        } else {
            String palabra = listaEmpleadores.get(listaEmpleadores.size() - 1).getCodigoE();
            Integer numero = Integer.valueOf(palabra.substring(2, palabra.length())) + 1;
            codigo = "EM" + String.valueOf(numero);
        }
        p.setCodigoE(codigo);
        System.out.println("Se creo el empleador: " + p.toString());
        listaEmpleadores.add(p);

        escribirBD_Empleador(listaEmpleadores);

        return codigo;
    }

    // Agregar un empleador a la BD, devuelve el codigo que se le asigna
    public void escribirBD_Empleador(ArrayList<Empleador> listaEmpleadores) {
        try {
            Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            String prettyJson = prettyGson.toJson(listaEmpleadores);
            FileWriter fr = new FileWriter("empleadores.json");
            fr.write(prettyJson);
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Para saber si un empleador esta conectado, se usa un boolean en la BD
    public boolean modificarEstado(Empleador p) {
        boolean prueba = false;
        ArrayList<Empleador> listaEmpleadores = cargarListaE();
        boolean conectado;

        for (Empleador cEmpleador : listaEmpleadores) {
            if(cEmpleador.getCodigoE().equals(p.getCodigoE())){
                conectado = cEmpleador.getConectado();
                if(conectado)
                    cEmpleador.setConectado(false);
                else 
                    cEmpleador.setConectado(true);
            }
        }
        escribirBD_Empleador(listaEmpleadores);

        return prueba;
    }

    // Eliminar un empleador de la bd
    public boolean eliminarEmpleador(Empleador p) {
        boolean prueba = false;

        ArrayList<Empleador> ListaEmpleadores = cargarListaE();
        ArrayList<OfertaTrabajo> ListaOfertaTrabajo = cargarListaOT();

        System.out.println("Entre a la funcion");

        if (p != null) {
            System.out.println("Entre a la 1er if");
            for(Iterator<OfertaTrabajo> it = ListaOfertaTrabajo.iterator(); it.hasNext(); ){
                OfertaTrabajo aOfertaTrabajo = it.next();
                if(aOfertaTrabajo.getCodigoE().equals(p.getCodigoE())){
                    it.remove();
                }
            }
            for(Iterator<Empleador> it = ListaEmpleadores.iterator(); it.hasNext(); ){
                Empleador aEmpleador = it.next();
                if(aEmpleador.getCodigoE().equals(p.getCodigoE())){
                    it.remove();
                    prueba = true;
                }
            }
        }

        escribirBD_OfertaTrabajo(ListaOfertaTrabajo);
        escribirBD_Empleador(ListaEmpleadores);
        return prueba;
    }

    // CargarLista de empleados
    public ArrayList<Empleador> cargarListaE() {

        Type listaEmpleadorTipo = new TypeToken<ArrayList<Empleador>>() {

        }.getType();

        JsonReader jr;
        Gson gson = new Gson();

        try {
            jr = new JsonReader(new FileReader("empleadores.json"));
            ArrayList<Empleador> empleadores = gson.fromJson(jr, listaEmpleadorTipo);
            return empleadores;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    // verifica la contrasenia de un empleador
    public Empleador verificarPass(String codigoE, String pass) {
        Empleador p = new Empleador();
        Empleador compara = buscarEmpleador(codigoE);
        boolean cent = false;

        if (compara != null) {
            System.out.println("Verificar pass");
            if (compara.getPass().equals(pass)){
                p = compara;
                System.out.println("Se verifico la contrasenia del usuario: " + compara.getCodigoE());
                cent = true;
            }
        }

        if(cent){
            return p;
        }
        return null;
    }

    // Buscar un empleador a partir de un codigo, sirve para verificar si existe el
    // empleador
    private Empleador buscarEmpleador(String codigoE) {
        Empleador p = new Empleador();
        boolean cent = false;

        ArrayList<Empleador> prueba = cargarListaE();

        for (Empleador ite : prueba) {
            if (ite.getCodigoE().equals(codigoE)) {
                p = ite;
                cent = true;
            }
        }
        if(cent){
            return p;
        } 
        return null;
    }

    // Agregar una oferta de trabajo a la BD, se le asigna un codigo segun el
    // consecutivo
    public boolean agregarOfertaTrabajo(OfertaTrabajo p) {
        boolean prueba = true;
        ArrayList<OfertaTrabajo> listaOfertas = cargarListaOT();
        String codigo = "";

        if (listaOfertas == null || listaOfertas.isEmpty()) {
            codigo = "OT0";
        } else {
            String palabra = listaOfertas.get(listaOfertas.size() - 1).getCodigoOT();
            Integer numero = Integer.valueOf(palabra.substring(2, palabra.length())) + 1;
            codigo = "OT" + String.valueOf(numero);
        }

        p.setCodigoOT(codigo);
        System.out.println("Se creo la oferta: " + p.toString());
        listaOfertas.add(p);

        escribirBD_OfertaTrabajo(listaOfertas);

        return prueba;
    }

    // Agregar un empleador a la BD, devuelve el codigo que se le asigna
    public void escribirBD_OfertaTrabajo(ArrayList<OfertaTrabajo> listaOfertaTrabajos) {
        try {
            Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            String prettyJson = prettyGson.toJson(listaOfertaTrabajos);
            FileWriter fr = new FileWriter("oferta_trabajo.json");
            fr.write(prettyJson);
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // CargarLista de ofertas de trabajo
    public ArrayList<OfertaTrabajo> cargarListaOT() {

        Type listaOfertaTrabajoTipo = new TypeToken<ArrayList<OfertaTrabajo>>() {

        }.getType();

        JsonReader jr;
        Gson gson = new Gson();

        try {
            jr = new JsonReader(new FileReader("oferta_trabajo.json"));
            ArrayList<OfertaTrabajo> OT = gson.fromJson(jr, listaOfertaTrabajoTipo);
            return OT;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}
