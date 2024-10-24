package cartas;

import tateti.Casillero;
import tateti.Tablero;

public class CartaAnularCasillero extends Carta {

	public CartaAnularCasillero(Tablero tablero) {
		super(tablero);
	}
	
	/**
     * pre: -, post: -
     * Bloquea un casillero vacio y no bloqueado
     * 
     * @param Casillero: debe no estar vacio y no estar bloqueado
     * @throws Exception
     */
	public void usar(Casillero casillero) throws Exception {
		if (casillero.estaVacio() &&
			!casillero.isBloqueado()) {
			casillero.alternarBloqueo();
		} else {
			throw new Exception("El casillero no esta vacio o ya esta bloqueado");
		}
	}
}
