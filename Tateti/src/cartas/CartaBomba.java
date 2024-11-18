package cartas;

import tateti.Casillero;
import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;


public class CartaBomba extends Carta {
	
	/**
     * Misma documentación de Carta.
     * post: Explota el entorno de un casillero (incluyendo a sí mismo).
     * Al explotar casilleros, dejan de estar bloqueados/anulados si es que lo estaban.
     */
	@Override
	public void usar(Jugador jugadorActual, Lista<Jugador> listaJugadores, Tablero tablero, Mazo mazo) throws Exception {
			
		if (jugadorActual == null) {
			throw new Exception("jugadorActual no puede ser null");
		}
		if (listaJugadores == null) {
			throw new Exception("listaJugadores no puede ser null");
		}
		if (tablero == null) {
			throw new Exception("tablero no puede ser null");
		}
		if (mazo == null) {
			throw new Exception("mazo no puede ser null");
		}
		
		boolean casilleroValido=false;
		Casillero casillero=null;
		System.out.println("Ingrese qué casillero quiere explotar: ");
		
		while(!casilleroValido) {	
			try {
				 casillero = pedirCasillero(tablero); 
				casilleroValido=true;
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.printf("\n");
		System.out.println("BOOM!");
		System.out.printf("\n");
		this.explotarEntorno(casillero);			
	}

	/**
	 * pre: casillero debe ser válido, post: Explota el enorno incluyendo al casillero.
	 * @param casillero No debe ser nulo.
	 * @throws Exception
	 */
	private void explotarEntorno(Casillero casillero) throws Exception {
		
		if (casillero == null) {
			throw new Exception("casillero no puede ser null");
		}

		Casillero[][][] entorno = casillero.getEntorno();

		for (int k = 0; k < entorno.length; k++) {				
			for (int j = 0; j < entorno[k].length; j++) {
				for (int i = 0; i < entorno[k][j].length; i++) {
					if (entorno[k][j][i] == null) {
						continue;
					}
					if(entorno[k][j][i].isBloqueado()) {
						entorno[k][j][i].alternarBloqueo();
					}
					if (entorno[k][j][i].getJugador() != null) {	
						entorno[k][j][i].getJugador().aumentarFichas();
						entorno[k][j][i].setJugador(null);
					}
				}				
			}
		}


	}
	
	/**
	 * pre: -, post: -
	 * @return Devuelve el nombre de la carta.
	 */
	@Override
	public String toString() {	
		return "Carta Bomba";
	}

}
