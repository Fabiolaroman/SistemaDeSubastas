package cr.ac.ucenfotec.bl.entidades;

import java.time.LocalDate;
import java.time.Period;

public abstract class Usuario {

    // Atributos
    protected String id;
    protected String nombre;
    protected String apellidos;
    protected String cedula;
    protected LocalDate fechaNacimiento;
    protected int edad;
    protected String correo;
    protected String password;

    // Constructor
    public Usuario(){

    }

    public Usuario(String nombre, String apellidos, String cedula, int dia, int mes, int annio, String correo, String password) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.fechaNacimiento = LocalDate.of(annio, mes, dia);
        this.edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        this.correo = correo;
        this.password = password;
    }

    public Usuario(String id, String nombre, String apellidos, String cedula, LocalDate fechaNacimiento, String correo, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        this.correo = correo;
        this.password = password;
    }

    // Getter y Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
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
                "\n | Cedula: " + cedula +
                "\n | Fecha de Nacimiento: " + fechaNacimiento.getDayOfMonth() + "/" + fechaNacimiento.getMonth() + "/" + getFechaNacimiento().getYear() +
                "\n | Edad: " + edad +
                "\n | Correo: " + correo;
    }

    //equals
    public boolean equals(Usuario usuario) {
        return this.cedula.equals(usuario.cedula);
    }
}