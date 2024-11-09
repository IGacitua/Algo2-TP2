package cartas;

import tateti.Casillero;

public class CartaAnularCasillero extends Carta {

	
	/**
     * pre: -, post: -
     * Bloquea un casillero vacio y no bloqueado
     * 
     * @param Casillero: no debe ser null, no debe estar vacio y no debe estar bloqueado
     * @throws Exception
     */
	public void usar(Casillero casillero) throws Exception {
		
		if (casillero == null) {
			throw new Exception("Casillero no puede ser null");
		}
		
		if (casillero.estaVacio() &&
			!casillero.isBloqueado()) {
			casillero.alternarBloqueo();
		} else {
			throw new Exception("El casillero no esta vacio o ya esta bloqueado");
		}
	}
}