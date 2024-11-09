package tateti;

import utilidades.Herramientas;
import utilidades.Lista;

public class Tablero {

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    private final String RUTA_IMAGENES = "Tp-2/Tateti/src/imagenes/"; // Porque ruta relativa depende de donde ejecutes el programa
    private final int TAMAÑO_IMAGENES = 8; // Dimensiones en pixeles de las imagenes. Deben ser cuadradas.
    private final int COLOR_BORDES = (64 << 16) | (64 << 8) | 64; // El color de los bordes. Separado en R | G | B

    //ATRIBUTOS --------------------------------------------- ------------------------------------------------
    private Lista<Lista<Lista<Casillero>>> casilleros = null; // Matriz tridimensional con punteros a todos los casilleros
    private int tamaño;
    private int condicionVictoria; // Cuantas fichas en hilera debe haber para ganar

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * pre: Recibe el tamaño, post: Crea el tablero de tamaño cuadrado, con
     * condición de victoria igual al tamaño.
     *
     * @param tamaño: Debe estar entre 3 y 100 (no inclusive).
     * @throws Exception: Si el tamaño no es válido.
     */
    public Tablero(int tamaño) throws Exception {
        if (tamaño < 3) {
            throw new Exception("El tamaño del tablero debe ser mayor o igual a 3. (El ingresado fue " + tamaño + ")");
        }
        if (tamaño > 99) {
            throw new Exception("El tamaño del tablero debe ser menor a 100. (El ingresado fue " + tamaño + ")");
        }
        this.tamaño = tamaño;
        this.condicionVictoria = tamaño;

        this.casilleros = new Lista<>(); // Nota: No es necesario poner nada entre <> luego del =
        // Crea todos los casilleros y los añade a la lista
        for (int x = 0; x < tamaño; x++) {
            Lista<Lista<Casillero>> fila = new Lista<>();
            for (int y = 0; y < tamaño; y++) {
                Lista<Casillero> columna = new Lista<>();
                for (int z = 0; z < tamaño; z++) {
                    columna.agregarElemento(new Casillero(x, y, z));
                }
                fila.agregarElemento(columna);
            }
            casilleros.agregarElemento(fila);
        }
        establecerEntornos(); // Establece los entornos de todos los casilleros.
    }

