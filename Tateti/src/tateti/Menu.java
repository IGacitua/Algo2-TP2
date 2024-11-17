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
	
	//COSAS POR HACER: establecer e implementar lo de los turnos, probar mover,
    //jugar cartas y ver que funcione bien, finalizar una vez que ganó
        
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
    	int tamaño = Teclado.pedirNumeroEntreIntervalo(mensaje, 3, 99);
    	Tablero tablero = new Tablero(tamaño);
    	return tablero;
	}
    
    //JUGADORES
	/**
	 * pre: tamanioTablero debe ser válido,
	 * post: Inicializa los jugadores.
	 * @param tamañoTablero Debe estar entre 3 y 99 inclusive en ambos casos.
	 * @throws Exception Si los parámetros que recibe el Jugador al ser inicializado son inválidos.
	 * @throws Exception Si el tamañoTablero es inválido.
	 */
	public static void inicializarJugadores(int tamañoTablero) throws Exception {
		if ((tamañoTablero < 3) || (tamañoTablero > 99)) {
			throw new Exception("Error de construcción de tablero.");
		}
		int cantidadJugadores;
		String mensajeDeNroJugadores = "Ingrese el número de jugadores";
		while (true) {
			cantidadJugadores = Teclado.pedirNumeroEntreIntervalo(mensajeDeNroJugadores, 2, 8);
			if (cantidadJugadores > tamañoTablero) {
				System.out.println("La cantidad de jugadores debe ser menor o igual al tamaño del tablero.");
			} else {
				break;
			}
		}
		for (int i = 0; i < cantidadJugadores ; i++) {
			Lista<Carta> cartas = new Lista<>();
			String mensajePorJugador = "Ingrese el nombre del jugador numero " + (i+1);
			String nombre = Teclado.pedirString(mensajePorJugador);
			String nombreDefinitivo = nombre.substring(0, 1).toUpperCase() + nombre.substring(1); //para asegurarnos que la 1era letra del nombre sea mayúscula
            ColoresDisponibles color = solicitarColor();
            Fichas ficha = solicitarFicha();
            Jugador jugador = new Jugador(nombreDefinitivo,tamañoTablero, (int) Math.round(tamañoTablero*0.5),ficha,color,cartas);
            listaJugadores.agregarElemento(jugador);
		}
		imprimirJugadoresPorPantalla(); //TODO: borrar. Si molesta mucho ahora al programar, no pasa nada si eliminan esta línea.
										//solo NO eliminen la funcion de imprimir, gracias:)
	}
	
    /**
     * pre: -, post: -
     * @return Devuelve la cantidadDeJugadores.
     */
    public static int getCantidadDeJugadores() {
    	return listaJugadores.getLongitud();
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
	 * TODO:
	 * @param jugadores
	 * @param mazo
	 * @throws Exception
	 */
    private static void jugadorRobaCartas(Jugador jugadorActual, Mazo mazo) throws Exception{
    	/*
    	 * FIXME: deberiamos hacer un try cathc dentro de este metodo para que en caso de que un jugador no pueda 
    	 * recibir cartas, siga con el siguiente jugador
    	 */
        try { //Si no puede recibirlas, lanza el msj y no saca nada del mazo:)
            int numeroAleatorio = tirarDado();
            jugadorActual.robarCartas(numeroAleatorio, mazo); // tirar dado y robar cartas
            System.out.println(jugadorActual.getNombreJugador() + " roba " + numeroAleatorio + " cartas.");
            imprimirJugadoresPorPantalla();
        } catch (Exception e) {
        	System.out.println("El jugador " + jugadorActual.getNombreJugador() + " no pudo robar cartas, supera la máxima cantidad de cartas.");
        }   
    }
   
    //MAZO
    /**
     * pre: -, post: -
     * @return Devuelve el mazo mezclado.
     * La cantidad de cartas es de la forma cantidadDeJugadores*cantidadDeTiposDeCarta.
     * @throws Exception Si la cantidadDeJugadores es 0.
     */
    public static Mazo inicializarMazo() throws Exception {
    	Mazo mazo = new Mazo(getCantidadDeJugadores());
    	return mazo;
    }
	
    //JUGAR
	/**
	 * pre: tablero y mazo deben ser válidos,
	 * post: ?? //TODO: ver tema de gestionar turnos y si devuelve o no al ganador acá.
	 * @param tablero No debe ser nulo.
	 * @param mazo No debe ser nulo.
	 * @throws Exception Si el tablero es nulo.
	 * @throws Exception Si el mazo es nulo.
	 */
    public static String gestionarTurnos(Tablero tablero, Mazo mazo) throws Exception {
    	if (tablero == null) {
    		throw new Exception("El tablero a utilizar no puede ser nulo.");
    	}
    	if (mazo == null) {
    		throw new Exception("El mazo a utilizar no puede ser nulo.");
    	}
		//TODO:validar todos los parametros
		//FIXME: ahora se juega un solo turno, ver de que forma repetir los turnos hasta qyue alguien gane
    	boolean alguienGano = false;
    	while (true) { //TODO: IMPLEMENTAR MANEJO DE TURNOS 
	        listaJugadores.iniciarCursor(); // Reiniciar el cursor al comienzo de la lista
	        while (listaJugadores.avanzarCursor()) {
	            Jugador jugadorActual = listaJugadores.obtenerCursor();
	            jugadorRobaCartas(jugadorActual, mazo);
	            if(!jugadorActual.isPierdeTurno()) {
		            alguienGano = jugarFicha(jugadorActual, tablero);
		            if (alguienGano) {
		            	return jugadorActual.getNombreJugador();
		            }
	            //jugarCarta(jugadorActual, tablero);           
	            }
	            else {
	            	jugadorActual.alternarPierdeTurno();
	            }
	        }
    	}
    }
	
	/**
	 * pre: jugadorActual y tablero deben ser válidos,
	 * post: ?? //TODO: ver tema de gestionar turnos
	 * @param jugadorActual No debe ser nulo.
	 * @param tablero No debe ser nulo.
	 * @throws Exception Si el jugadorActual es nulo.
	 * @throws Exception Si el tablero es nulo.
	 */
	private static boolean jugarFicha(Jugador jugadorActual, Tablero tablero) throws Exception {
		if (jugadorActual == null) {
			throw new Exception("El jugador no debe ser nulo.");
		}
		if (tablero == null) {
			throw new Exception("El tablero no debe ser nulo.");
		}
		boolean jugo = false;
		boolean alguienGano = false;
		if (jugadorActual.tieneFichas()) {
			while(!jugo) {
				try {
					alguienGano = colocarFicha(jugadorActual, tablero);
					jugo = true;
				}catch(Exception e) {
					System.out.println("No se pudo colocar la ficha.");
				}
			}
		} else {
			while(!jugo) {
				try {
					alguienGano = moverFicha(jugadorActual, tablero);
					jugo = true;
				} catch(Exception e) {
					System.out.println("No se pudo mover la ficha.");
				}
			}
		}
		tablero.imprimir(); //TODO: ELIMINAR
		return alguienGano;
	}
    
    /**
     * pre: jugadorActual y tablero deben ser válidos,
     * post: mueve la ficha del jugador a otro casillero adyacente.
     * @param jugadorActual No debe ser nulo.
	 * @param tablero No debe ser nulo.
	 * @throws Exception Si el jugadorActual es nulo.
	 * @throws Exception Si el tablero es nulo.
     */
    private static boolean moverFicha (Jugador jugadorActual, Tablero tablero) throws Exception{
    	if (jugadorActual == null) {
			throw new Exception("El jugador no debe ser nulo.");
		}
		if (tablero == null) {
			throw new Exception("El tablero no debe ser nulo.");
		}
        boolean moverFicha = false;
        boolean alguienGano = false;
        while(!moverFicha){
        	try {
        		System.out.println(jugadorActual.getNombreJugador() + " indique las coordenadas de la ficha a mover.");
	        	int xOrigen = Teclado.pedirNumeroEntreIntervalo("X", 0, tablero.getTamaño()-1);
	        	int yOrigen = Teclado.pedirNumeroEntreIntervalo("Y", 0, tablero.getTamaño()-1);
	        	int zOrigen = Teclado.pedirNumeroEntreIntervalo("Z", 0, tablero.getTamaño()-1);
	        	Casillero casilleroOrigen = tablero.getCasillero(xOrigen, yOrigen, zOrigen);
	        	if (!casilleroOrigen.getJugador().getNombreJugador().equals(jugadorActual.getNombreJugador())) {
	        		throw new Exception("El casillero indicado no te pertenece, jugador " + jugadorActual.getNombreJugador());
	        	}
	        	System.out.println(jugadorActual.getNombreJugador() + " indique las coordenadas del lugar a donde se quiere mover la ficha (debe ser adyacente).");
	        	int xDestino = Teclado.pedirNumeroEntreIntervalo("X", 0, tablero.getTamaño()-1);
	        	int yDestino = Teclado.pedirNumeroEntreIntervalo("Y", 0, tablero.getTamaño()-1);
	        	int zDestino = Teclado.pedirNumeroEntreIntervalo("Z", 0, tablero.getTamaño()-1);
	            alguienGano = tablero.moverFicha(xDestino, yDestino, zDestino, casilleroOrigen);
	            tablero.imprimir();
	            moverFicha=true;
        	}catch(Exception e) {
        		System.out.println("No se pudo colocar la ficha en el lugar indicado, verifique que sea de su perternencia y que el destino sea adyacente.");
        	}
        }
        return alguienGano;
    }
   
    /**
     * pre: jugadorActual y tablero deben ser válidos,
     * post: coloca la ficha del jugador en el casillero indicado por consola.
     * @param jugadorActual No debe ser nulo.
	 * @param tablero No debe ser nulo.
	 * @throws Exception Si el jugadorActual es nulo.
	 * @throws Exception Si el tablero es nulo.
     */
    private static boolean colocarFicha (Jugador jugadorActual, Tablero tablero) throws Exception{
    	if (jugadorActual == null) {
			throw new Exception("El jugador no debe ser nulo.");
		}
		if (tablero == null) {
			throw new Exception("El tablero no debe ser nulo.");
		}
        boolean colocarFicha = false;
        boolean alguienGano = false;
        while(!colocarFicha){
        	try {
        		System.out.println(jugadorActual.getNombreJugador() + " indique en que coordenadas desea colocar su ficha.");
	        	int x = Teclado.pedirNumeroEntreIntervalo("X", 0, tablero.getTamaño()-1);
	        	int y = Teclado.pedirNumeroEntreIntervalo("Y", 0, tablero.getTamaño()-1);
	        	int z = Teclado.pedirNumeroEntreIntervalo("Z", 0, tablero.getTamaño()-1);
	            alguienGano = tablero.colocarFicha(x, y, z, jugadorActual);
	            colocarFicha=true;
        	}catch(Exception e) {
        		System.out.println("No se pudo colocar la ficha en el lugar indicado.");
        	}
        }
        return alguienGano;
    }
     
    /**
     * TODO: OJOOOOOO
     * @param jugadorActual
     * @param tablero
     * @throws Exception
     */
    private static void jugarCarta(Jugador jugadorActual,Tablero tablero) throws Exception{
    	//Verificar
    	/*
    	 * Esto podria ser un metodo de jugador, jugadorActual.mostrarCartas()
    	 */
    	mostrarCartas(jugadorActual);
        if (!jugadorActual.getCartas().esVacia()) {
            String mensaje = "Ingrese el numero de la carta que desea utilizar";
            int nroCarta = Teclado.pedirNumeroEntreIntervalo(mensaje, 1,jugadorActual.getCantidadDeCartas());
            /*
             * FIXME: yo creo que el funcionamiento de las cartas deberia estar en cada carta y no en menu
             */
            /*Lista<Integer> coordenadas = new Lista<>();
            obtenerCoordenadas(coordenadas,tablero);
            try { //si la carta no se puede usar o genera error al usarse
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
            }*/
        }

        //jugadorActual.getCartas().use(obtenerCasillero(coordenadas, tablero)); no funciono
    }

    /**    
     * pre: jugadorActual debe ser válido,
     * post: ?? //TODO: ver cómo mostrar las cartas!!!
     * @param jugadorActual jugadorActual No debe ser nulo.
     * @throws Exception Si el jugadorActual es nulo.
     */
    private static void mostrarCartas(Jugador jugadorActual) throws Exception {
    	if (jugadorActual == null) {
			throw new Exception("El jugador no debe ser nulo.");
		}
    	if (jugadorActual.getCantidadDeCartas()==0) {
    		System.out.println("El jugador no posee cartas en la mano");
    	}else {
    		Lista<Carta> cartas=jugadorActual.getCartas();
    		cartas.iniciarCursor();
    		int i=1;
    		while(!cartas.avanzarCursor()) {
    			//TODO: les falta un to string a las Cartas!!!!!!!!!!!!
    	         System.out.println("En la posicion " + i + " se tiene la carta " );
    	         i++;
    	        }
    		}
    	}

    //tirar dado (Deberia estar en Utilidades)
    /**
     * TODO
     * @return
     */
    private static int tirarDado(){
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
