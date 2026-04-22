package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entidades.Vendedor;
import cr.ac.ucenfotec.bl.excepciones.ContraseniaIncorrectaException;
import cr.ac.ucenfotec.bl.excepciones.UsuarioNoExisteException;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOVendedor {
    private static String statement;
    private static String query;

    public static String insertarVendedor(Vendedor vendedor) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_vendedor VALUES ('" + vendedor.getId() + "', '" + vendedor.getNombre() + "', '" + vendedor.getApellidos() + "', '" + vendedor.getCedula() + "', '" + vendedor.getFechaNacimiento() + "', '" + vendedor.getCorreo() + "', '" + vendedor.getPassword() + "', " + vendedor.getPuntuacion() + ", '" + vendedor.getDireccion() + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "\nLa cuenta de vendedor se registró con éxito\n!!! Su ID de usuario es: " + vendedor.getId() + " !!!";
    }

    public static Vendedor seleccionarVendedor(String loginId, String password) throws SQLException, IOException, ClassNotFoundException, UsuarioNoExisteException, ContraseniaIncorrectaException {
        query = "SELECT * FROM t_vendedor WHERE id = ?;";
        ResultSet resultado =  Conector.getConexion().ejecutarQuery(query, loginId);
        if (!resultado.next()) throw new UsuarioNoExisteException("No existe un usuario de vendedor con ese ID");

        query = "SELECT * FROM t_vendedor WHERE id = ? AND contrasenia = ?;";
        resultado =  Conector.getConexion().ejecutarQuery(query, loginId, password);
        if (!resultado.next()) throw new ContraseniaIncorrectaException("La contraseña es incorrecta");

        return new Vendedor(
                resultado.getString("id"),
                resultado.getString("nombre"),
                resultado.getString("apellidos"),
                resultado.getString("cedula"),
                resultado.getDate("fecha_nacimiento").toLocalDate(),
                resultado.getString("correo"),
                resultado.getString("contrasenia"),
                resultado.getDouble("puntuacion"),
                resultado.getString("direccion")
        );
    }
}
