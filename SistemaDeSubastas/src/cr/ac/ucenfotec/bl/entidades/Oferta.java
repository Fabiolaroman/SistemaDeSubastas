package cr.ac.ucenfotec.bl.entidades;

import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Oferta {

    private String id;
    private Coleccionista usuario;
    private double monto;

    // constructor
    private static int numeroUltimoID() throws SQLException, IOException, ClassNotFoundException {
        String query = "SELECT * FROM t_vendedor ORDER BY id DESC LIMIT 1;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) return 0;
        String id = resultado.getString("id");
        return Integer.parseInt(id.substring(2));
    }


    public Oferta(Coleccionista usuario, double monto) throws SQLException, IOException, ClassNotFoundException {
        int numeroID = numeroUltimoID() + 1;
        this.id = "O-" + numeroID;
        this.usuario = usuario;
        this.monto = monto;
    }

    //getters y setters
    public Coleccionista getUsuario() {
        return usuario;
    }

    public void setUsuario(Coleccionista usuario) {
        this.usuario = usuario;
    }

    public String getNombreUsuario() {
        return usuario.getNombre();
    }

    public String getApellidosUsuario() {return usuario.getApellidos();}

    public double getPuntuacionUsuario() {
        return usuario.getPuntuacion();
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    //toString
    @Override
    public String toString() {
        return "Oferta: " +
                "\nDe: " + getNombreUsuario() + " " + getApellidosUsuario() +
                "\nPor: " + monto;
    }

    //equals
    public boolean equals(Oferta oferta) {
        return this.usuario.equals(oferta.usuario) && this.monto == oferta.monto;
    }
}