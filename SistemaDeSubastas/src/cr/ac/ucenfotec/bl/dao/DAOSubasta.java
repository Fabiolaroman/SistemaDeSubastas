package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entidades.*;
import cr.ac.ucenfotec.bl.excepciones.UsuarioNoExisteException;
import cr.ac.ucenfotec.dl.Conector;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DAOSubasta {
    private static String statement;
    private static String query;

    public static String seleccionarSubastasActivas() throws SQLException, IOException, ClassNotFoundException, UsuarioNoExisteException {
        Timestamp fechaHoraActual = Timestamp.valueOf(LocalDateTime.now());
        query = "SELECT * FROM t_subasta WHERE fecha_vencimiento > ?;";
        ResultSet resultadoSubasta = Conector.getConexion().ejecutarQuery(query, fechaHoraActual);
        if (!resultadoSubasta.next()) return "No hay subastas activas";

        Subasta subasta;
        String listaSubastas = "\n---Subastas Activas---";

        do {
            query = "SELECT * FROM t_item WHERE id_subasta = ?;";
            ResultSet resultadoItems = Conector.getConexion().ejecutarQuery(query, resultadoSubasta.getString("id"));
            ArrayList<Item> items = new ArrayList<>();

            query = "SELECT * FROM t_oferta WHERE id_subasta = ?;";
            ResultSet resultadoOfertas = Conector.getConexion().ejecutarQuery(query, resultadoSubasta.getString("id"));
            ArrayList<Oferta> ofertas = new ArrayList<>();

            Coleccionista coleccionista = null;
            Vendedor vendedor = null;

            do {
                if (resultadoSubasta.getString("id_vendedor") != null) {
                    vendedor = DAOVendedor.seleccionarVendedor(resultadoSubasta.getString("id_vendedor"));
                } else {
                    coleccionista = DAOColeccionista.seleccionarColeccionista(resultadoSubasta.getString("id_coleccionista"));
                }

                Item item = new Item(
                        resultadoItems.getString("id"),
                        resultadoItems.getString("nombre"),
                        resultadoItems.getString("descripcion"),
                        resultadoItems.getString("estado"),
                        resultadoItems.getDate("fecha_compra").toLocalDate()
                );

                items.add(item);

                Coleccionista coleccionistaOferta = DAOColeccionista.seleccionarColeccionista(resultadoOfertas.getString("id_coleccionista"));

                Oferta oferta = new Oferta(coleccionistaOferta, resultadoOfertas.getDouble("monto"));
                ofertas.add(oferta);

            } while (resultadoItems.next());

            if (resultadoSubasta.getString("id_vendedor") != null) {
                subasta = new Subasta(
                        resultadoSubasta.getString("id"),
                        resultadoSubasta.getTimestamp("fecha_vencimiento").toLocalDateTime(),
                        vendedor,
                        vendedor.getPuntuacion(),
                        resultadoSubasta.getDouble("precio_minimo"),
                        items,
                        resultadoSubasta.getBoolean("esta_activa"),
                        ofertas
                );
            } else {
                subasta = new Subasta(
                        resultadoSubasta.getString("id"),
                        resultadoSubasta.getTimestamp("fecha_vencimiento").toLocalDateTime(),
                        coleccionista,
                        coleccionista.getPuntuacion(),
                        resultadoSubasta.getDouble("precio_minimo"),
                        items,
                        resultadoSubasta.getBoolean("esta_activa"),
                        ofertas
                );
            }

            listaSubastas += "\n" + subasta;

        } while (resultadoSubasta.next());

        return listaSubastas;
    }

    public static Subasta seleccionarSubastaPorId(String idSubasta) throws SQLException, IOException, ClassNotFoundException, UsuarioNoExisteException {
        query = "SELECT * FROM t_subasta WHERE id = ?;";
        ResultSet resultadoSubasta = Conector.getConexion().ejecutarQuery(query, idSubasta);
        if (!resultadoSubasta.next()) return null;

        query = "SELECT * FROM t_item WHERE id_subasta = ?;";
        ResultSet resultadoItems = Conector.getConexion().ejecutarQuery(query, idSubasta);
        ArrayList<Item> items = new ArrayList<>();
        while (resultadoItems.next()) {
            items.add(new Item(
                    resultadoItems.getString("id"),
                    resultadoItems.getString("nombre"),
                    resultadoItems.getString("descripcion"),
                    resultadoItems.getString("estado"),
                    resultadoItems.getDate("fecha_compra").toLocalDate()
            ));
        }

        ArrayList<Oferta> ofertas = new ArrayList<>();

        Coleccionista coleccionista = null;
        Vendedor vendedor = null;
        if (resultadoSubasta.getString("id_vendedor") != null) {
            vendedor = DAOVendedor.seleccionarVendedor(resultadoSubasta.getString("id_vendedor"));
            return new Subasta(
                    resultadoSubasta.getString("id"),
                    resultadoSubasta.getTimestamp("fecha_vencimiento").toLocalDateTime(),
                    vendedor,
                    vendedor.getPuntuacion(),
                    resultadoSubasta.getDouble("precio_minimo"),
                    items,
                    resultadoSubasta.getBoolean("esta_activa"),
                    ofertas
            );
        } else {
            coleccionista = DAOColeccionista.seleccionarColeccionista(resultadoSubasta.getString("id_coleccionista"));
            return new Subasta(
                    resultadoSubasta.getString("id"),
                    resultadoSubasta.getTimestamp("fecha_vencimiento").toLocalDateTime(),
                    coleccionista,
                    coleccionista.getPuntuacion(),
                    resultadoSubasta.getDouble("precio_minimo"),
                    items,
                    resultadoSubasta.getBoolean("esta_activa"),
                    ofertas
            );
        }
    }

    public static String seleccionarSubastas(Vendedor vendedor) throws SQLException, IOException, ClassNotFoundException, UsuarioNoExisteException {
        query = "SELECT * FROM t_subasta WHERE id_vendedor = ?;";
        ResultSet resultadoSubasta = Conector.getConexion().ejecutarQuery(query, vendedor.getId());
        if (!resultadoSubasta.next()) return "Usted no ha creado ninguna Subasta";
        Subasta subasta;

        String listaSubastas = "\n---Mis Subastas---";

        do {
            query = "SELECT * FROM t_item WHERE id_subasta = ?;";
            ResultSet resultadoItems = Conector.getConexion().ejecutarQuery(query, resultadoSubasta.getString("id"));
            ArrayList<Item> items = new ArrayList<>();

            query = "SELECT * FROM t_oferta WHERE id_subasta = ?;";
            ResultSet resultadoOfertas = Conector.getConexion().ejecutarQuery(query, resultadoSubasta.getString("id"));
            ArrayList<Oferta> ofertas = new ArrayList<>();

            do {
                Item item = new Item(
                        resultadoItems.getString("id"),
                        resultadoItems.getString("nombre"),
                        resultadoItems.getString("descripcion"),
                        resultadoItems.getString("estado"),
                        resultadoItems.getDate("fecha_compra").toLocalDate()
                );

                items.add(item);

                Coleccionista coleccionista = DAOColeccionista.seleccionarColeccionista(resultadoOfertas.getString("id_coleccionista"));

                Oferta oferta = new Oferta(coleccionista, resultadoOfertas.getDouble("monto"));
                ofertas.add(oferta);

            } while (resultadoItems.next());

            subasta = new Subasta(
                    resultadoSubasta.getString("id"),
                    resultadoSubasta.getTimestamp("fecha_vencimiento").toLocalDateTime(),
                    vendedor,
                    vendedor.getPuntuacion(),
                    resultadoSubasta.getDouble("precio_minimo"),
                    items,
                    resultadoSubasta.getBoolean("esta_activa"),
                    ofertas
            );

            listaSubastas += "\n" + subasta;

        } while (resultadoSubasta.next());

        return listaSubastas;
    }

    public static String seleccionarSubastas(Coleccionista coleccionista) throws SQLException, IOException, ClassNotFoundException, UsuarioNoExisteException {
        query = "SELECT * FROM t_subasta WHERE id_coleccionista = ?;";
        ResultSet resultadoSubasta = Conector.getConexion().ejecutarQuery(query, coleccionista.getId());
        if (!resultadoSubasta.next()) return "Usted no ha creado ninguna Subasta";
        Subasta subasta;

        String listaSubastas = "\n---Mis Subastas---";

        do {
            query = "SELECT * FROM t_item WHERE id_subasta = ?;";
            ResultSet resultadoItems = Conector.getConexion().ejecutarQuery(query, resultadoSubasta.getString("id"));
            ArrayList<Item> items = new ArrayList<>();

            query = "SELECT * FROM t_oferta WHERE id_subasta = ?;";
            ResultSet resultadoOfertas = Conector.getConexion().ejecutarQuery(query, resultadoSubasta.getString("id"));
            ArrayList<Oferta> ofertas = new ArrayList<>();

            do {
                Item item = new Item(
                        resultadoItems.getString("id"),
                        resultadoItems.getString("nombre"),
                        resultadoItems.getString("descripcion"),
                        resultadoItems.getString("estado"),
                        resultadoItems.getDate("fecha_compra").toLocalDate()
                );

                items.add(item);

                Coleccionista coleccionistaOferta = DAOColeccionista.seleccionarColeccionista(resultadoOfertas.getString("id_coleccionista"));

                Oferta oferta = new Oferta(coleccionistaOferta, resultadoOfertas.getDouble("monto"));
                ofertas.add(oferta);

            } while (resultadoItems.next());

            subasta = new Subasta(
                    resultadoSubasta.getString("id"),
                    resultadoSubasta.getTimestamp("fecha_vencimiento").toLocalDateTime(),
                    coleccionista,
                    coleccionista.getPuntuacion(),
                    resultadoSubasta.getDouble("precio_minimo"),
                    items,
                    resultadoSubasta.getBoolean("esta_activa"),
                    ofertas
            );

            listaSubastas += "\n" + subasta;

        } while (resultadoSubasta.next());

        return listaSubastas;
    }

    public static String insertarSubasta(Subasta subasta, Vendedor vendedor) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_subasta VALUES (? , ?, null, ?, ?, ?);";
        Conector.getConexion().ejecutarStatement(statement, subasta.getId(), vendedor.getId(), subasta.getPrecioMinimo(), subasta.isEstaActiva(), subasta.getFechaHoraVencimiento());

        for (Item item : subasta.getItems()) {
            statement = "INSERT INTO t_item VALUES (?, ?, ?, ?, ?, ?, null);";
            Conector.getConexion().ejecutarStatement(statement, item.getId(), item.getNombre(), item.getDescripcion(), item.getEstado(), item.getFechaCompra(), subasta.getId());
        }

        return "Subasta creada con éxito";
    }

    public static String insertarSubasta(Subasta subasta, Coleccionista coleccionista) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_subasta VALUES (? , null, ?, ?, ?, ?);";
        Conector.getConexion().ejecutarStatement(statement, subasta.getId(), coleccionista.getId(), subasta.getPrecioMinimo(), subasta.isEstaActiva(), subasta.getFechaHoraVencimiento());

        for (Item item : subasta.getItems()) {
            statement = "UPDATE t_item SET id_coleccionista =  null, id_subasta =  ?;";
            Conector.getConexion().ejecutarStatement(statement, subasta.getId());
        }

        DAOOferta.insertarOferta(new Oferta(coleccionista, subasta.getPrecioMinimo()-1), subasta.getId()); //oferta base en caso de que la subasta venza sin ofertas

        return "Subasta creada con éxito";
    }

    public static ArrayList<Subasta> seleccionarSubastasVencidasNoAdjudicadas() throws SQLException, IOException, ClassNotFoundException, UsuarioNoExisteException {
        query = "SELECT * from t_subasta WHERE fecha_vencimiento <= ? AND esta_activa = true;";
        ResultSet resultadoSubasta = Conector.getConexion().ejecutarQuery(query, Timestamp.valueOf(LocalDateTime.now()));
        if(!resultadoSubasta.next()) return null;
        ArrayList<Subasta> subastas = new ArrayList<>();

        do {


            query = "SELECT * FROM t_item WHERE id_subasta = ?;";
            ResultSet resultadoItems = Conector.getConexion().ejecutarQuery(query, resultadoSubasta.getString("id"));
            ArrayList<Item> items = new ArrayList<>();

            query = "SELECT * FROM t_oferta WHERE id_subasta = ?;";
            ResultSet resultadoOfertas = Conector.getConexion().ejecutarQuery(query, resultadoSubasta.getString("id"));
            ArrayList<Oferta> ofertas = new ArrayList<>();

            do {
                Item item = new Item(
                        resultadoItems.getString("id"),
                        resultadoItems.getString("nombre"),
                        resultadoItems.getString("descripcion"),
                        resultadoItems.getString("estado"),
                        resultadoItems.getDate("fecha_compra").toLocalDate()
                );

                items.add(item);

                Coleccionista coleccionistaOferta = DAOColeccionista.seleccionarColeccionista(resultadoOfertas.getString("id_coleccionista"));

                Oferta oferta = new Oferta(coleccionistaOferta, resultadoOfertas.getDouble("monto"));
                ofertas.add(oferta);

            } while (resultadoItems.next());

            Subasta subasta = new Subasta(
                    resultadoSubasta.getString("id"),
                    resultadoSubasta.getTimestamp("fecha_vencimiento").toLocalDateTime(),
                    resultadoSubasta.getDouble("precio_minimo"),
                    items,
                    resultadoSubasta.getBoolean("esta_activa"),
                    ofertas
            );

            subastas.add(subasta);


        } while (resultadoSubasta.next());

        return subastas;
    }

    public static String actualizarSubastaVencida(Subasta subasta) throws SQLException, IOException, ClassNotFoundException {
        statement = "UPDATE t_subasta SET esta_activa = false WHERE id = ?;";
        Conector.getConexion().ejecutarStatement(statement, subasta.getId());
        return "Subasta " + subasta.getId() + "fue adjudicada";
    }
}
