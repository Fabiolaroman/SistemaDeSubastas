package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entidades.Coleccionista;
import cr.ac.ucenfotec.bl.entidades.Vendedor;
import cr.ac.ucenfotec.bl.excepciones.ContraseniaIncorrectaException;
import cr.ac.ucenfotec.bl.excepciones.UsuarioNoExisteException;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOColeccionista {
    private static String statement;
    private static String query;

    public static String insertarColeccionista(Coleccionista coleccionista) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_coleccionista VALUES ('" + coleccionista.getId() + "', '" + coleccionista.getNombre() + "', '" + coleccionista.getApellidos() + "', '" + coleccionista.getCedula() + "', '" + coleccionista.getFechaNacimiento() + "', '" + coleccionista.getCorreo() + "', '" + coleccionista.getPassword() + "', " + coleccionista.getPuntuacion() + ", '" + coleccionista.getDireccion() + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "\nLa cuenta de coleccionista se registró con éxito\n!!! Su ID de usuario es: " + coleccionista.getId() + " !!!";
    }

    public static Coleccionista seleccionarColeccionista(String loginId, String password) throws SQLException, IOException, ClassNotFoundException, UsuarioNoExisteException, ContraseniaIncorrectaException {
        query = "SELECT * FROM t_coleccionista WHERE id = ?;";
        ResultSet resultado =  Conector.getConexion().ejecutarQuery(query, loginId);
        if (!resultado.next()) throw new UsuarioNoExisteException("No existe un usuario de coleccionista con ese ID");

        query = "SELECT * FROM t_coleccionista WHERE id = ? AND contrasenia = ?;";
        resultado =  Conector.getConexion().ejecutarQuery(query, loginId, password);
        if (!resultado.next()) throw new ContraseniaIncorrectaException("La contraseña es incorrecta");

        return new Coleccionista(
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

    public static Coleccionista seleccionarColeccionista(String loginId) throws SQLException, IOException, ClassNotFoundException, UsuarioNoExisteException, ContraseniaIncorrectaException {
        query = "SELECT * FROM t_coleccionista WHERE id = ?;";
        ResultSet resultado =  Conector.getConexion().ejecutarQuery(query, loginId);
        resultado.next();

        return new Coleccionista(
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

    public static ArrayList<String> seleccionarIntereses(String idColeccionista) throws SQLException, IOException, ClassNotFoundException {

        query = "SELECT nombre FROM t_interes INNER JOIN t_coleccionista_interes ON id = id_interes WHERE id_coleccionista = ?;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, idColeccionista);

        ArrayList<String> intereses = new ArrayList<>();
        while (resultado.next()) {
            intereses.add(resultado.getString("nombre"));
        }
        return intereses;
    }

    public static ArrayList<String> seleccionarIntereses() throws SQLException, IOException, ClassNotFoundException {

        query = "Select * FROM t_interes i;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        ArrayList<String> intereses = new ArrayList<>();
        int contador = 0;
        while (resultado.next()) {
            contador++;
            intereses.add(contador +". " + resultado.getString("nombre"));
        }

        return intereses;
    }

    public static String insertarInteres(Coleccionista coleccionista, String idInteres) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_interes WHERE id = ?";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, idInteres);
        if(!resultado.next()) return "Numero de interes seleccionado no existe";

        statement = "INSERT INTO t_coleccionista_interes Values('" + coleccionista.getId() + "', '" + idInteres + "');";
        Conector.getConexion().ejecutarStatement(statement);

        return "Su nuevo interés ha sido agregado a su cuanta";
    }
}
