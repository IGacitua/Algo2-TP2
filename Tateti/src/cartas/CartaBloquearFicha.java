package cartas;

import tateti.Casillero;

public class CartaBloquearFicha extends Carta {

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