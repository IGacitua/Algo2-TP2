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
     * pre: -, post: crea el tablero
     *
     * @param tamañoX, @param tamañoY, @param tamañoZ, @param condicionVictoria: no puede ser <=0
     * @throws Exception
     */
    public Tablero(int tamañoX, int tamañoY, int tamañoZ, int condicionVictoria) throws Exception {
        if ((!Herramientas.validarNumeroPositivoEstricto(tamañoX)) || (!Herramientas.validarNumeroPositivoEstricto(tamañoY))
                || (!Herramientas.validarNumeroPositivoEstricto(tamañoZ)) || (!Herramientas.validarNumeroPositivoEstricto(condicionVictoria))) {
            throw new Exception("Los tamaños del tablero y la condición de victoria deben ser mayores a 0.");
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

    /**
     * pre: -, post: establece el entorno de trabajo (el tablero).
     * 
     * @throws Exception
     */
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
     * pre: -, post: Imprime el tablero por pantalla (TEMPORAL)
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
     * pre: recibe la posicion (x,y,z) y el jugador para colocar la ficha, post: -
     *
     * @param x, @param y, @param z: No puede ser < 0
     * @param jugador: debe existir?? //TODO: cuando esté hecho el jugador vemos
     * @retrurn devuelve si la jugada de dicho jugador es una victoria o no
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

    /**
     * pre: recibe la posicion (x,y,z), que es donde se colocó una ficha, post: -
     * 
     * @param x, @param y, @param z: No puede ser < 0
     * @return devuelve un booleano dependiendo de si la jugada fue de victoria o no
     * @throws Exception
     */
    private boolean revisarVictoria(int x, int y, int z) throws Exception {
        boolean victoria = false;
        int cantidadEjeX = victoriaEjeX(x, y, z); // Cantidad de fichas en hilera en el eje X
        int cantidadEjeY = victoriaEjeY(x, y, z); // Cantidad de fichas en hilera en el eje Y
        int cantidadEjeZ = victoriaEjeZ(x, y, z); // Cantidad de fichas en hilera en el eje Z
        int cantidadEjesXY = victoriaEjeXY(x, y, z); // Cantidad de fichas en hilera en el eje XY
        int cantidadEjesXZ = victoriaEjeXZ(x, y, z); // Cantidad de fichas en hilera en el eje XZ
        int cantidadEjesYZ = victoriaEjeYZ(x, y, z); // Cantidad de fichas en hilera en el eje YZ
        int cantidadEjesXYZ = victoriaEjeXYZ(x, y, z); // Cantidad de fichas en hilera en el eje XYZ
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

    private int auxiliarVictoria(int x, int y, int z, int cantidadEnHilera, Casillero fichaColocada, Casillero fichaAuxiliar) {
    	while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[x][y][z]) != null) {
            if (fichaAuxiliar.getJugador() == fichaColocada.getJugador()) {
                cantidadEnHilera++;
            } else {
                break; // Para evitar que fichas no-adyacentes cuenten
            }
        }
		return cantidadEnHilera;
    }
    
    /**
     * pre: recibe la posicion (x,y,z), que es donde se colocó una ficha, post: -
     * 
     * @param x, @param y, @param z: No puede ser < 0
     * @return devuelve la cantidadEnHilera del eje x
     * @throws Exception
     */
    private int victoriaEjeX(int x, int y, int z) throws Exception {
        int cantidadEnHilera = 1; // Empieza en 1 porque se considera la propia casilla
        Casillero fichaColocada = this.getFicha(x, y, z);
        Casillero fichaAuxiliar = fichaColocada;
        cantidadEnHilera = auxiliarVictoria(0, 1, 1, cantidadEnHilera, fichaColocada, fichaAuxiliar);
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        cantidadEnHilera = auxiliarVictoria(2, 1, 1, cantidadEnHilera, fichaColocada, fichaAuxiliar);
        
        return cantidadEnHilera;
    }
    
    /**
     * pre: recibe la posicion (x,y,z), que es donde se colocó una ficha, post: -
     * 
     * @param x, @param y, @param z: No puede ser < 0
     * @return devuelve la cantidadEnHilera del eje y
     * @throws Exception
     */
    private int victoriaEjeY(int x, int y, int z) throws Exception {
        int cantidadEnHilera = 1; // Empieza en 1 xq se considera la propia casilla
        Casillero fichaColocada = this.getFicha(x, y, z);
        Casillero fichaAuxiliar = fichaColocada;
        cantidadEnHilera = auxiliarVictoria(1, 0, 1, cantidadEnHilera, fichaColocada, fichaAuxiliar);
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        cantidadEnHilera = auxiliarVictoria(1, 2, 1, cantidadEnHilera, fichaColocada, fichaAuxiliar);
        return cantidadEnHilera;
    }

    /**
     * pre: recibe la posicion (x,y,z), que es donde se colocó una ficha, post: -
     * 
     * @param x, @param y, @param z: No puede ser < 0
     * @return devuelve la cantidadEnHilera del eje z
     * @throws Exception
     */
    private int victoriaEjeZ(int x, int y, int z) throws Exception {
        int cantidadEnHilera = 1; // Empieza en 1 xq se considera la propia casilla
        Casillero fichaColocada = this.getFicha(x, y, z);
        Casillero fichaAuxiliar = fichaColocada;
        cantidadEnHilera = auxiliarVictoria(1, 1, 0, cantidadEnHilera, fichaColocada, fichaAuxiliar);
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        cantidadEnHilera = auxiliarVictoria(1, 1, 2, cantidadEnHilera, fichaColocada, fichaAuxiliar);
        
        return cantidadEnHilera;
    }

    /**
     * pre: recibe la posicion (x,y,z), post: -
     * 
     * @param x, @param y, @param z: No puede ser < 0
     * @return revisa los cuatro cuadrantes de un plano x,y (1ero, 3ero cantidadEnHileraUno;
     * 2do y 4to cantidadEnHileraDos) y devuelve la cantidadEnHilera más grande
     * @throws Exception
     */
    private int victoriaEjeXY(int x, int y, int z) throws Exception {
        // Revisa linea x+ y+ / x- y-  (PRIMER CUADRANTE, TERCER CUADRANTE)
        int cantidadEnHileraUno = 1; // Empieza en 1 xq se considera la propia casilla
        Casillero fichaColocada = this.getFicha(x, y, z);
        Casillero fichaAuxiliar = fichaColocada;
        cantidadEnHileraUno = auxiliarVictoria(0, 0, 1, cantidadEnHileraUno, fichaColocada, fichaAuxiliar);
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        cantidadEnHileraUno = auxiliarVictoria(2, 2, 1, cantidadEnHileraUno, fichaColocada, fichaAuxiliar);
        
        // Revisa linea x+ y- / x- y+  (CUARTO CUADRANTE, SEGUNDO CUADRANTE)
        int cantidadEnHileraDos = 1; // Empieza en 1 xq se considera la propia casilla
        fichaAuxiliar = fichaColocada;
        cantidadEnHileraDos = auxiliarVictoria(0, 2, 1, cantidadEnHileraDos, fichaColocada, fichaAuxiliar);
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        cantidadEnHileraDos = auxiliarVictoria(2, 0, 1, cantidadEnHileraDos, fichaColocada, fichaAuxiliar);
        
        return Math.max(cantidadEnHileraUno, cantidadEnHileraDos); // Devuelve el mas alto de ambos
    }

    /**
     * pre: recibe la posicion (x,y,z), post: -
     * 
     * @param x, @param y, @param z: No puede ser < 0
     * @return revisa los cuatro cuadrantes de un plano x,z (1ero, 3ero cantidadEnHileraUno;
     * 2do y 4to cantidadEnHileraDos) y devuelve la cantidadEnHilera más grande
     * @throws Exception
     */
    private int victoriaEjeXZ(int x, int y, int z) throws Exception {
        // Revisa linea x+ z+ / x- z-  (PRIMER CUADRANTE, TERCER CUADRANTE)
        int cantidadEnHileraUno = 1; // Empieza en 1 xq se considera la propia casilla
        Casillero fichaColocada = this.getFicha(x, y, z);
        Casillero fichaAuxiliar = fichaColocada;
        cantidadEnHileraUno = auxiliarVictoria(0, 1, 0, cantidadEnHileraUno, fichaColocada, fichaAuxiliar);
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        cantidadEnHileraUno = auxiliarVictoria(2, 1, 2, cantidadEnHileraUno, fichaColocada, fichaAuxiliar);
        
        // Revisa linea x+ z- / x- z+  (CUARTO CUADRANTE, SEGUNDO CUADRANTE)
        int cantidadEnHileraDos = 1; // Empieza en 1 xq se considera la propia casilla
        fichaAuxiliar = fichaColocada;
        cantidadEnHileraDos = auxiliarVictoria(0, 1, 2, cantidadEnHileraDos, fichaColocada, fichaAuxiliar);
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        cantidadEnHileraDos = auxiliarVictoria(2, 1, 0, cantidadEnHileraDos, fichaColocada, fichaAuxiliar);
        
        return Math.max(cantidadEnHileraUno, cantidadEnHileraDos); // Devuelve el mas alto de ambos
    }

    /** 
     * pre: recibe la posicion (x,y,z), post: -
     * 
     * @param x, @param y, @param z: No puede ser < 0
     * @return revisa los cuatro cuadrantes de un plano y,z (1ero, 3ero cantidadEnHileraUno;
     * 2do y 4to cantidadEnHileraDos) y devuelve la cantidadEnHilera más grande
     * @throws Exception
     */
    private int victoriaEjeYZ(int x, int y, int z) throws Exception {
        // Revisa linea y+ z+ / y- z-   (PRIMER CUADRANTE, TERCER CUADRANTE)
        int cantidadEnHileraUno = 1; // Empieza en 1 xq se considera la propia casilla
        Casillero fichaColocada = this.getFicha(x, y, z);
        Casillero fichaAuxiliar = fichaColocada;
        cantidadEnHileraUno = auxiliarVictoria(1, 0, 0, cantidadEnHileraUno, fichaColocada, fichaAuxiliar);
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        cantidadEnHileraUno = auxiliarVictoria(1, 2, 2, cantidadEnHileraUno, fichaColocada, fichaAuxiliar);
        
        // Revisa linea y+ z- / y- z+  (CUARTO CUADRANTE, SEGUNDO CUADRANTE)
        int cantidadEnHileraDos = 1; // Empieza en 1 xq se considera la propia casilla
        fichaAuxiliar = fichaColocada;
        cantidadEnHileraDos = auxiliarVictoria(1, 0, 2, cantidadEnHileraDos, fichaColocada, fichaAuxiliar);
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        cantidadEnHileraDos = auxiliarVictoria(1, 2, 0, cantidadEnHileraDos, fichaColocada, fichaAuxiliar);
        
        return Math.max(cantidadEnHileraUno, cantidadEnHileraDos); // Devuelve el mas alto de ambos
    }

    /**
     * pre: recibe la posicion (x,y,z), post: -
     * 
     * @param x, @param y, @param z: No puede ser < 0
     * @return revisa los x,y,z y devuelve el mayor entre cantidadEnHileraUno, cantidadEnHileraDos,
     * cantidadEnHileraTres
     * @throws Exception
     */
    private int victoriaEjeXYZ(int x, int y, int z) throws Exception {
        // Revisa linea x+ y+ z+ / x- y- z-
        int cantidadEnHileraUno = 1; // Empieza en 1 xq se considera la propia casilla
        Casillero fichaColocada = this.getFicha(x, y, z);
        Casillero fichaAuxiliar = fichaColocada;
        cantidadEnHileraUno = auxiliarVictoria(0, 0, 0, cantidadEnHileraUno, fichaColocada, fichaAuxiliar);
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        cantidadEnHileraUno = auxiliarVictoria(2, 2, 2, cantidadEnHileraUno, fichaColocada, fichaAuxiliar);
    
        // Revisa linea x- y+ z+ / x+ y- z-
        int cantidadEnHileraDos = 1; // Empieza en 1 xq se considera la propia casilla
        fichaAuxiliar = fichaColocada;
        cantidadEnHileraDos = auxiliarVictoria(0, 2, 2, cantidadEnHileraDos, fichaColocada, fichaAuxiliar);
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        
        // Revisa linea x+ y- z+ / x- y+ z-
        int cantidadEnHileraTres = 1; // Empieza en 1 xq se considera la propia casilla
        fichaAuxiliar = fichaColocada;
        cantidadEnHileraTres = auxiliarVictoria(2, 0, 2, cantidadEnHileraTres, fichaColocada, fichaAuxiliar);
        fichaAuxiliar = fichaColocada; // Reiniciamos aux
        cantidadEnHileraTres = auxiliarVictoria(0, 2, 0, cantidadEnHileraTres, fichaColocada, fichaAuxiliar);

        return Math.max(Math.max(cantidadEnHileraUno, cantidadEnHileraDos), cantidadEnHileraTres); // Devuelve el mas alto de los tres
    }

    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    /**
     * pre: recibe la posición y la verifica, post: -
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
     * pre: debe existir la ficha, post: -
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
