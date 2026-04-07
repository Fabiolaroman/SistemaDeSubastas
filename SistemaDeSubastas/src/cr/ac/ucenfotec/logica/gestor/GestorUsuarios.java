package cr.ac.ucenfotec.logica.gestor;

import cr.ac.ucenfotec.logica.entidades.Coleccionista;
import cr.ac.ucenfotec.logica.entidades.Moderador;
import cr.ac.ucenfotec.logica.entidades.Usuario;
import cr.ac.ucenfotec.logica.entidades.Vendedor;

import java.util.ArrayList;

public class GestorUsuarios {

    private Moderador moderador;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<Coleccionista> coleccionistas;

    public GestorUsuarios() {
        vendedores = new ArrayList<>();
        coleccionistas = new ArrayList<>();
    }

    // registrar usuario
    public void registrarModerador(String nombre, String apellidos, String id, int dia, int mes, int annio, String correo, String password) {
            moderador = new Moderador(nombre, apellidos, id, dia, mes, annio, correo, password);
    }

    public void registrarVendedor(String nombre, String apellidos, String id, int dia, int mes, int annio, String correo, String password, String direccion) {
        Vendedor usuario = new Vendedor(nombre, apellidos, id, dia, mes, annio, correo, password, direccion);
        if(usuario.getEdad() >= 18) {
                vendedores.add(usuario);
            } else {
                System.out.println("Debe ser mayor de edad para registrarse en nuestro sistema");
            }
    }

    public void registrarColeccionista(String nombre, String apellidos, String id, int dia, int mes, int annio, String correo, String password, String direccion) {
        Coleccionista usuario = new Coleccionista(nombre, apellidos, id, dia, mes, annio, correo, password, direccion);
        if(usuario.getEdad() >= 18) {
                coleccionistas.add(usuario);
            } else {
                System.out.println("Debe ser mayor de edad para registrarse en nuestro sistema");
            }
    }

    // listar usuarios
    public void listarVendedores() {

        if (vendedores.isEmpty()) {
            System.out.println("No hay vendedores registrados");
        } else {

            for (Usuario u : vendedores) {
                System.out.println(u);
            }

        }
    }

    public void listarColeccionistas() {

        if (coleccionistas.isEmpty()) {
            System.out.println("No hay vendedores registrados");
        } else {

            for (Usuario u : coleccionistas) {
                System.out.println(u);
            }

        }
    }
    //getter


    public Moderador getModerador() {
        return moderador;
    }

    public ArrayList<Vendedor> getVendedores() {
        return vendedores;
    }

    public ArrayList<Coleccionista> getColeccionistas() {
        return coleccionistas;
    }

    //setter


    public void setModerador(Moderador moderador) {
        this.moderador = moderador;
    }

    //usuarioXid
    public Vendedor vendedorXId(String id){
        Vendedor vendedorEncontrado = null;
        boolean encontrado = false;

        for (Vendedor vendedor : vendedores){
            if(vendedor.getId().equals(id)){
                vendedorEncontrado = vendedor;
                encontrado = true;
            }
        }

        if(!encontrado){
            System.out.println("Usuario no exite");
        }
        return vendedorEncontrado;
    }

    public Coleccionista coleccionistaXId(String id){
        Coleccionista coleccionistaEncontrado = null;
        boolean encontrado = false;

        for (Coleccionista coleccionista : coleccionistas){
            if(coleccionista.getId().equals(id)){
                coleccionistaEncontrado = coleccionista;
                encontrado = true;
            }
        }

        if(!encontrado){
            System.out.println("Usuario no exite");
        }
        return coleccionistaEncontrado;
    }
}

