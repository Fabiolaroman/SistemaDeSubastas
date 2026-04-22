package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entidades.Coleccionista;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.SQLException;

public class DAOColeccionista {
    private static String statement;
    private static String query;

    public static String insertarColeccionista(Coleccionista coleccionista) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_coleccionista VALUES ('" + coleccionista.getId() + "', '" + coleccionista.getNombre() + "', '" + coleccionista.getApellidos() + "', '" + coleccionista.getCedula() + "', '" + coleccionista.getFechaNacimiento() + "', '" + coleccionista.getCorreo() + "', '" + coleccionista.getPassword() + "', " + coleccionista.getPuntuacion() + ", '" + coleccionista.getDireccion() + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "\nLa cuenta de coleccionista se registró con éxito\n!!! Su ID de usuario es: " + coleccionista.getId() + " !!!";
    }
}
