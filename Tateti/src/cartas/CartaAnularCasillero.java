package cartas;

import tateti.Casillero;
import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;

public class CartaAnularCasillero extends Carta {

	
	/**
     * Misma documentación de Carta.
     * post: Anula un casillero vacío y no bloqueado.
     */
	@Override
	public void usar(Jugador jugadorActual, Lista<Jugador> listaJugadores, Tablero tablero, Mazo mazo) throws Exception {
		boolean casilleroBloqueado= false;

		while(!casilleroBloqueado) {
			try {
				Casillero casillero = pedirCasillero(tablero);

				if (casillero.isBloqueado()){
					throw new Exception("El casillero ya esta bloqueado");
				}
				if(!casillero.estaVacio()) {
					throw new Exception("El casillero no esta vacio");
				}
				
				casillero.alternarBloqueo();
				System.out.println("El casillero fue anulado correctamente.");
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
		return "Carta Anular Casillero";
	}
	
	
}
