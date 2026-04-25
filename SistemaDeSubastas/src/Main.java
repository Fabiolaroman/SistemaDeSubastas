import cr.ac.ucenfotec.bl.excepciones.UsuarioInvalidoException;
import cr.ac.ucenfotec.bl.excepciones.UsuarioNoExisteException;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static cr.ac.ucenfotec.ui.Menu.iniciarMenu;

public class Main {

    public static void main(String[] args) throws IOException, SQLException, UsuarioInvalidoException, ClassNotFoundException, UsuarioNoExisteException {

        System.out.print(LocalDateTime.now());
        iniciarMenu();

    }

}