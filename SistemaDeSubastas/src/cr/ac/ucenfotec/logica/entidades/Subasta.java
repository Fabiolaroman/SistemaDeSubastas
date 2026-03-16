package cr.ac.ucenfotec.logica.entidades;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Subasta {
    //atributos
    private String id;
    private static int contador = 0;
    private LocalDateTime fechaHoraVencimiento;
    private Duration tiempoRestante;
    private Usuario usuarioCreador;
    private double puntuacion;
    private double precioMinimo;
    private ArrayList<Item> items;
    private boolean estaActiva;
    private ArrayList<Oferta> ofertas;
    //constructores

    public Subasta() {}

    public Subasta(int dia, int mes, int annio, int hora, int minutos, Vendedor usuario, double precio, ArrayList<Item> items) {
        contador++;
        this.id = "S-" + contador;
        this.fechaHoraVencimiento = LocalDateTime.of(annio, mes, dia, hora, minutos);
        this.tiempoRestante = Duration.between(fechaHoraVencimiento, LocalDateTime.now());
        this.usuarioCreador = usuario;
        this.puntuacion = usuario.getPuntuacion();
        this.precioMinimo = precio;
        this.estaActiva = true;
        this.items = items;
        this.ofertas = new ArrayList<>();
    }

    public Subasta(int dia, int mes, int annio, int hora, int minutos, Coleccionista usuario, double precio, ArrayList<Item> items) {
        contador++;
        this.id = "S-" + contador;
        this.fechaHoraVencimiento = LocalDateTime.of(annio, mes, dia, hora, minutos);
        this.tiempoRestante = Duration.between(fechaHoraVencimiento, LocalDateTime.now());
        this.usuarioCreador = usuario;
        this.puntuacion = usuario.getPuntuacion();
        this.precioMinimo = precio;
        this.estaActiva = true;
        this.items = items;
        this.ofertas = new ArrayList<>();
    }

    //getters y setter


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Subasta.contador = contador;
    }

    public LocalDateTime getFechaHoraVencimiento() {
        return fechaHoraVencimiento;
    }

    public Duration getTiempoRestante() {
        actualizarEstado();
        if (!estaActiva){
            return Duration.of(0, ChronoUnit.SECONDS);
        } else {
            return tiempoRestante;
        }
    }

    public void setFechaHoraVencimiento(LocalDateTime fechaHoraVencimiento) {
        this.fechaHoraVencimiento = fechaHoraVencimiento;
    }

    public Usuario getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(Usuario usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public double getPrecioMinimo() {
        return precioMinimo;
    }

    public void setPrecioMinimo(double precioMinimo) {
        this.precioMinimo = precioMinimo;
    }

    public boolean isEstaActiva() {
        return estaActiva;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    //actualizarEstado
    public void actualizarEstado(){
        if (fechaHoraVencimiento.isAfter(LocalDateTime.now()) || fechaHoraVencimiento.isEqual(LocalDateTime.now())){
            this.estaActiva = false;
        } else {
            this.tiempoRestante = Duration.between(fechaHoraVencimiento, LocalDateTime.now());
        }
    }

    //ofertar
    public void addOferta(Oferta oferta){
        ofertas.add(oferta);
    }

    //oferta mas alta
    public Oferta ofertaGanadora(){
        Oferta ofertaganadora = null;
        Oferta ofertaanterior = new Oferta(0);
        for(Oferta oferta : ofertas){

            if(oferta.getMonto() > ofertaanterior.getMonto()){
                ofertaganadora = oferta;
            }
            ofertaanterior = oferta;
        };

        return ofertaganadora;

    }
}
