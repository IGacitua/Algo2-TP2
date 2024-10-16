package tateti;

public class Casillero {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private int posicionX;
    private int posicionY;
    private int posicionZ;

    private Casillero[][][] entorno;

    private int jugador = 0; // 0 sin jugador
    private boolean bloqueado = false; // Con ficha -> No se puede sacar. Sin ficha -> No se puede colocar

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * pre: -, pos: crea el casillero
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
     * pre: -, pos: - Alterna el bool de bloqueo para bloquear/desbloquear el
     * casillero
     */
    public void alternarBloqueo() {
        this.bloqueado = !this.bloqueado;
    }

    /**
     * pre: el tablero debe existir, post: -
     * @param tablero: se establece el entorno de la ficha
     */
    public void establecerEntorno(Tablero tablero) {
        this.entorno = new Casillero[3][3][3];
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                for (int z = -1; z < 2; z++) {
                    try {
                        this.entorno[x + 1][y + 1][z + 1] = tablero.getFicha(this.posicionX + x, this.posicionY + y, this.posicionZ + z);
                    } catch (Exception e) {
                        this.entorno[x + 1][y + 1][z + 1] = null;
                    }
                }
            }
        }
    }

    // TODO delete
    public void imprimirEntorno() {
        for (int z = -1; z < 2; z++) {
            for (int y = -1; y < 2; y++) {
                for (int x = -1; x < 2; x++) {
                    if (this.entorno[x + 1][y + 1][z + 1] != null) {
                        System.out.printf("%2d ", this.entorno[x + 1][y + 1][z + 1].getJugador());
                    } else {
                        System.out.printf("-1 ");
                    }
                }
                System.out.printf("\n");
            }
            System.out.printf("\n");
        }
    }

    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * pre: -, pos: -
     *
     * @return devuelve el jugador que está en ese casillero
     */
    public int getJugador() {
        return jugador;
    }

    /**
     * pre: -, pos: -
     *
     * @return Devuelve la posición x del casillero
     */
    public int getPosicionX() {
        return posicionX;
    }

    /**
     * pre: -, pos: -
     *
     * @return Devuelve la posición y del casillero
     */
    public int getPosicionY() {
        return posicionY;
    }

    /**
     * pre: -, pos: -
     *
     * @return Devuelve la posición z del casillero
     */
    public int getPosicionZ() {
        return posicionZ;
    }

    /**
     * pre: -, pos: -
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

    // TODO pre-post
    public Casillero[][][] getEntorno() {
        return entorno;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * pre: debe existir el jugador, pos: -
     *
     * @param jugador: TODO: validar
     */
    public void setJugador(int jugador) {
        //TODO: validar que exista el jugador cuando la clase esté hecha
        this.jugador = jugador;
    }

}
