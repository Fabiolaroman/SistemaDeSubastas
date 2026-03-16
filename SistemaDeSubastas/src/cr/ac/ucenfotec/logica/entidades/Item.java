package cr.ac.ucenfotec.logica.entidades;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

public class Item {
    //atributos
    private String nombre;
    private String id;
    private static int contador = 0;
    private String descripcion;
    private String estado;
    private LocalDate fechaCompra;
    private Period antiguedad;

    //constructor
    public Item() {

    }

    public Item(String nombre, String descripcion, String estado, int dia, int mes, int annio) {
        contador ++;

        this.nombre = nombre;
        this.id = "I-" + contador;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCompra = LocalDate.of(annio, mes, dia);
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
        return super.toString();
    }

    //equals
    public boolean equals(Item item) {
        return this.id.equals(item.id);
    }
}
