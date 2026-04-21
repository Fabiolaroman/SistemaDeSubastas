package cr.ac.ucenfotec.bl.entidades;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrdenAdjudicacion {
    private Coleccionista usuario;
    private Subasta subasta;
    private Oferta oferta;

    //constructores
    public OrdenAdjudicacion(){}

    public OrdenAdjudicacion(Coleccionista usuario, Subasta subasta, Oferta oferta) {
        this.usuario = usuario;
        this.subasta = subasta;
        this.oferta = oferta;
    }

    //getters y setters

    public Coleccionista getUsuario() {
        return usuario;
    }

    public String getNombreUsuario() {
        return usuario.getNombre();
    }

    public void setUsuario(Coleccionista usuario) {
        this.usuario = usuario;
    }

    public Subasta getSubasta() {
        return subasta;
    }

    public LocalDate getFecha() {
        return subasta.getFechaHoraVencimiento().toLocalDate();
    }

    public ArrayList<Item> getItems(){
        return subasta.getItems();
    }

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public double getPrecio() {
        return oferta.getMonto();
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    //toString()

    //equals

}
