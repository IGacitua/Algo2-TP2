package tateti;

import cartas.Carta;
import cartas.CartaAnularCasillero;
import cartas.CartaBloquearFicha;
import java.util.Random;
import utilidades.Lista;
import utilidades.Teclado;

public class Menu {
	//TODO: todo lo que es static es porque eclipse me jode diciedo que tiene que ir así para
	//poder usar los métodos en el JuegoTateti, desp vemos qué onda
	//Atributos
    private static Lista<Jugador> listaJugadores; 
    private static Lista<ColoresDisponibles> coloresTomados;
    private static Lista<Fichas> fichasTomadas;
	
	//constructor
    /**
     * pre: -, post: Inicializa el Menu.
     */
    public Menu() {
    	//Para que quiere el menu una lista de jugadores, en todo caso el tablero se crea en el juegoTateti
    	//pero menu maneja la lista de jugadores	
        listaJugadores = new Lista<Jugador>();
        coloresTomados = new Lista<ColoresDisponibles>();
        fichasTomadas = new Lista<Fichas>(); 
    }
	
	
        
    ///MÉTODOS 
    //TODO:Separaria los metodos por Tematica, Tablero, Mazo, Jugadores, etc.
//------------------------------------------------------------------------------------------------------------------
	//TABLERO
    /**
	 * pre: -, post: Devuelve el tablero inicializado.
	 * @return Devuelve el tablero inicializado según la preferencia
	 * del usuario.
	 * @throws Exception Si el tamaño del tablero es inválido.
	 */
    public static Tablero inicializarTablero() throws Exception {
    	String mensaje = "Ingrese el tamaño del tablero con el quiere jugar";
    	int tamanio = Teclado.pedirNumeroEntreIntervalo(mensaje, 3, 99);
    	Tablero tablero = new Tablero(tamanio);
    	return tablero;
	}
    
    //JUGADORES
	/**
	 * pre: tamanioTablero debe ser válido,
	 * post: Inicializa los jugadores.
	 * @param tamanioTablero Debe estar entre 3 y 99 inclusive en ambos casos.
	 * //TODO: es necesario validar? porque al inicializar los jugadores el tablero
	 * jamás se irá de rango porque antes de eso lo inicializamos y ahi se valida eso. 
	 * @throws Exception Si los parámetros que recibe el Jugador al ser inicializado son inválidos.
	 */
	public static void inicializarJugadores(int tamanioTablero) throws Exception {
		int cantidadJugadores;
		Fichas[] fichasDisponibles = Fichas.values();
		Lista<Carta> cartas = new Lista<>();
		String mensajeDeNroJugadores = "Ingrese el número de jugadores";
		while (true) {
			cantidadJugadores = Teclado.pedirNumeroEntreIntervalo(mensajeDeNroJugadores, 2, 8);
			if (cantidadJugadores > tamanioTablero) {
				System.out.println("La cantidad de jugadores debe ser menor o igual al tamaño del tablero.");
			} else {
				break;
			}
		}
		for (int i = 0; i < cantidadJugadores ; i++) {
			String mensajePorJugador = "Ingrese el nombre del jugador numero " + (i+1);
			String nombre = Teclado.pedirString(mensajePorJugador);
			String nombreDefinitivo = nombre.substring(0, 1).toUpperCase() + nombre.substring(1); //para asegurarnos que la 1era letra del nombre sea mayúscula
            ColoresDisponibles color = solicitarColor();
            Fichas ficha = solicitarFicha();
            Jugador jugador = new Jugador(nombreDefinitivo,tamanioTablero, (int) Math.round(tamanioTablero*0.5),ficha,color,cartas);
            listaJugadores.agregarElemento(jugador);
		}
		imprimirJugadoresPorPantalla(); //TODO: borrar. Si molesta mucho ahora al programar, no pasa nada si eliminan esta línea.
										//solo NO eliminen la funcion de imprimir, gracias:)
	}
	
	//TODO: eliminar
	public static void imprimirJugadoresPorPantalla() throws Exception {
		listaJugadores.iniciarCursor();
		while (listaJugadores.avanzarCursor()) {
			Jugador jugador = listaJugadores.obtenerCursor();
			System.out.println(" ");
			System.out.println("Nombre: " + jugador.getNombreJugador());
			System.out.println("Identificacion: " + jugador.getIdentificacion());
			System.out.println("Cantidad de fichas: " + jugador.getCantidadDeFichas());
			System.out.println("Cantidad de cartas guardadas: " + jugador.getCantidadDeCartas());
			System.out.println("Cantidad de cartas total: " + jugador.getCartasMaximas());
			System.out.println("Ficha: " + jugador.getFichaCaracter());
			System.out.println("Color: " + jugador.getColor());
		}
	}

