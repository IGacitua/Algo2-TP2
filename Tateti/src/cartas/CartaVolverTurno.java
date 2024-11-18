package cartas;

import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;

public class CartaVolverTurno extends Carta {
	
	/**
	 * TODO:
	 */
	@Override
	public void usar(Jugador jugadorActual, Lista<Jugador> listaJugadores, Tablero tablero, Mazo mazo
			) throws Exception {
		
		System.out.println("Se regresa atras la ronda");
		tablero.alternarTablero();
		listaJugadores.iniciarCursor();
		while(!listaJugadores.avanzarCursor()) {
			int fichas=tablero.contarFichas(listaJugadores.obtenerCursor());
			listaJugadores.obtenerCursor().setCantidadDeFichas(listaJugadores.obtenerCursor().getCantidadDeFichasMaxima()-fichas);
		}
		
		
		
	}
	
	/**
	 * TODO:
	 */
	@Override
	public String ToString() {	
		return "Carta Volver Turno";
	}

	
	
}
