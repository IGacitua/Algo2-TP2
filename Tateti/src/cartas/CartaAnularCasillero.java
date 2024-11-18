package cartas;

import tateti.Casillero;
import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;
import utilidades.Teclado;

public class CartaAnularCasillero extends Carta {

	
	/**
     * pre: -, post: Bloquea un casillero no bloqueado y que este vacio
     * 
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
				casilleroBloqueado=true;
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	@Override
	public String toString() {
		return "Carta Anular Casillero";
	}
	
	
}