    /**
     * pre: -, post: Solicita al usuario un color de la lista de colores disponibles.
     * @return Devuelve el color seleccionado.
     * @throws Exception Si coloresTomados es nula o el Enum ColoresDisponibles está vacío.
     */
    public static ColoresDisponibles solicitarColor() throws Exception {
        return solicitarElementoDeEnum("Elija el color de la lista que desea utilizar", coloresTomados, ColoresDisponibles.values());
    }

    /**
     * pre: -, post: Solicita al usuario una ficha de la lista de fichas disponibles.
     * @return Devuelve la ficha seleccionada.
     * @throws Exception Si fichasTomadas es nula o el Enum Fichas está vacío.
     */
    public static Fichas solicitarFicha() throws Exception {
        return solicitarElementoDeEnum("Elija la ficha de la lista que desea utilizar", fichasTomadas, Fichas.values());
    }
    
    /**
     * pre: -, post: -
     * @return Devuelve la cantidadDeJugadores.
     */
    public static int getCantidadDeJugadores() {
    	return listaJugadores.getLongitud();
    }
   
    //MAZO
    /**
     * pre: -, post: -
     * @return Devuelve el mazo mezclado.
     * La cantidad de cartas es de la forma cantidadDeJugadores*cantidadDeTiposDeCarta
     * @throws Exception Si la cantidadDeJugadores es 0.
     */
    public static Mazo inicializarMazo() throws Exception {
    	Mazo mazo = new Mazo(getCantidadDeJugadores());
    	return mazo;
    }
	
    //JUGAR
    /*idea: repartirle a todos los jugadores cartasMax.
    ejemplo si elegimos tablero de 3x3x3 y 2 jugadores, c/u deberia tiene como max 2 cartas.
    La idea es que arranquen con todas las cartas posibles y se van descartando (así entendí q dijo el profe
    en los audios de joaquin el viernes 15)*/
    public static void repartirCartas(Mazo mazo) throws Exception {
    	listaJugadores.iniciarCursor();
		while (listaJugadores.avanzarCursor()) {
			Jugador jugador = listaJugadores.obtenerCursor();
			jugador.robarCarta(mazo);
			System.out.println("Nombre: " + jugador.getNombreJugador() + " Cantidad de cartas max: "
			+ jugador.getCartasMaximas() + " Cantidad de cartas que posee: " + jugador.getCantidadDeCartas());
			//aca ambos deberian tener 1 sola pero imprime jugador1 tiene 1 y jugador2 tiene 2
		}
    }
    
	/**
	 * TODO
	 * @param jugadores
	 * @param tablero
	 * @param mazo
	 * @throws Exception
	 */
	public void gestionarTurnos(Lista<Jugador> jugadores, Tablero tablero, Mazo mazo) throws Exception {
		//TODO:validar todos los parametros
		//FIXME: ahora se juega un solo turno, ver de que forma repetir los turnos hasta qyue alguien gane
        jugadoresRobanCartas(jugadores, mazo); //todos los jugadores roban cartas
        jugadores.iniciarCursor(); // Reiniciar el cursor al comienzo de la lista
        while (jugadores.avanzarCursor()) {
        	//FIXME: Verificar previamente si el jugador esta bloqueado
            Jugador jugadorActual = jugadores.obtenerCursor();
            /*
             * FIXME: solo puede poner ficha o mover ficha en el turno, no ambas
             */
            colocarFicha(jugadorActual,tablero); // el jugador coloca ficha
            moverFicha(jugadorActual,tablero); // el jugador mueve ficha
            jugarCarta(jugadorActual, tablero);
            jugadores.avanzarCursor();
        }
    }

