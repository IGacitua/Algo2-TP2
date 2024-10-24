package tateti;

import cartas.Carta;
import utilidades.Herramientas;
import utilidades.Lista;

public class Jugador {

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private String nombre = null; //TODO es necesario nombre?
    private int identificacion;
    private int cantidadDeFichas;
    private Fichas ficha; // TODO ni idea como funcionan los ENUM, q alguien lo haga. 
    private int maxCartas;
    private boolean pierdeTurno;
    @SuppressWarnings("FieldMayBeFinal") // TODO: Si sigue dando la warning cuando esté completo, hacerlo final
    private Lista<Carta> cartas = new Lista<>();
    //TODO: mano de cartas

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * pre: -, post: - Inicializa Jugador y establece los valores de los
     * atributos
     *
     * @param nombre: no puede estar vacío
     * @param cantidadJugadores, @param maxCartas: no pueden ser < 0
     * @param identificacion, @param cantidadDeFichas: no puede ser <= 0
     * @throws Exception
     */
    public Jugador(String nombre, int identificacion, int maxCartas, int cantidadDeFichas) throws Exception {
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
     * pre: -, post: - Inicializa Jugador sin nombre y establece sus otros
     * atributos
     *
     * @param identificacion, @param cantidadDeFichas: no puede ser <= 0
     * @param maxCartas: no puede ser < 0
     * @throws Exception
     */ //TODO: ELIMINAR
    public Jugador(int identificacion, int maxCartas, int cantidadDeFichas) throws Exception {
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
     * pre: -, post: - Roba cartas del mazo y las guarda en el jugador
     *
     * @param cantidad: si es <= 0, no roba cartas @param mazo: mazo del que se
     * roban las cartas @throws Exception
     */
    public void robarCartas(int cantidad, Mazo mazo) throws Exception {
        if (this.cartas.getLongitud() + cantidad > this.maxCartas) {
            throw new Exception("Maximo de cartas alcanzado");
        }

        if (mazo == null) {
            throw new Exception("No se indico un mazo");
        }

        for (int i = 0; i < cantidad; i++) {
            try {
                this.cartas.agregarElemento(mazo.tomarCarta());
            } catch (Exception e) {
                throw new Exception(); //TODO Ponerle mensaje de error. Saqué el printstacktrace porque no se debe usar pero no me voy a fijar q va aca
            }
        }
    }

    /**
     * pre: -, post: - Invierte el estado del turno
     */
    public void alternarPierdeTurno() {
        this.pierdeTurno = !this.pierdeTurno;
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * pre: -, post: -
     *
     * @return Devuelve el nombre del jugador si es que tiene uno asignado, sino
     * devuelve que no se le asignó nada
     */
    public String getNombreJugador() {
        if (this.nombre == null) {
            return "El jugador no tiene un nombre asignado";
        }
        return nombre;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve el máximo de cartas en mano
     */
    public int getMaxCartasEnMano() {
        return maxCartas;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve la identificacion
     */
    public int getIdentificacion() {
        return identificacion;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve un boolean correspondiente a si pierde el turno o no
     */
    public boolean isPierdeTurno() {
        return pierdeTurno;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve la cantidad de fichas que posee el jugador
     */
    public int getCantidadDeFichas() {
        return cantidadDeFichas;
    }

    public Fichas getFicha() {
        return ficha;
    }

    public int getCantidadCartas() {
        return cartas.getLongitud();
    }
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------	

}
