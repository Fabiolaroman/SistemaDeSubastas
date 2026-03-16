package cr.ac.ucenfotec.logica.entidades;

import java.util.ArrayList;

public class Coleccionista extends Usuario{
    private double puntuacion;
    private String direccion;
    private ArrayList<Item> coleccion;
    private ArrayList<String> intereses;

    //constructor
    public Coleccionista(String nombre, String apellidos, String identificacion, int dia, int mes, int annio, String correo, String password, String direccion){
        super(nombre, apellidos, identificacion, dia, mes, annio, correo, password);
        this.direccion = direccion;
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

    public ArrayList<Item> getColeccion() {
        return coleccion;
    }

    public ArrayList<String> getIntereses() {
        return intereses;
    }

    //agregar objetos e intereses

    public void agregarItems(Item item){
        coleccion.add(item);
    }

    public void agregarInteres(String interes){
        intereses.add(interes);
    }

    //toString
    public String toString() {
        return "Coleccionista:" + super.toString() +
                "\n | Puntuación: " + puntuacion +
                "\n | Dirección: " + direccion +
                "\n | Intereses: " + intereses +
                "\n | Colección: " + coleccion;
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
}
