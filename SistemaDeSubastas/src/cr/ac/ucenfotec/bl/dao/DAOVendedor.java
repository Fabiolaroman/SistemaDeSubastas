package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entidades.Vendedor;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.SQLException;

public class DAOVendedor {
    private static String statement;
    private static String query;

    public static String insertarVendedor(Vendedor vendedor) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_vendedor VALUES ('" + vendedor.getId() + "', '" + vendedor.getNombre() + "', '" + vendedor.getApellidos() + "', '" + vendedor.getCedula() + "', '" + vendedor.getFechaNacimiento() + "', '" + vendedor.getCorreo() + "', '" + vendedor.getPassword() + "', " + vendedor.getPuntuacion() + ", '" + vendedor.getDireccion() + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "\nLa cuenta de vendedor se registró con éxito\n!!! Su ID de usuario es: " + vendedor.getId() + " !!!";
    }
}
