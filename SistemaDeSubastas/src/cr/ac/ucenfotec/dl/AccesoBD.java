package cr.ac.ucenfotec.dl;

import java.sql.*;

public class AccesoBD {
    private Connection conexion;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public AccesoBD(String direccion, String usuario, String contrasenia) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection(direccion, usuario, contrasenia);
    }

    public void ejecutarStatement(String statement) throws SQLException {
        this.statement = conexion.createStatement();
        this.statement.executeUpdate(statement);
    }

    public ResultSet ejecutarQuery(String query) throws SQLException {
        preparedStatement = conexion.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

    public ResultSet ejecutarQuery(String query, String s1) throws SQLException {
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setString(1, s1);
        return preparedStatement.executeQuery();
    }

    public ResultSet ejecutarQuery(String query, Timestamp t) throws SQLException {
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setTimestamp(1, t);
        return preparedStatement.executeQuery();
    }

    public ResultSet ejecutarQuery(String query, String s1, String s2) throws SQLException {
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setString(1, s1);
        preparedStatement.setString(2, s2);
        return preparedStatement.executeQuery();
    }
}
