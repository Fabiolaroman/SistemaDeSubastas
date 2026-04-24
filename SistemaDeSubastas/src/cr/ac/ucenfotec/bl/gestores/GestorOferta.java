package cr.ac.ucenfotec.bl.gestores;

import cr.ac.ucenfotec.bl.dao.DAOOferta;
import cr.ac.ucenfotec.bl.dao.DAOSubasta;
import cr.ac.ucenfotec.bl.entidades.Coleccionista;
import cr.ac.ucenfotec.bl.entidades.Oferta;
import cr.ac.ucenfotec.bl.entidades.Subasta;
import cr.ac.ucenfotec.bl.excepciones.OfertaInvalidaException;
import cr.ac.ucenfotec.bl.excepciones.SubastaNoExisteException;
import cr.ac.ucenfotec.bl.excepciones.UsuarioNoExisteException;

import java.io.IOException;
import java.sql.SQLException;

public class GestorOferta {

    public static String realizarOferta(String idSubasta, Coleccionista coleccionista, double monto) throws SQLException, IOException, ClassNotFoundException, UsuarioNoExisteException, SubastaNoExisteException {

        Subasta subasta = DAOSubasta.seleccionarSubastaPorId(idSubasta);

        if (subasta == null) {
            throw new SubastaNoExisteException("No existe una subasta con ese ID");
        }

        subasta.actualizarEstado();

        try {
            if (!subasta.isEstaActiva()) {
                throw new OfertaInvalidaException("La subasta ya no está activa");
            }

            if (monto <= subasta.getPrecioMinimo()) {
                throw new OfertaInvalidaException("La oferta debe ser mayor al precio mínimo de $" + subasta.getPrecioMinimo());
            }

            Oferta oferta = new Oferta(coleccionista, monto);
            return DAOOferta.insertarOferta(oferta, idSubasta);

        } catch (OfertaInvalidaException e) {
            return e.getMessage();
        }
    }
}

