package cr.ac.ucenfotec.tl;

import cr.ac.ucenfotec.bl.excepciones.UsuarioInvalidoException;
import cr.ac.ucenfotec.bl.gestores.GestorColeccionista;
import cr.ac.ucenfotec.bl.gestores.GestorUsuarios;
import cr.ac.ucenfotec.bl.gestores.GestorVendedor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Controlador {

    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void registrarVendedor() throws IOException, SQLException, ClassNotFoundException{
        System.out.println("\nNombre: ");
        String nombre = in.readLine();
        System.out.println("\nApellidos: ");
        String apellidos = in.readLine();
        System.out.println("\nCédula: ");
        String cedula = in.readLine();
        System.out.println("\nFecha de nacimiento: Dia:");
        int dia = Integer.parseInt(in.readLine());
        System.out.println("\nFecha de nacimiento: Mes:");
        int mes = Integer.parseInt(in.readLine());
        System.out.println("\nFecha de nacimiento: Año:");
        int annio = Integer.parseInt(in.readLine());
        System.out.println("\nCorreo: ");
        String correo = in.readLine();
        System.out.println("\nContraseña: ");
        String password = in.readLine();
        System.out.println("\nDirección: ");
        String direccion = in.readLine();

        System.out.println(GestorVendedor.registrarVendedor(nombre, apellidos, cedula, dia, mes, annio, correo, password, direccion));
    }

    public static void registrarColeccionista() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\nNombre: ");
            String nombre = in.readLine();
            System.out.println("\nApellidos: ");
            String apellidos = in.readLine();
            System.out.println("\nCédula: ");
            String cedula = in.readLine();
            System.out.println("\nFecha de nacimiento: Dia:");
            int dia = Integer.parseInt(in.readLine());
            System.out.println("\nFecha de nacimiento: Mes:");
            int mes = Integer.parseInt(in.readLine());
            System.out.println("\nFecha de nacimiento: Año:");
            int annio = Integer.parseInt(in.readLine());
            System.out.println("\nCorreo: ");
            String correo = in.readLine();
            System.out.println("\nContraseña: ");
            String password = in.readLine();
            System.out.println("\nDirección: ");
            String direccion = in.readLine();

        System.out.println(GestorColeccionista.registrarVendedor(nombre, apellidos, cedula, dia, mes, annio, correo, password, direccion));
    }
}