    private void jugadoresRobanCartas(Lista<Jugador> jugadores,Mazo mazo) throws Exception{
    	/*
    	 * FIXME: deberiamos hacer un try cathc dentro de este metodo para que en caso de que un jugador no pueda 
    	 * recibir cartas, siga con el siguiente jugador
    	 */
        jugadores.iniciarCursor();
        while (jugadores.avanzarCursor()){
            Jugador jugadorActual = jugadores.obtenerCursor();
            /* Esto no siempre pasa, ver que ocurre en el caso de que no pueda recibir esas cartas
             * 
             */
            int numeroAleatorio = this.tirarDado();
            jugadorActual.robarCartas(numeroAleatorio,mazo); // tirar dado y robar cartas
            System.out.println(jugadorActual.getNombreJugador() + " roba " + numeroAleatorio + " cartas.");
            jugadores.avanzarCursor();
        }
        
    }
    
    /**
     * TODO
     * @param jugadorActual
     * @param tablero
     * @throws Exception
     */
    private void moverFicha (Jugador jugadorActual,Tablero tablero) throws Exception{
    	//Verificar
        boolean moverFicha = false;
        Lista<Integer> coordMover1 = new Lista<>();
        /*
         * TODO: deberiamos mostrarle las fichas del jugador, para que sepa donde estan,
         * preguntale al que esta en jugador si puede implementar ese metodo
         */
        System.out.println(jugadorActual.getNombreJugador() + " indique que ficha desea mover de coordenada.");
    
        this.obtenerCoordenadas(coordMover1,tablero);
        Casillero coordOrigen = new Casillero(coordMover1.obtenerDato(0),coordMover1.obtenerDato(1),coordMover1.obtenerDato(2));
        System.out.println(jugadorActual.getNombreJugador() + " indique hacia que coordenada desea mover su ficha.");
        Lista<Integer> coordMover2 = new Lista<>();
        this.obtenerCoordenadas(coordMover2,tablero);
        //origen = coordmover1
        while(!moverFicha){
            tablero.moverFicha(coordMover2.obtenerDato(0),coordMover2.obtenerDato(1),coordMover2.obtenerDato(2), coordOrigen);
        }
    }
   
    /**
     * 
     * @param jugadorActual
     * @param tablero
     * @throws Exception
     */
    private void colocarFicha (Jugador jugadorActual, Tablero tablero) throws Exception{
    	//Verificar
        boolean colocarFicha = false;
        Lista<Integer> coordAgregar = new Lista<>();
        System.out.println(jugadorActual.getNombreJugador() + " indique en que coordenadas desea colocar su ficha.");
        this.obtenerCoordenadas(coordAgregar,tablero);
        while(!colocarFicha){
            tablero.colocarFicha(coordAgregar.obtenerDato(0),coordAgregar.obtenerDato(1),coordAgregar.obtenerDato(2), jugadorActual);
        }
    }
    public int consultarCantidadCartas() throws Exception{
        String mensaje = "Ingrese la cantidad maxima de cartas que podra tener cada jugador";
        int cantidadCartas = Teclado.pedirNumeroEntreIntervalo(mensaje, 3,100);
        return cantidadCartas;
    }

    /** estaba hecho con lista de cartas !!!
    private void generarMazoAleatorio(Lista<Carta> cartas,int cantidadCartas) throws Exception {
        Random random = new Random();

        for (int i = 0; i < cantidadCartas; i++) {
            int tipoCarta = random.nextInt(3); // 0, 1 o 2 para los tres tipos de carta , van a haber mas?

            Carta carta;
            switch (tipoCarta) {
                case 0:
                    carta = new CartaAnularCasillero();
                    break;
                case 1:
                    carta = new CartaBloquearFicha();
                    break;
                case 2:
                    carta = new CartaPerderTurno();
                    break;
                default:
                    throw new IllegalStateException("Valor inesperado: " + tipoCarta);
            }

            cartas.agregarElemento(carta); // Agrega la carta generada a la lista de cartas
        }
    }
    */
     
