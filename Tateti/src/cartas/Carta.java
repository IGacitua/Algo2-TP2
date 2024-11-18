package cartas;

import tateti.Casillero;
import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;
import utilidades.Teclado;

public abstract class Carta {
	
	/**
	 * pre: -
	 * @param jugadorActual, el jugador que juega la carta
	 * @param listaJugadores, la lista de jugadores que estan el la partida
	 * @param tablero, el tablero con el que se desarrolla la partida
	 * @param mazo, el mazo con el que se juega la partida
	 * @throws Exception
	 */
	public abstract void usar(Jugador jugadorActual, Lista<Jugador> listaJugadores, Tablero tablero, Mazo mazo) throws Exception;
	
	/**
	 * Pre: - 
	 * Post: Devuelve el nombre de la carta
	 */
	public abstract String toString();
	
	/**
	 * TODO:
	 * @param tablero
	 * @return
	 */
	protected Casillero pedirCasillero(Tablero tablero) {
	
		boolean valido= false;
		Casillero casillero=null;
		
		while(!valido) {
			try {
				System.out.printf("\n");
				int x = Teclado.pedirNumeroEntreIntervalo("X", 1, tablero.getTamaño())-1;
				System.out.printf("\n");
				int y = Teclado.pedirNumeroEntreIntervalo("Y", 1, tablero.getTamaño())-1;
				System.out.printf("\n");
				int z = Teclado.pedirNumeroEntreIntervalo("Z", 1, tablero.getTamaño())-1;
				System.out.printf("\n");
				
				 casillero = tablero.getCasillero(x, y, z);
				 valido=true;				
				
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}		
		return casillero;	
	}
	
	/**
	 * TODO:
	 * @param listaJugadores
	 * @return
	 * @throws Exception
	 */
	protected Jugador pedirJugadorPorIdentificacion(Lista<Jugador> listaJugadores) throws Exception {
		Jugador jugadorResultado=null;		
		int numero=Teclado.pedirNumeroEntreIntervalo(" ", 1, listaJugadores.getLongitud());
		listaJugadores.iniciarCursor();
		while(listaJugadores.avanzarCursor()) {
			Jugador jugador=listaJugadores.obtenerCursor();
			if(jugador.getIdentificacion() == numero) {
				jugadorResultado=jugador;
			}
		}
		
		return jugadorResultado;
	}
	
	/**
	 * TODO:
	 * @param listaJugadores
	 */
	protected void mostrarJugadores(Lista<Jugador> listaJugadores) {
		
		listaJugadores.iniciarCursor();
		while(listaJugadores.avanzarCursor()) {
			Jugador jugador=listaJugadores.obtenerCursor();
			System.out.println(jugador.getIdentificacion()+") "+jugador.getNombre()); // 1) Manuel
		}
	}
	
}
