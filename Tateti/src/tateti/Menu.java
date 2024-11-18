package tateti;

import cartas.Carta;
import cartas.CartaAnularCasillero;
import cartas.CartaBloquearFicha;
import cartas.CartaPerderTurno;
import cartas.CartaRobarCartas;
import java.util.Random;
import utilidades.Lista;
import utilidades.Teclado;

//TODO pasar a template
public class Menu {

    //Atributos------------------------------------------------------------------------------------------------------
    @SuppressWarnings("FieldMayBeFinal") // TODO: Ver si puede ser final al terminar
    private Lista<Jugador> listaJugadores;
    @SuppressWarnings("FieldMayBeFinal") // TODO: Ver si puede ser final al terminar
    private Lista<ColoresDisponibles> coloresTomados;
    @SuppressWarnings("FieldMayBeFinal") // TODO: Ver si puede ser final al terminar
    private Lista<Fichas> fichasTomadas;
    private boolean victoria = false;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * post: Inicializa el Menu.
     */
    public Menu() {
        this.listaJugadores = new Lista<Jugador>();
        this.coloresTomados = new Lista<ColoresDisponibles>();
        this.fichasTomadas = new Lista<Fichas>();
        this.victoria = false;
    }

    //TODO: probar mover
    //jugar cartas y ver que funcione bien
    ///MÉTODOS --------------------------------------------------------------------------------------------------

	//TABLERO --------------------------------------------------------------------------------------------
    /**
	 * pre: -
	 * post inicializa el tablero con un input del usuario (debe estar entre 3 y 99), crea un tablero cuadrado de 
	 * 	lado*lado*lado
	 * @return Devuelve el tablero inicializado
	 */
    public Tablero inicializarTablero() throws Exception {
        return new Tablero(Teclado.pedirNumeroEntreIntervalo("Ingrese el tamaño del tablero con el quiere jugar", 3, 99));
    }

