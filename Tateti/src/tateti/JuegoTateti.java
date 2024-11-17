package tateti;
import utilidades.Teclado;

public class JuegoTateti{
    public static void main(String[] args) throws Exception{
    	Teclado teclado =new Teclado(); // scanner
    	Menu menu = new Menu(); // iniciamos menu
    	Tablero tablero = menu.inicializarTablero();
    	menu.inicializarJugadores(tablero.getTamaño());
    	Mazo mazo = menu.inicializarMazo();
    	String nombreGanador = menu.gestionarTurnos(tablero, mazo);
    	System.out.println("El ganador es " + nombreGanador);
    	//menu.repartirCartas(mazo);
        teclado.cerrarScanner();
    }
}