    private void jugarCarta(Jugador jugadorActual,Tablero tablero) throws Exception{
    	//Verificar
    	/*
    	 * Esto podria ser un metodo de jugador, jugadorActual.mostrarCartas()
    	 */
        for (int i=1; i < jugadorActual.getCantidadDeCartas();i++){ // AAAAAAAAAAA
            System.out.println("En la posicion " + i + " se tiene la carta " + jugadorActual.getCartas().obtenerDato(i));
        }
        String mensaje = "Ingrese el numero de la carta que desea utilizar";
        int nroCarta = Teclado.pedirNumeroEntreIntervalo(mensaje, 1,jugadorActual.getCantidadDeCartas());
        /*
         * FIXME: yo creo que el funcionamiento de las cartas deberia estar en cada carta y no en menu
         */
        Lista<Integer> coordenadas = new Lista<>();
        obtenerCoordenadas(coordenadas,tablero);
        try {
        	//FIXME: cada carta tiene su funcionamiento en usar(), el try cath deberia ser usado para en caso de que la 				carta no pueda usarse otro tema, le deje al usuario usar otra carta, ejemplo: bloquear un casillero 				bloqueado
            Carta carta = jugadorActual.getCartas().obtenerDato(nroCarta);
            if (carta instanceof CartaAnularCasillero) {
                System.out.println(jugadorActual.getNombreJugador()+ " utiliza la carta Anular Casillero.");
                ((CartaAnularCasillero) carta).usar(obtenerCasillero(coordenadas, tablero));
            } else if (carta instanceof CartaBloquearFicha) {
                System.out.println(jugadorActual.getNombreJugador()+ " utiliza la carta BLoquear Ficha.");
                ((CartaBloquearFicha) carta).usar(obtenerCasillero(coordenadas, tablero));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //jugadorActual.getCartas().use(obtenerCasillero(coordenadas, tablero)); no funciono
    }

        
    private void obtenerCoordenadas(Lista<Integer> coordenadas,Tablero tablero) throws Exception {
    	//Verificiar
    	String mensajeX = "Ingrese la coordenada X";
    	String mensajeY = "Ingrese la coordenada Y";
    	String mensajeZ = "Ingrese la coordenada Z";
    	int x=Teclado.pedirNumeroEntreIntervalo(mensajeX, 0, tablero.getTamaño());
    	coordenadas.agregarElemento(1,x);
    	int y=Teclado.pedirNumeroEntreIntervalo(mensajeY, 0, tablero.getTamaño());
	   	coordenadas.agregarElemento(2,y);
	   	int z=Teclado.pedirNumeroEntreIntervalo(mensajeZ, 0,tablero.getTamaño());
	   	coordenadas.agregarElemento(3,z);
    }
    
    /**
     * TODO
     * @param coordenadas
     * @param tablero
     * @return
     * @throws Exception
     */
    private Casillero obtenerCasillero(Lista<Integer> coordenadas,Tablero tablero) throws Exception{
    	//Verificaciones
        this.obtenerCoordenadas(coordenadas,tablero);
        Casillero casillero = new Casillero(coordenadas.obtenerDato(0),coordenadas.obtenerDato(1),coordenadas.obtenerDato(2));
        return casillero;
    }
    
    //limite cartas
    /**
     * TODO
     * @return
     * @throws Exception
     */
    public int limiteCartas() throws Exception{
        String mensaje = "Ingrese el limite de cartas con el que desea jugar, no debe superar el tamaño del tablero seleccionado";
        int cantCartas = Teclado.pedirNumeroEntreIntervalo(mensaje, 3, 99);
        return cantCartas; 
    }

    //tirar dado (Deberia estar en Utilidades)
    /**
     * TODO
     * @return
     */
    private int tirarDado(){
        Random random = new Random();
        int numeroAleatorio = random.nextInt(6) + 1;
        return numeroAleatorio;
    }
    
    
 	/**
 	 * pre: -, post: Solicita un elemento de un Enum al usuario y lo devuelve.
 	 * @param <T> Puede ser cualquier tipo de Enum.
 	 * @param mensaje Puede especificarse un mensaje al pedir el T, pero
     * en caso de que no se necesite, puede ser nulo o vacío y se lo reemplaza
     * por "Elija un elemento de la lista que desea utilizar". 
 	 * @param elementosTomados La lista a la cual se agregan los elementos que los usuarios
 	 * van seleccionando. Puede estar vacía pero no ser nula.
 	 * @param valoresEnum Es un arreglo de todos los valores en el Enum. No puede estar
 	 * vacío ni ser nulo.
 	 * @return Devuelve el T elegido por el usuario.
 	 * @throws Exception Si elementosTomados es nulo.
 	 * @throws Exception Si valoresEnum es nulo o está vacío.
 	 */
 	private static <T extends Enum<T>> T solicitarElementoDeEnum(String mensaje, Lista<T> elementosTomados, T[] valoresEnum) throws Exception {
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
}
