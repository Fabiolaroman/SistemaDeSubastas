package cr.ac.ucenfotec.logica.entidades;

import java.time.LocalDate;
import java.time.Period;

public abstract class Usuario {

    // Atributos
    protected String nombre;
    protected String apellidos;
    protected String identificacion;
    protected LocalDate fechaNacimiento;
    protected int edad;
    protected String correo;
    protected String password;

    // Constructor
    public Usuario(){

    }

    public Usuario(String nombre, String apellidos, String identificacion, int dia, int mes, int annio, String correo, String password) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.identificacion = identificacion;
        this.fechaNacimiento = LocalDate.of(annio, mes, dia);
        this.edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        this.correo = correo;
        this.password = password;
    }

    // Getter y Setters
    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }


    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }


    public void setEdad(int edad) {
        this.edad = edad;
    }


    public String getCorreo() {
        return correo;
    }


    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString
    public String toString() {
        return "\n | Nombre: " + nombre +
                "\n | ID: " + identificacion +
                "\n | Fecha de Nacimiento: " + fechaNacimiento.getDayOfMonth() + "/" + fechaNacimiento.getMonth() + "/" + getFechaNacimiento().getYear() +
                "\n | Edad: " + edad +
                "\n | Correo: " + correo;
    }

    //equals
    public boolean equals(Usuario usuario) {
        return this.identificacion.equals(usuario.identificacion);
    }
}