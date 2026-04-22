package cr.ac.ucenfotec.bl.gestores;

import cr.ac.ucenfotec.bl.entidades.Coleccionista;
import cr.ac.ucenfotec.bl.entidades.Moderador;
import cr.ac.ucenfotec.bl.entidades.Usuario;
import cr.ac.ucenfotec.bl.entidades.Vendedor;
import cr.ac.ucenfotec.bl.excepciones.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestorUsuarios {

    private static Moderador moderador;
    private static final ArrayList<Vendedor> vendedores = new ArrayList<>();
    private static final ArrayList<Coleccionista> coleccionistas = new ArrayList<>();

    // registrar usuario
    public static void registrarModerador(String nombre, String apellidos, String id, int dia, int mes, int annio, String correo, String password) {
        moderador = new Moderador(nombre, apellidos, id, dia, mes, annio, correo, password);
    }

    public static void registrarVendedor(String nombre, String apellidos, String cedula, int dia, int mes, int annio, String correo, String password, String direccion) throws UsuarioInvalidoException, SQLException, IOException, ClassNotFoundException {

        Vendedor usuario = new Vendedor(nombre, apellidos, cedula, dia, mes, annio, correo, password, direccion);

        if (usuario.getEdad() >= 18) {
            vendedores.add(usuario);
        } else {
            throw new UsuarioInvalidoException("Debe ser mayor de edad para registrarse en nuestro sistema");
        }

        vendedores.add(usuario);
        System.out.print("\n!!! Su ID de usuario es: " + usuario.getId() + " !!!\n");
    }

    public static void registrarColeccionista(String nombre, String apellidos, String id, int dia, int mes, int annio, String correo, String password, String direccion) throws UsuarioInvalidoException, SQLException, IOException, ClassNotFoundException {

        Coleccionista usuario = new Coleccionista(nombre, apellidos, id, dia, mes, annio, correo, password, direccion);

        if (usuario.getEdad() >= 18) {
            coleccionistas.add(usuario);
        } else {
            throw new UsuarioInvalidoException("\n ¡Debe ser mayor de edad para registrarse en nuestro sistema!");
        }

        coleccionistas.add(usuario);
        System.out.print("\n!!! Su ID de usuario es: " + usuario.getId() + " !!!\n");

    }

    // listar usuarios
    public static void listarVendedores() {
        if (vendedores.isEmpty()) {
            System.out.println("No hay vendedores registrados");
        } else {
            for (Usuario u : vendedores) {
                System.out.println(u);
            }
        }
    }

    public static void listarColeccionistas() {
        if (coleccionistas.isEmpty()) {
            System.out.println("No hay vendedores registrados");
        } else {
            for (Usuario u : coleccionistas) {
                System.out.println(u);
            }
        }
    }

    //getter
    public static Moderador getModerador() {
        return moderador;
    }

    public static ArrayList<Vendedor> getVendedores() {
        return vendedores;
    }

    public static ArrayList<Coleccionista> getColeccionistas() {
        return coleccionistas;
    }

    //setter
    public static void setModerador(Moderador mod) {
        moderador = mod;
    }

    //usuarioXid
    public static Vendedor vendedorXId(String id) throws UsuarioNoExisteException {
        Vendedor vendedorEncontrado = null;
        boolean encontrado = false;

        for (Vendedor vendedor : vendedores) {
            if (vendedor.getId().equals(id)) {
                vendedorEncontrado = vendedor;
                encontrado = true;
            }
        }

        if (!encontrado) {
            throw new UsuarioNoExisteException("Usuario no existe");
        }
        return vendedorEncontrado;
    }

    public static Coleccionista coleccionistaXId(String id) throws UsuarioNoExisteException {
        Coleccionista coleccionistaEncontrado = null;
        boolean encontrado = false;

        for (Coleccionista coleccionista : coleccionistas) {
            if (coleccionista.getId().equals(id)) {
                coleccionistaEncontrado = coleccionista;
                encontrado = true;
            }
        }

        if (!encontrado) {
            throw new UsuarioNoExisteException("Usuario no existe");
        }
        return coleccionistaEncontrado;
    }
}