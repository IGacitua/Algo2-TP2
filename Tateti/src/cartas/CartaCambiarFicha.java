package cartas;

import tateti.Casillero;
import tateti.Jugador;

public class CartaCambiarFicha extends Carta {

	/**
     * pre: -, post: Cambia el dueño de una ficha
     *
     * @param casillero: casillero en el que se encuentra la ficha a cambiar, no debe ser null, debe tener una ficha
     * @param jugador: nuevo dueño de la ficha, no debe ser null, no debe ser el dueño de la ficha en casillero
     * @throws Exception: Si casillero es null, si jugador es null, si casillero esta vacio, si la ficha ya pertenece a jugador
     */
	public void usar(Casillero casillero, Jugador jugador) throws Exception {
		
		if (casillero == null) {
			throw new Exception("Casillero no puede ser null");
		}
		
		if (jugador == null) {
			throw new Exception("Jugador no puede ser null");
		}
		
		if (casillero.getJugador() == null) {
			throw new Exception("Casillero debe tener una ficha");
		}
		
		if (casillero.getJugador() == jugador) {
			throw new Exception("No se puede cambiar una ficha que ya pertenece a jugador");
		}
		
		casillero.setJugador(null);
		casillero.setJugador(jugador);
	}
}
