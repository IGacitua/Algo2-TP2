package cartas;

import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;

public class CartaRobarCartas extends Carta {

	private final static int CANTIDAD_A_ROBAR = 2;

	/**
     * pre: -, post: -
     * 
     * 
     * @param jugador: jugador que recibira las cartas, no puede ser null
     * @param mazo: mazo del que se tomaran las cartas, no puede ser null
	 * @throws Exception 
     */
	@Override
	public void usar(Jugador jugadorActual, Lista<Jugador> listaJugadores, Tablero tablero, Mazo mazo) throws Exception {

		try {
			if (jugadorActual.getCantidadDeCartas()+CANTIDAD_A_ROBAR > jugadorActual.getCartasMaximas()) {
				throw new Exception("No se puede utilizar la carta en este jugador porque excede su maximo");
			}
			jugadorActual.robarCartas(CANTIDAD_A_ROBAR, mazo);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	/**
	 * TODO:
	 */
	@Override
	public String toString() {
		return "Carta Robar "+CANTIDAD_A_ROBAR+" Cartas";
	}
}
