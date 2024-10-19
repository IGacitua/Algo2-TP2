package tateti;

import java.io.File;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        /*try {
            Tablero tablero = new Tablero(3, 3, 1, 3);
            Jugador jugadorUno = new Jugador("Pepe", 1, 10, 5);
            Jugador jugadorDos = new Jugador(2, 10, 5);
            tablero.establecerEntornos();
            tablero.colocarFicha(0, 0, 0, jugadorUno);
            tablero.colocarFicha(1, 0, 0, jugadorUno);
            tablero.colocarFicha(2, 1, 0, jugadorUno);
            tablero.moverFicha(2, 0, 0, tablero.getCasillero(2, 1, 0));
            tablero.colocarFicha(0, 1, 0, jugadorDos);
            tablero.moverFicha(1, 0, 0, tablero.getCasillero(0, 1, 0));
            tablero.imprimirTablero();
        } catch (Exception e) {
            System.out.println(e);
        }*/

        System.out.println(new File("").getAbsolutePath());
        try {
            Imagen imagen = new Imagen("C:/Users/Gacitua/Documents/UBA/Algoritmos y Estructuras de Datos/Tp-2/Tateti/src/imagenes/number_0.bmp");
            imagen.bordear(1, 64, 64, 64);
            for (int i = 1; i < 10; i++) {
                String path = "C:/Users/Gacitua/Documents/UBA/Algoritmos y Estructuras de Datos/Tp-2/Tateti/src/imagenes/number_" + i + ".bmp";
                Imagen adicional = new Imagen(path);
                adicional.recolorizar((new Random()).nextInt(255), (new Random()).nextInt(255), (new Random()).nextInt(255));
                adicional.bordear(1, 64, 64, 64);
                imagen = imagen.añadirImagenAbajo(adicional);
            }
            imagen.exportar("Combo Bordeado");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
