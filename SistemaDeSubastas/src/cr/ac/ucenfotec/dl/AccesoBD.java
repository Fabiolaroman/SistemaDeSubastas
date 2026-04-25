package cr.ac.ucenfotec.dl;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public void ejecutarStatement(String statement, String s1) throws SQLException {
        preparedStatement = conexion.prepareStatement(statement);
        preparedStatement.setString(1, s1);
        preparedStatement.executeUpdate();
    }

    public void ejecutarStatement(String statement, String s1, String s2) throws SQLException {
        preparedStatement = conexion.prepareStatement(statement);
        preparedStatement.setString(1, s1);
        preparedStatement.setString(2, s2);
        preparedStatement.executeUpdate();
    }

    public void ejecutarStatement(String statement, String s1, String s2, double d, boolean b, LocalDateTime f) throws SQLException {
        preparedStatement = conexion.prepareStatement(statement);
        preparedStatement.setString(1, s1);
        preparedStatement.setString(2, s2);
        preparedStatement.setDouble(3, d);
        preparedStatement.setBoolean(4, b);
        preparedStatement.setTimestamp(5, Timestamp.valueOf(f));
        preparedStatement.executeUpdate();
    }

    public void ejecutarStatement(String statement, String s1, String s2, String s3, String s4, LocalDate d,String s5) throws SQLException {
        preparedStatement = conexion.prepareStatement(statement);
        preparedStatement.setString(1, s1);
        preparedStatement.setString(2, s2);
        preparedStatement.setString(3, s3);
        preparedStatement.setString(4, s4);
        preparedStatement.setDate(5, Date.valueOf(d));
        preparedStatement.setString(6, s5);
        preparedStatement.executeUpdate();
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