    /**
     * pre: -, post: Establece el entorno de cada ficha del tablero. El entorno
     * es un array con todas las fichas directamente adyacentes.
     */
    private void establecerEntornos() {
        for (int x = 0; x < this.tamaño; x++) {
            for (int y = 0; y < this.tamaño; y++) {
                for (int z = 0; z < this.tamaño; z++) {
                    // Al ser llamado dentro de un loop, la excepción de getCasillero() no debería suceder nunca
                    try {
                        this.getCasillero(x, y, z).establecerEntorno(this);
                    } catch (Exception e) {
                        System.out.println("Excepción impossible al establecer entornos. Revisar codigo.");
                    }
                }
            }
        }
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    /**
     * pre: -, post: Imprime el tablero por consola.
     */
    public void imprimir() {
        for (int z = 0; z < this.tamaño; z++) {
            System.out.printf("\n");
            for (int y = 0; y < this.tamaño; y++) {
                System.out.printf("\n");
                for (int x = 0; x < this.tamaño; x++) {
                    // Al ser llamado dentro de un loop, la excepción de getCasillero() no debería suceder nunca
                    try {
                        Jugador jugadorActual = getCasillero(x, y, z).getJugador();
                        if (jugadorActual != null) {
                            System.out.printf("%s%c ", jugadorActual.getColor().getPrintfColor(), jugadorActual.getFichaCaracter());
                            Herramientas.reiniciarColor();
                        } else {
                            System.out.printf("- ");
                        }
                    } catch (Exception e) {
                        System.out.println("Excepción imposible al imprimir Tablero. Revisar codigo.");
                    }
                }
            }
        }
        System.out.printf("\n");
    }

    /**
     * pre: Recibe el nombre del archivo a exportar, post: Crea un archivo .bmp
     * con el nombre dado, mostrando el estado del tablero.
     *
     * @param destino: No debe ser nulo.
     * @throws Exception: Si el destino es nulo.
     */
    public void exportar(String destino) throws Exception {
        if ((destino == null) || (destino.trim().isEmpty())) {
            throw new Exception("El archivo debe tener nombre.");
        }
        Imagen imagenFinal = null;
        for (int z = 0; z < this.tamaño; z++) {
            // For loop que crea cada capa
            Imagen imagenAuxiliar = crearCapa(z);
            if (imagenFinal == null) {
                // Primer capa
                imagenFinal = imagenAuxiliar;
            } else {
                // Añade las otras capas abajo de la primera
                try {
                    imagenFinal = imagenFinal.añadirImagenAbajo(imagenAuxiliar);
                } catch (Exception e) {
                    throw new Exception("Excepción imposible al crear Imagen. Revisar exportar()");
                }
            }
        }
        if (imagenFinal == null) {
            // No debería pasar NUNCA, pero da warning igual
            throw new Exception("No se pudo exportar el tablero. Revisar exportar()");
        }
        imagenFinal.exportar(destino); // Crea el archivo .bmp de la imagen
    }

    /**
     * pre: Recibe la posición Z de la capa, post: Crea una Imagen dependiendo
     * del contenido de la capa.
     *
     * @param z: Debe estar contenido en el Tablero.
     * @return Devuelve la imagen creada.
     */
    private Imagen crearCapa(int z) {
        try {
            Imagen imagenCapa = null;
            for (int x = 0; x <= this.tamaño; x++) {
                // For loop de cada columna
                // <= porque hay una columna extra
                Imagen imagenAuxiliar = crearColumna(x, z);
                if (imagenCapa == null) {
                    // Si es la primer columna
                    imagenCapa = imagenAuxiliar;
                } else {
                    // Añade el resto de las columnas a la derecha de la primera
                    imagenCapa = imagenCapa.añadirImagenDerecha(imagenAuxiliar);
                }
            }
            if (z == this.tamaño - 1) {
                // Si es la última capa, no añade nada abajo
                return imagenCapa;
            } else {
                // Añade una capa en blanco abajo de la capa si no es la última
                /**
                 * Calculo de tamaño final:
                 *
                 * this.tamaño + 1 = Cada columna + La columna de numeros
                 *
                 * TAMAÑO_IMAGENES*2 = Los casilleros son el doble de ancho y
                 * alto que las imagenes numericas
                 *
                 * (TAMAÑO_IMAGENES *2) + 2 = El tamaño de cada casillero + los
                 * bordes
                 */
                if (imagenCapa != null) {
                    return imagenCapa.añadirImagenAbajo(new Imagen((this.tamaño + 1) * (TAMAÑO_IMAGENES * 2 + 2), TAMAÑO_IMAGENES));
                }
            }
        } catch (Exception e) {
            System.out.println("Excepción imposible al crear Imagen. Revisar crearCapa()");

        }
        return null; // No debería llegarse NUNCA a este caso, pero da warnings igual
    }

    /**
     * pre: Recibe la posición X Z de la columna, post: Crea una Imagen
     * dependiendo del contenido de la columna.
     *
     * @param x: Debe estar contenido en el Tablero.
     * @param z: Debe estar contenido en el Tablero.
     * @return Devuelve la imagen creada.
     */
    private Imagen crearColumna(int x, int z) {
        Imagen imagenColumna = null;
        for (int y = 0; y <= this.tamaño; y++) {
            // <= porque hay una fila extra
            Imagen imagenAuxiliar = crearCasillero(x, y, z);
            if (imagenColumna == null) {
                imagenColumna = imagenAuxiliar;
            } else {
                // añadirImagenAbajo() puede dar excepciones.
                // Las excepciones no deberían suceder nunca en esta función.
                try {
                    imagenColumna = imagenColumna.añadirImagenAbajo(imagenAuxiliar);
                } catch (Exception e) {
                    System.out.println("Excepción imposible al crear Imagen. Revisar crearColumna()");
                }
            }
        }
        return imagenColumna;
    }

    /**
     * pre: Recibe la posición X Y Z del casillero, post: Crea una Imagen
     * dependiendo del contenido del casillero.
     *
     * @param x: Debe estar contenido en el Tablero.
     * @param y: Debe estar contenido en el Tablero.
     * @param z: Debe estar contenido en el Tablero.
     * @return Devuelve la imagen creada.
     */
    private Imagen crearCasillero(int x, int y, int z) {
        // El constructor de Imagen puede dar excepciones. 
        // Las excepciones no pueden suceder nunca en esta función.
        // TODO Constructor en Imagen en base a casillero para simplificar
        try {
            Imagen imagenCasillero;
            if (x == 0 && y == 0) {
                // Caso esquina superior izquierda. Casilla vacía
                imagenCasillero = new Imagen(this.TAMAÑO_IMAGENES * 2, this.TAMAÑO_IMAGENES * 2);
            } else if (x == 0 && y != 0) {
                // Caso primera columna. Numero de fila
                Imagen digitoMayor = new Imagen(RUTA_IMAGENES + "number_" + Herramientas.devolverDigito(y, 1) + ".bmp");
                Imagen digitoMenor = new Imagen(RUTA_IMAGENES + "number_" + Herramientas.devolverDigito(y, 0) + ".bmp");
                Imagen numeroCompleto = digitoMayor.añadirImagenDerecha(digitoMenor);
                imagenCasillero = numeroCompleto.añadirImagenAbajo(new Imagen(TAMAÑO_IMAGENES * 2, TAMAÑO_IMAGENES));
            } else if (x != 0 && y == 0) {
                // Caso primera fila. Numero de columna
                Imagen digitoMayor = new Imagen(RUTA_IMAGENES + "number_" + Herramientas.devolverDigito(x, 1) + ".bmp");
                Imagen digitoMenor = new Imagen(RUTA_IMAGENES + "number_" + Herramientas.devolverDigito(x, 0) + ".bmp");
                Imagen numeroCompleto = digitoMayor.añadirImagenDerecha(digitoMenor);
                imagenCasillero = numeroCompleto.añadirImagenAbajo(new Imagen(TAMAÑO_IMAGENES * 2, TAMAÑO_IMAGENES));
            } else {
                // Caso casillero normal. Ficha del usuario.
                Jugador jugadorEnCasillero = this.getCasillero(x - 1, y - 1, z).getJugador(); // X e Y son -1 porque hay una fila y columna extra
                if (jugadorEnCasillero == null) {
                    // Caso: Casillero no tiene usuario (Casillero vacío)
                    imagenCasillero = new Imagen(this.TAMAÑO_IMAGENES * 2, this.TAMAÑO_IMAGENES * 2);
                } else {
                    Fichas fichaJugador = jugadorEnCasillero.getFichaImagen();
                    imagenCasillero = new Imagen(RUTA_IMAGENES + "shape_" + fichaJugador.ordinal() + ".bmp");
                    imagenCasillero.recolorizar(jugadorEnCasillero.getColor().getRGB());
                }
            }
            imagenCasillero.bordear(1, COLOR_BORDES); // Darle borde a la imagen
            return imagenCasillero;
        } catch (Exception e) {
            System.out.println("Excepción imposible al crear Imagen. Revisar crearCasillero()");
            return null;
        }

    }

    /**
     * pre: Recibe una posición X Y Z y un Jugador, post: Coloca la ficha en la
     * posicion indicada si no hay una todavía. O la elimina si el jugador es
     * nulo y ya existe una ficha.
     *
     * @param x: Debe estar contenida dentro del tablero.
     * @param y: Debe estar contenida dentro del tablero.
     * @param z: Debe estar contenida dentro del tablero.
     * @param jugador: Puede ser nulo.
     *
     * @return Devuelve si el movimiento resulta en una victoria.
     *
     * @throws Exception: Si la posición dada es inválida.
     * @throws Exception: Si el casillero está bloqueado.
     * @throws Exception: Si el jugador no es nulo y la casilla ya tiene una
     * ficha.
     * @throws Exception: Si el jugador es nulo y la casilla no tiene una ficha.
     */
    public boolean colocarFicha(int x, int y, int z, Jugador jugador) throws Exception {
        validarCasillero(x, y, z);
        if (this.getCasillero(x, y, z).isBloqueado()) {
            throw new Exception("No se puede colocar una ficha en una casilla que está bloqueada.");
        }
        getCasillero(x, y, z).setJugador(jugador);
        return revisarVictoria(x, y, z);
    }

    /**
     * pre: Recibe una ubicación X Y Z destino, y un puntero al casillero
     * original, post: Mueve la ficha del casillero original a la posición
     * adyacente dada.
     *
     * @param x: Debe estar contenido en el tablero.
     * @param y: Debe estar contenido en el tablero.
     * @param z: Debe estar contenido en el tablero.
     * @param casilleroOriginal: Debe estar contenido en el tablero, y poseer
     * una ficha.
     *
     * @return Devuelve si el movimiento resulta en una victoria.
     *
     * @throws Exception: Si alguno de los dos Casilleros no están contenidos en
     * el tablero.
     * @throws Exception: Si la ubicación destino ya posee una ficha.
     * @throws Exception: Si la ubicación original no posee una ficha.
     * @throws Exception: Si las ubicaciones no son adyacentes.
     */
    public boolean moverFicha(int x, int y, int z, Casillero casilleroOriginal) throws Exception {
        validarCasillero(x, y, z);
        casilleroExiste(casilleroOriginal);
        Casillero casilleroDestino = this.getCasillero(x, y, z);
        if (casilleroDestino.isBloqueado()) {
            throw new Exception("La casilla destino está bloqueada.");
        }
        if (casilleroOriginal.isBloqueado()) {
            throw new Exception("La casilla original está bloqueada.");
        }
        if (casilleroOriginal.estaVacio()) {
            throw new Exception("La casilla original no posee una ficha.");
        }
        if (!casilleroDestino.estaVacio()) {
            throw new Exception("La casilla destino ya posee una ficha.");
        }
        if (!casilleroOriginal.esAdyacente(this.getCasillero(x, y, z))) {
            throw new Exception("Los casilleros dados no son adyacentes.");
        }
        casilleroDestino.setJugador(casilleroOriginal.getJugador());
        casilleroOriginal.setJugador(null); // Set jugador null -> Eliminar jugador
        return revisarVictoria(x, y, z);
    }

    /**
     * pre: Recibe una ubicación X Y Z, post: Revisa si la ficha en esa
     * ubicación es parte de una linea que resulta en victoria.
     *
     * @param x: Debe estar contenido en el tablero.
     * @param y: Debe estar contenido en el tablero.
     * @param z: Debe estar contenido en el tablero.
     *
     * @return Devuelve un boolean indicando si la ficha es parte de una
     * victoria o no.
     * @throws Exception: Si la ubicación dada no es parte del tablero.
     */
    private boolean revisarVictoria(int posicionX, int posicionY, int posicionZ) throws Exception {
        // getCasillero() se encarga de validar la ubicación dada
        Casillero fichaColocada = this.getCasillero(posicionX, posicionY, posicionZ);
        // Los iteradores empiezan en -1 para ayudar legibilidad
        // Son el desplazamiento en base al casillero
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == y && y == z && z == 0) {
                        continue; // Saltea el desplazamiento nulo para evitar bucle infinito
                    }
                    if (auxiliarVictoria(x, y, z, fichaColocada) + auxiliarVictoria(-x, -y, -z, fichaColocada) >= this.condicionVictoria - 1) {
                        // Se le resta 1 a la condicion de victoria porque los auxiliares no consideran la propia ficha
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * pre: Recibe un desplazamiento separado en X Y Z y un casillero que
     * contiene una ficha, post: Revisa la cantidad de fichas en linea en la
     * dirección dada.
     *
     * @param desplazamientoX: Debe ser -1, 0, o 1.
     * @param desplazamientoY: Debe ser -1, 0, o 1.
     * @param desplazamientoZ: Debe ser -1, 0, o 1.
     *
     * @param casilleroModificado: No puede ser nula. No puede ser un casillero
     * vacio.
     *
     * @return Devuelve la cantidad de fichas en hilera.
     * @throws Exception: Si el casillero dado es nulo o no contiene una ficha.
     */
    private int auxiliarVictoria(int desplazamientoX, int desplazamientoY, int desplazamientoZ, Casillero casilleroModificado) throws Exception {
        int cantidadEnHilera = 0; // Empieza en 1 porque se considera la propia ficha
        if (casilleroModificado == null) {
            throw new Exception("La ficha colocada no puede ser nula");
        }
        if (casilleroModificado.getJugador() == null) {
            throw new Exception("El casillero dado no contiene ninguna ficha.");
        }
        Casillero fichaAuxiliar = casilleroModificado;
        while ((fichaAuxiliar = fichaAuxiliar.getEntorno()[desplazamientoX + 1][desplazamientoY + 1][desplazamientoZ + 1]) != null) {
            if (fichaAuxiliar.getIdentificacionDeJugador() == casilleroModificado.getIdentificacionDeJugador()) {
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
     * pre: Recibe una ubicación XYZ, post: Verifica que esté contenida en el
     * tablero.
     *
     * @throws Exception: Si la ubicación dada no está contenida en el tablero.
     */
    private void validarCasillero(int x, int y, int z) throws Exception {
        if ((!Herramientas.validarNumeroPositivo(x)) || (!Herramientas.validarNumeroPositivo(y))
                || (!Herramientas.validarNumeroPositivo(z))) {
            throw new Exception("La posición del casillero no es válida.");
        }
        if (x > this.tamaño || y > this.tamaño || z > this.tamaño) {
            throw new Exception("La posición dada no está contenida en el tablero.");
        }
    }

    /**
     * pre: Recibe un Casillero
     *
     * post: Verifica que esté contenido en el tablero.
     *
     * @return Devuelve un boolean determinando si está o no contenido en el
     * tablero.
     * @throws Exception: Si la ubicación dada no está contenida en el tablero.
     */
    public boolean casilleroExiste(Casillero casillero) throws Exception {
        for (int x = 0; x < this.tamaño; x++) {
            for (int y = 0; y < this.tamaño; y++) {
                for (int z = 0; z < this.tamaño; z++) {
                    if (casillero == this.getCasillero(x, y, z)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * pre: Recibe una ubicación X Y Z, post: -
     *
     * @param x: Debe estar contenido en el tablero.
     * @param y: Debe estar contenido en el tablero.
     * @param z: Debe estar contenido en el tablero.
     *
     * @return Devuelve un puntero al casillero en esa ubicación.
     * @throws Exception: Si la ubicación dada no está contenida en el tablero.
     */
    public final Casillero getCasillero(int x, int y, int z) throws Exception {
        validarCasillero(x + 1, y + 1, z + 1); // Listas son index 1
        return this.casilleros.obtenerDato(x + 1).obtenerDato(y + 1).obtenerDato(z + 1);
    }

    /**
     * @return El tamaño de cada eje del tablero
     */
    public int getTamaño() {
        return tamaño;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}
