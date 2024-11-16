package tateti;

import cartas.Carta;
import cartas.CartaAnularCasillero;
import cartas.CartaBloquearFicha;
import java.util.Random;
import utilidades.Lista;
import utilidades.Teclado;

public class Menu {
	//atributos
    private Lista<Jugador> listaJugadores; 
    private Lista<ColoresDisponibles> coloresTomados;
    private Lista<Fichas> fichasTomadas;
	
	//constructor
    public Menu(Lista<Jugador> listaJugadores) {
        // Quizas podriamos crear la lista aca mismo, debido a que se cargan los jugadores, asi que la estas creando 			practicamente
        this.listaJugadores = listaJugadores;
        this.coloresTomados = new Lista<ColoresDisponibles>();
        this.fichasTomadas = new Lista<Fichas>();
        //agregar mazo?
    }
	
	
        
    ///metodos
//------------------------------------------------------------------------------------------------------------------
	/*public Tablero inicializarTablero() throws Exception {
		int tamanio;
		String mensaje = "Ingrese un número para crear el tablero";
		while (true) {
			tamanio = Teclado.pedirNumeroEntreIntervalo(mensaje, 3, 99);
			if (tamanio >= getCantidadDeJugadores()) {
				break;
			}
		}
		Tablero tablero = new Tablero(tamanio);
		return tablero;
	}*/ //INTENTÉ HACERLO MÁS PROLIJO PERO NO TERMINÉ -R
    
    public int getCantidadDeJugadores() {
    	return this.listaJugadores.getLongitud();
    }
   
   //TEMA COLORES
    /**
     * pre: -, post: Devuelve una lista de los colores tomados.
     * @return Devuelve una lista que contiene los
     * colores ya tomados por los jugadores.
     */
    public Lista<ColoresDisponibles> getColoresTomados() {
        return this.coloresTomados;
    }
    
    /**
     * pre: -, post: Agrega un color a la lista de colores tomados.
     * @param color Debe pertenecer al Enum y no estar en la lista.
     */
    private void agregarColorTomado(ColoresDisponibles color) {
    	if (!coloresTomados.existe(color)) {
            this.coloresTomados.agregarElemento(color);
        }
    }
    
    /**
     * pre: -, post: Devuelve una lista de los colores disponibles.
     * @return Devuelve una lista con los colores disponibles.
     */
    public Lista<ColoresDisponibles> getColoresDisponibles() {
    	Lista<ColoresDisponibles> coloresDisponibles = new Lista<>();
    	
    	for (int i = 0; i < ColoresDisponibles.values().length; i++) {
    	    ColoresDisponibles color = ColoresDisponibles.values()[i];
    	    boolean estaTomado = coloresTomados.existe(color);
    	    if (!estaTomado) {
    	        coloresDisponibles.agregarElemento(color);
    	    }
    	}
    	return coloresDisponibles;
    }
    
    /**
     * pre: -, post: Solicita un color del Enum hasta que se ingresa uno válido.
     * @return Si el color no está tomado y es válido (forma parte del Enum), lo devuelve.
     */
    public ColoresDisponibles solicitarColor() {
    	String coloresAImprimir = "";
    	Lista<ColoresDisponibles> coloresDisponibles = getColoresDisponibles();
    	coloresDisponibles.iniciarCursor();
    	while (coloresDisponibles.avanzarCursor()) {
    		String color = coloresDisponibles.obtenerCursor().toString();
    		coloresAImprimir += color + " ";
    	}
    	
	  	String mensaje = "Elija el color de la lista que desea utilizar " + coloresAImprimir;
	  	while (true) {
	  		String colorElegido = Teclado.pedirString(mensaje).toUpperCase();
	  		try { //pongo el try porque es necesario al pasar de String a Enum, sino explota todo si pone algo que no es del enum
	            ColoresDisponibles colorSeleccionado = ColoresDisponibles.valueOf(colorElegido); //esto se supone que lo pasa de String al enum
	            if (!getColoresTomados().existe(colorSeleccionado)) {
	                return colorSeleccionado;
	            } else {
	                System.out.println("El color " + colorSeleccionado + " ya ha sido tomado por otro jugador, seleccione otro.");
	            }
	  		} catch (IllegalArgumentException e) {
	  			System.out.println("El color ingresado no está dentro de las opciones, vuelva a intentarlo.");
	  		}
	  	}
    }
   
