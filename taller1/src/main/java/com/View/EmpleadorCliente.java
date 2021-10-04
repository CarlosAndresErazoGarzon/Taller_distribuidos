package com.View;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

import com.Model.Empleador;

public class EmpleadorCliente {
	private static final String IP = "127.0.0.1"; // Puede ser cambiado a localhost
	private static final int PUERTO = 1100; // El puerto debe ser el mismo que se configura en el servidor

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(IP, PUERTO);
		Interfaz interfaz = (Interfaz) registry.lookup("Empleador"); // Buscar en el registro...
		Scanner sc = new Scanner(System.in);

		boolean cent = true;
		boolean sesion = false;
		Empleador usuario = new Empleador();
		String opc = "";
		String pass = "";
		String codigoE = "";
		String nombre = "";
		ArrayList<String> lista = new ArrayList<>();
		String nombreO = "";
		String vacante = "";
		String sueldo = "";
		String clase = "";
		String capacidades = "";
		String opc1 = "";

		System.out.println("SISTEMA DE EMPLEADOR\n\n");

		while (cent) {
			System.out.println("\n\nMenu principal");
			if (!sesion) {
				System.out.println("1. Registrarse");
				System.out.println("2. Iniciar sesion");
				System.out.println("8. Salir");
			}
				System.out.println("3. Consultar empleadores");
			if (sesion) {
				System.out.println("4. Registrar oferta de trabajo");
				System.out.println("5. Consultar ofertas registradas");
				System.out.println("6. Eliminar cuenta");
				System.out.println("7. Cerrar sesion");
			}
			if (!sesion) {
				System.out.println("8. Salir");
			}

			opc = sc.nextLine();

			if (opc.equals("1") && !sesion) {
				System.out.println("REGISTRARSE\n\n");

				System.out.println("Ingrese su nombre: ");
				nombre = sc.nextLine();
				System.out.println("Ingrese su contrasenia");
				pass = sc.nextLine();
				codigoE = interfaz.registrarEmpleador(nombre, pass);

				if (codigoE != null) {
					System.out.println("\nUsuario registrado con exito!");
					usuario = interfaz.iniciarSesion(codigoE, pass);
					sesion = true; // Ahora ya se tiene un usuario
				} else
					System.out.println("\nHa ocurrido un error");

				continue; // Reinicia menu
			} else if (opc.equals("2") && !sesion) {
				System.out.println("INICIAR SESION\n\n");

				System.out.println("Ingrese su codigo: ");
				codigoE = sc.nextLine();
				System.out.println("Ingrese su contrasenia: ");
				pass = sc.nextLine();

				usuario = interfaz.iniciarSesion(codigoE, pass);

				if (usuario != null) {
					System.out.println("\nSe ha iniciado sesion correctamente!");
					sesion = true; // Ahora ya se tiene un usuario
				} else{
					System.out.println("\nAlguno de los campos es incorrecto");
				}
					
				continue; // Reinicia menu
			} else if (opc.equals("3")) {
				System.out.println("CONSULTAR EMPLEADORES\n\n");

				lista = interfaz.consultarEmpleadores();

				for (String p : lista) {
					System.out.println(p);
				}
				System.out.println("\nLista desplegada exitosamente");
				continue; // Reinicia menu
			} else if (opc.equals("4") && sesion) {
				System.out.println("REGISTRAR OFERTA DE TRABAJO\n\n");

				System.out.println("Ingrese el nombre de la oferta: ");
				nombreO = sc.nextLine();
				System.out.println("Ingrese el numero de vacantes: ");
				vacante = sc.nextLine();
				System.out.println("Ingrese el sueldo de la oferta: ");
				sueldo = sc.nextLine();
				System.out.println("Ingrese el numero de la clase: ");
				clase = sc.nextLine();
				System.out.println("Ingrese las capacidades que requiere la oferta: ");
				capacidades = sc.nextLine();

				if (interfaz.registrarOfertaTrabajo(Integer.valueOf(vacante), nombreO, Long.valueOf(sueldo), Integer.parseInt(clase), capacidades, codigoE)) {
					System.out.println("\nSatisfactoria");
				} else
					System.out.println("\nNo satisfactoria");

				continue; // Reinicia menu
			} else if (opc.equals("5") && sesion) {
				System.out.println("CONSULTAR OFERTAS DE TRABAJO\n\n");

				lista = interfaz.consultarOfertasRegistradas();

				for (String p : lista) {
					System.out.println(p);
				}
				System.out.println("\nLista desplegada exitosamente");
				continue; // Reinicia menu
			} else if (opc.equals("6") && sesion) {
				System.out.println("ELIMINAR CUENTA\n\n");

				System.out.println("Recuerde que esta operacion es definitiva");
				System.out.println("Desea continuar? S/N");
				opc1 = sc.nextLine();

				if (opc1.equals("S")){
					System.out.println("\nRealizando operacion...");
					interfaz.eliminarEmpleador(usuario);
					System.out.println("\nOperacion realizada...");
					sesion = false;
				}
				else if (opc1.equals("N"))
					System.out.println("\nCancelando operacion...");
				else
					System.out.println("\nOpcion no valida, intentelo de nuevo");

				continue; // Reinicia menu
			} else if (opc.equals("7") && sesion) {
				interfaz.cerrarSesion(usuario);
				System.out.println("\nCerrando sesion");
				sesion = false;
				continue;
			} else if(opc.equals("8")){
				System.out.println("\nGracias por usar el sistema...");
				break;
			} else {
				System.out.println("\nOpcion no valida, intentelo de nuevo");
			}

		}

		sc.close();
	}
}
