package cartas;

import tateti.Casillero;
import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;

public class CartaBloquearFicha extends Carta {

	/**
     * Misma documentación de Carta.
     * post: Bloquea un casillero no vacio y no bloqueado.
     */
	@Override
	public void usar(Jugador jugadorActual, Lista<Jugador> listaJugadores, Tablero tablero, Mazo mazo) throws Exception {
		
		boolean casilleroBloqueado= false;

		while(!casilleroBloqueado) {
			try {
				Casillero casillero = pedirCasillero(tablero);
				if (casillero.getJugador().getNombre().equals(jugadorActual.getNombre())) {
					throw new Exception("No puedes bloquear una ficha propia.");
				}
				if (casillero.isBloqueado()) {
					throw new Exception("El casillero esta bloqueado.");					
				}
				if(casillero.estaVacio()) {
					throw new Exception("El casillero esta vacio.");	
				}
				casillero.alternarBloqueo();
				System.out.printf("\n");
				System.out.println("Se bloqueó la ficha en el casillero correctamente.");
				System.out.printf("\n");
				casilleroBloqueado=true;
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * pre: -, post: -
	 * @return Devuelve el nombre de la carta.
	 */
	@Override
	public String toString() {
		
		return "Carta Bloquear Ficha";
	}
}
