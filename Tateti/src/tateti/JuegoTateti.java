package tateti;

import utilidades.Teclado;

public class JuegoTateti{
    public static void main(String[] args) throws Exception{
    	Teclado teclado =new Teclado(); // scanner
    	Menu menu = new Menu(); // iniciamos menu
    	Tablero tablero = Menu.inicializarTablero();
    	Menu.inicializarJugadores(tablero.getTamaño());
    	Mazo mazo = Menu.inicializarMazo();
    	Menu.repartirCartas(mazo);
    	//Menu.imprimirJugadoresPorPantalla();
    	
    	
    	
    	//Mazo mazo = new Mazo(menu.consultarCantidadCartas()); 
    	
    	//obtenemos los jugadores de 2 a 8 (como maximo posible) cada uno con un simbolo y color
    	//menu.cargarJugadores(); 
    	
    	/*   	
    	menu.gestionarTurnos(jugadores,tablero,mazo); // iniciamos la gestion de turnos
       */ 
    	// falta modularizar mover fichas y colocar fichas en gestionar turnos, y recorrer los jugadores 
    	// para repartir las cartas antes de cada turno.
		
        teclado.cerrarScanner();
    }
}
