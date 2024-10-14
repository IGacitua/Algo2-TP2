package tateti;

public class Jugador {

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private String nombre;
    private int identificacion;
    private int maxCartas;
    private boolean pierdeTurno;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * pre: -, pos: - Inicializa Jugador y establece los valores de los
     * atributos
     *
     * @param cantidadJugadores, @param maxCartas: no pueden ser < 0
     * @throws Exception
     */
    public Jugador(String nombre, int identificacion, int maxCartas) throws Exception {
        // TODO: add identificacion to pre-post
        if (nombre.isEmpty()) {
            throw new Exception("El nombre no es valido");
        }
        if (!Herramientas.validarNumeroPositivo(maxCartas)) {
            throw new Exception("El valor de cartas maximas debe ser mayor o igual que 0");
        }
        if (!Herramientas.validarNumeroPositivoEstricto(identificacion)) {
            throw new Exception("El valor de identificacion del usuario debe ser mayor a 0");
        }
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.maxCartas = maxCartas;
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
        return nombre;
    }

    public int getMaxCartasEnMano() {
        return maxCartas;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public boolean isPierdeTurno() {
        return pierdeTurno;
    }
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}