    //TEMA FICHAS
    /**
     * pre: -, post: Devuelve una lista de las fichas tomadas.
     * @return Devuelve una lista que contiene las
     * fichas ya tomadas por los jugadores.
     */
    public Lista<Fichas> getFichasTomadas() {
        return this.fichasTomadas;
    }
    
    /**
     * pre: -, post: Agrega una ficha a la lista de fichas tomadas.
     * @param ficha Debe pertenecer al Enum y no estar en la lista.
     */
    private void agregarFichaTomada(Fichas ficha) {
    	if (!fichasTomadas.existe(ficha)) {
            this.fichasTomadas.agregarElemento(ficha);
        }
    }

    /**
     * pre: -, post: Devuelve una lista de las fichas disponibles.
     * @return Devuelve una lista con las fichas disponibles.
     */
    public Lista<Fichas> getFichasDisponibles() {
    	Lista<Fichas> fichasDisponibles = new Lista<>();
    	
    	for (int i = 0; i < Fichas.values().length; i++) {
    	    Fichas ficha = Fichas.values()[i];
    	    boolean estaTomada = fichasTomadas.existe(ficha);
    	    if (!estaTomada) {
    	    	fichasDisponibles.agregarElemento(ficha);
    	    }
    	}
    	return fichasDisponibles;
    }
    
