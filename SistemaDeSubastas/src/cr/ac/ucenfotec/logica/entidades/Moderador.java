package cr.ac.ucenfotec.logica.entidades;

public class Moderador extends Usuario{
    //constructores
    public Moderador(){
        super();
    }

    public Moderador(String nombre, String apellidos, String identificacion, int dia, int mes, int annio, String correo, String password) {
        super(nombre, apellidos, identificacion, dia, mes, annio, correo, password);
    }

    //toString
    public String toString() {
        return "Moderador:" + super.toString();
    }

    //equals

    @Override
    public boolean equals(Usuario usuario) {
        if (usuario instanceof Moderador){
           return super.equals(usuario);
        } else {
            return false;
        }
    }
}
