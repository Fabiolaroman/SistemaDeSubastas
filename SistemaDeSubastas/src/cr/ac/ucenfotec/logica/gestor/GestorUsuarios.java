package cr.ac.ucenfotec.logica.gestor;

import cr.ac.ucenfotec.logica.entidades.Coleccionista;
import cr.ac.ucenfotec.logica.entidades.Moderador;
import cr.ac.ucenfotec.logica.entidades.Usuario;
import cr.ac.ucenfotec.logica.entidades.Vendedor;

import java.util.ArrayList;

public class GestorUsuarios {

    private ArrayList<Usuario> usuarios;

    public GestorUsuarios() {
        usuarios = new ArrayList<>();
    }

    // registrar usuario
    public void registrarModerador(String nombre, String apellidos, String id, int dia, int mes, int annio, String correo, String password) {
            Moderador usuario = new Moderador(nombre, apellidos, id, dia, mes, annio, correo, password);
            if(usuario.getEdad() >= 18) {
                usuarios.add(usuario);
            } else {
                System.out.println("Debe ser mayor de edad para registrarse en nuestro sistema");
            }
    }

    public void registrarVendedor(String nombre, String apellidos, String id, int dia, int mes, int annio, String correo, String password, String direccion) {
        Vendedor usuario = new Vendedor(nombre, apellidos, id, dia, mes, annio, correo, password, direccion);
        if(usuario.getEdad() >= 18) {
                usuarios.add(usuario);
            } else {
                System.out.println("Debe ser mayor de edad para registrarse en nuestro sistema");
            }
    }

    public void registrarColeccionista(String nombre, String apellidos, String id, int dia, int mes, int annio, String correo, String password, String direccion) {
        Coleccionista usuario = new Coleccionista(nombre, apellidos, id, dia, mes, annio, correo, password, direccion);
        if(usuario.getEdad() >= 18) {
                usuarios.add(usuario);
            } else {
                System.out.println("Debe ser mayor de edad para registrarse en nuestro sistema");
            }
    }

    // listar usuarios
    public void listarUsuarios() {

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados");
        } else {

            for (Usuario u : usuarios) {
                System.out.println(u);
            }

        }
    }
}

