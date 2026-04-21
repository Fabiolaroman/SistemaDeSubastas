package cr.ac.ucenfotec.bl.excepciones;

public class ItemNoExisteException extends RuntimeException {
    public ItemNoExisteException(String message) {
        super(message);
    }
}
