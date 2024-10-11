package tateti;

public class Tablero {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------

    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private Lista<Lista<Lista<Casillero>>> casilleros = null;
    private int tamañoX;
    private int tamañoY;
    private int tamañoZ;
    private int condicionVictoria;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * pre: -, pos: crea el tablero TODO: Actualizar pre. Ahora pide la cantidad
     * de fichas en hilera para ganar
     *
     * @param tamañoX, @param tamañoY, @param tamañoZ: no puede ser <=0
     * @throws Exception
     */
    public Tablero(int tamañoX, int tamañoY, int tamañoZ, int condicionVictoria) throws Exception {
        if ((!Herramientas.validarNumeroPositivoEstricto(tamañoX)) || (!Herramientas.validarNumeroPositivoEstricto(tamañoY))
                || (!Herramientas.validarNumeroPositivoEstricto(tamañoZ))) {
            throw new Exception("Los tamaños del tablero deben ser mayores a 0.");
        }
        this.tamañoX = tamañoX;
        this.tamañoY = tamañoY;
        this.tamañoZ = tamañoZ;
        this.condicionVictoria = condicionVictoria;
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

    // TODO pre-post; fijarse si va en otro lado
    public void establecerEntornos() throws Exception {
        for (int x = 0; x < this.tamañoX; x++) {
            for (int y = 0; y < this.tamañoY; y++) {
                for (int z = 0; z < this.tamañoZ; z++) {
                    this.getFicha(x + 1, y + 1, z + 1).establecerEntorno(this);
                }
            }
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
            System.out.printf("\n");
            for (int y = 0; y < this.tamañoY; y++) {
                System.out.printf("\n");
                for (int x = 0; x < this.tamañoX; x++) {
                    System.out.printf("%2d ", getFicha(x + 1, y + 1, z + 1).getJugador());
                }
            }
        }
        System.out.printf("\n");
    }

    /**
     * pre: recibe la posicion (x,y,z) y el jugador para colocar la ficha, pos:
     * TODO actualizar post, ahora devuelve si la jugada resultó en victoria
     *
     * @param x, @param y, @param z: no puede ser <0
     * @param jugador: debe existir?? //TODO: cuando esté hecho el jugador vemos
     * @throws Exception
     */
    public boolean colocarFicha(int x, int y, int z, int jugador) throws Exception {
        verificarPosicionFichaIngresada(x, y, z); // TODO innecesario?
        //TODO: validación si el jugador no existe????
        getFicha(x, y, z).setJugador(jugador);
        //return revisarVictoria(x, y, z);
        return false;
    }

    //TODO pre-post (Toma la posicion de la ficha colocada y revisa si el jugador ganó)
    //TODO no revisa diagonales todavia!!!
    private boolean revisarVictoria(int x, int y, int z) throws Exception {
        boolean victoria = false;
        int cantidadEjeX = victoriaEjeX(x, y, z); // Cantidad de fichas en hilera en el eje X
        int cantidadEjeY = victoriaEjeY(x, y, z); // IDEM Y
        int cantidadEjeZ = victoriaEjeZ(x, y, z); // IDEM Z
        if (cantidadEjeX >= this.condicionVictoria) {
            victoria = true;
        }
        if (cantidadEjeY >= this.condicionVictoria) {
            victoria = true;
        }
        if (cantidadEjeZ >= this.condicionVictoria) {
            victoria = true;
        }
        return victoria;
    }

    // TODO pre-post
    private int victoriaEjeX(int x, int y, int z) throws Exception {
        int cantidadEnHilera = 0;
        Casillero fichaColocada = this.getFicha(x, y, z);
        Casillero fichaAuxiliar = fichaColocada;
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[0][1][1]) != null) {
            // Revisar por izquierda
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHilera++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[2][1][1]) != null) {
            // Revisar por derecha
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHilera++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        return cantidadEnHilera;
    }

    // TODO pre-post
    private int victoriaEjeY(int x, int y, int z) throws Exception {
        int cantidadEnHilera = 0;
        Casillero fichaColocada = this.getFicha(x, y, z);
        Casillero fichaAuxiliar = fichaColocada;
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[1][0][1]) != null) {
            // Revisar por arriba
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHilera++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[1][2][1]) != null) {
            // Revisar por abajo
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHilera++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        return cantidadEnHilera;
    }

// TODO pre-post
    private int victoriaEjeZ(int x, int y, int z) throws Exception {
        int cantidadEnHilera = 0;
        Casillero fichaColocada = this.getFicha(x, y, z);
        Casillero fichaAuxiliar = fichaColocada;
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[1][1][0]) != null) {
            // Revisar por adelante
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHilera++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[1][1][2]) != null) {
            // Revisar por atras
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHilera++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        return cantidadEnHilera;
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
    public final Casillero getFicha(int x, int y, int z) throws Exception {
        verificarPosicionFichaIngresada(x, y, z);
        return this.casilleros.obtener(x).obtener(y).obtener(z);
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}
