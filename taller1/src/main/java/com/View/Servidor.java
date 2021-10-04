package com.View;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.Controller.ControllerEmpleador;
import com.Controller.ControllerOfertaTrabajo;
import com.Model.Enum.Clas_trabajo;
import com.Model.Empleador;

public class Servidor {
    private static final int PUERTO = 1100; // El puerto debe ser el mismo que se configura en el cliente

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Remote remote = UnicastRemoteObject.exportObject(new Interfaz() {
            /*
             * Sobrescribir opcionalmente los metodos que escribimos en la interfaz
             */
            @Override
            public String registrarEmpleador(String nombre, String pass) throws RemoteException{
                ControllerEmpleador c = new ControllerEmpleador();
                return c.registrarEmpleador(nombre, pass);
            }

            @Override
            public Empleador iniciarSesion(String codigoE, String pass) throws RemoteException{
                ControllerEmpleador c = new ControllerEmpleador();
                return c.iniciarSesion(codigoE, pass);
            }

            @Override
            public boolean cerrarSesion(Empleador usuario) throws RemoteException{
                ControllerEmpleador c = new ControllerEmpleador();
                return c.cerrarSesion(usuario);
            }

            @Override
            public boolean eliminarEmpleador(Empleador usuario) throws RemoteException{
                ControllerEmpleador c = new ControllerEmpleador();
                return c.eliminarEmpleador(usuario);
            }

            @Override
            public ArrayList<String> consultarEmpleadores() throws RemoteException{
                ControllerEmpleador c = new ControllerEmpleador();
                return c.consultarEmpleadores();
            }

            @Override
            public boolean registrarOfertaTrabajo(Integer vacante, String nombre, long sueldo,
                    int clase, String capacidades, String codigoE) throws RemoteException{
                        ControllerOfertaTrabajo c = new ControllerOfertaTrabajo();
                        return c.registrarOfertaTrabajo(vacante, nombre, sueldo, Clas_trabajo.values()[clase], capacidades, codigoE);
                    }

            @Override
            public ArrayList<String> consultarOfertasRegistradas(){
                ControllerOfertaTrabajo c = new ControllerOfertaTrabajo();
                return c.consultarOfertasRegistradas();
            }
            
        }, 0);
        Registry registry = LocateRegistry.createRegistry(PUERTO);
        System.out.println("Servidor escuchando en el puerto " + String.valueOf(PUERTO));
        registry.bind("Empleador", remote); // Registrar calculadora
    }
}