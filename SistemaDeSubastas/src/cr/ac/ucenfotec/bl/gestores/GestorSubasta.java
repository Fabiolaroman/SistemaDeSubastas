package cr.ac.ucenfotec.bl.gestores;

import cr.ac.ucenfotec.bl.dao.DAOItem;
import cr.ac.ucenfotec.bl.dao.DAOOferta;
import cr.ac.ucenfotec.bl.dao.DAOSubasta;
import cr.ac.ucenfotec.bl.entidades.*;
import cr.ac.ucenfotec.bl.excepciones.UsuarioNoExisteException;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GestorSubasta {

    public static String mostrarSubastasActivas() throws UsuarioNoExisteException, SQLException, IOException, ClassNotFoundException {
        return DAOSubasta.seleccionarSubastasActivas();
    }

    public static String mostrarSubastas(Vendedor vendedor) throws UsuarioNoExisteException, SQLException, IOException, ClassNotFoundException {
        return DAOSubasta.seleccionarSubastas(vendedor);
    }

    public static String mostrarSubastas(Coleccionista coleccionista) throws UsuarioNoExisteException, SQLException, IOException, ClassNotFoundException {
        return DAOSubasta.seleccionarSubastas(coleccionista);
    }

    public static String crearSubasta(Vendedor vendedor, ArrayList<Item> items, LocalDateTime fechaVencimiento, double precio) throws SQLException, IOException, ClassNotFoundException {
        return DAOSubasta.insertarSubasta(new Subasta(fechaVencimiento, vendedor, precio, items), vendedor);
    }

    public static String crearSubasta(Coleccionista coleccionista, ArrayList<Item> items, LocalDateTime fechaVencimiento, double precio) throws SQLException, IOException, ClassNotFoundException {
        return DAOSubasta.insertarSubasta(new Subasta(fechaVencimiento, coleccionista, precio, items), coleccionista);
    }

    public static void adjudicarSubastasVencidas() throws SQLException, IOException, ClassNotFoundException, UsuarioNoExisteException {
        ArrayList<Subasta> subastasVencidasNoAdjudicadas = DAOSubasta.seleccionarSubastasVencidasNoAdjudicadas();
        if(subastasVencidasNoAdjudicadas != null) {

            for (Subasta subasta : subastasVencidasNoAdjudicadas) {
                ArrayList<Oferta> ofertas = new ArrayList<>();

                ofertas = DAOOferta.seleccionarOfertas(subasta.getId());
                Oferta ofertaGanadora = ofertas.getFirst();
                for (Oferta oferta : ofertas) {
                    if (ofertaGanadora.getMonto() < oferta.getMonto()) {
                        ofertaGanadora = oferta;
                    }
                }
                System.out.println(DAOSubasta.actualizarSubastaVencida(subasta));
                System.out.println(DAOItem.actualizarItemsAdjudicados(subasta.getItems(), ofertaGanadora.getUsuario()));
            }
        } else {
            System.out.println("No hay ninguna subasta vencida sin adjudicar");
        }
    }
}
