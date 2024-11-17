package cartas;

import tateti.Casillero;

public class CartaBomba extends Carta {
	
	/**
     * pre: -, post: Elimina cualquier ficha que se encuenre en el entorno de "casillero"
     *
     * @param casillero: no debe ser null
     * @throws Exception: Si casillero es null
     */
	public void usar(Casillero casillero) throws Exception {
			
			if (casillero == null) {
				throw new Exception("Casillero no puede ser null");
			}
			
			Casillero[][][] entorno = casillero.getEntorno();
			
			for (int k = 0; k < entorno.length; k++) {				
				for (int j = 0; j < entorno[k].length; j++) {
					for (int i = 0; i < entorno[k][j].length; i++) {
						if (entorno[k][j][i] == null) {
							continue;
						}
						if (entorno[k][j][i].getJugador() != null) {							
							entorno[k][j][i].setJugador(null);
						}
					}				
				}
			}
		}

}
