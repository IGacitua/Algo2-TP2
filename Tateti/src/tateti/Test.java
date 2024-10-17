package tateti;

public class Test {

    public static void main(String[] args) {
        try {
            Tablero tablero = new Tablero(3, 3, 1, 3);
            Jugador jugadorUno = new Jugador("Pepe", 1, 10);
            Jugador jugadorDos = new Jugador(2, 10);
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
        }
    }
}
