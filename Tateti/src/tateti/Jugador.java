package tateti;

public class Jugador {
	
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	//ATRIBUTOS -----------------------------------------------------------------------------------------------
	private String nombreJugador;
	private int maxCartasEnMano;
	private boolean pierdeTurno;
	
	//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	/**
	 * pre: -, pos: - Inicializa Jugador y establece los valores de los atributos
	 * 
	 * @param cantidadJugadores, @param maxCartas: no pueden ser < 0
	 * @throws Exception
	 */
	public Jugador(String nombre, int maxCartas) throws Exception {
		if (nombre.isEmpty()) {
			throw new Exception("El nombre no es valido");
		}
		if (!Herramientas.validarNumeroPositivoEstricto(maxCartas)) {
			throw new Exception("El valor debe ser positivo");
		}
		
		this.nombreJugador = nombre;
		this.maxCartasEnMano = maxCartas;
		this.pierdeTurno = false;
	}
	//METODOS DE CLASE ----------------------------------------------------------------------------------------
	//METODOS GENERALES ---------------------------------------------------------------------------------------
	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
	
	/**
	 * pre: -, post: - invierte el estado del turno
	 */
	public void cambiarEstadoDelTurno() {
		this.pierdeTurno = !this.pierdeTurno;
	}
	
	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	
	public String getNombreJugador() {
		return nombreJugador;
	}
	
	public int getMaxCartasEnMano() {
		return maxCartasEnMano;
	}
	
	public boolean isPierdeTurno() {
		return pierdeTurno;
	}
	
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}
