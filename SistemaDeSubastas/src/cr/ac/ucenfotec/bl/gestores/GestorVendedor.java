package cr.ac.ucenfotec.bl.gestores;

import cr.ac.ucenfotec.bl.dao.DAOVendedor;
import cr.ac.ucenfotec.bl.entidades.Vendedor;
import cr.ac.ucenfotec.bl.excepciones.UsuarioInvalidoException;

import java.io.IOException;
import java.sql.SQLException;

public class GestorVendedor {

    public static String registrarVendedor(String nombre, String apellidos, String cedula, int dia, int mes, int annio, String correo, String password, String direccion) throws SQLException, IOException, ClassNotFoundException, UsuarioInvalidoException {
        Vendedor vendedor;

        try {
            vendedor = new Vendedor(nombre, apellidos, cedula, dia, mes, annio, correo, password, direccion);
        } catch (UsuarioInvalidoException e) {
            return e.getMessage();
        }


        return DAOVendedor.insertarVendedor(vendedor);
    }
}
