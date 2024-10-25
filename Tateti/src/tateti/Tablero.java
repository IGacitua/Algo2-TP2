package tateti;

import java.util.Arrays;
import utilidades.Herramientas;
import utilidades.Lista;

public class Tablero {

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    private final String RUTA_IMAGENES = "Tp-2/Tateti/src/imagenes/"; // Porque ruta relativa depende de donde ejecutes el programa
    private final int TAMAÑO_IMAGENES = 8; // Dimensiones de las imagenes. Deben ser cuadradas
    private final int COLOR_BORDES = (64 << 16) | (64 << 8) | 64; // El color de los bordes. Separado en R, G, B
    
    //ATRIBUTOS --------------------------------------------- ------------------------------------------------
    private Lista<Lista<Lista<Casillero>>> casilleros = null;
    private int tamañoX;
    private int tamañoY;
    private int tamañoZ;
    private int condicionVictoria; // Cuantas fichas en hilera debe haber para ganar

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * pre: -, post: Crea el tablero
     * @param tamañoX, @param tamaño, @param tamañoZ: Debe estar entre 3 y 100 no inclusivo
     * @throws Exception
     */
    public Tablero(int tamañoX, int tamañoY, int tamañoZ) throws Exception {
        if ((tamañoX < 3) || (tamañoY < 3) || (tamañoZ < 3)) {
            throw new Exception("Los tamaños del tablero deben ser mayores o iguales a 3.");
        }
        if ((tamañoX != tamañoY) || (tamañoY != tamañoZ)) {
            throw new Exception("El tablero debe ser cuadrado.");
        }
        if (tamañoX > 99) {
            throw new Exception("Los tamaños del tablero deben ser menores a 100");
        }
        this.tamañoX = tamañoX;
        this.tamañoY = tamañoY;
        this.tamañoZ = tamañoZ;
        this.condicionVictoria = this.tamañoX;
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
        establecerEntornos();
    }

    /**
     * pre: -, post: Establece el entorno de cada ficha del tablero.
     * El entorno es un array con todas las fichas directamente adyacentes.
     * @throws Exception
     */
    private void establecerEntornos() throws Exception {
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
     * pre: -, post: Imprime el tablero por pantalla
     * @throws Exception
     */
    public void imprimir() throws Exception {
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

    /** TODO: pre-post
     * pre:
     * @param RUTA_EXPORTAR: 
     * @throws Exception
     */
    public void exportar(String RUTA_EXPORTAR) throws Exception {
        Imagen imagenFinal = null;
        for (int z = 0; z < this.tamañoZ; z++) {
            // For loop que crea cada capa
            Imagen imagenAuxiliar = crearCapa(z);
            if (imagenFinal == null) {
                // Primer capa
                imagenFinal = imagenAuxiliar;
            } else {
                // Añade las otras capas abajo de la primera
                imagenFinal = imagenFinal.añadirImagenAbajo(imagenAuxiliar);
            }
        }
        if (imagenFinal == null) { // No debería pasar NUNCA, pero da warning igual
            throw new Exception("No se pudo exportar el tablero.");
        }
        imagenFinal.exportar(RUTA_EXPORTAR); // Crea el archivo .bmp de la imagen
    }


    /** TODO pre-post
     * pre:
     * @param z:
     * @return devuelve
     * @throws Exception
     */
    private Imagen crearCapa(int z) throws Exception {
        Imagen imagenCapa = null;
        for (int x = 0; x <= this.tamañoX; x++) {
            // For loop de cada columna
            // MenorIgual porque hay una columna extra
            Imagen imagenAuxiliar = crearColumna(x, z);
            if (imagenCapa == null) {
                // Si es la primer columna
                imagenCapa = imagenAuxiliar;
            } else {
                // Añade el resto de las columnas a la derecha de la primera
                imagenCapa = imagenCapa.añadirImagenDerecha(imagenAuxiliar);
            }
        }
        if (imagenCapa == null) {
            // De nuevo, no debería suceder nunca, pero da warning igual.
            throw new Exception("No se pudo crear la imagen de la capa " + z);
        }
        if (z == this.tamañoZ - 1) {
            // Si es la última capa, no añade nada abajo
            return imagenCapa;
        } else {
            // Añade una capa en blanco abajo de la capa si no es la última
            return imagenCapa.añadirImagenAbajo(new Imagen((this.tamañoX + 1) * (TAMAÑO_IMAGENES * 2 + 2), TAMAÑO_IMAGENES));
            /**
             * Calculo de tamaño:
             *
             * this.tamañoX + 1 = Cada columna + La columna de numeros
             *
             * TAMAÑO_IMAGENES*2 = Los casilleros son el doble de ancho y alto
             * que las imagenes numericas
             *
             * (TAMAÑO_IMAGENES *2) + 2 = El tamaño de cada casillero + los
             * bordes
             */
        }
    }

    /** TODO: pre-post
     * pre:
     * @param x, @param z:
     * @return devuelve 
     * @throws Exception
     */
    private Imagen crearColumna(int x, int z) throws Exception {
        Imagen imagenColumna = null;
        for (int y = 0; y <= this.tamañoY; y++) {
            // <= porque hay una fila extra
            Imagen imagenAuxiliar = crearCasillero(x, y, z);
            if (imagenColumna == null) {
                imagenColumna = imagenAuxiliar;
            } else {
                imagenColumna = imagenColumna.añadirImagenAbajo(imagenAuxiliar);
            }
        }
        if (imagenColumna == null) {
            throw new Exception("No se pudo crear la imagen de la columna " + x + "de la capa " + z);
        }
        return imagenColumna;
    }

    /** TODO: pre-post
     * pre: 
     * @param x, @param y, @param z:
     * @return devuelve
     * @throws Exception
     */
    private Imagen crearCasillero(int x, int y, int z) throws Exception {
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
                imagenCasillero.recolorizar(jugadorEnCasillero.getColor());
            }
        }
        imagenCasillero.bordear(1, COLOR_BORDES); // Darle borde a la imagen
        return imagenCasillero;
    }

