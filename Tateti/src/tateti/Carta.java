package tateti;

public abstract class Carta {
	
	protected Tablero tablero;
	
	/**
	 * pre: -, post: Inicializa la carta
	 * @param tablero
	 */
	public Carta(Tablero tablero) {
		this.tablero = tablero;
	}
}
