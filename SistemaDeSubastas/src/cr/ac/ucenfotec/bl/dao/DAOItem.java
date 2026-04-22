package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entidades.Item;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
