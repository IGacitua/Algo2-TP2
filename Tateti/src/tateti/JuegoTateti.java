package tateti;

import utilidades.Lista;
import utilidades.Teclado;

public class JuegoTateti{
    public static void main(String[] args) throws Exception{
    	Teclado teclado =new Teclado(); // scanner
    	Menu menu = new Menu(); // iniciamos menu
    	
    	Tablero tablero = new Tablero(teclado.pedirNumeroEntreIntervalo("Ingrese el tamaño del tablero con el quiere 				jugar", 3, 99)); 
    	
    	// generamos el mazo con la cantidad de cartas que los jugadores indiquen
    	Mazo mazo = new Mazo(menu.consultarCantidadCartas()); 
    	
    	//obtenemos los jugadores de 2 a 8 (como maximo posible) cada uno con un simbolo y color
    	menu.cargarJugadores(); 
    	/*   	
    	menu.gestionarTurnos(jugadores,tablero,mazo); // iniciamos la gestion de turnos
       */ 
    	// falta modularizar mover fichas y colocar fichas en gestionar turnos, y recorrer los jugadores 
    	// para repartir las cartas antes de cada turno.
		
        teclado.cerrarScanner();
    }
}