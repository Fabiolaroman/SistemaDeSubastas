package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entidades.*;
import cr.ac.ucenfotec.bl.excepciones.*;
import cr.ac.ucenfotec.tl.Controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import static cr.ac.ucenfotec.bl.gestores.GestorSubastas.*;
import static cr.ac.ucenfotec.tl.Controlador.*;

public class Menu {
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void iniciarMenu() throws IOException, SQLException, UsuarioInvalidoException, ClassNotFoundException {
//        registrarModerador("Roberto", "González", "117260520", 13, 11, 1998, "rgonzalezca@ucenfotec.ac.cr", "Password1");
        boolean ejecutando = true;


        while (ejecutando) {
            System.out.println("\nBienvenido al Subastador de Coleccionistas" +
                    "\n Ingresar como:" +
                    "\n - [1]Vendedor" +
                    "\n - [2]Coleccionista" +
                    "\n - [3]Salir");

            String opcion = in.readLine();

            switch (opcion) {
                case "1":
                    inicioVendedor();
                    break;

                case "2":
                    inicioColeccionista();
                    break;

                case "3":
                    ejecutando = false;
                    break;

                default:
                    System.out.println("Opción invalida");
                    break;
            }

            adjudicarSubastasVencidas();
        }
    }

    public static void inicioVendedor() throws IOException, SQLException, ClassNotFoundException {
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
                    System.out.println("\nOpción invalida");
                    break;
            }
        }

    }

    public static void inicioColeccionista() throws IOException, SQLException, ClassNotFoundException {
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
                    System.out.println("\nOpción invalida");
                    break;
            }
        }

    }

    public static void menuColeccionista(Coleccionista coleccionista) throws IOException {
        boolean ejecutando = true;
        while (ejecutando) {
            System.out.println("\n-----Coleccionista " + coleccionista.getId() + "-----");
            System.out.println("- [1] Ver Colección");
            System.out.println("- [2] Ver Subastas Activas");
            System.out.println("- [3] Crear Nueva Subasta");
            System.out.println("- [4] Ver Mis Subastas");
            System.out.println("- [5] Ver Intereses");
            System.out.println("- [6] Agregar Intereses");
            System.out.println("- [7] Salir");
            String opcion = in.readLine();
            switch (opcion) {
                case "1":
                    System.out.println("\n-----Mi Colección-----");
                    System.out.println("\n" + coleccionista.getColeccion());
                    break;

//                case "2":
//                    System.out.println("\n-----Subastas-----" +
//                            "\n" + getSubastasActivas());
//                    System.out.println("\nSeleccione una Subasta - Digite el ID de la Subasta:");
//                    String subastaID = in.readLine();
//                    Subasta subasta;
//                    try {
//                        subasta = subastaXId(subastaID);
//                    } catch (SubastaNoExisteException e) {
//                        System.out.println("\n" + e.getMessage());
//                        break;
//                    }
//                    menuSubastasColeccionista(coleccionista, subasta);
//                    break;
//
//                case "3":
//                    if(coleccionista.getColeccion().isEmpty()){
//                        System.out.print("-Los coleccionistas solo pueden vender items de su colección. \n-Su colección esta vacía");
//                    } else {
//                        menuCrearSubasta(coleccionista);
//                    }
//                    break;

                case "4":
                    System.out.println("\n-----Mis Subastas-----");
                    System.out.print(getSubastasXUsuario(coleccionista));
                    break;


                case "5":
                    System.out.print("\n-----Intereses-----");
                    System.out.print("\n" + coleccionista.getIntereses());
                    break;

                case "6":
                    System.out.print("\n-----Intereses-----");
                    System.out.print("\n-Digite el interes de desea agregar:");
                    String interes = in.readLine();
                    coleccionista.agregarInteres(interes);
                    break;

                case "7":
                    ejecutando = false;
                    break;

                default:
                    System.out.println("Opción invalida");
                    break;

            }

        }
    }
