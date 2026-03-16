package cr.ac.ucenfotec.logica.entidades;

public class Oferta {

    private Coleccionista usuario;
    private double monto;

    // constructor
    public Oferta() {}

    public Oferta(double monto) {
        this.monto = monto;
    }

    public Oferta(Coleccionista usuario, double monto) {
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
                "\nDe: " + getNombreUsuario() +
                "\nPor: " + monto;
    }

    //equals
    public boolean equals(Oferta oferta) {
        return this.usuario.equals(oferta.usuario) && this.monto == oferta.monto;
    }
}