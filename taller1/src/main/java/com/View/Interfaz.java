package com.View;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import com.Model.Empleador;

/*
	Declarar firma de metodos que seran sobrescritos
*/
public interface Interfaz extends Remote {
    String registrarEmpleador(String nombre, String pass) throws RemoteException;
    Empleador iniciarSesion(String codigoE, String pass) throws RemoteException;
    boolean cerrarSesion(Empleador usuario) throws RemoteException;
    boolean eliminarEmpleador(Empleador usuario) throws RemoteException;
    ArrayList<String> consultarEmpleadores() throws RemoteException;
    boolean registrarOfertaTrabajo(Integer vacante, String nombre, long sueldo, int clase, String capacidades, String codigoE) throws RemoteException;
    ArrayList<String> consultarOfertasRegistradas() throws RemoteException;
}