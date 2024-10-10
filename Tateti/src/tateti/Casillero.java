package tateti;

public class Casillero {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final int POSICION_X;
    private final int POSICION_Y;
    private final int POSICION_Z;

    private int jugador = 0; // 0 sin jugador.
    private boolean bloqueado = false; // Con ficha -> No se puede sacar. Sin ficha -> No se puede colocar

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Casillero(int x, int y, int z) {
        this.POSICION_X = x;
        this.POSICION_Y = y;
        this.POSICION_Z = z;
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    // TODO pre-post
    public boolean alternarBloqueo() {
        bloqueado = !bloqueado;
        return bloqueado;
    }

    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    // TODO todos los pre y post de getters 
    public int getJugador() {
        return jugador;
    }

    public int getPosicionX() {
        return POSICION_X;
    }

    public int getPosicionY() {
        return POSICION_Y;
    }

    public int getPosicionZ() {
        return POSICION_Z;
    }

    public int[] getCoordenadas() {
        int[] coordenadas = new int[3];
        coordenadas[0] = this.POSICION_X;
        coordenadas[1] = this.POSICION_Y;
        coordenadas[2] = this.POSICION_Z;
        return coordenadas;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
    // TODO pre-post
    public void setJugador(int jugador) {
        this.jugador = jugador;
    }
}
