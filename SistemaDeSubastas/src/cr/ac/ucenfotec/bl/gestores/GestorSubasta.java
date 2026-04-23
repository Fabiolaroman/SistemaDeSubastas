package cr.ac.ucenfotec.bl.gestores;

import cr.ac.ucenfotec.bl.dao.DAOSubasta;
import cr.ac.ucenfotec.bl.entidades.Vendedor;
import cr.ac.ucenfotec.bl.excepciones.UsuarioNoExisteException;

import java.io.IOException;
import java.sql.SQLException;

public class GestorSubasta {

    public static String mostrarSubastasActivas() throws UsuarioNoExisteException, SQLException, IOException, ClassNotFoundException {
        return DAOSubasta.seleccionarSubastasActivas();
    }

    public static String mostrarSubastas(Vendedor vendedor) throws UsuarioNoExisteException, SQLException, IOException, ClassNotFoundException {
        return DAOSubasta.seleccionarSubastas(vendedor);
    }
}
