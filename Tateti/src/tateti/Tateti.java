package tateti;

import java.util.Scanner;
import java.io.IOException;
import javax.imageio.ImageIO;

public class JuegoTateti{
    public static void main(String[] args){

        //validacion
        boolean entero_positivo = false;
        Scanner teclado = new Scanner(System.in);
        //obtenemos el nombre del jugador1.
        System.out.println("Ingrese el nombre del primer jugador: ");
        String nombre_1= teclado.nextDato();
        teclado.nextLIne();
        //obtenemos el nombre del jugador2.
        System.out.println("Ingrese el nombre del segundo jugador: ");
        String nombre_2= teclado.nextDato();
        teclado.nextLIne();
        //preguntar numero limite de cartas
        //preguntar colores
        Jugador jugador_1 = new Jugador(nombre_1,1,100,100); //hardcodeado
        Jugador jugador_2 = new Jugador(nombre_2,2,100,100);

        //obtenemos los parametros para el tablero
        System.out.println("Elija la cantidad de casillas NxN que tendra el tablero: ");
        int tamanio_n= validarEntero(teclado);
        System.out.println("Elija la cantidad de dimensiones del tablero: ");
        int dim=validarEnteros(teclado);
        Tablero tablero = new Tablero(tamanio_n,tamanio_n,dimensiones);


        // validar enteros
        public static int validarEntero(Scanner teclado) {
            int numero =0;
            boolean entero_positivo = false;
            while (!entero_positivo){
                try {
                    // tengo un error aca porque la excepcion es para enteros pero necesito un numero en un rango determinado a arreglar
                    numero = teclado.nextInt();
                    if (numero > 0 && numero < 100){
                        entero_positivo = true;
                    }
                } catch (Exception e) {
                    System.out.println("Error: Tenes que ingresar un numero entero positivo menor a 100");
                    teclado.nextLine(); 
                    }
            }
        return numero; 
        }
    }
}