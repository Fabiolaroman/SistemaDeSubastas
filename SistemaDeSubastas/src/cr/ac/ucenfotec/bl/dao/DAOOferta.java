package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entidades.Coleccionista;
import cr.ac.ucenfotec.bl.entidades.Oferta;
import cr.ac.ucenfotec.bl.excepciones.ContraseniaIncorrectaException;
import cr.ac.ucenfotec.bl.excepciones.UsuarioNoExisteException;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOOferta {
    private static String statement;
    private static String query;

    public static String insertarOferta(Oferta oferta, String idSubasta) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_oferta VALUES ('" + oferta.getId() + "', " + oferta.getMonto() + ", '" + oferta.getUsuario().getId() + "', '" + idSubasta + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "\nOferta registrada con éxito por $" + oferta.getMonto();
    }

    public static ArrayList<Oferta> seleccionarOfertas(String idSubasta) throws SQLException, IOException, ClassNotFoundException, UsuarioNoExisteException, ContraseniaIncorrectaException {
        query = "SELECT * FROM t_oferta WHERE id_subasta = ?;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, idSubasta);

        ArrayList<Oferta> ofertas = new ArrayList<>();
        if(resultado.next()){
            do  {
                Coleccionista coleccionista = DAOColeccionista.seleccionarColeccionista(resultado.getString("id_coleccionista"));
                Oferta oferta = new Oferta(resultado.getString("id"), coleccionista, resultado.getDouble("monto"));
                ofertas.add(oferta);
            } while (resultado.next());
        }
        return ofertas;
    }
}

