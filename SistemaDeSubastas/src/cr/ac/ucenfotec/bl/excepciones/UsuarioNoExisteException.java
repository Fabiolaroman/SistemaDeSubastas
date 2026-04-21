package cr.ac.ucenfotec.bl.excepciones;

public class UsuarioNoExisteException extends Exception {
    public UsuarioNoExisteException(String mensaje) {
        super(mensaje);
    }
}
