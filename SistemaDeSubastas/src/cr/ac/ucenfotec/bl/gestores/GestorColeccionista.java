package cr.ac.ucenfotec.bl.gestores;

import cr.ac.ucenfotec.bl.dao.DAOColeccionista;
import cr.ac.ucenfotec.bl.entidades.Coleccionista;
import cr.ac.ucenfotec.bl.excepciones.UsuarioInvalidoException;
import cr.ac.ucenfotec.bl.excepciones.UsuarioNoExisteException;

import java.io.IOException;
import java.sql.SQLException;

public class GestorColeccionista {

    public static String registrarVendedor(String nombre, String apellidos, String cedula, int dia, int mes, int annio, String correo, String password, String direccion) throws SQLException, IOException, ClassNotFoundException {
        Coleccionista coleccionista;

        try {
            coleccionista = new Coleccionista(nombre, apellidos, cedula, dia, mes, annio, correo, password, direccion);
        } catch (UsuarioInvalidoException e) {
            return e.getMessage();
        }

        return DAOColeccionista.insertarColeccionista(coleccionista);
    }

    public static Coleccionista ingresarColeccionista(String loginId, String password) throws UsuarioNoExisteException, SQLException, IOException, ClassNotFoundException {
        return DAOColeccionista.seleccionarColeccionista(loginId, password);
    }
}
