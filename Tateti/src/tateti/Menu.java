package tateti;

import utilidades.Lista;
import java.util.Random;
import java.util.Scanner;
import java.util.Scanner;
import java.io.IOException;
import cartas.Carta;
import tateti.ColoresDisponibles;
import cartas.CartaAnularCasillero;
import cartas.CartaBloquearFicha;
import cartas.CartaPerderTurno;

public class Menu {
	//atributos
    private Lista<Jugador> listaJugadores; 
	
	//constructor
    public Menu(Lista<Jugador> listaJugadores) {
        // Quizas podriamos crear la lista aca mismo, debido a que se cargan los jugadores, asi que la estas creando 			practicamente
        this.listaJugadores = listaJugadores;
        //agregar mazo?
    }
	
	
        
    ///metodos
//------------------------------------------------------------------------------------------------------------------
	public void cargarJugadores() throws Exception{
		int cantidadJugadores;
        Fichas[] fichasDisponibles = Fichas.values();
        Lista<Carta> cartas = new Lista<>();
		System.out.println("Ingrese el numero de jugadores: (minimo 2 - maximo 8)");
		cantidadJugadores = Teclado.pedirNumero(2,8); //
		for (int i=0; i < cantidadJugadores ; i++) {
			System.out.println("Ingrese el nombre del jugador nro" + i+1 + ": ");
			String nombre = Teclado.pedirNombre();
            ColoresDisponibles color = this.solicitarColor();
            Fichas fichaAsignada = fichasDisponibles[i % fichasDisponibles.length];
			Jugador jugador = new Jugador(nombre,100,10,fichaAsignada,color,cartas);
			this.listaJugadores.agregarElemento(jugador);
	        //preguntar numero limite de cartas , fichas maximas sujeto a tablero y jugadores_???	
		}
		}
	
	
	public void gestionarTurnos(Lista<Jugador> jugadores,Tablero tablero,Mazo mazo) throws Exception {
		//TODO:validar todos los parametros
		//TODO: Modularizar los metodos de jugar ficha y mover carta}
		//FIXME: Si podes Trata de usar nombres enteros y no acortados, se menciona en el enunciado del tp
        jugadores.iniciarCursor(); // Reiniciar el cursor al comienzo de la lista
        
        while (jugadores.avanzarCursor()) {
            boolean colocarFicha = false;
            boolean moverFicha = false;
            
            Jugador jugadorActual = jugadores.obtenerCursor();
            //FIXME:las cartas se levantan antes del turno, para todos los jugadores
            int numeroAleatorio = this.tirarDado();
            jugadorActual.robarCartas(numeroAleatorio,mazo); // tirar dado y robar cartas
            System.out.println(jugadorActual.getNombreJugador() + " roba " + numeroAleatorio + " cartas.");
            
            //Colocar ficha
            Lista<Integer> coordAgregar = new Lista<>();
            System.out.println(jugadorActual.getNombreJugador() + " indique en que coordenadas desea colocar su ficha.");
            this.obtenerCoordenadas(coordAgregar,tablero); // q haces
            while(!colocarFicha){
                tablero.colocarFicha(coordAgregar.obtenerDato(0),coordAgregar.obtenerDato(1),coordAgregar.obtenerDato(2), jugadorActual);
            }
            //Mover ficha
            Lista<Integer> coordMover1 = new Lista<>();
            System.out.println(jugadorActual.getNombreJugador() + " indique que ficha desea mover de coordenada.");
            //FIXME:ahora tenemos un teclado para hacer esto:
            this.obtenerCoordenadas(coordMover1,tablero);
            Casillero coordOrigen = new Casillero(coordMover1.obtenerDato(0),coordMover1.obtenerDato(1),coordMover1.obtenerDato(2));
            System.out.println(jugadorActual.getNombreJugador() + " indique hacia que coordenada desea mover su ficha.");
            Lista<Integer> coordMover2 = new Lista<>();
            this.obtenerCoordenadas(coordMover2,tablero);
            //origen = coordmover1
            while(!moverFicha){
                tablero.moverFicha(coordMover2.obtenerDato(0),coordMover2.obtenerDato(1),coordMover2.obtenerDato(2), coordOrigen);
            }
            jugarCarta(jugadorActual, tablero);
            
        }
    }

