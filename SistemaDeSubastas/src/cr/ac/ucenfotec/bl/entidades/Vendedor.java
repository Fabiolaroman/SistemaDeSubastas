package cr.ac.ucenfotec.bl.entidades;

import cr.ac.ucenfotec.bl.excepciones.UsuarioInvalidoException;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Vendedor extends Usuario{
    private double puntuacion;
    private String direccion;

    //constructor
    private static int numeroUltimoID() throws SQLException, IOException, ClassNotFoundException {
        String query = "SELECT * FROM t_vendedor ORDER BY id DESC LIMIT 1;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) return 0;
        String id = resultado.getString("id");
        return Integer.parseInt(id.substring(2));
    }

    public Vendedor(String nombre, String apellidos, String cedula, int dia, int mes, int annio, String correo, String password, String direccion) throws SQLException, IOException, ClassNotFoundException, UsuarioInvalidoException {
        int numeroID = numeroUltimoID() + 1;
        super(nombre, apellidos, cedula, dia, mes, annio, correo, password);
        id = "V-" + numeroID;
        this. direccion = direccion;
        this.puntuacion = 5.0;

        if (edad <= 18) {
            throw new UsuarioInvalidoException("Debe ser mayor de edad para registrarse en nuestro sistema");
        }
    }

    //getters y setters

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    //toString
    public String toString() {
        return "Vendedor:" + super.toString() +
                "\n | Puntuación: " + puntuacion +
                "\n | Dirección: " + direccion;
    }

    //equals

    @Override
    public boolean equals(Usuario usuario) {
        if (usuario instanceof Vendedor){
            return super.equals(usuario);
        } else {
            return false;
        }
    }

    //crear subasta
//    public void crearSubasta(GestorSubastas gestor, int dia, int mes, int annio, int hora, int minutos, double precio, ArrayList<Item> items){
//        try {
//            gestor.crearSubasta(dia, mes, annio, hora, minutos, this, precio, items);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
}
