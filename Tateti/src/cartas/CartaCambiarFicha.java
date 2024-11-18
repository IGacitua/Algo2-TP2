package cartas;

import tateti.Casillero;
import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;

public class CartaCambiarFicha extends Carta {

	/**
     * pre: -, post: Cambia el dueño de una ficha
     *
     * @throws Exception: Si casillero es null, si jugador es null, si casillero esta vacio, si la ficha ya pertenece a jugador
     */
	public void usar(Jugador jugadorActual, Lista<Jugador> listaJugadores, Tablero tablero, Mazo mazo) throws Exception {
		
		boolean casilleroModificado=false;
		Casillero casillero;
		while(!casilleroModificado) {
			try {
				casillero=this.pedirCasillero(tablero);
				
				if (casillero.getJugador() == null) {
					throw new Exception("El casillero indicado debe pertenecer a alguien");
				}
				if (casillero.getJugador().getIdentificacion()==jugadorActual.getIdentificacion()) {
					throw new Exception("No puedes cambiar tu propia ficha");
				}
				
				casillero.setJugador(null);
				casillero.setJugador(jugadorActual);
				casilleroModificado=true;
				
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * 
	 */
	@Override
	public String ToString() {	
		return "Carta Cambiar Ficha";
	}
}
