package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entidades.Coleccionista;
import cr.ac.ucenfotec.bl.entidades.Item;
import cr.ac.ucenfotec.bl.excepciones.ItemNoExisteException;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOItem {
    private static String statement;
    private static String query;

    public static String seleccionarColeccion(String idColeccionista) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_item WHERE id_coleccionista = ?;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, idColeccionista);
        if(!resultado.next()) return "Su colección está vacía";
        String coleccion = "\n-----Mi Colección-----";
        Item item;
        do {
            item = new Item(
                    resultado.getString("id"),
                    resultado.getString("nombre"),
                    resultado.getString("descripcion"),
                    resultado.getString("estado"),
                    resultado.getDate("fecha_compra").toLocalDate()
                    );
            coleccion += "\n" + item;
        } while (resultado.next());

        return coleccion;
    }

    public static Item seleccionarItem(String idItem, String idColeccionista) throws SQLException, IOException, ClassNotFoundException, ItemNoExisteException {
        query = "SELECT * FROM t_item WHERE id = ? AND id_coleccionista = ?;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, idItem, idColeccionista);
        if (!resultado.next()) throw new ItemNoExisteException("El ID indicado no existe en su colección");

        Item item = new Item(
                resultado.getString("id"),
                resultado.getString("nombre"),
                resultado.getString("descripcion"),
                resultado.getString("estado"),
                resultado.getDate("fecha_compra").toLocalDate()
        );

        return item;
    }

    public static String actualizarItemsAdjudicados(ArrayList<Item> items, Coleccionista coleccionista) throws SQLException, IOException, ClassNotFoundException {
        String mensaje = "\nAdjudicaciones:\n";
        for (Item item : items) {
            statement = "UPDATE t_item SET id_subasta = null, id_coleccionista = ? WHERE id = ?;";
            Conector.getConexion().ejecutarStatement(statement, coleccionista.getId(), item.getId());
            mensaje += "Item " + item.getNombre() +" (ID:"+ item.getId() +") fue adjudicado a " + coleccionista.getNombre() + " " + coleccionista.getApellidos() + " (ID:" + coleccionista.getId() + ").\n";
        }

        return mensaje;
    }
}
