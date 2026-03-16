package cr.ac.ucenfotec.logica.entidades;

import cr.ac.ucenfotec.logica.gestor.GestorSubastas;

import java.util.ArrayList;

public class Vendedor extends Usuario{
    private double puntuacion;
    private String direccion;

    //constructor
    public Vendedor(String nombre, String apellidos, String identificacion, int dia, int mes, int annio, String correo, String password, String direccion){
        super(nombre, apellidos, identificacion, dia, mes, annio, correo, password);
        this. direccion = direccion;
        this.puntuacion = 5.0;
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
    public void crearSubasta(GestorSubastas gestor, int dia, int mes, int annio, int hora, int minutos, double precio, ArrayList<Item> items){
        gestor.crearSubasta(dia, mes, annio, hora, minutos, this, precio, items);
    }
}
