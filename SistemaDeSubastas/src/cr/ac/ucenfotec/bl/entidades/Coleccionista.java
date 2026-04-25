package cr.ac.ucenfotec.bl.entidades;

import cr.ac.ucenfotec.bl.excepciones.ItemNoExisteException;
import cr.ac.ucenfotec.bl.excepciones.UsuarioInvalidoException;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Coleccionista extends Usuario{
    private double puntuacion;
    private String direccion;
    private ArrayList<Item> coleccion = new ArrayList<>();
    private ArrayList<String> intereses = new ArrayList<>();

    //constructor
    private static int numeroUltimoID() throws SQLException, IOException, ClassNotFoundException {
        String query = "SELECT * FROM t_coleccionista ORDER BY id DESC LIMIT 1;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) return 0;
        String id = resultado.getString("id");
        return Integer.parseInt(id.substring(2));
    }

    public Coleccionista(String nombre, String apellidos, String cedula, int dia, int mes, int annio, String correo, String password, String direccion) throws SQLException, IOException, ClassNotFoundException, UsuarioInvalidoException {
        int numeroID = numeroUltimoID() + 1;
        super(nombre, apellidos, cedula, dia, mes, annio, correo, password);
        id = "C-" + numeroID;
        this.direccion = direccion;
        this.puntuacion = 5.0;

        if (edad <= 18) {
            throw new UsuarioInvalidoException("Debe ser mayor de edad para registrarse en nuestro sistema");
        }
    }

    public Coleccionista(String id, String nombre, String apellidos, String cedula, LocalDate fechaNacimiento, String correo, String password, double puntuacion, String direccion) {
        super(id, nombre, apellidos, cedula, fechaNacimiento, correo, password);
        this.puntuacion = puntuacion;
        this.direccion = direccion;
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
        coleccion.removeIf(item -> item.getId().equals(idItem));
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
//    public void crearSubasta(GestorSubastas gestor, int dia, int mes, int annio, int hora, int minutos, double precio, ArrayList<Item> items){
//        if (this.coleccion.containsAll(items)) {
//            try {
//                gestor.crearSubasta(dia, mes, annio, hora, minutos, this, precio, items);
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        } else {
//            System.out.println("Solo puedes vender items de tu colección");
//        }
//    }

    //ofertar
//    public void ofertar(GestorSubastas gestor, String idSubasta, double monto){
//        gestor.agregarOferta(idSubasta, this, monto);
//    }
}