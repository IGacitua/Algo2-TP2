package tateti;

import cartas.Carta;
import utilidades.Herramientas;
import utilidades.Lista;

public class Jugador {

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private String nombre = null; //TODO es necesario nombre?
    private int identificacion; // Numero de ID interno

    private int cantidadDeFichas; // Cantidad de fichas que le quedan
    private int cartasMaximas; // Cantidad maxima de cartas en mano
    @SuppressWarnings("FieldMayBeFinal") // TODO: Si sigue dando la warning cuando esté completo, hacerlo final
    private Lista<Carta> cartas = new Lista<>();

    private Fichas fichaImagen; // Ficha que usa el usuario -> Para exportar tablero
    private char fichaCaracter; // Ficha que usa el usuario -> Para imprimir tablero
    private int color; // Valor RGB del usuario

    private boolean pierdeTurno;

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
    public Jugador(String nombre, int identificacion, int fichasMaximas, int cartasMaximas, Fichas fichaImagen, char fichaCaracter, int color) throws Exception {
        if (nombre.trim().isEmpty()) {
            throw new Exception("El nombre no es válido");
        }
        if (!Herramientas.validarNumeroPositivoEstricto(identificacion)) {
            throw new Exception("El valor de identificación del usuario debe ser mayor a 0");
        }
        if (!Herramientas.validarNumeroPositivo(this.cartasMaximas)) {
            throw new Exception("El valor de cartas máximas debe ser mayor o igual que 0");
        }
        if (!Herramientas.validarNumeroPositivoEstricto(fichasMaximas)) {
            throw new Exception("La cantidad de fichas debe ser positiva");
        }
        if (fichaImagen == null) {
            throw new Exception("La ficha debe existir");
        }

        this.nombre = nombre;
        this.identificacion = identificacion;

        this.cantidadDeFichas = fichasMaximas;
        this.cartasMaximas = cartasMaximas;

        this.fichaImagen = fichaImagen;
        this.fichaCaracter = fichaCaracter;
        this.color = color;
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
        if (this.cartas.getLongitud() + cantidad > this.cartasMaximas) {
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
     * @return Devuelve la identificacion
     */
    public int getIdentificacion() {
        return identificacion;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve la cantidad de fichas que posee el jugador
     */
    public int getCantidadDeFichas() {
        return cantidadDeFichas;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve el máximo de cartas en mano
     */
    public int getCartasMaximas() {
        return cartasMaximas;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve la cantidad de cartas en mano
     */
    public int getCantidadCartas() {
        return cartas.getLongitud();
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve la ficha del usuario en forma de imagen
     */
    public Fichas getFichaImagen() {
        return fichaImagen;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve la ficha del usuario en forma de caracter
     */
    public char getFichaCaracter() {
        return fichaCaracter;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve el color del usuario en formato RGB
     */
    public int getColor() {
        return color;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve un boolean correspondiente a si pierde el turno o no
     */
    public boolean isPierdeTurno() {
        return pierdeTurno;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}
