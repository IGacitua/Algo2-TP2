
package tateti;


public class Tablero {

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    private final String RUTA_IMAGENES = "Tp-2/Tateti/src/imagenes/"; // Porque ruta relativa depende de DONDE ejecutes el programa
    private final int TAMAÑO_IMAGENES = 8; // Dimensiones de las imagenes. Deben ser cuadradas
    private final int COLOR_BORDES = (64 << 16) | (64 << 8) | 64; // El color de los bordes. Separado en R, G, B
    //ATRIBUTOS --------------------------------------------- ------------------------------------------------
    private Lista<Lista<Lista<Casillero>>> casilleros = null;
    private int tamañoX;
    private int tamañoY;
    private int tamañoZ;
    private int condicionVictoria;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * pre: -, post: crea el tablero
     *
     * @param tamañoX: Debe estar entre 0 y 100, no inclusivo
     * @param tamañoY: Debe estar entre 0 y 100, no inclusivo
     * @param tamañoZ: Debe estar entre 0 y 100, no inclusivo
     * @param condicionVictoria: Debe ser mayor a 0
     * @throws Exception
     */
    public Tablero(int tamañoX, int tamañoY, int tamañoZ, int condicionVictoria) throws Exception {
        if ((!Herramientas.validarNumeroPositivoEstricto(tamañoX))
                || (!Herramientas.validarNumeroPositivoEstricto(tamañoY))
                || (!Herramientas.validarNumeroPositivoEstricto(tamañoZ))
                || (!Herramientas.validarNumeroPositivoEstricto(condicionVictoria))) {
            throw new Exception("Los tamaños del tablero y la condición de victoria deben ser mayores a 0.");
        }
        if (tamañoX > 99 || tamañoY > 99 || tamañoZ > 99) {
            throw new Exception("Los tamaños del tablero deben ser menores a 100");
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
                    this.getCasillero(x, y, z).establecerEntorno(this);
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
                    System.out.printf("%2d ", getCasillero(x, y, z).getIdentificacionDeJugador());
                }
            }
        }
        System.out.printf("\n");
    }

    //TODO pre-post
    //TODO solo exporta una capa
    //TODO darle parametro PATH
    public void exportar() throws Exception {
        Imagen imagenPrincipal = null;
        Imagen imagenAuxiliar = null;
        // X + 2 | Y +2 | Debido a los bordes numerados
        for (int x = 0; x < this.tamañoX + 1; x++) {
            if (x == 0) {
                // Caso: Esquina en blanco
                // TODO: cambiar a 0 y poner todo +1?
                imagenPrincipal = new Imagen(TAMAÑO_IMAGENES * 2, TAMAÑO_IMAGENES);
                imagenPrincipal.bordear(1, COLOR_BORDES);
            } else {
                imagenAuxiliar = null;
            }
            for (int y = 0; y < this.tamañoY + 1; y++) {
                if ((x == 0 && y != 0) && imagenPrincipal != null) {
                    // Caso: Primera columna
                    Imagen digitoUno = new Imagen(RUTA_IMAGENES + "number_" + Herramientas.devolverDigito(y, 1) + ".bmp");
                    Imagen digitoDos = new Imagen(RUTA_IMAGENES + "number_" + Herramientas.devolverDigito(y, 0) + ".bmp");
                    Imagen numeroCompleto = digitoUno.añadirImagenDerecha(digitoDos);
                    numeroCompleto.bordear(1, COLOR_BORDES);
                    imagenPrincipal = imagenPrincipal.añadirImagenAbajo(numeroCompleto);
                } else if (x != 0 && y == 0) {
                    // Caso: Primera fila
                    Imagen digitoUno = new Imagen(RUTA_IMAGENES + "number_" + Herramientas.devolverDigito(x, 1) + ".bmp");
                    Imagen digitoDos = new Imagen(RUTA_IMAGENES + "number_" + Herramientas.devolverDigito(x, 0) + ".bmp");
                    Imagen numeroCompleto = digitoUno.añadirImagenDerecha(digitoDos);
                    numeroCompleto.bordear(1, COLOR_BORDES);
                    if (imagenAuxiliar == null) {
                        imagenAuxiliar = numeroCompleto;
                    } else {
                        imagenAuxiliar = imagenAuxiliar.añadirImagenAbajo(numeroCompleto);
                    }
                } else if (x != 0 && y != 0) {
                    // Caso: Interior (Tablero)
                    // TODO: Las imagenes del interior son 8x8 en vez de 16x8
                    // TODO: Solucion: Cambiar fichas a 16x16, y aumentar el tamaño de las filas luego de la primera
                    Imagen imagenTemporal = new Imagen(TAMAÑO_IMAGENES * 2, TAMAÑO_IMAGENES); //TODO remplazar
                    imagenTemporal.bordear(1, COLOR_BORDES);
                    if (imagenAuxiliar != null) {
                        imagenAuxiliar = imagenAuxiliar.añadirImagenAbajo(imagenTemporal);
                    } else {
                        imagenAuxiliar = imagenTemporal;
                    }
                }
            }
            if (x != 0 && imagenPrincipal != null && imagenAuxiliar != null) {
                imagenPrincipal = imagenPrincipal.añadirImagenDerecha(imagenAuxiliar);
            } else if (x != 0 && imagenAuxiliar == null) {
                throw new Exception("Imagen auxiliar es null!? Iteracion " + x); // TODO remove, no deberia suceder nunca
            }
        }
        if (imagenPrincipal != null) {
            imagenPrincipal.exportar("TestTableroEntero");
        }
    }

    /**
     * pre: recibe la posicion (x,y,z) y el jugador para colocar la ficha, post:
     * -
     *
     * @param x, @param y, @param z: No puede ser < 0
     * @param jugador: debe existir?? //TODO: cuando esté hecho el jugador vemos
     * @retrurn devuelve si la jugada de dicho jugador es una victoria o no
     * @throws Exception
     */
    public boolean colocarFicha(int x, int y, int z, Jugador jugador) throws Exception {
        verificarValidezCasillero(x, y, z);
        if (this.getCasillero(x, y, z).isBloqueado()) {
            throw new Exception("La casilla está bloqueada.");
        }
        //TODO: validación si el jugador no existe.
        getCasillero(x, y, z).setJugador(jugador);
        return revisarVictoria(x, y, z);
    }

    /**
     * pre: (x,y,z) deben ser válidos y debe existir ubicacionOriginal, post: -
     *
     * @param x, @param y, @param z: no puede ser <0
     * @param ubicacionOriginal: //TODO: validar
     * @return devuelve si al mover la ficha se ganó o no
     * @throws Exception
     */
    public boolean moverFicha(int x, int y, int z, Casillero ubicacionOriginal) throws Exception {
        verificarValidezCasillero(x, y, z);
        if (this.getCasillero(x, y, z).isBloqueado()) {
            throw new Exception("La casilla está bloqueada.");
        }
        Casillero casilleroDestino = this.getCasillero(x, y, z);
        if (ubicacionOriginal.esAdyacente(casilleroDestino) && casilleroDestino.getJugador() == null) {
            System.out.println(casilleroDestino.getJugador());
            casilleroDestino.setJugador(ubicacionOriginal.getJugador());
            ubicacionOriginal.setJugador(null);
        } else if (!ubicacionOriginal.esAdyacente(casilleroDestino)) {
            throw new Exception("Las casillas indicadas no son adyacentes."); // TODO aclarar ubicacion de ambas.
        } else if (casilleroDestino.getJugador() != null) {
            throw new Exception("El casillero de destino ya tiene una ficha colocada."); // TODO aclarar ubicacion de destino
        }
        return revisarVictoria(x, y, z);
    }

    /**
     * pre: recibe la posicion (x,y,z), que es donde se colocó una ficha, post:
     * -
     *
     * @param x, @param y, @param z: No puede ser < 0
     * @return devuelve un booleano dependiendo de si la jugada fue de victoria
     * o no
     * @throws Exception
     */
    private boolean revisarVictoria(int posicionX, int posicionY, int posicionZ) throws Exception {
        Casillero fichaColocada = this.getCasillero(posicionX, posicionY, posicionZ);
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == y && x == z && z == 0) {
                        continue; // Saltea el desplazamiento nulo para evitar bucle infinito
                    }
                    if (auxiliarVictoria(x, y, z, fichaColocada) + auxiliarVictoria(-x, -y, -z, fichaColocada) >= this.condicionVictoria - 1) {
                        // Se le resta 1 a la condicion de victoria porque se considera la propia ficha
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * pre: los desplazamientos ingresados deben ser válidos y la ficha también,
     * post: -
     *
     * @param desplazamientoX, @param desplazamientoY, @param desplazamientoZ:
     * //TODO: validar
     * @param fichaColocada: //TODO: validar
     * @return
     */
    private int auxiliarVictoria(int desplazamientoX, int desplazamientoY, int desplazamientoZ, Casillero fichaColocada) {
        int cantidadEnHilera = 0; // Empieza en 1 porque se considera la propia ficha
        Casillero fichaAuxiliar = fichaColocada;
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[desplazamientoX + 1][desplazamientoY + 1][desplazamientoZ + 1]) != null) {
            if (fichaAuxiliar.getIdentificacionDeJugador() == fichaColocada.getIdentificacionDeJugador()) {
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
     * pre: recibe la posición y la verifica, post: -
     *
     * @param x, @param y, @param z: no puede ser <0
     * @throws Exception
     */
    private void verificarValidezCasillero(int x, int y, int z) throws Exception {
        if ((!Herramientas.validarNumeroPositivo(x)) || (!Herramientas.validarNumeroPositivo(y))
                || (!Herramientas.validarNumeroPositivo(z))) {
            throw new Exception("La posición del casillero debe ser válida.");
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
    public final Casillero getCasillero(int x, int y, int z) throws Exception {
        verificarValidezCasillero(x + 1, y + 1, z + 1); // Listas son index 1
        return this.casilleros.obtenerDato(x + 1).obtenerDato(y + 1).obtenerDato(z + 1);
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}
