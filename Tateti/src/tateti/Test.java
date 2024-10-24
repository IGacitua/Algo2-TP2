package tateti;

import cartas.Carta;
import cartas.CartaAnularCasillero;
import cartas.CartaBloquearFicha;
import cartas.CartaPerderTurno;

public class Test {

    public static void main(String[] args) {
    	try {
			Jugador jugador = new Jugador(1, 4, 10);
			
			Carta[] cartas = {
					new CartaAnularCasillero(),
					new CartaBloquearFicha(),
					new CartaPerderTurno(),
					new CartaAnularCasillero(),
					new CartaBloquearFicha(),
					new CartaPerderTurno()
			};
			Mazo mazo = new Mazo(cartas);
			
			jugador.robarCartas(1, mazo);
			jugador.robarCartas(3, mazo);
			
			for (Carta carta : jugador.getCartas()) {
				System.out.println(carta);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	/*
        try {
            Tablero tablero = new Tablero(5, 5, 1, 3);
            tablero.establecerEntornos();
            tablero.colocarFicha(1, 1, 0, new Jugador(10, 9, 1));
            tablero.exportar();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        /*System.out.println(new File("").getAbsolutePath());
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
        }*/
    }
}