//
//    public static void menuSubastasColeccionista(Coleccionista coleccionista, Subasta subasta) throws IOException {
//        boolean ejecutando = true;
//        while (ejecutando) {
//            System.out.println("\n- [1] Ofertar" + "\n- [2] Ver ofertas" + "\n- [3] Salir");
//
//            String opcion = in.readLine();
//            switch (opcion) {
//                case "1":
//                    System.out.println("¿Cuanto desea ofertar?");
//                    int monto = Integer.parseInt(in.readLine());
//                    agregarOferta(subasta, coleccionista, monto);
//                    break;
//
//                case "2":
//                    System.out.print("\n" + subasta.verOfertas());
//                    break;
//
//                case "3":
//                    ejecutando = false;
//                    break;
//            }
//        }
//    }
//
    public static void menuVendedor(Vendedor vendedor) throws IOException {
        boolean ejecutando = true;
        ArrayList<Item> itemsSubasta;
        while(ejecutando) {
            itemsSubasta = new ArrayList<>();
            System.out.println("\n-----Vendedor " + vendedor.getId() + "-----");
            System.out.println("\n- [1] Crear Nueva Subasta");
            System.out.println("\n- [2] Ver Mis Subastas");
            System.out.println("\n- [3] Adjudicar Subastas Vencidas");
            System.out.println("\n- [4] Salir");
            String opcion = in.readLine();

            switch(opcion) {
//                case "1":
//                    menuCrearSubasta(vendedor);
//                    break;

                case "2":
                    System.out.println("\n-----Mis Subastas-----");
                    System.out.print(getSubastasXUsuario(vendedor));
                    break;

                case "3":
                    adjudicarSubastasVencidas();
                    System.out.println("\nSe adjudicaron todas las subastas vencidas");
                    break;

                case "4":
                    ejecutando = false;
                    break;

                default:
                    System.out.println("\nOpción invalida");
            }

        }

    }

//    public static void menuCrearSubasta(Usuario usuario) throws IOException{
//        System.out.println("\n-----Crear Subasta-----");
//        ArrayList<Item> items = new ArrayList<>();
//
//        if (usuario instanceof Vendedor){
//            System.out.println("\n¿Cuantos items desea agregar a la subasta?");
//            int cantidad = Integer.parseInt(in.readLine());
//            for(int i = 1; i <= cantidad; i++ ){
//                System.out.println("\n-Item " + i);
//                System.out.println("\nDigite el nombre del item: ");
//                String nombre = in.readLine();
//                System.out.println("\nDescriba el item: ");
//                String descripcion = in.readLine();
//                System.out.println("\n¿Cual es el estado del item? ");
//                String estado = in.readLine();
//                System.out.println("\nFecha de origen -- Digite el año:");
//                int annio = Integer.parseInt(in.readLine());
//                System.out.println("\nFecha de origen -- Digite el mes:");
//                int mes = Integer.parseInt(in.readLine());
//                System.out.println("\nFecha de origen -- Digite el dia:");
//                int dia = Integer.parseInt(in.readLine());
//
//                Item item = new Item(nombre, descripcion, estado, dia, mes, annio);
//
//                items.add(item);
//            }
//
//            System.out.println("\n¿Cuantos días desea que la subasta se mantenga activa?");
//            int dias = Integer.parseInt(in.readLine());
//            LocalDateTime fechaVencimiento = LocalDateTime.now().plusDays(dias);
//
//            System.out.println("\n¿Cual es el precio mínimo que aceptaría por esta subasta?");
//            double precio = Double.parseDouble(in.readLine());
//            crearSubasta(fechaVencimiento, (Vendedor) usuario, precio, items);
//
//            try {
//                System.out.print(subastaXId("S-1").getUsuarioCreador());
//            } catch (SubastaNoExisteException e) {
//                System.out.print(e.getMessage());
//            }
//
//
//        } else if (usuario instanceof Coleccionista) {
//            System.out.println("\n¿Cuantos items desea agregar a la subasta?");
//            int cantidad = Integer.parseInt(in.readLine());
//            for(int i = 1; i <= cantidad; i++ ) {
//                System.out.print(((Coleccionista) usuario).getColeccion());
//                System.out.println("\nDigite el ID del item que desea agregar a la subasta");
//                String idItem = in.readLine();
//                Item item = null;
//                try {
//                     item = ((Coleccionista) usuario).itemXID(idItem);
//                } catch (ItemNoExisteException e) {
//                    System.out.println(e.getMessage());
//                    break;
//                }
//                 items.add(item);
//
//            }
//
//            System.out.println("\n¿Cuantos días desea que la subasta se mantenga activa?");
//            int dias = Integer.parseInt(in.readLine());
//            LocalDateTime fechaVencimiento = LocalDateTime.now().plusDays(dias);
//
//            System.out.println("\n¿Cual es el precio mínimo que aceptaría por esta subasta?");
//            double precio = Double.parseDouble(in.readLine());
//            crearSubasta(fechaVencimiento, (Coleccionista) usuario, precio, items);
//        }
//    }
}