    /**
     * pre: -, post: Solicita una ficha del Enum hasta que se ingresa una válida.
     * @return Si la ficha no está tomada y es válida (forma parte del Enum), la devuelve.
     */
    public Fichas solicitarFicha() {
    	String fichasAImprimir = "";
    	Lista<Fichas> fichasDisponibles = getFichasDisponibles();
    	fichasDisponibles.iniciarCursor();
    	while (fichasDisponibles.avanzarCursor()) {
    		String ficha = fichasDisponibles.obtenerCursor().toString();
    		fichasAImprimir += ficha + " ";
    	}
    	String mensaje = "Elija el color de la lista que desea utilizar " + fichasAImprimir;
	  	while (true) {
	  		String fichaElegida = Teclado.pedirString(mensaje).toUpperCase();
	  		try { //pongo el try porque es necesario al pasar de String a Enum, sino explota todo si pone algo que no es del enum
	            Fichas fichaSeleccionada = Fichas.valueOf(fichaElegida); //esto se supone que lo pasa de String al enum
	            if (!getFichasTomadas().existe(fichaSeleccionada)) {
	                return fichaSeleccionada;
	            } else {
	                System.out.println("La ficha " + fichaSeleccionada + " ya ha sido tomado por otro jugador, seleccione otro.");
	            }
	  		} catch (IllegalArgumentException e) {
	  			System.out.println("La ficha ingresada no está dentro de las opciones, vuelva a intentarlo.");
	  		}
	  	}
    }
    
    
    public void cargarJugadores() throws Exception{
		int cantidadJugadores;
        Fichas[] fichasDisponibles = Fichas.values();
        Lista<Carta> cartas = new Lista<>();
		String mensajeDeNroJugadores = "Ingrese el número de jugadores";
		/*
		 * Consulta la cantidad de jugadores solo entre 2 y 8 o lo hacemos en base al tablero
		 * si el tablero es de 3x3 jugar 8 es imposible ahora (cada jugador tiene 3 fichas y no podrian poner todas las 		  			* fichas)
		 * yo lo haria en base al tablero si es de 3x3x3 dejaria maximo 3 jugadores
		 */
		cantidadJugadores = Teclado.pedirNumeroEntreIntervalo(mensajeDeNroJugadores, 2, 8);
		for (int i = 0; i < cantidadJugadores ; i++) {
			String mensajePorJugador = "Ingrese el nombre del jugador numero " + (i+1);
			String nombre = Teclado.pedirString(mensajePorJugador);
            ColoresDisponibles color = this.solicitarColor();
            agregarColorTomado(color);
            Fichas ficha = this.solicitarFicha();
            agregarFichaTomada(ficha);
            //Fichas fichaAsignada = fichasDisponibles[i % fichasDisponibles.length];
            //FIXME: estan hardodeadas la cantidad de fichas y cartas
            /*
             * Cantidad de cartas: la preguntaste antes, mantener coherencia
             * Cantidad de fichas: es el largo de alguno de lo lados del tablero (es cuadrado asi que agarra cualquiera)
             */
			Jugador jugador = new Jugador(nombre,100,10,ficha,color,cartas);
			this.listaJugadores.agregarElemento(jugador);
		}
	}
	
	
	public void gestionarTurnos(Lista<Jugador> jugadores,Tablero tablero,Mazo mazo) throws Exception {
		//TODO:validar todos los parametros
		//FIXME: ahora se juega un solo turno, ver de que forma repetir los turnos hasta qyue alguien gane
        jugadoresRobanCartas(jugadores, mazo); //todos los jugadores roban cartas
        jugadores.iniciarCursor(); // Reiniciar el cursor al comienzo de la lista
        while (jugadores.avanzarCursor()) {
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
            int numeroAleatorio = this.tirarDado();
            jugadorActual.robarCartas(numeroAleatorio,mazo); // tirar dado y robar cartas
            System.out.println(jugadorActual.getNombreJugador() + " roba " + numeroAleatorio + " cartas.");
            jugadores.avanzarCursor();
        }
        
    }
    private void moverFicha (Jugador jugadorActual,Tablero tablero) throws Exception{
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
    
    private void colocarFicha (Jugador jugadorActual, Tablero tablero) throws Exception{
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
    	/*
    	 * Esto podria ser un metodo de jugador, jugadorActual.mostrarCartas()
    	 */
        for (int i=1; i < jugadorActual.getCantidadCartas();i++){ // AAAAAAAAAAA
            System.out.println("En la posicion " + i + " se tiene la carta " + jugadorActual.getCartas().obtenerDato(i));
        }
        String mensaje = "Ingrese el numero de la carta que desea utilizar";
        int nroCarta = Teclado.pedirNumeroEntreIntervalo(mensaje, 1,jugadorActual.getCantidadCartas());
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

    private Casillero obtenerCasillero(Lista<Integer> coordenadas,Tablero tablero) throws Exception{
        this.obtenerCoordenadas(coordenadas,tablero);
        Casillero casillero = new Casillero(coordenadas.obtenerDato(0),coordenadas.obtenerDato(1),coordenadas.obtenerDato(2));
        return casillero;
    }
    
    //limite cartas
    public int limiteCartas() throws Exception{
        String mensaje = "Ingrese el limite de cartas con el que desea jugar, no debe superar el tamaño del tablero seleccionado";
        int cantCartas = Teclado.pedirNumeroEntreIntervalo(mensaje, 3, 99);
        return cantCartas; 
    }

    //tirar dado
    private int tirarDado(){
        Random random = new Random();
        int numeroAleatorio = random.nextInt(6) + 1;
        return numeroAleatorio;
    }
    
   

    public int obtenerTamonioTablero() throws Exception{
        int tamañoTablero;
        int cantidadJugadores = this.listaJugadores.getLongitud();
        String mensaje = "Elija la cantidad de casillas NxN que tendra el tablero, el minimo esta sujeto a la cantidad de jugadores";
        //System.out.println("Este minimo para 2-4 jugadores es de 4, para 5-6 es de 5 y para 7-8 es de 6. ");
        if (cantidadJugadores<5){
            tamañoTablero = Teclado.pedirNumeroEntreIntervalo(mensaje, 4, 99);   
        } else if (cantidadJugadores<7){
            tamañoTablero = Teclado.pedirNumeroEntreIntervalo(mensaje, 5, 99);   
        } else {
            tamañoTablero = Teclado.pedirNumeroEntreIntervalo(mensaje, 6, 99);
        }
        return tamañoTablero;
    }

}
