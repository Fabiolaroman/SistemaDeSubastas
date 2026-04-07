package cr.ac.ucenfotec.logica.entidades;

import cr.ac.ucenfotec.logica.gestor.GestorSubastas;

import java.util.ArrayList;

public class Coleccionista extends Usuario{
    private static int contador = 0;
    private double puntuacion;
    private String direccion;
    private ArrayList<Item> coleccion;
    private ArrayList<String> intereses;

    //constructor
    public Coleccionista(String nombre, String apellidos, String cedula, int dia, int mes, int annio, String correo, String password, String direccion){
        contador++;
        super(nombre, apellidos, cedula, dia, mes, annio, correo, password);
        id = "C-" + contador;
        this.direccion = direccion;
        this.puntuacion = 5.0;
        coleccion = new ArrayList<>();
        intereses = new ArrayList<>();
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

    //agregar y eliminar objetos e intereses

    public void agregarItems(Item item){
        coleccion.add(item);
    }

    public void agregarItems(ArrayList<Item> items){
        coleccion.addAll(items);
    }

    public void eliminar(String idItem){
        coleccion.forEach(item -> {
            if (idItem.equals(item.getId())) {
                coleccion.remove(item);
            }
        });
    }

    public void agregarInteres(String interes){
        intereses.add(interes);
    }

    public void eliminarInteres(String interes){
        intereses.remove(interes);
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
        if (usuario instanceof Coleccionista){
            return super.equals(usuario);
        } else {
            return false;
        }
    }

    //crear subasta
    public void crearSubasta(GestorSubastas gestor, int dia, int mes, int annio, int hora, int minutos, double precio, ArrayList<Item> items){
        if (this.coleccion.containsAll(items)) {
            gestor.crearSubasta(dia, mes, annio, hora, minutos, this, precio, items);
        } else {
            System.out.println("Solo puedes vender items de tu colección");
        }
    }

    //ofertar
//    public void ofertar(GestorSubastas gestor, String idSubasta, double monto){
//        gestor.agregarOferta(idSubasta, this, monto);
//    }
}
