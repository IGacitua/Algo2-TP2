package cartas;

import tateti.Jugador;
import tateti.Mazo;

public class CartaRobarCartas extends Carta {

	private final static int CANTIDAD_A_ROBAR = 2;

	/**
     * pre: -, post: -
     * 
     * 
     * @param jugador: jugador que recibira las cartas
	 * @throws Exception 
     */
	public void usar(Jugador jugador, Mazo mazo) throws Exception {
		if (jugador == null) {
			throw new Exception("No se especifico un jugador");
		}
		if (mazo == null) {
			throw new Exception("No se especifico un mazo");
		}
		if (jugador.getCantidadCartas()+CANTIDAD_A_ROBAR > jugador.getCartasMaximas()) {
			throw new Exception("No se puede utilizar la carta en este jugador porque excede su maximo");
		}
		jugador.robarCartas(CANTIDAD_A_ROBAR, mazo);
	}
}