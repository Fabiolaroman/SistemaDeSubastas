package cr.ac.ucenfotec.logica.gestor;

import cr.ac.ucenfotec.logica.entidades.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class GestorSubastas {
    private ArrayList<Subasta> subastas;

    //constructor
    public GestorSubastas() {subastas = new ArrayList<>();}

    //crear subasta
    public void crearSubasta(int dia, int mes, int annio, int hora, int minutos, Vendedor usuario, double precio, ArrayList<Item> items){
        Subasta subasta = new Subasta(dia, mes, annio, hora, minutos, usuario, precio, items);
        if(!subasta.getFechaHoraVencimiento().isEqual(LocalDateTime.now()) && !subasta.getFechaHoraVencimiento().isBefore(LocalDateTime.now())){
            subastas.add(subasta);
        } else {
            System.out.println("La fecha limite debe estar en el futuro");
        }

    }

    public void crearSubasta(int dia, int mes, int annio, int hora, int minutos, Coleccionista usuario, double precio, ArrayList<Item> items){
        Subasta subasta = new Subasta(dia, mes, annio, hora, minutos, usuario, precio, items);
        if(!subasta.getFechaHoraVencimiento().isEqual(LocalDateTime.now()) && !subasta.getFechaHoraVencimiento().isBefore(LocalDateTime.now())){
            subastas.add(subasta);
        } else {
            System.out.println("La fecha limite debe estar en el futuro");
        }

    }

    //ofertar
    public void agregarOferta(String idSubasta, Coleccionista usuario, double monto){
        subastas.forEach(subasta -> {
            subasta.actualizarEstado();
            if(idSubasta.equals(subasta.getId()) && subasta.isEstaActiva()){
                Oferta oferta = new Oferta(usuario, monto);
                subasta.addOferta(oferta);
            }
        });
    }

    //adjudicar
    public void adjudicarSubastasVencidas(){
        subastas.forEach(subasta -> {
            subasta.actualizarEstado();
            if(!subasta.isEstaActiva()){
                if(!subasta.getOfertas().isEmpty()){
                    OrdenAdjudicacion orden = new OrdenAdjudicacion(subasta.ofertaGanadora().getUsuario(), subasta, subasta.ofertaGanadora());
                }
            }
        });
    }
}
