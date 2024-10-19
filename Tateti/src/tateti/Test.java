package tateti;

import java.io.File;

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
            Imagen imagenUno = new Imagen("Tp-2/Tateti/src/imagenes/number_0.bmp"); // El path relativo es desde el proceso que lo abre
            Imagen imagenDos = new Imagen("Tp-2/Tateti/src/imagenes/number_1.bmp");
            imagenDos.recolorizar(128, 0, 255);
            imagenUno.añadirImagenAbajo(imagenDos).exportar("imagenDoble");

            System.out.printf("\n\n\n");
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }
}
