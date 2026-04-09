package cr.ac.ucenfotec.logica.excepciones;

public class UsuarioNoExisteException extends Exception {
    public UsuarioNoExisteException(String mensaje) {
        super(mensaje);
    }
}
