package tateti;

public class Tablero {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------

    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private Lista<Lista<Lista<Casillero>>> casilleros = null;
    private int tamañoX;
    private int tamañoY;
    private int tamañoZ;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * pre: -, pos: crea el tablero
     *
     * @param tamañoX, @param tamañoY, @param tamañoZ: no puede ser <=0
     * @throws Exception
     */
    public Tablero(int tamañoX, int tamañoY, int tamañoZ) throws Exception {
        if ((!Herramientas.validarNumeroPositivoEstricto(tamañoX)) || (!Herramientas.validarNumeroPositivoEstricto(tamañoY))
                || (!Herramientas.validarNumeroPositivoEstricto(tamañoZ))) {
            throw new Exception("Los tamaños del tablero deben ser mayores a 0.");
        }
        this.tamañoX = tamañoX;
        this.tamañoY = tamañoY;
        this.tamañoZ = tamañoZ;
        this.casilleros = new Lista<>();
        for (int x = 0; x < tamañoX; x++) {
            Lista<Lista<Casillero>> fila = new Lista<>();
            for (int y = 0; y < tamañoY; y++) {
                Lista<Casillero> columna = new Lista<>();
                for (int z = 0; z < tamañoZ; z++) {
                    columna.agregarElemento(new Casillero(x, y, z));
                }
                fila.agregarElemento(columna);
            }
            casilleros.agregarElemento(fila);
        }
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    /**
     * pre: -, pos: imprime el tablero por pantalla
     *
     * @throws Exception
     */
    public void imprimirTablero() throws Exception {
        for (int z = 0; z < this.tamañoZ; z++) {
            System.out.printf("\n\n");
            for (int y = 0; y < this.tamañoY; y++) {
                System.out.printf("\n");
                for (int x = 0; x < this.tamañoX; x++) {
                    System.out.printf("%d", getFicha(x + 1, y + 1, z + 1).getJugador());
                }
            }
        }
    }

    /**
     * pre: recibe la posicion (x,y,z) y el jugador para colocar la ficha, pos:
     * -
     *
     * @param x, @param y, @param z: no puede ser <0
     * @param jugador: debe existir?? //TODO: cuando esté hecho el jugador vemos
     * @throws Exception
     */
    public void colocarFicha(int x, int y, int z, int jugador) throws Exception {
        verificarPosicionFichaIngresada(x, y, z);
        //TODO: validación si el jugador no existe????
        getFicha(x, y, z).setJugador(jugador);
    }
    //METODOS GENERALES ---------------------------------------------------------------------------------------

    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    /**
     * pre: recibe la posición y la verifica, pos: -
     *
     * @param x, @param y, @param z: no puede ser <0
     * @throws Exception
     */
    private void verificarPosicionFichaIngresada(int x, int y, int z) throws Exception {
        if ((!Herramientas.validarNumeroPositivo(x)) || (!Herramientas.validarNumeroPositivo(y))
                || (!Herramientas.validarNumeroPositivo(z))) {
            throw new Exception("La posición de la ficha debe ser válida.");
        }
        // TODO creo que no es necesario esto. Lista<> ya tiene sus verificaciones. Es redundante
        // Si se queda, hacer mensaje de error mas descriptivo
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * pre: debe existir la ficha, pos: -
     *
     * @param x, @param y, @param z: no puede ser <0
     * @return devuelve la posición de la ficha (y el tipo??)
     * @throws Exception
     */
    public Casillero getFicha(int x, int y, int z) throws Exception {
        verificarPosicionFichaIngresada(x, y, z);
        return this.casilleros.obtener(x).obtener(y).obtener(z);
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}
