import cr.ac.ucenfotec.logica.gestor.GestorUsuarios;

public class Main {

    public static void main(String[] args) {

        GestorUsuarios gestor = new GestorUsuarios();

        gestor.registrarUsuario("Fabiola", "101", 20, "fabi@gmail.com");
        gestor.registrarUsuario("Carlos", "102", 25, "carlos@gmail.com");

        gestor.listarUsuarios();

    }

}
