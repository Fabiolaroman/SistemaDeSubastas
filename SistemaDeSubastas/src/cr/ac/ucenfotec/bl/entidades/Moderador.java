package cr.ac.ucenfotec.bl.entidades;

import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Moderador extends Usuario{

    //constructores
    private static int numeroUltimoID() throws SQLException, IOException, ClassNotFoundException {
        String query = "SELECT * FROM t_moderador ORDER BY id DESC LIMIT 1;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) return 0;
        String id = resultado.getString("id");
        return Integer.parseInt(id.substring(2));
    }

    public Moderador(String nombre, String apellidos, String cedula, int dia, int mes, int annio, String correo, String password) throws SQLException, IOException, ClassNotFoundException {
        super(nombre, apellidos, cedula, dia, mes, annio, correo, password);
        int numeroID = numeroUltimoID() + 1;
        id = "M-" + numeroID;
    }

    public Moderador(String id, String nombre, String apellidos, String cedula, LocalDate fechaNacimiento, String correo, String password) {
        super(id, nombre, apellidos, cedula, fechaNacimiento, correo, password);
    }

    //toString
    public String toString() {
        return "Moderador:" + super.toString();
    }

    //equals

    @Override
    public boolean equals(Usuario usuario) {
        if (usuario instanceof Moderador){
           return super.equals(usuario);
        } else {
            return false;
        }
    }
}
