package cr.ac.ucenfotec.logica.entidades;

public class Moderador extends Usuario{
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
