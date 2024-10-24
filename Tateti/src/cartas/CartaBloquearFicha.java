package cartas;

import tateti.Casillero;
import tateti.Tablero;

public class CartaBloquearFicha extends Carta {

	/**
	 * pre: -, post: Inicializa la carta
	 * @param tablero
	 */
	public CartaBloquearFicha(Tablero tablero) {
		super(tablero);
	}

	/**
     * pre: -, post: -
     * Bloquea un casillero no vacio y no bloqueado
     * 
     * @param Casillero: no puede estar vacio o bloqueado
     * @throws Exception
     */
	public void usar(Casillero casillero) throws Exception {
		if (!casillero.estaVacio() &&
			!casillero.isBloqueado()) {
			casillero.alternarBloqueo();
		} else {
			throw new Exception("El casillero esta vacio o ya esta bloqueado");
		}
	}
}
