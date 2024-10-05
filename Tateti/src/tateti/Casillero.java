package tateti;

public class Casillero {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	//ATRIBUTOS -----------------------------------------------------------------------------------------------
	
	private int posicionX;
	private int posicionY;
	private int posicionZ;
	
	private int jugador = 0; // 0 sin jugador.
	private boolean bloqueado = false; // Con ficha -> No se puede sacar. Sin ficha -> No se puede colocar
	
	//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	/**
	 * pre: -, pos: crea el casillero
	 * @param x, @param y, @param z: no puede ser < 0
	 * @throws Exception 
	 */
	public Casillero(int x, int y, int z) throws Exception {
		if ((!Herramientas.validarNumeroPositivo(x)) || (!Herramientas.validarNumeroPositivo(y))
				|| (!Herramientas.validarNumeroPositivo(z))) {
			throw new Exception("La posición del casillero debe ser válida.");
		}
		this.posicionX = x;
		this.posicionY = y;
		this.posicionZ = z;
	}
	
	//METODOS DE CLASE ----------------------------------------------------------------------------------------
	
	/**
	 * pre: -, pos: -
	 * Alterna el bool de bloqueo para bloquear/desbloquear el casillero
	 */
	public void alternarBloqueo() {
		this.bloqueado = !this.bloqueado;
	}
	
	//METODOS GENERALES ---------------------------------------------------------------------------------------
	
	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
	
	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	
	/**
	 * pre: -, pos: -
	 * @return devuelve el jugador que está en ese casillero
	 */
	public int getJugador() {
		return jugador;
	}

	/**
	 * pre: -, pos: -
	 * @return devuelve la posición x del casillero
	 */
	public int getPosicionX() {
		return posicionX;
	}

	/**
	 * pre: -, pos: -
	 * @return devuelve la posición y del casillero
	 */
	public int getPosicionY() {
		return posicionY;
	}

	/**
	 * pre: -, pos: -
	 * @return devuelve la posición z del casillero
	 */
	public int getPosicionZ() {
		return posicionZ;
	}
	

	/**
	 * pre: -, pos: -
	 * @return devuelve las coordenadas completas del casillero
	 */
	public int[] getCoordenadas() {
		int[] coordenadas = new int[3];
		coordenadas[0] = this.posicionX;
		coordenadas[1] = this.posicionY;
		coordenadas[2] = this.posicionZ;
		return coordenadas;
	}

	/**
	 * pre: -, pos: -
	 * @return devuelve un booleano correspondiente a si el casillero está bloqueado o no.
	 */
	public boolean verificarEstadoBloqueo() {
		return bloqueado;
	}
	
	
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------
	
	/**
	 * pre: debe existir el jugador, pos: -
	 * @param jugador: TODO: validar
	 */
	public void setJugador(int jugador) {
		//TODO: validar que exista el jugador cuando la clase esté hecha
		this.jugador = jugador;
	}
}
