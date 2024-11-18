package tateti;

import cartas.Carta;
import java.util.Random;
import utilidades.Lista;
import utilidades.Teclado;

public class Menu {

    //Atributos------------------------------------------------------------------------------------------------------
    // Jugadores/Colores/Fichas son final porque siempre apuntan a las mismas listas
    private final Lista<Jugador> listaJugadores = new Lista<>();
    private final Lista<ColoresDisponibles> coloresTomados = new Lista<>();
    private final Lista<Fichas> fichasTomadas = new Lista<>();
    private boolean victoria = false;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * post: Inicializa el Menu.
     */
    public Menu() {
    }

    //TODO: jugar cartas y ver que funcione bien
    ///MÉTODOS --------------------------------------------------------------------------------------------------
	//TABLERO --------------------------------------------------------------------------------------------
    /**
	 * post: Inicializa el tablero con un input del usuario (Debe estar entre 3 y 99)
     * crea un tablero cubico de lado*lado*lado.
	 * @return Devuelve el tablero inicializado.
	 */
    public Tablero inicializarTablero() throws Exception {
        return new Tablero(Teclado.pedirNumeroEntreIntervalo("Ingrese el tamaño del tablero con el quiere jugar", 3, 99));
    }

    //JUGADORES --------------------------------------------------------------------------------------------
    /**
     * pre: tamañoTablero debe ser válido
     *
     * post: Inicializa los jugadores, la cantidad de jugadores no puede superar
     * el tamaño del tablero y debe ser menor a 8.
     *
     * @param tamañoTablero Debe estar entre 3 y 99 inclusive en ambos casos.
     */
    public void inicializarJugadores(int tamañoTablero) {
        int cantidadJugadores;
        while (true) {
            if (tamañoTablero < 8) {
                cantidadJugadores = Teclado.pedirNumeroEntreIntervalo("Ingrese el número de jugadores", 2, tamañoTablero);
            } else {
                cantidadJugadores = Teclado.pedirNumeroEntreIntervalo("Ingrese el número de jugadores", 2, 8);
            }
            if (cantidadJugadores > tamañoTablero) {
                System.out.println("La cantidad de jugadores debe ser menor o igual al tamaño del tablero.");
            } else {
                break;
            }
        }
        for (int i = 0; i < cantidadJugadores; i++) {
            try {
                String nombre = Teclado.pedirString(("Ingrese el nombre del jugador número " + (i + 1)));
                nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1); //Formatea el nombre con 1er letra mayus
                Jugador jugador = new Jugador(nombre, (int) (tamañoTablero * 1.5), (int) (tamañoTablero * 0.5), solicitarFicha(), solicitarColor(), new Lista<>());
                this.listaJugadores.agregarElemento(jugador);
            } catch (Exception e) {
                // Todo está debidamente validado, asi que no debería generar excepción
                System.out.println("Excepción imposible al crear Jugador");
                System.out.println(e);
            }

        }
    }

    /**
     * post: imprime todos los jugadores de la partida, cada uno con sus datos
     */
    public void imprimirJugadoresPorPantalla() throws Exception {
        this.listaJugadores.iniciarCursor();
        while (this.listaJugadores.avanzarCursor()) {
            Jugador jugador = this.listaJugadores.obtenerCursor();
            System.out.println(" ");
            System.out.println("Nombre: " + jugador.getNombre());
            System.out.println("Identificacion: " + jugador.getIdentificacion());
            System.out.println("Cantidad de fichas: " + jugador.getCantidadDeFichas());
            System.out.println("Cantidad de cartas guardadas: " + jugador.getCantidadDeCartas());
            System.out.println("Cantidad de cartas total: " + jugador.getCartasMaximas());
            System.out.println("Ficha: " + jugador.getFichaCaracter());
            System.out.println("Color: " + jugador.getColor());
        }
    }

    // Solicitar Datos ---------------------------------------------------------------------------------------------
    /**
     * post: Solicita al usuario un color de la lista de colores disponibles.
     *
     * @return Devuelve el color seleccionado.
     * @throws Exception Si coloresTomados es nula o el Enum ColoresDisponibles
     * está vacío.
     */
    public ColoresDisponibles solicitarColor() throws Exception {
        return solicitarElementoDeEnum("Elija el color de la lista que desea utilizar", this.coloresTomados, ColoresDisponibles.values());
    }

    /**
     * post: Solicita al usuario una ficha de la lista de fichas disponibles.
     *
     * @return Devuelve la ficha seleccionada.
     * @throws Exception Si fichasTomadas es nula o el Enum Fichas está vacío.
     */
    public Fichas solicitarFicha() throws Exception {
        return solicitarElementoDeEnum("Elija la ficha de la lista que desea utilizar", this.fichasTomadas, Fichas.values());
    }

    /**
     * pre: Recibe el mensaje con el que pedirle input al usuario, la lista de
     * valores del ENUM, y el array de todos los elementos del ENUM
     *
     * post: Solicita un elemento de un Enum al usuario y lo devuelve.
     *
     * @param <T> Puede ser cualquier tipo de Enum.
     * @param mensaje Puede ser nulo, en cuyo caso se utilizará: "Elija un
     * elemento de la lista que desea utilizar".
     * @param elementosTomados Puede estar vacía pero no ser nula.
     * @param valoresEnum No puede estar vacío ni ser nulo.
     *
     * @return Devuelve el <T> elegido por el usuario.
     * @throws Exception Si elementosTomados es nulo.
     * @throws Exception Si valoresEnum es nulo o está vacío.
     */
    private <T extends Enum<T>> T solicitarElementoDeEnum(String mensaje, Lista<T> elementosTomados, T[] valoresEnum) throws Exception {
        if ((mensaje == null) || (mensaje.trim().isEmpty())) {
            mensaje = "Elija un elemento de la lista que desea utilizar";
        }
        if (elementosTomados == null) {
            throw new Exception("La lista de elementos tomados no puede ser nula.");
        }
        if (valoresEnum.length == 0) {
            // No es necesario verificar NULL, se tirará una excepción si es el caso
            throw new Exception("El Enum no debe ser nulo ni debe estar vacío.");
        }
        String elementosAImprimir = "";
        Lista<T> elementosDisponibles = new Lista<>();
        for (T valor : valoresEnum) {
            // For especializado que itera sobre un array
            if (!elementosTomados.existe(valor)) {
                elementosDisponibles.agregarElemento(valor);
            }
        }

        elementosDisponibles.iniciarCursor();
        while (elementosDisponibles.avanzarCursor()) {
            elementosAImprimir += " " + elementosDisponibles.obtenerCursor().toString();
        }
        mensaje += elementosAImprimir;

        while (true) {
            String opcionElegida = Teclado.pedirString(mensaje).toUpperCase();
            try { //try porque es necesario al pasar de String a Enum
                T elementoSeleccionado = Enum.valueOf(valoresEnum[0].getDeclaringClass(), opcionElegida);
                if (!elementosTomados.existe(elementoSeleccionado)) {
                    elementosTomados.agregarElemento(elementoSeleccionado);
                    return elementoSeleccionado;
                } else {
                    System.out.println("El " + elementoSeleccionado + " ya ha sido tomado, seleccione otro.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("La opción ingresada no está dentro de las opciones, vuelva a intentarlo.");
            }
        }
    }

    //MAZO --------------------------------------------------------------------------------------------
    /**
     * post: Inicializa un mazo con cartas dependiendo de la cantidad de
     * jugadores.
     *
     * @return Devuelve el mazo mezclado. La cantidad de cartas es
     * cantidadDeJugadores*cantidadDeTiposDeCarta.
     * @throws Exception Si la cantidadDeJugadores es 0.
     */
    public Mazo inicializarMazo() throws Exception {
        Mazo mazo = new Mazo(getCantidadDeJugadores());
        return mazo;
    }

    //ACCIONES DE JUEGO -------------------------------------------------------------------------------------------
    /**
     * pre: Se debe pasar por parámetro un jugador y un mazo válidos
     *
     * post: Se tira un dado y el jugador roba una cantidad de cartas
     * aleatorias. Si el número del dado más las que ya tiene supera el maximo,
     * no roba cartas.
     *
     * @param jugador No debe ser nulo.
     * @param mazo No debe ser nulo ni vacío.
     */
    public void jugadorRobaCartas(Jugador jugadorActual, Mazo mazo) {
        try {
            int numeroAleatorio = tirarDado();
            jugadorActual.robarCartas(numeroAleatorio, mazo); // tirar dado y robar cartas
            System.out.print("\n");
            System.out.println(jugadorActual.getNombre() + " roba " + numeroAleatorio + " cartas.");

        } catch (Exception e) {
            System.out.print("\n");
            System.out.println("El jugador " + jugadorActual.getNombre() + " no pudo robar cartas ya que el"
                    + " número que salió de tirar el dado más las que ya tiene supera la cantidad máxima.");
        }
    }

    /**
     * pre: jugadorActual y tablero deben ser válidos
     *
     * post: Si el jugador tiene fichas en la mano, coloca una ficha, caso
     * contrario está olbigado a mover una ficha puesta.
     *
     * @param jugadorActual No debe ser nulo.
     * @param tablero No debe ser nulo.
     * @throws Exception Si el jugadorActual es nulo.
     * @throws Exception Si el tablero es nulo.
     */
    public void jugarFicha(Jugador jugadorActual, Tablero tablero) throws Exception {
        if (jugadorActual == null) {
            throw new Exception("El jugador no debe ser nulo.");
        }
        if (tablero == null) {
            throw new Exception("El tablero no debe ser nulo.");
        }
        boolean seJugo = false;
        if (jugadorActual.tieneFichas()) {
            while (!seJugo) {
                try {
                    colocarFicha(jugadorActual, tablero);
                    seJugo = true;
                } catch (Exception e) {
                    System.out.println("No se pudo colocar la ficha.");
                }
            }
        } else {
            while (!seJugo) {
                try {
                    moverFicha(jugadorActual, tablero);
                    seJugo = true;
                } catch (Exception e) {
                    System.out.println("No se pudo mover la ficha.");
                }
            }
        }
    }

    /**
     * pre: jugadorActual y tablero deben ser válidos
     *
     * post: Mueve la ficha del jugador a otro casillero adyacente.
     *
     * @param jugadorActual No debe ser nulo.
     * @param tablero No debe ser nulo.
     * @throws Exception Si el jugadorActual es nulo.
     * @throws Exception Si el tablero es nulo.
     */
    public void moverFicha(Jugador jugadorActual, Tablero tablero) throws Exception {
        if (jugadorActual == null) {
            throw new Exception("El jugador no debe ser nulo.");
        }
        if (tablero == null) {
            throw new Exception("El tablero no debe ser nulo.");
        }
        boolean moverFicha = false;
        while (!moverFicha) {
            try {
                System.out.println(jugadorActual.getNombre() + " indique las coordenadas de la ficha a mover.");
                int[] coordenadasOrigen = pedirCoordenadas(tablero);
                Casillero casilleroOrigen = tablero.getCasillero(coordenadasOrigen[0], coordenadasOrigen[1], coordenadasOrigen[2]);
                if (!casilleroOrigen.getJugador().getNombre().equals(jugadorActual.getNombre())) {
                    throw new Exception("El casillero indicado no te pertenece, jugador " + jugadorActual.getNombre());
                }
                System.out.printf("\n");
                System.out.println(jugadorActual.getNombre() + " indique las coordenadas del lugar a donde se quiere mover la ficha (debe ser adyacente).");
                int[] coordenadasDestino = pedirCoordenadas(tablero);
                this.victoria = tablero.moverFicha(coordenadasDestino[0], coordenadasDestino[1], coordenadasDestino[2], casilleroOrigen);
                moverFicha = true;
            } catch (Exception e) {
                System.out.println("No se pudo mover la ficha en el lugar indicado, verifique que sea de su perternencia y que el destino sea adyacente.");
            }
        }
    }

    /**
     * pre: jugadorActual y tablero deben ser válidos
     *
     * post: Coloca la ficha del jugador en el casillero indicado por consola.
     *
     * @param jugadorActual No debe ser nulo.
     * @param tablero No debe ser nulo.
     * @throws Exception Si el jugadorActual es nulo.
     * @throws Exception Si el tablero es nulo.
     */
    public void colocarFicha(Jugador jugadorActual, Tablero tablero) throws Exception {
        if (jugadorActual == null) {
            throw new Exception("El jugador no debe ser nulo.");
        }
        if (tablero == null) {
            throw new Exception("El tablero no debe ser nulo.");
        }
        boolean colocarFicha = false;
        while (!colocarFicha) {
            try {
                System.out.println(jugadorActual.getNombre() + " indique en que coordenadas desea colocar su ficha.");
                int[] coordenadas = pedirCoordenadas(tablero);
                this.victoria = tablero.colocarFicha(coordenadas[0], coordenadas[1], coordenadas[2], jugadorActual);
                jugadorActual.disminuirFichas();
                colocarFicha = true;
            } catch (Exception e) {
                System.out.println("No se pudo colocar la ficha en el lugar indicado, verifique que esté vacío y sin el efecto de las cartas.");
            }
        }
    }

    /**
     * pre: jugadorActual, tablero y mazo deben ser válidos
     *
     * post: Si el jugador tiene cartas juega una de las que tiene en mano.
     *
     * @param jugadorActual No debe ser nulo, lo verifican colocar y mover
     * ficha.
     * @param tablero No debe ser nulo, lo verifican colocar y mover ficha.
     * @param mazo No debe ser nulo.
     */
    public void jugarCarta(Jugador jugadorActual, Tablero tablero, Mazo mazo) throws Exception {
        if (mazo == null) {
            throw new Exception("El mazo no debe ser nulo.");
        }
        if (jugadorActual.getCartas().esVacia()) {
            return;
        }
        this.mostrarCartas(jugadorActual);
        System.out.print("\n");
        Carta carta;
        try {
            int numeroCarta = Teclado.pedirNumeroEntreIntervalo("Ingrese el número de la carta que desea utilizar, 0 para no usar ninguna", 0, jugadorActual.getCantidadDeCartas());
            System.out.print("\n");
            if (numeroCarta != 0) {
                carta = jugadorActual.getCartas().obtenerDato(numeroCarta);
                jugadorActual.getCartas().remover(carta);
                carta.usar(jugadorActual, listaJugadores, tablero, mazo);
            } else {
                System.out.println("No utilizó ninguna carta.");
            }

        } catch (Exception e) {
            System.out.println("Excepcion imposible al jugar carta.");
            System.out.println(e);
        }

    }

    /**
     * pre: jugadorActual debe ser válido
     *
     * post: Muestra las cartas que tiene el jugador actual en la mano.
     *
     * @param jugadorActual jugadorActual No debe ser nulo.
     * @throws Exception Si el jugadorActual es nulo.
     */
    private void mostrarCartas(Jugador jugadorActual) throws Exception {
        if (jugadorActual == null) {
            throw new Exception("El jugador no debe ser nulo.");
        }
        if (jugadorActual.getCantidadDeCartas() == 0) {
            System.out.println("El jugador no posee cartas en la mano");
        } else {
            for (int i = 1; i <= jugadorActual.getCantidadDeCartas(); i++) {
                System.out.printf("%d - %s.\n", i, jugadorActual.getCartas().obtenerDato(i).toString());
            }
        }
    }

    /**
     * post: Tira un dado, devolviendo un numero entre 1 y 6
     *
     * @return Devuelve un número random entre 1 y 6.
     */
    private int tirarDado() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(6) + 1;
        return numeroAleatorio;
    }

    /**
     * pre: tablero debe ser válido
     *
     * post: Devuelve las coordenadas ingresadas por el usuario.
     *
     * @param tablero No debe ser nulo.
     * @return Devuelve un arreglo de enteros que contiene las coordenadas.
     * @throws Exception Si el tablero es nulo.
     */
    private int[] pedirCoordenadas(Tablero tablero) throws Exception {
        if (tablero == null) {
            throw new Exception("El tablero no debe ser nulo.");
        }
        int[] coordenadas = new int[3];
        System.out.printf("\n");
        coordenadas[0] = Teclado.pedirNumeroEntreIntervalo("X", 1, tablero.getTamaño()) - 1;
        coordenadas[1] = Teclado.pedirNumeroEntreIntervalo("Y", 1, tablero.getTamaño()) - 1;
        coordenadas[2] = Teclado.pedirNumeroEntreIntervalo("Z", 1, tablero.getTamaño()) - 1;
        return coordenadas;
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * @return Devuelve la lista de jugadores.
     */
    public Lista<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    /**
     * @return Devuelve un boolean indicando si la partida terminó y hay un
     * ganador.
     */
    public boolean esVictoria() {
        return victoria;
    }

    /**
     * @return Devuelve la cantidadDeJugadores.
     */
    public int getCantidadDeJugadores() {
        return this.listaJugadores.getLongitud();
    }

}
