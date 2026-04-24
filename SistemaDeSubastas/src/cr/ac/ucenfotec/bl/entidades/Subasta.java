package cr.ac.ucenfotec.bl.entidades;

import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Subasta {
    private String id;
    private LocalDateTime fechaHoraVencimiento;
    private Duration tiempoRestante;
    private Usuario usuarioCreador;
    private double puntuacion;
    private double precioMinimo;
    private ArrayList<Item> items;
    private boolean estaActiva;
    private ArrayList<Oferta> ofertas;

    private static int numeroUltimoID() throws SQLException, IOException, ClassNotFoundException {
        String query = "SELECT * FROM t_subasta ORDER BY id DESC LIMIT 1;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) return 0;
        String id = resultado.getString("id");
        return Integer.parseInt(id.substring(2));
    }

    public Subasta(LocalDateTime fechaHoraVencimiento, Vendedor usuario, double precio, ArrayList<Item> items) throws SQLException, IOException, ClassNotFoundException {
        int numeroID = numeroUltimoID() + 1;
        this.id = "S-" + numeroID;
        this.fechaHoraVencimiento = fechaHoraVencimiento;
        this.tiempoRestante = Duration.between(fechaHoraVencimiento, LocalDateTime.now());
        this.usuarioCreador = usuario;
        this.puntuacion = usuario.getPuntuacion();
        this.precioMinimo = precio;
        this.estaActiva = true;
        this.items = items;
        this.ofertas = new ArrayList<>();
    }

    public Subasta(LocalDateTime fechaHoraVencimiento, Coleccionista usuario, double precio, ArrayList<Item> items) throws SQLException, IOException, ClassNotFoundException {
        int numeroID = numeroUltimoID() + 1;
        this.id = "S-" + numeroID;
        this.fechaHoraVencimiento = fechaHoraVencimiento;
        this.tiempoRestante = Duration.between(fechaHoraVencimiento, LocalDateTime.now());
        this.usuarioCreador = usuario;
        this.puntuacion = usuario.getPuntuacion();
        this.precioMinimo = precio;
        this.estaActiva = true;
        this.items = items;
        this.ofertas = new ArrayList<>();
    }

    public Subasta(String id, LocalDateTime fechaHoraVencimiento, Usuario usuarioCreador, double puntuacion, double precioMinimo, ArrayList<Item> items, boolean estaActiva, ArrayList<Oferta> ofertas) {
        this.id = id;
        this.fechaHoraVencimiento = fechaHoraVencimiento;
        this.tiempoRestante = Duration.between(fechaHoraVencimiento, LocalDateTime.now());
        this.usuarioCreador = usuarioCreador;
        this.puntuacion = puntuacion;
        this.precioMinimo = precioMinimo;
        this.items = items;
        this.estaActiva = estaActiva;
        this.ofertas = ofertas;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public LocalDateTime getFechaHoraVencimiento() { return fechaHoraVencimiento; }

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

    public Usuario getUsuarioCreador() { return usuarioCreador; }
    public void setUsuarioCreador(Usuario usuarioCreador) { this.usuarioCreador = usuarioCreador; }
    public double getPuntuacion() { return puntuacion; }
    public double getPrecioMinimo() { return precioMinimo; }
    public void setPrecioMinimo(double precioMinimo) { this.precioMinimo = precioMinimo; }
    public boolean isEstaActiva() { return estaActiva; }
    public ArrayList<Item> getItems() { return items; }
    public ArrayList<Oferta> getOfertas() { return ofertas; }

    public void actualizarEstado(){
        if (LocalDateTime.now().isAfter(fechaHoraVencimiento) || fechaHoraVencimiento.isEqual(LocalDateTime.now())){
            this.estaActiva = false;
        } else {
            this.tiempoRestante = Duration.between(fechaHoraVencimiento, LocalDateTime.now());
        }
    }

    public void addOferta(Oferta oferta){
        ofertas.add(oferta);
    }

    public String verOfertas() {
        return ofertas.toString();
    }

    public Oferta ofertaGanadora(){
        Oferta mayor = null;

        for(Oferta oferta : ofertas){
            if(mayor == null || oferta.getMonto() > mayor.getMonto()){
                mayor = oferta;
            }
        }

        return mayor;
    }

    public String toString(){
        return "\nSubasta: " + id + " Precio Minimo: $" + precioMinimo + "\n" + items;
    }
}