package cr.ac.ucenfotec.bl.excepciones;

public class SubastaNoExisteException extends Exception {
    public SubastaNoExisteException(String mensaje) {
        super(mensaje);
    }
}
