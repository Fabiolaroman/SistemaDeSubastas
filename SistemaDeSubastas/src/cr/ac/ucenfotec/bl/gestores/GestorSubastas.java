package cr.ac.ucenfotec.bl.gestores;

import cr.ac.ucenfotec.bl.entidades.*;
import cr.ac.ucenfotec.bl.excepciones.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GestorSubastas {
    private final static ArrayList<Subasta> subastas = new ArrayList<>();
    private final static ArrayList<OrdenAdjudicacion> ordenes = new ArrayList<>();

    //crear subasta
    public static void crearSubasta(LocalDateTime fechaHoraVencimiento, Vendedor usuario, double precio, ArrayList<Item> items) {

        try {
            Subasta subasta = new Subasta(fechaHoraVencimiento, usuario, precio, items);

            if(!subasta.getFechaHoraVencimiento().isEqual(LocalDateTime.now()) &&
                    !subasta.getFechaHoraVencimiento().isBefore(LocalDateTime.now())){
                subastas.add(subasta);
            } else {
                throw new FechaInvalidaException("La fecha limite debe estar en el futuro");
            }

        } catch (FechaInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void crearSubasta(LocalDateTime fechaHoraVencimiento, Coleccionista usuario, double precio, ArrayList<Item> items) {

        try {
            Subasta subasta = new Subasta(fechaHoraVencimiento, usuario, precio, items);

            if(!subasta.getFechaHoraVencimiento().isEqual(LocalDateTime.now()) &&
                    !subasta.getFechaHoraVencimiento().isBefore(LocalDateTime.now())){
                subastas.add(subasta);
            } else {
                throw new FechaInvalidaException("La fecha limite debe estar en el futuro");
            }

        } catch (FechaInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    //getter
    public static ArrayList<Subasta> getSubastas() {
        return subastas;
    }

    public static ArrayList<Subasta> getSubastasActivas() {
        ArrayList<Subasta> subastasActivas = new ArrayList<>();
        for (Subasta subasta : subastas){
            if (subasta.getFechaHoraVencimiento().isAfter(LocalDateTime.now())){
                subastasActivas.add(subasta);
            }
        }
        return subastasActivas;
    }

    public static ArrayList<Subasta> getSubastasXUsuario(Usuario usuario){
        ArrayList<Subasta> subastasUsuario =  new ArrayList<>();
        for (Subasta subasta : subastas){
            if (subasta.getUsuarioCreador().equals(usuario)){
                subastasUsuario.add(subasta);
            }
        }

        return subastasUsuario;
    }

    //ofertar
    public static void agregarOferta(Subasta subasta, Coleccionista usuario, double monto) {

        try {
            subasta.actualizarEstado();

            if (subasta.isEstaActiva()) {

                if (monto <= subasta.getPrecioMinimo()) {
                    throw new OfertaInvalidaException("La oferta debe ser mayor al precio mínimo");
                }

                Oferta oferta = new Oferta(usuario, monto);
                subasta.addOferta(oferta);

            } else {
                throw new OfertaInvalidaException("La subasta está cerrada");
            }
        } catch (OfertaInvalidaException e){
            System.out.println(e.getMessage());
        }
    }

    //adjudicar
    public static void adjudicarSubastasVencidas(){
        subastas.forEach(subasta -> {
            subasta.actualizarEstado();
            if(!subasta.isEstaActiva()){
                if(!subasta.getOfertas().isEmpty()){
                    OrdenAdjudicacion orden = new OrdenAdjudicacion(
                            subasta.ofertaGanadora().getUsuario(),
                            subasta,
                            subasta.ofertaGanadora()
                    );
                    ordenes.add(orden);
                    subasta.ofertaGanadora().getUsuario().agregarItems(subasta.getItems());
                }
            }
        });
    }

    //subastaXId
    public static Subasta subastaXId(String id) throws SubastaNoExisteException {
        Subasta subastaEncontrado = null;
        boolean encontrado = false;
        for (Subasta cliente : subastas) {
            if (cliente.getId().equals(id)) {
                subastaEncontrado = cliente;
                encontrado = true;
            }
        }

        if (!encontrado) {
            throw new SubastaNoExisteException("Subasta no existe");
        }
        return subastaEncontrado;
    }
}