package tateti;

import utilidades.Lista;
import java.util.Scanner;
import java.util.Random;
import java.io.IOException;
import javax.imageio.ImageIO;
import cartas.Carta;
import cartas.CartaAnularCasillero;
import cartas.CartaBloquearFicha;
import cartas.CartaPerderTurno;

public class Menu {
	//atributos
    private Lista<Jugador> listaJugadores; 
	
	//constructor
    public Menu(Lista<Jugador> listaJugadores) {
        this.teclado = new Teclado();
        // Quizas podriamos crear la lista aca mismo, debido a que se cargan los jugadores, asi que la estas creando 			practicamente
        this.listaJugadores = listaJugadores;
    }
	
	
        
    ///metodos
//------------------------------------------------------------------------------------------------------------------
	public void cargarJugadores(){
		int cant_jugadores;
		System.out.println("Ingrese el numero de jugadores: ");
		cant_jugadores = this.obtenerEntero(teclado,2,8); //
		for (int i=1; i <= cant_jugadores ; i++) {
			System.out.println("Ingrese el nombre del jugador nro" + i + ": ");
			String nombre = Teclado.pedirNombre();
			//color = this.obtenerColor(); VER TEMA CARTAS
		
			Jugador jugador = new Jugador(nombre,i,100,10,Fichas.CIRCULO,'X',color);
			this.listaJugadores.agregarElemento(jugador);
	        //preguntar numero limite de cartas

	        //preguntar colores
	        // Bucle para pedir el color hasta que el usuario ingrese uno válido	
		}
		}
	
	
	public void gestionarTurnos(Lista<Jugador> jugadores,Tablero tablero,Mazo mazo) {
        jugadores.iniciarCursor(); // Reiniciar el cursor al comienzo de la lista
        while (this.jugadores.avanzarCursor()) {
            bool colocarFicha,MoverFicha = false;
            Jugador jugadorActual = jugadores.obtenerCursor();
            jugadorActual.robarCartas(this.tirarDado(),mazo); // tirar dado y robar cartas
            System.out.println(jugadorActual.getNombreJugador() + " roba " + numeroAleatorio + " cartas.");
            //Colocar ficha
            Lista coordAgregar = new Lista();
            System.out.println(jugadorActual.getNombreJugador() + " indique en que coordenadas desea colocar su ficha.");
            this.obtenerCoordenadas(coordenadas,tablero);
            while(!colocarFicha){
                tablero.colocarFicha(coordenadas.obtenerDato(0),coordenadas.obtenerDato(1),coordenadas.obtenerDato(2), jugadorActual);
            }
            //Mover ficha
            Lista coordMover1 = new Lista();
            System.out.println(jugadorActual.getNombreJugador() + " indique que ficha desea mover de coordenada.");
            obtenerCoordenadas(coordMover1,tablero);
            Casillero coordOrigen = new Casillero(coordMover1.obtenederDato(0),coordMover1.obtenerDato(1),coordMover1.obtenerDato(2));
            System.out.println(jugadorActual.getNombreJugador() + " indique hacia que coordenada desea mover su ficha.");
            Lista coordMover2 = new Lista();
            obtenerCoordenadas(coordMover2,tablero);
            while(!moverFicha){
                tablero.moverFicha(coordenadas.obtenerDato(0),coordenadas.obtenerDato(1),coordenadas.obtenerDato(2), coordOrigen);
            }
            jugarCarta(jugadorActual, tablero);
            
        }
    }
            
    public void generarMazoAleatorio(int cantidadCartas) throws Exception {
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
     

    private void jugarCarta(Jugador jugadorActual,Tablero tablero){
        for (int i=1; i < jugador.cartas.getLongitud();i++){
            System.out.println("En la posicion " + i + " se tiene la carta " + jugador.cartas.obtenerDato(i));
        }
        System.out.println("Ingrese el numero de la carta que desea utilizar: ");
        int nroCarta = Teclado.obtenerEntero(1,jugador.cartas.getLongitud());
        Lista coordenadas = new Lista();
        obtenerCoordenadas(coordenadas,tablero);
        try {
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

        
    private void obtenerCoordenadas(Lista coordenadas,Tablero tablero) {
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

    private Casillero obtenerCasillero(Lista coordenadas,Tablero tablero){
        this.obtenerCoordenadas(coordenadas,tablero);
        Casillero casillero = new Casillero(coordenadas.obtenerDato(0),coordenadas.obtenerDato(1),coordenadas.obtenerDato(2));
        return casillero;
    }
    
    //limite cartas
    private int limiteCartas(){
        System.out.println("Ingrese el limite de cartas con el que desea jugar, no debe superar el tamaño del tablero seleccionado: ");
        int cantCartas=Teclado.validarEntero(3,99);
        return cantCartas; 
    }

    //tirar dado
    private int tirarDado(){
        Random random = new Random();
        int numeroAleatorio = random.nextInt(6) + 1;
        return numeroAleatorio;
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