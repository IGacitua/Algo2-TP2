package cartas;

import tateti.Jugador;

public class CartaPerderTurno extends Carta {
	
	/**
     * pre: -, post: -
     * Hace que un jugador que no perdio el turno pierda el turno
     * 
     * @param Jugador: no debe ser null ni haber perdido el turno
     * @throws Exception
     */
	public void usar(Jugador jugador) throws Exception {
		
		if(jugador == null) {
			throw new Exception("Jugador no puede ser null");
		}
		
		if (!jugador.isPierdeTurno()) {
			jugador.alternarPierdeTurno();
		} else {
			throw new Exception("El jugador ya perdio el turno");
		}
	}
}
