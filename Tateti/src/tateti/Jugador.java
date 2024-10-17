package tateti;

public class Jugador {

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private String nombre = null;
    private int identificacion;
    private int maxCartas;
    private boolean pierdeTurno;
    private Carta cartas[];
    private int cantidadDeFichas;
    //TODO: mano de cartas

	//CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * pre: -, post: -
     * Inicializa Jugador y establece los valores de los
     * atributos
     *
     * @param nombre: no puede estar vacío
     * @param cantidadJugadores, @param maxCartas: no pueden ser < 0
     * @param identificacion, @param cantidadDeFichas: no puede ser <= 0
     * @throws Exception
     */
    public Jugador(String nombre, int maxCartas , int identificacion, int cantidadDeFichas) throws Exception {
        if (nombre.trim().isEmpty()) {
            throw new Exception("El nombre no es válido");
        }
        if (!Herramientas.validarNumeroPositivo(maxCartas)) {
            throw new Exception("El valor de cartas máximas debe ser mayor o igual que 0");
        }
        if (!Herramientas.validarNumeroPositivoEstricto(identificacion)) {
            throw new Exception("El valor de identificación del usuario debe ser mayor a 0");
        }
        if (!Herramientas.validarNumeroPositivoEstricto(cantidadDeFichas)) {
        	throw new Exception("La cantidad de fichas debe ser positiva");
        }
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.maxCartas = maxCartas;
        this.pierdeTurno = false;
        this.cantidadDeFichas = cantidadDeFichas;
    }

    /**
     * pre: -, post: -
     * Inicializa Jugador sin nombre y establece sus otros atributos
     * 
     * @param identificacion, @param cantidadDeFichas: no puede ser <= 0
     * @param maxCartas: no puede ser < 0
     * @throws Exception
     */ //TODO: ELIMINAR
    public Jugador(int maxCartas, int identificacion, int cantidadDeFichas) throws Exception {
    	if (!Herramientas.validarNumeroPositivo(maxCartas)) {
            throw new Exception("El valor de cartas máximas debe ser mayor o igual que 0");
        }   	
    	if (!Herramientas.validarNumeroPositivoEstricto(identificacion)) {
            throw new Exception("El valor de identificación del usuario debe ser mayor a 0");
        }
    	if (!Herramientas.validarNumeroPositivoEstricto(cantidadDeFichas)) {
        	throw new Exception("La cantidad de fichas debe ser positiva");
        }
    	
        this.identificacion = identificacion;
        this.maxCartas = maxCartas;
        this.pierdeTurno = false;
        this.cantidadDeFichas = cantidadDeFichas;
    }
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * pre: -, post: - 
     * Invierte el estado del turno
     */
    public void alternarPierdeTurno() {
        this.pierdeTurno = !this.pierdeTurno;
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * pre: -, post: -
     * @return Devuelve el nombre del jugador si es que tiene
     * uno asignado, sino devuelve que no se le asignó nada
     */
    public String getNombreJugador() {
        if (this.nombre == null) {
        	return "El jugador no tiene un nombre asignado";
        }
        return nombre;
    }

    /**
     * pre: -, post: -
     * @return Devuelve el máximo de cartas en mano
     */
    public int getMaxCartasEnMano() {
        return maxCartas;
    }

    /**
     * pre: -, post: -
     * @return Devuelve la identificacion
     */
    public int getIdentificacion() {
        return identificacion;
    }

    /**
     * pre: -, post: -
     * @return Devuelve un boolean correspondiente a
     * si pierde el turno o no
     */
    public boolean isPierdeTurno() {
        return pierdeTurno;
    }
    
    /**
     * pre: -, post: -
     * @return Devuelve la cantidad de fichas que posee el jugador
     */
    public int getCantidadDeFichas() {
		return cantidadDeFichas;
	}
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}
