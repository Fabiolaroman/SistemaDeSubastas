package cr.ac.ucenfotec.bl.excepciones;

public class UsuarioInvalidoException extends Exception {

    public UsuarioInvalidoException(String mensaje) {
        super(mensaje);
    }
}