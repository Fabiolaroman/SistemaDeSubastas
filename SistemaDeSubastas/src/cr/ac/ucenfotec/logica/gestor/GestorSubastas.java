package cr.ac.ucenfotec.logica.gestor;

import cr.ac.ucenfotec.logica.entidades.*;
import cr.ac.ucenfotec.logica.excepciones.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class GestorSubastas {
    private ArrayList<Subasta> subastas;
    private ArrayList<OrdenAdjudicacion> ordenes;

    //constructor
    public GestorSubastas() {
        subastas = new ArrayList<>();
        ordenes = new ArrayList<>();
    }

    //crear subasta
    public void crearSubasta(int dia, int mes, int annio, int hora, int minutos, Vendedor usuario, double precio, ArrayList<Item> items)
            throws FechaInvalidaException {

        try {
            Subasta subasta = new Subasta(dia, mes, annio, hora, minutos, usuario, precio, items);

            if(!subasta.getFechaHoraVencimiento().isEqual(LocalDateTime.now()) &&
                    !subasta.getFechaHoraVencimiento().isBefore(LocalDateTime.now())){
                subastas.add(subasta);
            } else {
                throw new FechaInvalidaException("La fecha limite debe estar en el futuro");
            }

        } catch (FechaInvalidaException e) {
            throw e;
        }
    }

    public void crearSubasta(int dia, int mes, int annio, int hora, int minutos, Coleccionista usuario, double precio, ArrayList<Item> items)
            throws FechaInvalidaException {

        try {
            Subasta subasta = new Subasta(dia, mes, annio, hora, minutos, usuario, precio, items);

            if(!subasta.getFechaHoraVencimiento().isEqual(LocalDateTime.now()) &&
                    !subasta.getFechaHoraVencimiento().isBefore(LocalDateTime.now())){
                subastas.add(subasta);
            } else {
                throw new FechaInvalidaException("La fecha limite debe estar en el futuro");
            }

        } catch (FechaInvalidaException e) {
            throw e;
        }
    }

    //getter
    public ArrayList<Subasta> getSubastas() {
        return subastas;
    }

    //ofertar
    public void agregarOferta(String idSubasta, Coleccionista usuario, double monto)
            throws SubastaNoExisteException, OfertaInvalidaException {

        Subasta subasta = subastaXId(idSubasta);

        subasta.actualizarEstado();

        if(subasta.isEstaActiva()){

            if(monto <= subasta.getPrecioMinimo()){
                throw new OfertaInvalidaException("La oferta debe ser mayor al precio mínimo");
            }

            Oferta oferta = new Oferta(usuario, monto);
            subasta.addOferta(oferta);

        } else {
            throw new OfertaInvalidaException("La subasta está cerrada");
        }
    }

    //adjudicar
    public void adjudicarSubastasVencidas(){
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
    public Subasta subastaXId(String id) throws SubastaNoExisteException {
        Subasta subastaEncontrado = null;
        boolean encontrado = false;

        for (Subasta cliente : subastas){
            if(cliente.getId().equals(id)){
                subastaEncontrado = cliente;
                encontrado = true;
            }
        }

        if(!encontrado){
            throw new SubastaNoExisteException("Subasta no existe");
        }

        return subastaEncontrado;
    }
}