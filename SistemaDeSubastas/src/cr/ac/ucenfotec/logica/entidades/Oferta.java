package cr.ac.ucenfotec.logica.entidades;

public class Oferta {

    private String idUsuario;
    private double monto;

    // constructor
    public Oferta(String idUsuario, double monto) {
        this.idUsuario = idUsuario;
        this.monto = monto;
    }

    // getters
    public String getIdUsuario() {
        return idUsuario;
    }


    public double getMonto() {
        return monto;
    }


    public String toString() {
        return "Usuario: " + idUsuario + " | Monto: " + monto;
    }

}