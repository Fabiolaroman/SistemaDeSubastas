package cr.ac.ucenfotec.logica.entidades;

public class Usuario {

    // Atributos
    private String nombre;
    private String identificacion;
    private int edad;
    private String correo;

    // Constructor
    public Usuario(String nombre, String identificacion, int edad, String correo) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.edad = edad;
        this.correo = correo;
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

    // toString
    public String toString() {
        return "Nombre: " + nombre +
                " | ID: " + identificacion +
                " | Edad: " + edad +
                " | Correo: " + correo;
    }
}