    public void generarMazoAleatorio(Lista<Carta> cartas,int cantidadCartas) throws Exception {
        Random random = new Random();

        for (int i = 0; i < cantidadCartas; i++) {
            int tipoCarta = random.nextInt(3); // 0, 1 o 2 para los tres tipos de carta

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
     
    private void jugarCarta(Jugador jugadorActual,Tablero tablero) throws Exception{
        for (int i=1; i < jugadorActual.getCantidadCartas();i++){ // AAAAAAAAAAA
            System.out.println("En la posicion " + i + " se tiene la carta " + jugadorActual.cartas.obtenerDato(i));
        }
        System.out.println("Ingrese el numero de la carta que desea utilizar: ");
        int nroCarta = Teclado.pedirNumero(1,jugadorActual.getCantidadCartas());
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
	   System.out.println("Ingrese la coordenada X: ");
	   int x=Teclado.pedirNumero(0,tablero.getTamaño());
	   coordenadas.agregarElemento(1,x);
	   System.out.println("Ingrese la coordenada Y: ");
	   int y=Teclado.pedirNumero(0,tablero.getTamaño());
	   coordenadas.agregarElemento(2,y);
	   System.out.println("Ingrese la coordenada Z: ");
	   int z=Teclado.pedirNumero(0,tablero.getTamaño());
	   coordenadas.agregarElemento(3,z);
    }

    private Casillero obtenerCasillero(Lista<Integer> coordenadas,Tablero tablero) throws Exception{
        this.obtenerCoordenadas(coordenadas,tablero);
        Casillero casillero = new Casillero(coordenadas.obtenerDato(0),coordenadas.obtenerDato(1),coordenadas.obtenerDato(2));
        return casillero;
    }
    
    //limite cartas
    public int limiteCartas(){
        System.out.println("Ingrese el limite de cartas con el que desea jugar, no debe superar el tamaño del tablero seleccionado: ");
        int cantCartas=Teclado.pedirNumero(3,99);
        return cantCartas; 
    }

    //tirar dado
    private int tirarDado(){
        Random random = new Random();
        int numeroAleatorio = random.nextInt(6) + 1;
        return numeroAleatorio;
    }
    
    private ColoresDisponibles solicitarColor() {
        try (Scanner teclado = new Scanner(System.in)) {
            ColoresDisponibles colorElegido = null;
            // Muestra lista de colores
            System.out.println("Elija el color de la lista que desees utilizar: ");
            System.out.println(ColoresDisponibles.obtenerColores());
            boolean colorValido = false;
            while (!colorValido) {
                System.out.print("Ingresa el color: ");
                String input = teclado.nextLine().toUpperCase(); // converitmos a mayus para que coincda con el enum
                try {
                    // Intentamos convertir la entrada del usuario a un valor de ColoresDisponibles
                    colorElegido = ColoresDisponibles.valueOf(input);
                    colorValido = true; // Si llega aquí, la entrada fue válida
                } catch (IllegalArgumentException e) {
                    System.out.println("Este color no es valido, intentar de nuevo.");
                }
            }

            System.out.println("Eligiste el color: " + colorElegido);
            teclado.close();
            return colorElegido;
        }
    }

    public int obtenerTamonioTablero(){
        int tamañoTablero;
        int cantidadJugadores = this.listaJugadores.getLongitud();
        System.out.println("Elija la cantidad de casillas NxN que tendra el tablero, el minimo esta sujeto a la cantidad de jugadores. ");
        System.out.println("Este minimo para 2-4 jugadores es de 4, para 5-6 es de 5 y para 7-8 es de 6. ");
        if (cantidadJugadores<5){
            tamañoTablero = Teclado.pedirNumero(4, 99);   
        } else if (cantidadJugadores<7){
            tamañoTablero = Teclado.pedirNumero(5, 99);   
        } else {
            tamañoTablero = Teclado.pedirNumero(6, 99);
        }
        return tamañoTablero;

    } 


    
    // validar color
   /*  private static color obtenerColor(Teclado teclado,String nombre) {
        Color colorSeleccionado = null;
        boolean colorValido = false;
        while (!colorValido) {
            System.out.print("Ingrese el color que va a utilizar "+ nombre + ": ");
            String inputColor = teclado.nextLine().toUpperCase();

            try {
                colorSeleccionado = Color.valueOf(inputColor);  // Convertir el input al enum
                colorValido = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: El color ingresado no es válido. Inténtalo nuevamente. ");
            }
        }
       return colorSeleccionado;
    }                                                                                                       */

}