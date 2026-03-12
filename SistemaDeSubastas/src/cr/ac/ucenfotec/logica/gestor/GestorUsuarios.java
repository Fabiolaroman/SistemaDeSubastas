package cr.ac.ucenfotec.logica.gestor;

import cr.ac.ucenfotec.logica.entidades.Usuario;
import java.util.ArrayList;

public class GestorUsuarios {

    private ArrayList<Usuario> usuarios;

    public GestorUsuarios() {
        usuarios = new ArrayList<>();
    }

    // registrar usuario
    public void registrarUsuario(String nombre, String id, int edad, String correo) {

        Usuario nuevoUsuario = new Usuario(nombre, id, edad, correo);
        usuarios.add(nuevoUsuario);

        System.out.println("Usuario registrado correctamente");
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