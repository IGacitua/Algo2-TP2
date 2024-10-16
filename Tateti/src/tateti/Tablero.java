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

    // TODO pre-post;
    public void establecerEntornos() throws Exception {
        for (int x = 0; x < this.tamañoX; x++) {
            for (int y = 0; y < this.tamañoY; y++) {
                for (int z = 0; z < this.tamañoZ; z++) {
                    this.getFicha(x, y, z).establecerEntorno(this);
                }
            }
        }
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    /**
     * pre: -, pos: Imprime el tablero por pantalla
     *
     * @throws Exception
     */
    public void imprimirTablero() throws Exception {
        for (int z = 0; z < this.tamañoZ; z++) {
            System.out.printf("\n");
            for (int y = 0; y < this.tamañoY; y++) {
                System.out.printf("\n");
                for (int x = 0; x < this.tamañoX; x++) {
                    System.out.printf("%2d ", getFicha(x, y, z).getJugador());
                }
            }
        }
        System.out.printf("\n");
    }

    /**
     * pre: recibe la posicion (x,y,z) y el jugador para colocar la ficha, pos:
     * TODO actualizar post, ahora devuelve si la jugada resultó en victoria
     *
     * @param x, @param y, @param z: No puede ser < 0
     * @param jugador: debe existir?? //TODO: cuando esté hecho el jugador vemos
     * @throws Exception
     */
    public boolean colocarFicha(int x, int y, int z, int jugador) throws Exception {
        verificarPosicionFichaIngresada(x, y, z);
        if (this.getFicha(x, y, z).isBloqueado()) {
            throw new Exception("La casilla está bloqueada.");
        }
        //TODO: validación si el jugador no existe.
        getFicha(x, y, z).setJugador(jugador);
        return revisarVictoria(x, y, z);
    }

    //TODO pre-post (Toma la posicion de la ficha colocada y revisa si el jugador ganó)
    //TODO no revisa diagonales todavia!!!
    //TODO, no funciona
    private boolean revisarVictoria(int x, int y, int z) throws Exception {
        boolean victoria = false;
        int cantidadEjeX = victoriaEjeX(x, y, z); // Cantidad de fichas en hilera en el eje X
        int cantidadEjeY = victoriaEjeY(x, y, z); // IDEM Y
        int cantidadEjeZ = victoriaEjeZ(x, y, z); // IDEM Z
        int cantidadEjesXY = victoriaEjeXY(x, y, z); // IDEM XY
        int cantidadEjesXZ = victoriaEjeXZ(x, y, z); // IDEM XZ
        int cantidadEjesYZ = victoriaEjeYZ(x, y, z); // IDEM YZ
        int cantidadEjesXYZ = victoriaEjeXYZ(x, y, z); // IDEM XYZ
        if (cantidadEjeX >= this.condicionVictoria
                || cantidadEjeY >= this.condicionVictoria
                || cantidadEjeZ >= this.condicionVictoria
                || cantidadEjesXY >= this.condicionVictoria
                || cantidadEjesXZ >= this.condicionVictoria
                || cantidadEjesYZ >= this.condicionVictoria
                || cantidadEjesXYZ >= this.condicionVictoria) {
            victoria = true;
        }
        return victoria;
    }

    // TODO pre-post
    private int victoriaEjeX(int x, int y, int z) throws Exception {
        int cantidadEnHilera = 1; // Empieza en 1 xq se considera la propia casilla
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
        int cantidadEnHilera = 1; // Empieza en 1 xq se considera la propia casilla
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
        int cantidadEnHilera = 1; // Empieza en 1 xq se considera la propia casilla
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

    // TODO pre-post
    private int victoriaEjeXY(int x, int y, int z) throws Exception {
        // Revisa linea x+ y+ / x- y-
        int cantidadEnHileraUno = 1; // Empieza en 1 xq se considera la propia casilla
        Casillero fichaColocada = this.getFicha(x, y, z);
        Casillero fichaAuxiliar = fichaColocada;
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[0][0][1]) != null) {
            // Revisar arriba a la izquierda
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHileraUno++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[2][2][1]) != null) {
            // Revisar por atras
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHileraUno++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        // Revisa linea x+ y- / x- y+
        int cantidadEnHileraDos = 1; // Empieza en 1 xq se considera la propia casilla
        fichaAuxiliar = fichaColocada;
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[0][2][1]) != null) {
            // Revisar arriba a la izquierda
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHileraDos++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[2][0][1]) != null) {
            // Revisar por atras
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHileraDos++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        return Math.max(cantidadEnHileraUno, cantidadEnHileraDos); // Devuelve el mas alto de ambos
    }

    // TODO pre-post
    private int victoriaEjeXZ(int x, int y, int z) throws Exception {
        // Revisa linea x+ z+ / x- z-
        int cantidadEnHileraUno = 1; // Empieza en 1 xq se considera la propia casilla
        Casillero fichaColocada = this.getFicha(x, y, z);
        Casillero fichaAuxiliar = fichaColocada;
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[0][1][0]) != null) {
            // Revisar arriba a la izquierda
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHileraUno++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[2][1][2]) != null) {
            // Revisar por atras
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHileraUno++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        // Revisa linea x+ z- / x- z+
        int cantidadEnHileraDos = 1; // Empieza en 1 xq se considera la propia casilla
        fichaAuxiliar = fichaColocada;
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[0][1][2]) != null) {
            // Revisar arriba a la izquierda
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHileraDos++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[2][1][0]) != null) {
            // Revisar por atras
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHileraDos++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        return Math.max(cantidadEnHileraUno, cantidadEnHileraDos); // Devuelve el mas alto de ambos
    }

    // TODO pre-post
    private int victoriaEjeYZ(int x, int y, int z) throws Exception {
        // Revisa linea y+ z+ / y- z-
        int cantidadEnHileraUno = 1; // Empieza en 1 xq se considera la propia casilla
        Casillero fichaColocada = this.getFicha(x, y, z);
        Casillero fichaAuxiliar = fichaColocada;
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[1][0][0]) != null) {
            // Revisar arriba a la izquierda
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHileraUno++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[1][2][2]) != null) {
            // Revisar por atras
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHileraUno++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        // Revisa linea y+ z- / y- z+
        int cantidadEnHileraDos = 1; // Empieza en 1 xq se considera la propia casilla
        fichaAuxiliar = fichaColocada;
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[1][0][2]) != null) {
            // Revisar arriba a la izquierda
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHileraDos++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[1][2][0]) != null) {
            // Revisar por atras
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHileraDos++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        return Math.max(cantidadEnHileraUno, cantidadEnHileraDos); // Devuelve el mas alto de ambos
    }

    // TODO pre-post
    private int victoriaEjeXYZ(int x, int y, int z) throws Exception {
        // Revisa linea x+ y+ z+ / x- y- z-
        int cantidadEnHileraUno = 1; // Empieza en 1 xq se considera la propia casilla
        Casillero fichaColocada = this.getFicha(x, y, z);
        Casillero fichaAuxiliar = fichaColocada;
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[0][0][0]) != null) {
            // Revisar arriba a la izquierda
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHileraUno++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[2][2][2]) != null) {
            // Revisar por atras
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHileraUno++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        // Revisa linea x- y+ z+ / x+ y- z-
        int cantidadEnHileraDos = 1; // Empieza en 1 xq se considera la propia casilla
        fichaAuxiliar = fichaColocada;
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[0][2][2]) != null) {
            // Revisar arriba a la izquierda
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHileraDos++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[2][0][0]) != null) {
            // Revisar por atras
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHileraDos++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        // Revisa linea x+ y- z+ / x- y+ z-
        int cantidadEnHileraTres = 1; // Empieza en 1 xq se considera la propia casilla
        fichaAuxiliar = fichaColocada;
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[2][0][2]) != null) {
            // Revisar arriba a la izquierda
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHileraDos++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[0][2][0]) != null) {
            // Revisar por atras
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHileraDos++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }

        return Math.max(Math.max(cantidadEnHileraUno, cantidadEnHileraDos), cantidadEnHileraTres); // Devuelve el mas alto de los tres
    }

    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    /**
     * pre: recibe la posición y la verifica, pos: -
     *
     * @param x, @param y, @param z: no puede ser < 0
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
     * @param x, @param y, @param z: no puede ser < 0
     * @return Devuelve un puntero a la ficha
     * @throws Exception
     */
    public final Casillero getFicha(int x, int y, int z) throws Exception {
        verificarPosicionFichaIngresada(x + 1, y + 1, z + 1); // Listas son index 1
        return this.casilleros.obtener(x + 1).obtener(y + 1).obtener(z + 1);
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}
