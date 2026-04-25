package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entidades.*;
import cr.ac.ucenfotec.bl.excepciones.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import static cr.ac.ucenfotec.tl.Controlador.*;
import cr.ac.ucenfotec.bl.excepciones.SubastaNoExisteException;

public class Menu {
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void iniciarMenu() throws IOException, SQLException, ClassNotFoundException, UsuarioNoExisteException {
//        registrarModerador("Roberto", "González", "117260520", 13, 11, 1998, "rgonzalezca@ucenfotec.ac.cr", "Password1");
        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("\nBienvenido al Subastador de Coleccionistas" +
                    "\n Ingresar como:" +
                    "\n - [1]Vendedor" +
                    "\n - [2]Coleccionista" +
                    "\n - [3]Moderador" +
                    "\n - [4]Salir");

            String opcion = in.readLine();

            switch (opcion) {
                case "1":
                    inicioVendedor();
                    break;

                case "2":
                    inicioColeccionista();
                    break;

                case "3":
                    inicioModerador();
                    break;

                case "4":
                    ejecutando = false;
                    break;

                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }

    public static void inicioVendedor() throws IOException, SQLException, ClassNotFoundException, UsuarioNoExisteException {
        boolean ejecutando = true;
        while (ejecutando) {
            System.out.println("\n-----Vendedores-----");
            System.out.println("- [1] Iniciar Sesión");
            System.out.println("- [2] Registrarse");
            System.out.println("- [3] Salir");

            String opcion = in.readLine();

            switch(opcion){
                case "1":
                    Vendedor vendedor;
                    try {
                        vendedor = ingresarVendedor();
                    } catch(UsuarioNoExisteException | ContraseniaIncorrectaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    menuVendedor(vendedor);
                    break;

                case "2":
                    registrarVendedor();

                case "3":
                    ejecutando = false;
                    break;

                default:
                    System.out.println("\nOpción inválida");
                    break;
            }
        }

    }

    public static void inicioColeccionista() throws IOException, SQLException, ClassNotFoundException, UsuarioNoExisteException {
        boolean ejecutando = true;
        while (ejecutando) {
            System.out.println("\n-----Coleccionistas-----");
            System.out.println("- [1] Iniciar Sesión");
            System.out.println("- [2] Registrarse");
            System.out.println("- [3] Salir");

            String opcion = in.readLine();

            switch(opcion){
                case "1":
                    Coleccionista coleccionista;
                    try {
                        coleccionista = ingresarColeccionista();
                    } catch(UsuarioNoExisteException | ContraseniaIncorrectaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    menuColeccionista(coleccionista);
                    break;

                case "2":
                    registrarColeccionista();

                case "3":
                    ejecutando = false;
                    break;

                default:
                    System.out.println("\nOpción inválida");
                    break;
            }
        }

    }

    public static void inicioModerador() throws IOException, SQLException, ClassNotFoundException, UsuarioNoExisteException {
        boolean ejecutando = true;

        while (ejecutando){
            System.out.println("\n-----MODERADOR-----");
            System.out.println("- [1] Iniciar Sesión");
            System.out.println("- [2] Salir");
            String opcion = in.readLine();
            switch(opcion) {
                case "1":
                    Moderador moderador;
                    try {
                        moderador = ingresarModerador();
                    } catch (UsuarioNoExisteException | ContraseniaIncorrectaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    menuModerador(moderador);

                case "2":
                    ejecutando = false;
                    break;

                default:
                    System.out.println("\nOpción inválida");
                    break;
            }
        }
    }

    public static void menuColeccionista(Coleccionista coleccionista) throws IOException, SQLException, ClassNotFoundException, UsuarioNoExisteException {
        boolean ejecutando = true;
        while (ejecutando) {
            System.out.println("\n-----Coleccionista " + coleccionista.getId() + "-----");
            System.out.println("- [1] Ver mi Colección");
            System.out.println("- [2] Ver Subastas Activas");
            System.out.println("- [3] Crear Nueva Subasta");
            System.out.println("- [4] Ver Mis Subastas");
            System.out.println("- [5] Ver Mis Intereses");
            System.out.println("- [6] Agregar Intereses");
            System.out.println("- [7] Salir");
            String opcion = in.readLine();
            switch (opcion) {
                case "1":
                    mostrarColeccion(coleccionista.getId());
                    break;

                case "2":
                    mostrarSubastasActivas();
                    System.out.print("\n¿Desea realizar una oferta? [s/n]: ");
                    String respuesta = in.readLine();
                    if (respuesta.equalsIgnoreCase("s")) {
                        try {
                            realizarOferta(coleccionista);
                        } catch (SubastaNoExisteException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                case "3":
                    crearSubasta(coleccionista);
                    break;

                case "4":
                    System.out.print("\n-----Mis Subastas-----");
                    mostrarSubastas(coleccionista);
                    break;

                case "5":
                    System.out.print("\n-----Intereses-----");
                    mostrarInteresesColeccionista(coleccionista);
                    break;

                case "6":
                    System.out.print("\n-----Intereses-----");
                    agregarIntereses(coleccionista);
                    break;

                case "7":
                    ejecutando = false;
                    break;

                default:
                    System.out.println("Opción inválida");
                    break;

            }

        }
    }

    public static void menuVendedor(Vendedor vendedor) throws IOException, UsuarioNoExisteException, SQLException, ClassNotFoundException {
        boolean ejecutando = true;
        while(ejecutando) {
            System.out.println("\n-----Vendedor " + vendedor.getId() + "-----");
            System.out.println("\n- [1] Crear Nueva Subasta");
            System.out.println("\n- [2] Ver Mis Subastas");
            System.out.println("\n- [3] Salir");
            String opcion = in.readLine();

            switch(opcion) {

                case "1":
                    crearSubasta(vendedor);
                    break;

                case "2":
                    System.out.println("\n-----Mis Subastas-----");
                    mostrarSubastas(vendedor);
                    break;

                case "3":
                    ejecutando = false;
                    break;

                default:
                    System.out.println("\nOpción inválida");
                    break;
            }

        }

    }

    public static void menuModerador(Moderador moderador) throws IOException, UsuarioNoExisteException, SQLException, ClassNotFoundException {
        boolean ejecutando = true;

        while (ejecutando){
            System.out.println("\n-----Moderador " + moderador.getId() + "-----");
            System.out.println("\n- [1] Adjudicar Subastas Vencidas");
            System.out.println("\n- [2] Salir");
            String opcion = in.readLine();
            switch(opcion) {
                case "1":
                    adjudicarSubastasVencidas();
                    menuModerador(moderador);

                case "2":
                    ejecutando = false;
                    break;

                default:
                    System.out.println("\nOpción inválida");
                    break;
            }
        }
    }

}