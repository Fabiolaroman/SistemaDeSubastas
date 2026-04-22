package cr.ac.ucenfotec.bl.entidades;

import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

public class Item {
    //atributos
    private String id;
    private String nombre;
    private String descripcion;
    private String estado;
    private LocalDate fechaCompra;
    private Period antiguedad;

    //constructor
    private static int numeroUltimoID() throws SQLException, IOException, ClassNotFoundException {
        String query = "SELECT * FROM t_coleccionista ORDER BY id DESC LIMIT 1;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) return 0;
        String id = resultado.getString("id");
        return Integer.parseInt(id.substring(2));
    }

    public Item() {

    }

    public Item(String nombre, String descripcion, String estado, int dia, int mes, int annio) throws SQLException, IOException, ClassNotFoundException {
        int numeroId = numeroUltimoID() + 1;
        this.id = "I-" + numeroId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCompra = LocalDate.of(annio, mes, dia);
        this.antiguedad = Period.between(fechaCompra, LocalDate.now());
    }

    public Item(String id, String nombre, String descripcion, String estado, LocalDate fechaCompra) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
        this.antiguedad = Period.between(fechaCompra, LocalDate.now());
    }

    //getter y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Item.contador = contador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Period getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Period antiguedad) {
        this.antiguedad = antiguedad;
    }


    //toString

    @Override
    public String toString() {
        return "ID: " + id +" | Item: " + nombre + " | Descripcción: " + descripcion + " | Fecha de Origen: " + fechaCompra + " | Antigüedad: " + antiguedad;
    }

    //equals
    public boolean equals(Item item) {
        return this.id.equals(item.id);
    }
}
