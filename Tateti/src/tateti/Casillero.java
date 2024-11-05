package tateti;

import utilidades.Herramientas;

public class Casillero {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private int posicionX;
    private int posicionY;
    private int posicionZ;

    private Casillero[][][] entorno; // Un array con punteros a los casilleros inmediatamente adyacentes. Nulls son fuera de los limites del tablero

    private Jugador jugador = null; // El jugador dueño de la ficha en el casillero. Null si está vacío
    private boolean bloqueado = false; // Con ficha -> No se puede sacar. Sin ficha -> No se puede colocar

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * pre: Recibe la posición del casillero en los tres ejes
     *
     * post: Crea el casillero
     *
     * @param x: No puede ser menor a 0
     * @param y: No puede ser menor a 0
     * @param z: No puede ser menor a 0
     *
     * @throws Exception: Excepción cuando alguno de los parámetros es >= 0
     */
    public Casillero(int x, int y, int z) throws Exception {
        if ((!Herramientas.validarNumeroPositivo(x)) || (!Herramientas.validarNumeroPositivo(y))
                || (!Herramientas.validarNumeroPositivo(z))) {
            throw new Exception("La posición del casillero debe ser válida. (Dada " + x + " " + y + " " + z + ")");
        }
        this.posicionX = x;
        this.posicionY = y;
        this.posicionZ = z;
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    /**
     * post: Alterna el bool de bloqueo para bloquear/desbloquear el casillero
     */
    public void alternarBloqueo() {
        this.bloqueado = !this.bloqueado;
    }

    /**
     * pre: Recibe el tablero en el que se encuentra el casillero
     *
     * post: Crea el array entorno del casillero
     *
     * @param tablero: Debe no ser nulo. Debe contener al casillero.
     *
     * @throws Exception: Si el tablero es nulo, o no contiene al casillero.
     */
    public void establecerEntorno(Tablero tablero) throws Exception {
        if (tablero == null) {
            throw new Exception("El tablero no es válido.");
        }
        if (!tablero.verificarExistenciaCasillero(this)) {
            throw new Exception("El tablero no contiene este casillero.");
        }
        this.entorno = new Casillero[3][3][3];

        // Los iteradores empiezan en -1 para ayudar legibilidad
        // Son el desplazamiento en base al casillero
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                for (int z = -1; z < 2; z++) {
                    try {
                        this.entorno[x + 1][y + 1][z + 1] = tablero.getCasillero(this.posicionX + x, this.posicionY + y, this.posicionZ + z);
                    } catch (Exception e) {
                        // No hay casillero en el desplazamiento dado (Es decir, está fuera del tablero)
                        this.entorno[x + 1][y + 1][z + 1] = null;
                    }
                }
            }
        }
    }

    /**
     * post: Imprime el entorno del casillero. Utilizado para debugging.
     */
    public void imprimirEntorno() {
        for (int z = -1; z < 2; z++) {
            for (int y = -1; y < 2; y++) {
                for (int x = -1; x < 2; x++) {
                    if (this.entorno[x + 1][y + 1][z + 1] != null) {
                        // getJugador() tira excepción, pero no debería suceder nunca, asi que uso try/catch en vez de throws
                        try {
                            System.out.printf("%2c ", this.entorno[x + 1][y + 1][z + 1].getJugador().getFichaCaracter());
                        } catch (Exception e) {
                            System.out.println("Excepción imposible al imprimir entorno. Revisar el código.");
                        }
                    } else {
                        System.out.printf("- ");
                    }
                }
                // Fin de linea
                System.out.printf("\n");
            }
            // Fin de prints, para separar mejor
            System.out.printf("\n");
        }
    }

    /**
     * pre: Recibe un casillero
     *
     * post: Devuelve si es adyacente a este casillero
     *
     * @param casillero: No debe ser nulo
     *
     * @return Devuelve un boolean correspondiente a si el casillero con el que
     * se está trabajando es adyacente al propuesto
     *
     * @throws Exception: Si el casillero es nulo.
     */
    public boolean esAdyacente(Casillero casillero) throws Exception {
        if (casillero == null) {
            throw new Exception("El casillero dado es nulo.");
        }
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    if (this.getEntorno()[x][y][z] == casillero) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * pre: -, post: -
     *
     * @return Devuelve el jugador que está en ese casillero. Null si no hay
     * jugador.
     */
    public Jugador getJugador() throws Exception {
        return this.jugador;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve 0 si no hay un jugador en el casillero, o devuelve la
     * identificación del mismo
     */
    public int getIdentificacionDeJugador() {
        //TODO probablemente sea eliminado
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
     * @return Devuelve las coordenadas completas del casillero como un array
     * ordenado X-Y-Z
     */
    public int[] getCoordenadas() {
        int[] coordenadas = new int[3];
        coordenadas[0] = this.posicionX;
        coordenadas[1] = this.posicionY;
        coordenadas[2] = this.posicionZ;
        return coordenadas;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve un booleano correspondiente a si el casillero está
     * bloqueado o no.
     */
    public boolean isBloqueado() {
        return bloqueado;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve un booleano correspondiente a si el casillero está vacío
     * o no.
     */
    public boolean estaVacio() {
        return this.jugador == null;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve el entorno del casillero
     */
    public Casillero[][][] getEntorno() {
        return entorno;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * pre: Recibe un jugador o null
     *
     * post: Si el jugador no es null, se vuelve de ese jugador. Si es null se
     * elimina el jugador de la casilla
     *
     * @param jugador: Puede ser nulo
     * @throws Exception: Si se intenta cambiar el jugador sin antes eliminarlo,
     * o eliminar un jugador cuando ya es nulo.
     */
    public void setJugador(Jugador jugador) throws Exception {
        if (!estaVacio() && jugador != null) {
            throw new Exception("Se intentó colocar una ficha en un casillero que ya tiene.");
        }
        if (estaVacio() && jugador == null) {
            throw new Exception("El casillero ya está vacío, no se puede quitar una ficha.");
        }
        this.jugador = jugador;
    }

}