    /**
     * pre: recibe la posicion (x,y,z) y el jugador para colocar la ficha post:
     * coloca la ficha en el casillero
     * post: devuelve un boolean correspondiente a si se ganó o no.
     * @param x, @param y, @param z: No puede ser < 0 (verificarCasillero())
     * @param jugador: no debe ser nulo
     * @retrurn devuelve si la jugada de dicho jugador es una victoria o no
     * @throws Exception
     */
    public boolean colocarFicha(int x, int y, int z, Jugador jugador) throws Exception {
        verificarValidezCasillero(x, y, z);
        if (this.getCasillero(x, y, z).isBloqueado()) {
            throw new Exception("No se puede colocar una ficha en una casilla que está bloqueada.");
        }
        if (jugador == null) {
            throw new Exception("El jugador que colocará la ficha no puede ser nulo.");
        }
        getCasillero(x, y, z).setJugador(jugador);
        return revisarVictoria(x, y, z);
    }

    /**
     * pre: (x,y,z) deben ser válidos y debe existir ubicacionOriginal post:
     * mueve la ficha ya colocada
     * post: devuelve un boolean correspondiente a si se ganó o no.
     * @param x, @param y, @param z: no puede ser < 0 (verificarCasillero())
     * @param ubicacionOriginal: debe haber una ficha en dicha ubicación
     * @return devuelve si al mover la ficha se ganó o no
     * @throws Exception
     */
    public boolean moverFicha(int x, int y, int z, Casillero ubicacionOriginal) throws Exception {
        verificarValidezCasillero(x, y, z);
        if (this.getCasillero(x, y, z).isBloqueado()) {
            throw new Exception("No se puede mover una ficha a una casilla que está bloqueada.");
        }
        if (ubicacionOriginal.estaVacio()) {
            throw new Exception("No se puede mover una ficha que no está en la ubicación original.");
        }
        if (ubicacionOriginal.isBloqueado()) {
        	throw new Exception("No se puede mover una ficha que se encuentra bloqueada en un casillero.");
        }
        
        Casillero casilleroDestino = this.getCasillero(x, y, z);
        //verifica si está vacío el casillero destino y si es adyacente
        if (ubicacionOriginal.esAdyacente(casilleroDestino) && casilleroDestino.estaVacio()) {
            casilleroDestino.setJugador(ubicacionOriginal.getJugador());
            ubicacionOriginal.quitarJugador();

            //si no es adyacente, lanza una excepción:
        } else if (!ubicacionOriginal.esAdyacente(casilleroDestino)) {
            throw new Exception("Las casillas indicadas " + Arrays.toString(ubicacionOriginal.getCoordenadas())
                    + " (ubicación original) y " + Arrays.toString(casilleroDestino.getCoordenadas()) + " (ubicación de destino) no son adyacentes");

            //si está vacío, lanza una excepción
        } else if (!casilleroDestino.estaVacio()) {
            throw new Exception("El casillero " + Arrays.toString(casilleroDestino.getCoordenadas()) + " (ubicacion de destino) ya tiene una ficha colocada.");
        }
        return revisarVictoria(x, y, z);
    }

    /**
     * pre: recibe la posicion (x,y,z), que es donde se colocó una ficha
     * post: devuelve un boolean correspondiente a si se ganó o no
     * @param x, @param y, @param z: No puede ser < 0
     * @return devuelve un boolean dependiendo de si la jugada fue de victoria
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
     * pre: los desplazamientos ingresados deben ser válidos y la ficha también
     * post: devuelve la cantidad de fichas en hilera
     * @param desplazamientoX, @param desplazamientoY, @param desplazamientoZ
     * @param fichaColocada: no puede ser nula
     * @return devuelve la cantidad de fichas en hilera
     * @throws Exception
     */
    private int auxiliarVictoria(int desplazamientoX, int desplazamientoY, int desplazamientoZ, Casillero fichaColocada) throws Exception {
        int cantidadEnHilera = 0; // Empieza en 1 porque se considera la propia ficha
        if (fichaColocada == null) {
            throw new Exception("La ficha colocada no puede ser nula");
        }
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
     * @param x, @param y, @param z: no puede ser < 0
     * @throws Exception
     */
    private void verificarValidezCasillero(int x, int y, int z) throws Exception {
        if ((!Herramientas.validarNumeroPositivo(x)) || (!Herramientas.validarNumeroPositivo(y))
                || (!Herramientas.validarNumeroPositivo(z))) {
            throw new Exception("La posición del casillero debe ser válida.");
        }
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * pre: debe existir la ficha
     * post: devuelve el casillero con las posiciones:
     * [x][y][z]
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
