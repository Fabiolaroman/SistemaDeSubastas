package cr.ac.ucenfotec.logica.ui;

import cr.ac.ucenfotec.logica.entidades.*;
import cr.ac.ucenfotec.logica.gestor.GestorSubastas;
import cr.ac.ucenfotec.logica.gestor.GestorUsuarios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private boolean ejecutando = true;
    GestorUsuarios gestorUsuarios = new GestorUsuarios();
    GestorSubastas gestorSubastas = new GestorSubastas();


//    gestorUsuarios.registrarModerador(("Roberto", "González", "117260520", 13, 11, 1998, "rgonzalezca@ucenfotec.ac.cr", "Password1"));
//    Vendedor vendedor1 = new Vendedor("John", "Doe", "111111111", 01, 01, 2000, "anonimo@ucenfotec.ac.cr", "Password2", "San José");
//    Vendedor vendedor2 = new Vendedor("Fulanito", "Detal", "222222222", 01, 01, 2000, "fdetal@ucenfotec.ac.cr", "Password3", "Heredia");
//    Item itemSubasta1 = new Item("Biblia de Guthemberg", "Biblia original de Guthemberg", "Original", 24, 02, 1455);


    public void iniciarMenu() throws IOException {
        gestorUsuarios.registrarModerador("Roberto", "González", "117260520", 13, 11, 1998, "rgonzalezca@ucenfotec.ac.cr", "Password1");
        gestorUsuarios.registrarVendedor("John", "Doe", "111111111", 01, 01, 2000, "anonimo@ucenfotec.ac.cr", "Password2", "San José");
        Item itemSubasta1 = new Item("Biblia de Guthemberg", "Biblia original de Guthemberg", "Original", 24, 02, 1455);
        if (gestorUsuarios.getUsuarios().get(1) instanceof Vendedor) {
            ((Vendedor) gestorUsuarios.getUsuarios().get(1)).crearSubasta(gestorSubastas, 16, 3, 2026, 23, 18, 250000, new ArrayList<Item>(List.of(itemSubasta1)));
        }
        ;
        System.out.println("\nBienvenido al Subastador de Coleccionistas" +
                "\n Digite 1 para registrarse");
        while (ejecutando) {
            switch (in.readLine()) {
                case "1":
                    System.out.println("\nNombre: ");
                    String nombre = in.readLine();
                    System.out.println("\nApellidos: ");
                    String apellidos = in.readLine();
                    System.out.println("\nIdentificación: ");
                    String identificacion = in.readLine();
                    System.out.println("\nFecha de nacimiento: Dia:");
                    int dia = Integer.parseInt(in.readLine());
                    System.out.println("\nFecha de nacimiento: Mes:");
                    int mes = Integer.parseInt(in.readLine());
                    System.out.println("\nFecha de nacimiento: Año:");
                    int annio = Integer.parseInt(in.readLine());
                    System.out.println("\nCorreo: ");
                    String correo = in.readLine();
                    System.out.println("\nContraseña: ");
                    String password = in.readLine();
                    System.out.println("\nDirección: ");
                    String direccion = in.readLine();


                    gestorUsuarios.registrarColeccionista(nombre, apellidos, identificacion, dia, mes, annio, correo, password, direccion);
                    break;

                case "2":
                    System.out.println("Subastas:" +
                            "\n" + gestorSubastas.getSubastas().toString());
                    System.out.println("\n[1]Ofertar" +
                            "\n[2]Ver ofertas");
                    switch(in.readLine()){
                        case "1":
                            System.out.println("Digite el ID de la subasta en la que desea ofertar:");
                            if (gestorUsuarios.getUsuarios().get(2) instanceof Coleccionista) { //Solucion temporal para menu de ejemplo
                                String idSubasta = in.readLine();
                                if (gestorSubastas.getSubastas().stream().anyMatch(subasta -> subasta.getId().equals(idSubasta))) {
                                    System.out.println("¿Cuanto desea ofertar?");
                                    int monto = Integer.parseInt(in.readLine());
                                    ((Coleccionista) gestorUsuarios.getUsuarios().get(2)).ofertar(gestorSubastas, idSubasta, monto);
                                } else {
                                    System.out.println();
                                }

                            }
                            break;

                        case "2":
                            System.out.println("Digite el ID de la subasta que desea examinar:");
                            String idSubasta = in.readLine();
                            if (gestorSubastas.getSubastas().stream().anyMatch(subasta -> subasta.getId().equals(idSubasta))) {
                                for (Subasta subasta : gestorSubastas.getSubastas()) {
                                    if (idSubasta.equals(subasta.getId())) {
                                        System.out.println(subasta.verOfertas());
                                    }
                                }
                            }
                    }



                    break;

                case "3":
                    System.out.println(gestorUsuarios.getUsuarios().get(2));
                    break;

                case "4":
                    System.out.println("¿Que interés desea agregar?");
                    if (gestorUsuarios.getUsuarios().get(2) instanceof Coleccionista) {
                        ((Coleccionista) gestorUsuarios.getUsuarios().get(2)).agregarInteres(in.readLine());
                    }


                default:
                    System.out.println("Opcion invalida");


            }
            gestorSubastas.adjudicarSubastasVencidas();
            System.out.println("\nBienvenido al Subastador de Coleccionistas" +
                    "\n Seleccione una opcion:" +
                    "\n - [2]Ver Subastas" +
                    "\n - [3]Ver Colección");
        }
    }
}
