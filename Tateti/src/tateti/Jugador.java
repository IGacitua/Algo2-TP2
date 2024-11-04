package tateti;

import cartas.Carta;
import utilidades.Herramientas;
import utilidades.Lista;


public class Jugador {

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	private static int idActual = 0; // Numero de ID interno  //TODO: ES NECESARIO???
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private String nombre = null;

    private int cantidadDeFichas; // Cantidad de fichas que le quedan
    private int cartasMaximas; // Cantidad maxima de cartas en mano
    @SuppressWarnings("FieldMayBeFinal") // TODO: Si sigue dando la warning cuando esté completo, hacerlo final
    private Lista<Carta> cartas = new Lista<>();
    private int identificacion;
    private Fichas fichaImagen; // Ficha que usa el usuario -> Para exportar tablero
    private char fichaCaracter; // Ficha que usa el usuario -> Para imprimir tablero
    private Color color; // Valor RGB del usuario

    private boolean pierdeTurno;

    //TODO: mano de cartas
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre: -, post: inicializa Jugador y establece los valores de los
     * atributos
     * @param nombre: no puede estar vacío
     * @param fichasMaximas: debe ser > 0
     * @param cartasMaximas: debe ser >= 0
     * @param fichaImagen: debe existir, no puede ser nulo/vacío
     * @param color: debe estar dentro de las opciones de color del enum
     * @throws Exception
     */
    public Jugador(String nombre, int fichasMaximas, int cartasMaximas, Fichas fichaImagen, Color color) throws Exception {
        if (nombre.trim().isEmpty()) {
            throw new Exception("El nombre no es válido");
        }
        if (!Herramientas.validarNumeroPositivoEstricto(fichasMaximas)) {
            throw new Exception("La cantidad de fichas debe ser positiva");
        }
        if (!Herramientas.validarNumeroPositivo(cartasMaximas)) {
            throw new Exception("El valor de cartas máximas debe ser mayor o igual que 0");
        }
        if (fichaImagen == null) { 
            throw new Exception("La ficha debe existir y estar dentro de las siguientes opciones: " + Fichas.obtenerTiposFicha());
        }
        if ((color == null) || (!Herramientas.validarRGB(color.getRGB()))) {
        	throw new Exception("El color debe existir estar dentro de las siguientes opciones: " + Color.obtenerColores());
        }
        this.nombre = nombre;
        this.cantidadDeFichas = fichasMaximas;
        this.cartasMaximas = cartasMaximas;
        
        this.fichaImagen = fichaImagen;
        this.fichaCaracter = fichaImagen.getFichaCaracter();
        this.color = color;
        this.identificacion = ++idActual;
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    /**
     * pre: -, post: roba cartas del mazo y las guarda en el jugador
     *
     * @param cantidad: si es <= 0 o ya alcanzó el máximo de cartas, no roba cartas
     * @param mazo: no puede ser nulo
     * @throws Exception
     */
    public void robarCartas(int cantidad, Mazo mazo) throws Exception {
    	if (cantidad <= 0) {
    		throw new Exception("Se debe robar por lo menos una carta");
    	}
        if (this.cartas.getLongitud() + cantidad > this.cartasMaximas) {
            throw new Exception("No se puede agregar esa cantidad de cartas ya que el máximo es " + this.getCartasMaximas() + " y ya se tienen " + this.getCantidadCartas());
        }

        if (mazo == null) {
            throw new Exception("No se indicó un mazo del que robar");
        }

        for (int i = 0; i < cantidad; i++) {
                this.cartas.agregarElemento(mazo.tomarCarta());
        }
    }

    /**
     * pre: -, post: invierte el estado del turno
     */
    public void alternarPierdeTurno() {
        this.pierdeTurno = !this.pierdeTurno;
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * pre: -, post: -
     *
     * @return Devuelve el nombre del jugador
     */
    public String getNombreJugador() {
        return this.nombre;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve la identificacion
     */
    public int getIdentificacion() {
        return this.identificacion;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve la cantidad de fichas que posee el jugador
     */
    public int getCantidadDeFichas() {
        return this.cantidadDeFichas;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve el máximo de cartas en mano
     */
    public int getCartasMaximas() {
        return this.cartasMaximas;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve la cantidad de cartas en mano
     */
    public int getCantidadCartas() {
        return this.cartas.getLongitud();
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve la ficha del usuario en forma de imagen
     */
    public Fichas getFichaImagen() {
        return this.fichaImagen;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve la ficha del usuario en forma de caracter
     */
    public char getFichaCaracter() {
        return this.fichaCaracter;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve el color del usuario haciendo referencia al enum
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve un boolean correspondiente a si pierde el turno o no
     */
    public boolean isPierdeTurno() {
        return this.pierdeTurno;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}
