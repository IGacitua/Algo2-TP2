package tateti;

import utilidades.Lista;
import java.util.Scanner;
import java.util.Random;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Menu {
	//atributos
    private Teclado teclado; 
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
		for (int i; i <= cant_jugadores ; i++) {
			System.out.println("Ingrese el nombre del jugador nro" + i + ": ");
			String nombre = Teclado.pedirNombre();
			color = this.obtenerColor();
		
			Jugador jugador = new Jugador(nombre,i,100,10,Fichas.CIRCULO,'X',color)
			this.listaJugadores.agregarElemento(jugador);
			i++;
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
            // Jugar carta
        }
    }
            
        
        
   public void obtenerCoordenadas(Lista coordenadas,Tablero tablero) {
	   System.out.println("Ingrese la coordenada X: ");
	   int x=obtenerEntero(teclado,0,99);// faltaria donde esta hardtipeado ingresar un geter de tablero
	   coordenadas.agregarElemento(1,x);
	   System.out.println("Ingrese la coordenada Y: ");
	   int y=obtenerEntero(teclado,0,99);  //faltaria donde esta hardtipeado ingresar un geter de tablero
	   coordenadas.agregarElemento(2,y);
	   System.out.println("Ingrese la coordenada Z: ");
	   int z=obtenerEntero(teclado,0,99);  //faltaria donde esta hardtipeado ingresar un geter de tablero
	   coordenadas.agregarElemento(3,z);
    }
    
    //tirar dado
    public int tirarDado(){
        Random random = new Random();
        int numeroAleatorio = random.nextInt(6) + 1;
        return numeroAleatorio;
    }

    //iniciar tablero
    
    // validar enteros
    public static int obtenerEntero(Teclado teclado) {
        int numero =0;
        boolean entero_valido = false;
        while (!entero_valido){
            try {
                numero = teclado.nextInt();
                if (numero > 3 && numero < limite){
                    entero_valido = true;
                } else {
                	System.outprintln("Tenes que ingresar un numero en el rango 3-100.");
                }
            } catch (Exception e) {
                System.out.println("Error: No es un numero entero.");
                teclado.nextLine(); 
                }
        }
    return numero; 
    }
    
    public static int obtenerEntero(Teclado teclado,int minimo,int limite) {
        int numero =0;
        boolean entero_valido = false;
        while (!entero_valido){
            try {
                // tengo un error aca porque la excepcion es para enteros pero necesito un numero en un rango determinado a arreglar
                numero = teclado.nextInt();
                if (numero > minimo && numero < limite){
                    entero_valido = true;
                } else {
                	System.outprintln("Tenes que ingresar un numero en el rango", minimo, "-", maximo, ".");
                }
            } catch (Exception e) {
                System.out.println("Error: No es un numero entero.");
                teclado.nextLine(); 
                }
        }
    return numero; 
    }
    
    // validar color
    public static color obtenerColor(Teclado teclado,String nombre) {
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
    }

}