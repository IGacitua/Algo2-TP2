package tateti;

public class Tablero {
    // TODO validacion
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------

    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private Lista<Lista<Lista<Casillero>>> casilleros = null;
    private final int TAMAÑO_X;
    private final int TAMAÑO_Y;
    private final int TAMAÑO_Z;
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    //TODO pre-post
    public Tablero(int tamañoX, int tamañoY, int tamañoZ) throws Exception {
        // TODO validacion tamaños
        this.TAMAÑO_X = tamañoX;
        this.TAMAÑO_Y = tamañoY;
        this.TAMAÑO_Z = tamañoZ;
        this.casilleros = new Lista<Lista<Lista<Casillero>>>();
        for (int x = 0; x < tamañoX; x++) {
            Lista<Lista<Casillero>> fila = new Lista<Lista<Casillero>>();
            for (int y = 0; y < tamañoY; y++) {
                Lista<Casillero> columna = new Lista<Casillero>();
                for (int z = 0; z < tamañoZ; z++) {
                    columna.agregarElemento(new Casillero(x, y, z));
                }
                fila.agregarElemento(columna);
            }
            casilleros.agregarElemento(fila);
        }
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    // TODO pre-post
    public void imprimirTablero() throws Exception {
        for (int z = 0; z < this.TAMAÑO_Z; z++) {
            System.out.printf("\n\n");
            for (int y = 0; y < this.TAMAÑO_Y; y++) {
                System.out.printf("\n");
                for (int x = 0; x < this.TAMAÑO_X; x++) {
                    System.out.printf("%d", getFicha(x, y, z).getJugador());
                }
            }
        }
    }

    // TODO pre-post
    public void colocarFicha(int x, int y, int z, int jugador) throws Exception {
        getFicha(x, y, z).setJugador(jugador);
    }

    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    // TODO pre-post
    private Casillero getFicha(int x, int y, int z) throws Exception {
        return this.casilleros.obtener(x).obtener(y).obtener(z);
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}
