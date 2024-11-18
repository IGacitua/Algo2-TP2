package tateti;

import cartas.Carta;
import utilidades.Herramientas;
import utilidades.Lista;

public class Jugador {

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    private static int idActual = 0; // Numero de ID interno, usado para verificar fichas
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private String nombre = null;

    private int cantidadDeFichas; // Cantidad de fichas que le quedan
    private int cantidadDeFichasMaxima; // Cantidad de fichas que posee al principio de la partida

    private int cartasMaximas; // Cantidad maxima de cartas en mano
    private Lista<Carta> cartas = new Lista<>(); // Final porque siempre apunta a la misma Lista

    private int identificacion; // ID interno utilizado por algunos métodos

    private Fichas fichaImagen; // Ficha que usa el usuario -> Para exportar tablero
    private char fichaCaracter; // Ficha que usa el usuario -> Para imprimir tablero
    private ColoresDisponibles color; // Valor RGB de las fichas del usuario

    private boolean pierdeTurno;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * pre: Recibe el nombre, la cantidad inicial de fichas, la cantidad maxima
     * de cartas en mano, el valor del ENUM Ficha que desea utilizar, el valor
     * del ENUM Color que desea utilizar, y la Lista<> de Cartas que compone su
     * mano
     *
     * post: Inicializa Jugador y establece los valores de los atributos.
     *
     * @param nombre No debe estar vacío ni ser nulo.
     * @param fichasMaximas Debe ser > 0.
     * @param cartasMaximas Debe ser >= 0
     * @param fichaImagen Debe ser un valor no nulo del enum Fichas
     * @param color Debe ser un valor no nulo del enum ColoresDisponibles
     * @param cartas No debe ser nulo
     *
     * @throws Exception Si alguno de los parametros no cumple con lo
     * establecido
     */
    public Jugador(String nombre, int fichasMaximas, int cartasMaximas, Fichas fichaImagen, ColoresDisponibles color, Lista<Carta> cartas) throws Exception {

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
            throw new Exception("El color debe existir estar dentro de las siguientes opciones: " + ColoresDisponibles.obtenerColores());
        }
        if (cartas == null) {
            throw new Exception("La lista de cartas del jugador no debe ser nula");
        }
        this.nombre = nombre;
        this.cantidadDeFichas = fichasMaximas;
        this.cantidadDeFichasMaxima = fichasMaximas;
        this.cartasMaximas = cartasMaximas;
        this.fichaImagen = fichaImagen;
        this.fichaCaracter = fichaImagen.getFichaCaracter();
        this.color = color;
        this.identificacion = ++idActual;
        this.cartas = cartas;
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    /**
     * pre: Recibe la cantidad de cartas a robar, y el mazo del cual se roban
     *
     * post: Roba cantidad Cartas del Mazo y las guarda en la mano del Jugador
     *
     * @param cantidad Debe ser > 0
     * @param mazo No debe ser nulo.
     * @throws Exception Si alguno de los parametros no cumple con lo
     * establecido
     * @throws Exception Si robar cantidad Cartas fuese a superar la cantidad
     * maxima en mano
     */
    public void robarCartas(int cantidad, Mazo mazo) throws Exception {
        if (!Herramientas.validarNumeroPositivoEstricto(cantidad)) {
            throw new Exception("Se debe robar por lo menos una carta");
        }
        if (this.cartas.getLongitud() + cantidad > this.cartasMaximas) {
            throw new Exception("No se puede agregar esa cantidad de cartas ya que el máximo es " + this.getCartasMaximas() + " y ya se tienen " + this.getCantidadDeCartas());
        }
        if (mazo == null) {
            throw new Exception("No se indicó un mazo del que robar");
        }
        for (int i = 0; i < cantidad; i++) {
            this.cartas.agregarElemento(mazo.tomarCarta());
        }
    }

