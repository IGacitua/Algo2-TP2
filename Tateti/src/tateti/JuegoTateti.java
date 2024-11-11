package tateti;

import utilidades.Lista;

public class JuegoTateti{
    public static void main(String[] args) throws Exception{
    	Teclado teclado =new Teclado(); // scanner
    	Lista<Jugador> jugadores = new Lista<>(); //iniciamos la lista de jugadores
    	Menu menu = new Menu(jugadores); // iniciamos al menu con la lista vacia
    	Mazo mazo = new Mazo(null); //iniciamos mazo !!!!!!!!!! !! ! ! !!!!!!!! !! ! ! 
    	menu.generarMazoAleatorio(mazo,menu.limiteCartas()); //iniciamos mazo !!!!!!!!!! !! ! ! !!!!!!!! !! ! ! 
    	
    	menu.cargarJugadores(); //obtenemos los jugadores
    	
        
        Tablero tablero = new Tablero(menu.obtenerTamonioTablero()); //obtenemos el tamaño del tablero
		menu.gestionarTurnos(Lista<Jugador> jugadores,Tablero tablero,Mazo mazo)
        

        //turnos
        
        
        teclado.cerrarScanner();
    }
}