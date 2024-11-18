package cartas;

import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;

public class CartaPerderTurno extends Carta {
	
	/**
	 * TODO:
	 */
	@Override
	public void usar(Jugador jugadorActual, Lista<Jugador> listaJugadores, Tablero tablero, Mazo mazo) throws Exception {
		boolean bloqueado=false;
		this.mostrarJugadores(listaJugadores);

		while(!bloqueado) {
			try {

				System.out.println("Indique que jugador quiere bloquear (por identificacion)");
				Jugador jugadorObjetivo=this.pedirJugadorPorIdentificacion(listaJugadores);

				if(jugadorObjetivo.getIdentificacion()==jugadorActual.getIdentificacion()) {
					throw new Exception("No puedes autobloquearte");
				}
				if(jugadorObjetivo.isPierdeTurno()) {
					throw new Exception("El jugador indicado ya esta bloqueado");
				}

				jugadorObjetivo.alternarPierdeTurno();
				bloqueado=true;

			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Carta perder Turno";
	}

}
