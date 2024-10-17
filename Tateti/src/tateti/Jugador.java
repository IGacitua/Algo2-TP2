package tateti;

public class Jugador {

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private String nombre = null;
    private int identificacion;
    private int maxCartas;
    private boolean pierdeTurno;
    //TODO: mano de cartas
    //TODO: cantidad de fichas max

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * pre: -, post: -
     * Inicializa Jugador y establece los valores de los
     * atributos
     *
     * @param nombre: no puede estar vacío
     * @param cantidadJugadores, @param maxCartas: no pueden ser < 0
     * @throws Exception
     */
    public Jugador(String nombre, int identificacion, int maxCartas) throws Exception {
        if (nombre.trim().isEmpty()) {
            throw new Exception("El nombre no es válido");
        }
        if (!Herramientas.validarNumeroPositivo(maxCartas)) {
            throw new Exception("El valor de cartas máximas debe ser mayor o igual que 0");
        }
        if (!Herramientas.validarNumeroPositivoEstricto(identificacion)) {
            throw new Exception("El valor de identificación del usuario debe ser mayor a 0");
        }
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.maxCartas = maxCartas;
        this.pierdeTurno = false;
    }

    /**
     * pre: -, post: -
     * Inicializa Jugador sin nombre y establece sus otros atributos
     * 
     * @param identificacion: no puede ser <= 0
     * @param maxCartas: no puede ser < 0
     * @throws Exception
     */ //TODO: ELIMINAR
    public Jugador(int identificacion, int maxCartas) throws Exception {
    	if (!Herramientas.validarNumeroPositivoEstricto(identificacion)) {
            throw new Exception("El valor de identificación del usuario debe ser mayor a 0");
        }
        if (!Herramientas.validarNumeroPositivo(maxCartas)) {
            throw new Exception("El valor de cartas máximas debe ser mayor o igual que 0");
        }
        this.identificacion = identificacion;
        this.maxCartas = maxCartas;
        this.pierdeTurno = false;
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
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}
