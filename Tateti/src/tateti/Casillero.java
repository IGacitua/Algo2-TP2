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
    private boolean bloqueado = false; // Con ficha -> No se puede sacar. Sin ficha -> No se puede colocar.

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * pre: Recibe la posición del casillero en los tres ejes
     *
     * post: Crea el casillero.
     *
     * @param x: No puede ser menor a 0.
     * @param y: No puede ser menor a 0.
     * @param z: No puede ser menor a 0.
     * @throws Exception: Excepción cuando alguno de los parámetros es >= 0
     */
    public Casillero(int x, int y, int z) throws Exception {
        if ((!Herramientas.validarNumeroPositivo(x))
                || (!Herramientas.validarNumeroPositivo(y))
                || (!Herramientas.validarNumeroPositivo(z))) {
            throw new Exception("La posición del casillero debe ser válida. (Dada " + x + " " + y + " " + z + ")");
        }
        this.posicionX = x;
        this.posicionY = y;
        this.posicionZ = z;
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    /**
     * pre: Recibe el tablero en el que se encuentra el casillero
     *
     * post: Crea el array entorno del casillero.
     *
     * @param tablero: Debe no ser nulo. Debe contener al casillero.
     * @throws Exception: Si el tablero es nulo, o no contiene al casillero.
     */
    public void establecerEntorno(Tablero tablero) throws Exception {
        if (tablero == null) {
            throw new Exception("El tablero no es válido.");
        }
        if (!tablero.casilleroExiste(this)) {
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
     * pre: Recibe un casillero
     *
     * post: Devuelve un boolean correspondiente a si es adyacente a este
     * casillero.
     *
     * @param casillero: No debe ser nulo.
     * @return Devuelve un boolean correspondiente a si casillero es adyacente a
     * this
     * @throws Exception: Si el casillero es nulo.
     */
    public boolean esAdyacente(Casillero casillero) throws Exception {
        if (casillero == null) {
            throw new Exception("El casillero dado es nulo.");
        }
        // Busca el casillero en el entorno
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
     * @return Devuelve el jugador que está en ese casillero. Si no hay jugador,
     * devuelve null.
     */
    public Jugador getJugador() {
        return this.jugador;
    }

    /**
     * @return Devuelve 0 si no hay un jugador en el casillero, o su
     * identificación si hay
     */
    public int getIdentificacionDeJugador() {
        if (jugador == null) {
            return 0;
        } else {
            return this.jugador.getIdentificacion();
        }
    }

    /**
     * @return Devuelve la posición x del casillero.
     */
    public int getPosicionX() {
        return posicionX;
    }

    /**
     * @return Devuelve la posición y del casillero.
     */
    public int getPosicionY() {
        return posicionY;
    }

    /**
     * @return Devuelve la posición z del casillero.
     */
    public int getPosicionZ() {
        return posicionZ;
    }

    /**
     * @return Devuelve un booleano correspondiente a si el casillero está
     * bloqueado o no.
     */
    public boolean isBloqueado() {
        return bloqueado;
    }

    /**
     * @return Devuelve un booleano correspondiente a si el casillero tiene un
     * jugador o no.
     */
    public boolean estaVacio() {
        return this.jugador == null;
    }

    /**
     * @return Devuelve el array entorno del casillero.
     */
    public Casillero[][][] getEntorno() {
        return entorno;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * pre: Recibe un jugador o null
     *
     * post: Si el jugador no es null, se lo establece como el jugador del
     * casillero. Si es null se elimina el jugador de la casilla.
     *
     * @param jugador: Puede ser nulo.
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

    /**
     * post: Alterna el bool de bloqueo para bloquear/desbloquear el casillero.
     */
    public void alternarBloqueo() {
        this.bloqueado = !this.bloqueado;
    }

}
