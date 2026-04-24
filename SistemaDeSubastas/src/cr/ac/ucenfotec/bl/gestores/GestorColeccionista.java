package cr.ac.ucenfotec.bl.gestores;

import cr.ac.ucenfotec.bl.dao.DAOColeccionista;
import cr.ac.ucenfotec.bl.entidades.Coleccionista;
import cr.ac.ucenfotec.bl.excepciones.UsuarioInvalidoException;
import cr.ac.ucenfotec.bl.excepciones.UsuarioNoExisteException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
        Coleccionista coleccionista = DAOColeccionista.seleccionarColeccionista(loginId, password);

        ArrayList<String> intereses = DAOColeccionista.seleccionarIntereses(loginId);
        intereses.forEach(coleccionista::agregarInteres);
        return coleccionista;
    }

    public static ArrayList<String> obtenerIntereses(String idColeccionista) throws SQLException, IOException, ClassNotFoundException {
        return DAOColeccionista.seleccionarIntereses(idColeccionista);
    }
}
