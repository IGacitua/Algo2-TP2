package tateti;

import java.util.Scanner;
import java.io.IOException;
import javax.imageio.ImageIO;

public class JuegoTateti{
    public static void main(String[] args){
    	Scanner teclado = new Scanner(System.in);
    	Lista<Jugador> jugadores = new Lista<>();
    	Mazo mazo = new mazo();
    	Menu menu = new menu();
    	
    	//obtenemos los jugadores
    	jugadores=menu.cargarJugadores();
    	
        //obtenemos los parametros para el tablero
        System.out.println("Elija la cantidad de casillas NxN que tendra el tablero: ");
        int tamanio_n= validarEntero(teclado);
        System.out.println("Elija la cantidad de dimensiones del tablero: ");
        int dim=validarEnteros(teclado);
        Tablero tablero = new Tablero(tamanio_n,tamanio_n,dimensiones);

        //turnos
        menu.gestionarTurnos(tablero,mazo)
        
        
        
    }
}