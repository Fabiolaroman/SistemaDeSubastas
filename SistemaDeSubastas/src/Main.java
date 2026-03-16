import cr.ac.ucenfotec.logica.entidades.Item;
import cr.ac.ucenfotec.logica.entidades.Moderador;
import cr.ac.ucenfotec.logica.entidades.Vendedor;
import cr.ac.ucenfotec.logica.gestor.GestorSubastas;
import cr.ac.ucenfotec.logica.gestor.GestorUsuarios;
import cr.ac.ucenfotec.logica.ui.Menu;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();

        System.out.print(LocalDateTime.now());
        menu.iniciarMenu();

    }

}
