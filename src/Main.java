import controlador.Controlador;
import vista.Vista;

/*
    Galindo, Blanco, Héctor.
 */

public class Main {
    public static void main(String[] args) {
        Vista vista = new Vista();
        new Controlador(vista);
    }
}
