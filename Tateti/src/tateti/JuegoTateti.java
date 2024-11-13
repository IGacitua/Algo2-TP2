package tateti;

import utilidades.Lista;
import utilidades.Teclado;
public class JuegoTateti{
    public static void main(String[] args) throws Exception{
    	Teclado teclado =new Teclado(); // scanner
    	Lista<Jugador> jugadores = new Lista<>(); //iniciamos jugadores
    	Menu menu = new Menu(jugadores); // iniciamos menu
    	Mazo mazo = new Mazo(menu.consultarCantidadCartas()); // generamos el mazo con la cantidad de cartas que los jugadores indiquen   	
    	menu.cargarJugadores(); //obtenemos los jugadores de 2 a 8 cada uno con un simbolo y color
        Tablero tablero = new Tablero(menu.obtenerTamonioTablero()); //obtenemos el tamaño del tablero y lo iniciamos
		menu.gestionarTurnos(jugadores,tablero,mazo); // iniciamos la gestion de turnos
		// falta modularizar mover fichas y colocar fichas en gestionar turnos, y recorrer los jugadores 
		// para repartir las cartas antes de cada turno.
        teclado.cerrarScanner();
    }
}