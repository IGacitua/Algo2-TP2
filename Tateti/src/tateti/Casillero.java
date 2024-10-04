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
	
	public Casillero(int x, int y, int z) {
		this.posicionX = x;
		this.posicionY = y;
		this.posicionZ = z;
	}
	
	//METODOS DE CLASE ----------------------------------------------------------------------------------------
	
	// TODO pre-post
	public boolean alternarBloqueo() {
		bloqueado = !bloqueado;
		return bloqueado;
	}
	
	//METODOS GENERALES ---------------------------------------------------------------------------------------
	
	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
	
	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	
	// TODO todos los pre y post de getters 
	
	public int getJugador() {
		return jugador;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public int getPosicionZ() {
		return posicionZ;
	}
	
	public int[] getCoordenadas() {
		int[] coordenadas = new int[3];
		coordenadas[0] = this.posicionX;
		coordenadas[1] = this.posicionY;
		coordenadas[2] = this.posicionZ;
		return coordenadas;
	}
	
	public boolean isBloqueado() {
		return bloqueado;
	}
	
	
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------
	
	// TODO pre-post
	public void setJugador(int jugador) {
		this.jugador = jugador;
	}
}
