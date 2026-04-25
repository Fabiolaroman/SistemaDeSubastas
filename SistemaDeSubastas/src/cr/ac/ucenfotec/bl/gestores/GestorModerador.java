package cr.ac.ucenfotec.bl.gestores;

import cr.ac.ucenfotec.bl.dao.DAOModerador;
import cr.ac.ucenfotec.bl.entidades.Moderador;
import cr.ac.ucenfotec.bl.excepciones.UsuarioNoExisteException;

import java.io.IOException;
import java.sql.SQLException;

public class GestorModerador {

    public static Moderador ingresarModerador(String loginId, String password) throws UsuarioNoExisteException, SQLException, IOException, ClassNotFoundException {
        return DAOModerador.seleccionarModerador(loginId, password);
    }
}