    //JUGADORES
    /**
     * pre: tamañoTablero debe ser válido post: Inicializa los jugadores, la
     * cantidad de jugadores no puede superar el tamaño del tablero y debe ser
     * menor a 8
     *
     * @param tamañoTablero Debe estar entre 3 y 99 inclusive en ambos casos.
     * @throws Exception Si los parámetros que recibe el Jugador al ser
     * inicializado son inválidos.
     * @throws Exception Si el tamañoTablero es inválido.
     */
    public void inicializarJugadores(int tamañoTablero) throws Exception {
        if ((tamañoTablero < 3) || (tamañoTablero > 99)) {
            //TODO eliminar, la validación ya la hace inicializarTablero()
            throw new Exception("Error de construcción de tablero.");
        }
        int cantidadJugadores;
        while (true) {
            cantidadJugadores = Teclado.pedirNumeroEntreIntervalo("Ingrese el número de jugadores", 2, 8);
            if (cantidadJugadores > tamañoTablero) {
                System.out.println("La cantidad de jugadores debe ser menor o igual al tamaño del tablero.");
            } else {
                break;
            }
        }
        for (int i = 0; i < cantidadJugadores; i++) {
            String nombre = Teclado.pedirString(("Ingrese el nombre del jugador numero " + (i + 1)));
            nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1); // Formatea el nombre con 1er letra mayus
            Jugador jugador = new Jugador(nombre, (int) (tamañoTablero * 1.5), (int) Math.round(tamañoTablero * 0.5), solicitarFicha(), solicitarColor(), new Lista<>());
            this.listaJugadores.agregarElemento(jugador);
        }
        imprimirJugadoresPorPantalla(); //TODO: borrar
    }

    //TODO: eliminar
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
     * pre: -, post: Solicita al usuario un color de la lista de colores
     * disponibles.
     *
     * @return Devuelve el color seleccionado.
     * @throws Exception Si coloresTomados es nula o el Enum ColoresDisponibles
     * está vacío.
     */
    public ColoresDisponibles solicitarColor() throws Exception {
        return solicitarElementoDeEnum("Elija el color de la lista que desea utilizar: ", this.coloresTomados, ColoresDisponibles.values());
    }

    /**
     * pre: -, post: Solicita al usuario una ficha de la lista de fichas
     * disponibles.
     *
     * @return Devuelve la ficha seleccionada.
     * @throws Exception Si fichasTomadas es nula o el Enum Fichas está vacío.
     */
    public Fichas solicitarFicha() throws Exception {
        return solicitarElementoDeEnum("Elija la ficha de la lista que desea utilizar: ", this.fichasTomadas, Fichas.values());
    }

    /**
     * pre: -, post: Solicita un elemento de un Enum al usuario y lo devuelve.
     *
     * @param <T> Puede ser cualquier tipo de Enum.
     * @param mensaje Puede especificarse un mensaje al pedir el T, pero en caso
     * de que no se necesite, puede ser nulo o vacío y se lo reemplaza por
     * "Elija un elemento de la lista que desea utilizar".
     * @param elementosTomados La lista a la cual se agregan los elementos que
     * los usuarios van seleccionando. Puede estar vacía pero no ser nula.
     * @param valoresEnum Es un arreglo de todos los valores en el Enum. No
     * puede estar vacío ni ser nulo.
     * @return Devuelve el T elegido por el usuario.
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
        if ((valoresEnum.length == 0) || (valoresEnum == null)) {
            throw new Exception("El Enum no debe ser nulo ni debe estar vacío.");
        }
        String elementosAImprimir = "";
        Lista<T> elementosDisponibles = new Lista<>();
        for (int i = 0; i < valoresEnum.length; i++) {
            T valor = valoresEnum[i];
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
            try { //try porque es necesario al pasar de String a Enum, sino explota todo si pone algo que no es del Enum
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

    //MAZO
    /**
     * pre: -, post: inicializa un mazo con cartas dependiendo de la cantidad de
     * jugadores
     *
     * @return Devuelve el mazo mezclado. La cantidad de cartas es de la forma
     * cantidadDeJugadores*cantidadDeTiposDeCarta.
     * @throws Exception Si la cantidadDeJugadores es 0.
     */
    public Mazo inicializarMazo() throws Exception {
        Mazo mazo = new Mazo(getCantidadDeJugadores());
        return mazo;
    }

    //Acciones de Juego -------------------------------------------------------------------------------------------
    /**
     * pre: se debe pasar por parametros un jugador no nulo y un mazo no nulo
     * post: se tira un dado y el jugador roba una cantidad de cartas
     * aleatorias, si no puede porque supera el maximo, no roba cartas
     *
     * @param jugador no nulo
     * @param mazo no nulo ni vacio
     */
    public void jugadorRobaCartas(Jugador jugadorActual, Mazo mazo) throws Exception {
        //Si no puede recibirlas, lanza el msj y no saca nada del mazo:)
        try {
            int numeroAleatorio = tirarDado();
            jugadorActual.robarCartas(numeroAleatorio, mazo); // tirar dado y robar cartas
            System.out.println(jugadorActual.getNombre() + " roba " + numeroAleatorio + " cartas.");
            //TODO:eliminar
            imprimirJugadoresPorPantalla();
        } catch (Exception e) {
            System.out.println("El jugador " + jugadorActual.getNombre() + " no pudo robar cartas, supera la máxima cantidad de cartas.");
        }
    }

    /**
     * pre: jugadorActual y tablero deben ser válidos post: Si el jugador tiene
     * fichas en la mano, coloca una ficha, caso contrario esta olbigado a mover
     * una ficha puesta
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
        boolean jugo = false;
        if (jugadorActual.tieneFichas()) {
            while (!jugo) {
                try {
                    colocarFicha(jugadorActual, tablero);
                    jugo = true;
                } catch (Exception e) {
                    System.out.println("No se pudo colocar la ficha.");
                }
            }
        } else {
            while (!jugo) {
                try {
                    moverFicha(jugadorActual, tablero);
                    jugo = true;
                } catch (Exception e) {
                    System.out.println("No se pudo mover la ficha.");
                }
            }
        }
    }

    /**
     * pre: jugadorActual y tablero deben ser válidos post: mueve la ficha del
     * jugador a otro casillero adyacente.
     *
     * @param jugadorActual No debe ser nulo.
     * @param tablero No debe ser nulo.
     * @return
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
                int xOrigen = Teclado.pedirNumeroEntreIntervalo("X", 0, tablero.getTamaño() - 1);
                int yOrigen = Teclado.pedirNumeroEntreIntervalo("Y", 0, tablero.getTamaño() - 1);
                int zOrigen = Teclado.pedirNumeroEntreIntervalo("Z", 0, tablero.getTamaño() - 1);
                Casillero casilleroOrigen = tablero.getCasillero(xOrigen, yOrigen, zOrigen);
                if (!casilleroOrigen.getJugador().getNombre().equals(jugadorActual.getNombre())) {
                    throw new Exception("El casillero indicado no te pertenece, jugador " + jugadorActual.getNombre());
                }
                System.out.println(jugadorActual.getNombre() + " indique las coordenadas del lugar a donde se quiere mover la ficha (debe ser adyacente).");
                int xDestino = Teclado.pedirNumeroEntreIntervalo("X", 0, tablero.getTamaño() - 1);
                int yDestino = Teclado.pedirNumeroEntreIntervalo("Y", 0, tablero.getTamaño() - 1);
                int zDestino = Teclado.pedirNumeroEntreIntervalo("Z", 0, tablero.getTamaño() - 1);
                this.victoria = tablero.moverFicha(xDestino, yDestino, zDestino, casilleroOrigen);
                tablero.imprimir();
                moverFicha = true;
            } catch (Exception e) {
                System.out.println("No se pudo colocar la ficha en el lugar indicado, verifique que sea de su perternencia y que el destino sea adyacente.");
            }
        }
    }

    /**
     * pre: jugadorActual y tablero deben ser válidos post: coloca la ficha del
     * jugador en el casillero indicado por consola.
     *
     * @param jugadorActual No debe ser nulo.
     * @param tablero No debe ser nulo.
     * @return
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
                System.out.printf("\n");
                int x = Teclado.pedirNumeroEntreIntervalo("X", 0, tablero.getTamaño() - 1);
                System.out.printf("\n");
                int y = Teclado.pedirNumeroEntreIntervalo("Y", 0, tablero.getTamaño() - 1);
                System.out.printf("\n");
                int z = Teclado.pedirNumeroEntreIntervalo("Z", 0, tablero.getTamaño() - 1);
                System.out.printf("\n");
                this.victoria = tablero.colocarFicha(x, y, z, jugadorActual);
                jugadorActual.disminuirFichas();
                colocarFicha = true;
            } catch (Exception e) {
                System.out.println("No se pudo colocar la ficha en el lugar indicado.");
            }
        }
    }

    /**
     * pre: parametros validos post: Si el jugador tiene cartas juega una de las
     * que tiene en mano
     *
     * @param jugadorActual, no nulo
     * @param tablero, no nulo
     * @param mazo, no nulo
     */
    public void jugarCarta(Jugador jugadorActual, Tablero tablero, Mazo mazo) throws Exception {
    	if(jugadorActual.getCartas().esVacia()) {
    		return;
    	}
        this.mostrarCartas(jugadorActual);
        Carta carta;
        try {
            int numeroCarta = Teclado.pedirNumeroEntreIntervalo("Ingrese el numero de la carta que desea utilizar: ", 1, jugadorActual.getCantidadDeCartas());
            carta = jugadorActual.getCartas().obtenerDato(numeroCarta);
            carta.usar(jugadorActual, listaJugadores, tablero, mazo);
            jugadorActual.getCartas().remover(carta);
        } catch (Exception e) {
            System.out.println("Excepcion imposible");
        }

    }

    /**
     * pre: jugadorActual debe ser válido post: Muestra las cartas que tiene el
     * jugador actual en la mano.
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
            for (int i = 1; i < jugadorActual.getCantidadDeCartas(); i++) {
                System.out.printf("%d - %s.\n ", i, jugadorActual.getCartas().obtenerDato(i).toString());
            }
        }
    }

    /**
     * pre: - post: Tira un dado, devolviendo un numero entre 1 y 6
     *
     */
    private int tirarDado() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(6) + 1;
        return numeroAleatorio;
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * pre: - post: devuelve la lista de jugadores
     */
    public Lista<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    /**
     * pre: -
     *
     * @return un booleano indicando si la partido termino y hay un ganador
     */
    public boolean isVictoria() {
        return victoria;
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve la cantidadDeJugadores.
     */
    public int getCantidadDeJugadores() {
        return this.listaJugadores.getLongitud();
    }

}
