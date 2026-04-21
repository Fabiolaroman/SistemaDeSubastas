package cr.ac.ucenfotec.bl.entidades;

public class Moderador extends Usuario{
    private static int contador = 0;

    //constructores
    public Moderador(){
        super();
    }

    public Moderador(String nombre, String apellidos, String cedula, int dia, int mes, int annio, String correo, String password) {
        contador++;
        super(nombre, apellidos, cedula, dia, mes, annio, correo, password);
        id = "M-" + contador;
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
