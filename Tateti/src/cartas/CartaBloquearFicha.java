package cartas;

import tateti.Casillero;
import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;
import utilidades.Teclado;

public class CartaBloquearFicha extends Carta {

	/**
     * pre: -, post: Bloquea un casillero no vacio y no bloqueado
     * 
     */
	@Override
	public void usar(Jugador jugadorActual, Lista<Jugador> listaJugadores, Tablero tablero, Mazo mazo) throws Exception {
		
		boolean casilleroBloqueado= false;

		while(!casilleroBloqueado) {
			try {
				Casillero casillero = pedirCasillero(tablero);

				if (casillero.isBloqueado()) {
					throw new Exception("El casillero esta bloqueado");					
				}
				if(casillero.estaVacio()) {
					throw new Exception("El casillero esta vacio");	
				}
				
				casillero.alternarBloqueo();
				casilleroBloqueado=true;
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public String ToString() {
		
		return "Carta bloquear ficha";
	}
	
}