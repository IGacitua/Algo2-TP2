package cartas;

import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;

public class CartaVolverTurno extends Carta {
	
	/**
     * Misma documentación de Carta.
     * post: Vuelve hacia atrás un turno completo (una versión anterior del tablero).
     */
	@Override
	public void usar(Jugador jugadorActual, Lista<Jugador> listaJugadores, Tablero tablero, Mazo mazo) throws Exception {
		
		if (jugadorActual == null) {
			throw new Exception("jugadorActual no puede ser null");
		}
		if (listaJugadores == null) {
			throw new Exception("listaJugadores no puede ser null");
		}
		if (tablero == null) {
			throw new Exception("tablero no puede ser null");
		}
		if (mazo == null) {
			throw new Exception("mazo no puede ser null");
		}
		
		System.out.printf("\n");
		System.out.println("Se regresa atras la ronda!");
		System.out.printf("\n");
		tablero.alternarTablero();
		listaJugadores.iniciarCursor();
		while(!listaJugadores.avanzarCursor()) {
			int fichas=tablero.contarFichas(listaJugadores.obtenerCursor());
			listaJugadores.obtenerCursor().setCantidadDeFichas(listaJugadores.obtenerCursor().getCantidadDeFichasMaxima()-fichas);
		}
	}
	
	/**
	 * pre: -, post: -
	 * @return Devuelve el nombre de la carta.
	 */
	@Override
	public String toString() {	
		return "Carta Volver Turno";
	}

}
