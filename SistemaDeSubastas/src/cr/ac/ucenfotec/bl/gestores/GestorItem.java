package cr.ac.ucenfotec.bl.gestores;

import cr.ac.ucenfotec.bl.dao.DAOItem;

import java.io.IOException;
import java.sql.SQLException;

public class GestorItem {

    public static String mostrarColeccion(String idColeccionista) throws SQLException, IOException, ClassNotFoundException {
       return DAOItem.seleccionarColeccion(idColeccionista);
    }
}
