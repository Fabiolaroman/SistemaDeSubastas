package cr.ac.ucenfotec.bl.excepciones;

public class ContraseniaIncorrectaException extends RuntimeException {
    public ContraseniaIncorrectaException(String message) {
        super(message);
    }
}
