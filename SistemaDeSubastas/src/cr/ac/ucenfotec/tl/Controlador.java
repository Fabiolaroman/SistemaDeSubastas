package cr.ac.ucenfotec.tl;

import cr.ac.ucenfotec.bl.dao.DAOColeccionista;
import cr.ac.ucenfotec.bl.dao.DAOItem;
import cr.ac.ucenfotec.bl.dao.DAOSubasta;
import cr.ac.ucenfotec.bl.entidades.*;
import cr.ac.ucenfotec.bl.excepciones.*;
import cr.ac.ucenfotec.bl.gestores.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Controlador {

    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void registrarVendedor() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\nNombre: ");
        String nombre = in.readLine();
        System.out.println("\nApellidos: ");
        String apellidos = in.readLine();
        System.out.println("\nCédula: ");
        String cedula = in.readLine();
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

        System.out.println(GestorVendedor.registrarVendedor(nombre, apellidos, cedula, dia, mes, annio, correo, password, direccion));
    }

    public static void registrarColeccionista() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\nNombre: ");
        String nombre = in.readLine();
        System.out.println("\nApellidos: ");
        String apellidos = in.readLine();
        System.out.println("\nCédula: ");
        String cedula = in.readLine();
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

        System.out.println(GestorColeccionista.registrarVendedor(nombre, apellidos, cedula, dia, mes, annio, correo, password, direccion));
    }

    public static Vendedor ingresarVendedor() throws IOException, SQLException, ClassNotFoundException, UsuarioNoExisteException, ContraseniaIncorrectaException {
        System.out.println("---Inicio de Sesión---");
        System.out.println("\nID de Usuario: ");
        String loginId = in.readLine();
        System.out.println("\nContraseña: ");
        String password = in.readLine();

        return GestorVendedor.ingresarVendedor(loginId, password);
    }

    public static Coleccionista ingresarColeccionista() throws IOException, SQLException, ClassNotFoundException, UsuarioNoExisteException, ContraseniaIncorrectaException {
        System.out.println("---Inicio de Sesión---");
        System.out.println("\nID de Usuario: ");
        String loginId = in.readLine();
        System.out.println("\nContraseña: ");
        String password = in.readLine();

        return GestorColeccionista.ingresarColeccionista(loginId, password);
    }

    public static Moderador ingresarModerador() throws IOException, SQLException, ClassNotFoundException, UsuarioNoExisteException, ContraseniaIncorrectaException {
        System.out.println("---Inicio de Sesión---");
        System.out.println("\nID de Usuario: ");
        String loginId = in.readLine();
        System.out.println("\nContraseña: ");
        String password = in.readLine();

        return GestorModerador.ingresarModerador(loginId, password);
    }

    public static void mostrarColeccion(String idColeccionista) throws SQLException, IOException, ClassNotFoundException {
        System.out.println(GestorItem.mostrarColeccion(idColeccionista));
    }

    public static void mostrarSubastasActivas() throws UsuarioNoExisteException, SQLException, IOException, ClassNotFoundException {
        System.out.println(GestorSubasta.mostrarSubastasActivas());
    }

    public static void mostrarSubastas(Vendedor vendedor) throws UsuarioNoExisteException, SQLException, IOException, ClassNotFoundException {
        System.out.print(GestorSubasta.mostrarSubastas(vendedor));
    }

    public static void mostrarSubastas(Coleccionista coleccionista) throws UsuarioNoExisteException, SQLException, IOException, ClassNotFoundException {
        System.out.print(GestorSubasta.mostrarSubastas(coleccionista));
    }

    public static void realizarOferta(Coleccionista coleccionista) throws IOException, SQLException, ClassNotFoundException, UsuarioNoExisteException, SubastaNoExisteException {
        System.out.println("\nDigite el ID de la subasta:");
        String idSubasta = in.readLine();
        System.out.println("\nDigite el monto de su oferta:");
        double monto = Double.parseDouble(in.readLine());
        System.out.println(GestorOferta.realizarOferta(idSubasta, coleccionista, monto));
    }

    public static void crearSubasta(Vendedor vendedor) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\n-----Crear Subasta-----");
        ArrayList<Item> items = new ArrayList<>();

        System.out.println("\n¿Cuantos items desea agregar a la subasta?");
        int cantidad = 1;
        try {
            cantidad = Integer.parseInt(in.readLine());
        } catch (NumberFormatException e) {
            System.out.print("Debe digitar un numero");
            crearSubasta(vendedor);
            return;
        }
        int ultimoId = 1;
        for (int i = 1; i <= cantidad; i++) {
            System.out.println("\n-Item " + i);
            System.out.println("\nDigite el nombre del item: ");
            String nombre = in.readLine();
            System.out.println("\nDescriba el item: ");
            String descripcion = in.readLine();
            System.out.println("\n¿Cual es el estado del item? ");
            String estado = in.readLine();
            System.out.println("\nFecha de origen -- Digite el año:");
            int annio = Integer.parseInt(in.readLine());
            System.out.println("\nFecha de origen -- Digite el mes:");
            int mes = Integer.parseInt(in.readLine());
            System.out.println("\nFecha de origen -- Digite el dia:");
            int dia = Integer.parseInt(in.readLine());
            Item item = null;
            if (i==1) {
                item = new Item(nombre, descripcion, estado, dia, mes, annio);
            } else {
                item = new Item("I-"+ultimoId, nombre, descripcion, estado, LocalDate.of(annio, mes, dia));
            }
            ultimoId = Character.getNumericValue(item.getId().charAt(2));
            ultimoId++;

            items.add(item);
        }

        System.out.println("\n¿Cuantos días desea que la subasta se mantenga activa?");
        int dias = Integer.parseInt(in.readLine());
        LocalDateTime fechaVencimiento = LocalDateTime.now().plusDays(dias);

        System.out.println("\n¿Cual es el precio mínimo que aceptaría por esta subasta?");
        double precio = Double.parseDouble(in.readLine());


        System.out.println(GestorSubasta.crearSubasta(vendedor, items, fechaVencimiento, precio));
    }

    public static void crearSubasta(Coleccionista coleccionista) throws IOException, SQLException, ClassNotFoundException{
        System.out.println("\n-----Crear Subasta-----");
        ArrayList<Item> items = new ArrayList<>();
        int cantidad = 1;


        System.out.println(DAOItem.seleccionarColeccion(coleccionista.getId()));

        System.out.println("\n¿Cuantos items desea agregar a la subasta?");
        try {
            cantidad = Integer.parseInt(in.readLine());
        } catch (NumberFormatException e) {
            System.out.print("Debe digitar un numero");
            crearSubasta(coleccionista);
            return;
        }

        for (int i = 1; i <= cantidad; i++) {
            System.out.println("Digite el ID del item que desea agregar:");
            String idItem = in.readLine();
            try {
                Item item = GestorItem.seleccionarItem(idItem, coleccionista.getId());
                items.add(item);
            } catch (ItemNoExisteException e) {
                System.out.println(e.getMessage());
                i--;
            }
        }



        System.out.println("\n¿Cuantos días desea que la subasta se mantenga activa?");
        int dias = Integer.parseInt(in.readLine());
        LocalDateTime fechaVencimiento = LocalDateTime.now().plusDays(dias);

        System.out.println("\n¿Cual es el precio mínimo que aceptaría por esta subasta?");
        double precio = Double.parseDouble(in.readLine());


        System.out.println("\n" + GestorSubasta.crearSubasta(coleccionista, items, fechaVencimiento, precio));
    }

    public static void mostrarInteresesColeccionista(Coleccionista coleccionista) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n" + GestorColeccionista.mostrarIntereses(coleccionista.getId()));
    }

    public static void agregarIntereses(Coleccionista coleccionista) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n" + GestorColeccionista.mostrarTodosIntereses());

        System.out.println("¿Cúal interes desea agragar a su cuenta?\nDigite el número correspondiente");
        String idInteres = in.readLine();

        System.out.println(GestorColeccionista.agregarInteres(coleccionista, idInteres));
    }

    public static void adjudicarSubastasVencidas() throws SQLException, IOException, ClassNotFoundException, UsuarioNoExisteException {
        GestorSubasta.adjudicarSubastasVencidas();
    }

}
