package cr.ac.ucenfotec.bl.gestores;

import cr.ac.ucenfotec.bl.dao.DAOItem;
import cr.ac.ucenfotec.bl.entidades.Item;
import cr.ac.ucenfotec.bl.excepciones.ItemNoExisteException;

import java.io.IOException;
import java.sql.SQLException;

public class GestorItem {

    public static String mostrarColeccion(String idColeccionista) throws SQLException, IOException, ClassNotFoundException {
       return DAOItem.seleccionarColeccion(idColeccionista);
    }

    public static Item seleccionarItem(String idItem, String idColeccionista) throws SQLException, IOException, ClassNotFoundException {
        return  DAOItem.seleccionarItem(idItem, idColeccionista);
    }
}
