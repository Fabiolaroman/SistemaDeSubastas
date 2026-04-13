package cr.ac.ucenfotec.logica.excepciones;

public class ItemNoExisteException extends RuntimeException {
    public ItemNoExisteException(String message) {
        super(message);
    }
}
