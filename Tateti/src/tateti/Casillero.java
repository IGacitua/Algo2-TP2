package tateti;
import utilidades.Herramientas;


public class Casillero {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private int posicionX;
    private int posicionY;
    private int posicionZ;

    private Casillero[][][] entorno;

    private Jugador jugador = null;
    private boolean bloqueado = false; // Con ficha -> No se puede sacar. Sin ficha -> No se puede colocar

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * pre: -, post: crea el casillero
     *
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
     * pre: -, post: - 
     * Alterna el bool de bloqueo para bloquear/desbloquear el
     * casillero
     */
    public void alternarBloqueo() {
        this.bloqueado = !this.bloqueado;
    }

    /**
     * pre: el tablero debe existir, post: -
     *
     * @param tablero: se establece el entorno de la ficha
     */
    public void establecerEntorno(Tablero tablero) {
        this.entorno = new Casillero[3][3][3];
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                for (int z = -1; z < 2; z++) {
                    try {
                        this.entorno[x + 1][y + 1][z + 1] = tablero.getCasillero(this.posicionX + x, this.posicionY + y, this.posicionZ + z);
                    } catch (Exception e) {
                        this.entorno[x + 1][y + 1][z + 1] = null;
                    }
                }
            }
        }
    }

    // TODO delete
    public void imprimirEntorno() throws Exception {
        for (int z = -1; z < 2; z++) {
            for (int y = -1; y < 2; y++) {
                for (int x = -1; x < 2; x++) {
                    if (this.entorno[x + 1][y + 1][z + 1] != null) {
                        System.out.printf("%2d ", this.entorno[x + 1][y + 1][z + 1].getJugador().getIdentificacion());
                    } else {
                        System.out.printf("-1 ");
                    }
                }
                System.out.printf("\n");
            }
            System.out.printf("\n");
        }
    }

    /**
     * pre: recibe un casillero válido, post: -
     * @param casilla: debe ser válida //TODO: VALIDAR
     * @return Devuelve un boolean correspondiente a si el casillero
     * con el que se está trabajando es adyacente al propuesto
     */
    public boolean esAdyacente(Casillero casilla) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    if (this.getEntorno()[x][y][z] == casilla) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public void quitarJugador(int identificacion) throws Exception {
    	if ((identificacion < 0) || (this.jugador.getIdentificacion() != identificacion)) {
    		throw new Exception("No existe ningún jugador con esa identificación en este casillero.");
    	}
    	this.jugador = null;
    }
    
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * pre: -, post: -
     *
     * @return Devuelve el jugador que está en ese casillero
     */
    public Jugador getJugador() throws Exception {
        return this.jugador;
    }

    /**
     * pre: debe existir un jugador, post: -
     * @return Devuelve 0 si no hay un jugador en el casillero, o devuelve la
     * identificación del mismo
     */
    public int getIdentificacionDeJugador() {
        if (jugador == null) {
            return 0;
        } else {
            return this.jugador.getIdentificacion();
        }
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve la posición x del casillero
     */
    public int getPosicionX() {
        return posicionX;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve la posición y del casillero
     */
    public int getPosicionY() {
        return posicionY;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve la posición z del casillero
     */
    public int getPosicionZ() {
        return posicionZ;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve las coordenadas completas del casillero
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
     *
     * @return Devuelve un booleano correspondiente a si el casillero está
     * bloqueado o no.
     */
    public boolean isBloqueado() {
        return bloqueado;
    }
    
    public boolean estaVacio() {
    	return this.jugador == null;
    }

    /**
     * pre: -, post: -
     * @return Devuelve el entorno del casillero
     */
    public Casillero[][][] getEntorno() {
        return entorno;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * pre: debe existir el jugador, post: -
     *
     * @param jugador: no puede ser nulo
     * @throws Exception 
     */
    public void setJugador(Jugador jugador) throws Exception {
        if (jugador == null) {
        	throw new Exception("Ese jugador no existe");
        }
        this.jugador = jugador;
    }

}
