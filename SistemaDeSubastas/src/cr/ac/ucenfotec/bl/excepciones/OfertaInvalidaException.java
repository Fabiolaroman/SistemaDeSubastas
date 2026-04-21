package cr.ac.ucenfotec.bl.excepciones;

public class OfertaInvalidaException extends Exception {
    public OfertaInvalidaException(String mensaje) {
        super(mensaje);
    }
}