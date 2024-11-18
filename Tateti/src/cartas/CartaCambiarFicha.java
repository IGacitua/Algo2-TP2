package cartas;

import tateti.Casillero;
import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;

public class CartaCambiarFicha extends Carta {

	/**
     * Misma documentación de Carta.
     * post: Se apropia de un Casillero ocupado por la ficha de otro jugador. 
     */
	public void usar(Jugador jugadorActual, Lista<Jugador> listaJugadores, Tablero tablero, Mazo mazo) throws Exception {
		boolean casilleroModificado=false;
		Casillero casillero;
		while(!casilleroModificado) {
			try {
				System.out.println("Ingrese a qué casillero quiere cambiarle la ficha: ");
				casillero=this.pedirCasillero(tablero);
				
				if (casillero.getJugador() == null) {
					throw new Exception("El casillero indicado debe pertenecer a alguien");
				}
				if (casillero.getJugador().getIdentificacion()==jugadorActual.getIdentificacion()) {
					throw new Exception("No puedes cambiar tu propia ficha");
				}
				
				casillero.setJugador(null);
				casillero.setJugador(jugadorActual);
				System.out.printf("\n");
				System.out.println("Se cambió la ficha correctamente.");
				System.out.printf("\n");
				casilleroModificado=true;
				
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
		return "Carta Cambiar Ficha";
	}
}