    /**
     * pre: Recibe la posición en la mano de la carta que se desee descartar
     *
     * post: Elimina la carta de la mano
     *
     * @param posicion Debe ser >0
     * @throws Exception Si las posicion que se intenta eliminar no es parte de
     * la mano
     */
    public void descartarCarta(int posicion) throws Exception {
        if (!Herramientas.validarNumeroPositivoEstricto(posicion)) {
            throw new Exception("La posición dada debe ser > 0");
        }
        this.cartas.remover(posicion);
    }

    /**
     * post: Reduce en 1 la cantidad de fichas actuales del jugador. No hace
     * nada si el jugador posee 0 fichas.
     */
    public void disminuirFichas() {
        if (this.tieneFichas()) {
            this.cantidadDeFichas -= 1;
        }
    }

    /**
     * post: Aumenta en 1 la cantidad de fichas actuales del jugador.
     */
    public void aumentarFichas() {
        this.cantidadDeFichas++;
    }

    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * @return Devuelve el nombre del jugador.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * @return Devuelve la identificacion del jugador.
     */
    public int getIdentificacion() {
        return this.identificacion;
    }

    /**
     * @return Devuelve la cantidad de fichas actuales que posee el jugador.
     */
    public int getCantidadDeFichas() {
        return this.cantidadDeFichas;
    }

    /**
     * @return Devuelve la cantidad de fichas que tuvo el usuario al inicio de
     * la partida.
     */
    public int getCantidadDeFichasMaxima() {
        return cantidadDeFichasMaxima;
    }

    /**
     * @return Devuelve la cantidad de cartas actuales que posee el jugador.
     */
    public int getCantidadDeCartas() {
        return this.cartas.getLongitud();
    }

    /**
     * @return Devuelve el máximo de cartas que puede tener en mano el jugador.
     */
    public int getCartasMaximas() {
        return this.cartasMaximas;
    }

    /**
     * @return Devuelve el valor Enum de la ficha del usuario.
     */
    public Fichas getFichaImagen() {
        return this.fichaImagen;
    }

    /**
     * @return Devuelve la ficha del usuario en forma de caracter.
     */
    public char getFichaCaracter() {
        return this.fichaCaracter;
    }

    /**
     * @return Devuelve el valor Enum del color de la ficha del usuario.
     */
    public ColoresDisponibles getColor() {
        return this.color;
    }

    /**
     * @return Devuelve la Lista<> de Cartas del jugador.
     */
    public Lista<Carta> getCartas() {
        return cartas;
    }

    /**
     * @return Devuelve un boolean indicando si el jugador tiene fichas en mano.
     */
    public boolean tieneFichas() {
        return this.cantidadDeFichas != 0;
    }

    /**
     * @return Devuelve un boolean correspondiente a si el Jugador pierde su
     * turno o no.
     */
    public boolean isPierdeTurno() {
        return this.pierdeTurno;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------	
    /**
     * post: Establece la cantidade fichas del jugador con las indicadas por el
     * parámetro.
     *
     * @param cantidadDeFichas Debe ser > 0
     * @throws Exception: Si el parametro no cumple con lo requerido.
     */
    public void setCantidadDeFichas(int cantidadDeFichas) throws Exception {
        if (!Herramientas.validarNumeroPositivo(cantidadDeFichas)) {
            throw new Exception("La cantidad de fichas debe ser mayor a 0.");
        }
        this.cantidadDeFichas = cantidadDeFichas;
    }

    /**
     * post: Actualiza la identifiación del jugador.
     *
     * @param id Debe ser > 0.
     * @throws Exception Si la identifiación es <= 0.
     */
    public void setIdentificacion(int id) throws Exception {
        if (!Herramientas.validarNumeroPositivo(id)) {
            throw new Exception("La identificacion debe ser un numero positivo mayor a cero.");
        }
        this.identificacion = id;
    }

    /**
     * post: Invierte si el jugador pierde su turno o no
     */
    public void alternarPierdeTurno() {
        this.pierdeTurno = !this.pierdeTurno;
    }

}
