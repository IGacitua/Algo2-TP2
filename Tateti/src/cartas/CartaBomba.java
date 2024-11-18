package cartas;

import tateti.Casillero;
import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;


public class CartaBomba extends Carta {
	
	/**
     * TODO:
     */
	@Override
	public void usar(Jugador jugadorActual, Lista<Jugador> listaJugadores, Tablero tablero, Mazo mazo) throws Exception {
			
			boolean casilleroValido=false;
			Casillero casillero=null;
			System.out.println("Ingrese que casillero quiere explotar");
			
			while(!casilleroValido) {	
				try {
					 casillero = pedirCasillero(tablero); 
					casilleroValido=true;
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			
			this.explotarEntorno(casillero);			
	}

	/**
	 * TODO:
	 * @param casillero
	 * @throws Exception
	 */
	private void explotarEntorno(Casillero casillero) throws Exception {

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

	@Override
	public String ToString() {	
		return "Carta Bomba";
	}

}
