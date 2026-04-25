package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entidades.Moderador;
import cr.ac.ucenfotec.bl.excepciones.ContraseniaIncorrectaException;
import cr.ac.ucenfotec.bl.excepciones.UsuarioNoExisteException;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOModerador {
    private static String query;


    public static Moderador seleccionarModerador(String loginId, String password) throws SQLException, IOException, ClassNotFoundException, UsuarioNoExisteException {
        query = "SELECT * FROM t_moderador WHERE id = ?;";
        ResultSet resultado =  Conector.getConexion().ejecutarQuery(query, loginId);
        if (!resultado.next()) throw new UsuarioNoExisteException("No existe un usuario de coleccionista con ese ID");

        query = "SELECT * FROM t_moderador WHERE id = ? AND contrasenia = ?;";
        resultado =  Conector.getConexion().ejecutarQuery(query, loginId, password);
        if (!resultado.next()) throw new ContraseniaIncorrectaException("La contraseña es incorrecta");

        return new Moderador(
                resultado.getString("id"),
                resultado.getString("nombre"),
                resultado.getString("apellidos"),
                resultado.getString("cedula"),
                resultado.getDate("fecha_nacimiento").toLocalDate(),
                resultado.getString("correo"),
                resultado.getString("contrasenia")
        );
    }
}
