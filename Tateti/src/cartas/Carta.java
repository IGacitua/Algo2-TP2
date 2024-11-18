package cartas;

import tateti.Casillero;
import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;
import utilidades.Teclado;

public abstract class Carta {
	
	/**
	 * pre: jugadorActual, listaJugadores, tablero y mazo deben ser válidos,
	 * post: Varía según la carta.
	 * @param jugadorActual El jugador que juega la carta, no debe ser nulo.
	 * @param listaJugadores La lista de jugadores que están el la partida, no debe ser nula.
	 * @param tablero El tablero con el que se desarrolla la partida, no debe ser nulo.
	 * @param mazo El mazo con el que se juega la partida, no debe ser nulo.
	 * @throws Exception Si alguno de los parámetros es inválido.
	 */
	public abstract void usar(Jugador jugadorActual, Lista<Jugador> listaJugadores, Tablero tablero, Mazo mazo) throws Exception;
	
	/**
	 * pre: -, post: -
	 * @return Devuelve el nombre de la carta.
	 */
	public abstract String toString();
	
	/**
	 * pre: tablero debe ser válido,
	 * post: Devuelve un Casillero en base a lo ingresado por el usuario.
	 * @param tablero No debe ser nulo.
	 * @return Devuelve un Casillero ingresado por el usuario.
	 */
	protected Casillero pedirCasillero(Tablero tablero) {
		boolean valido= false;
		Casillero casillero=null;
		
		while(!valido) {
			try {
				System.out.printf("\n");
				int x = Teclado.pedirNumeroEntreIntervalo("X", 1, tablero.getTamaño())-1;
				int y = Teclado.pedirNumeroEntreIntervalo("Y", 1, tablero.getTamaño())-1;
				int z = Teclado.pedirNumeroEntreIntervalo("Z", 1, tablero.getTamaño())-1;
				
				casillero = tablero.getCasillero(x, y, z);
				valido=true;				
				
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}		
		return casillero;	
	}
	
	/**
	 * pre: listaJugadores debe ser válida,
	 * post: Devuelve el Jugador que fue elegido por consola.
	 * @param listaJugadores No debe ser nula.
	 * @return Devuelve un Jugador según la identificación.
	 */
	protected Jugador pedirJugadorPorIdentificacion(Lista<Jugador> listaJugadores) {
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
	 * pre: listaJugadores debe ser válida,
	 * post: Imprime todos los jugadores por pantalla de la forma:
	 * 1) nombreJugador1, ...
	 * N) nombreJugadorN
	 * @param listaJugadores No debe ser nula.
	 */
	protected void mostrarJugadores(Lista<Jugador> listaJugadores) {
		listaJugadores.iniciarCursor();
		while(listaJugadores.avanzarCursor()) {
			Jugador jugador=listaJugadores.obtenerCursor();
			System.out.println(jugador.getIdentificacion()+") "+jugador.getNombre()); // 1) Manuel
		}
	}
	
}

