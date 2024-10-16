package tateti;

public class Test {

    public static void main(String[] args) {
        try {
            Tablero tablero = new Tablero(3, 3, 3, 3);
            Jugador jugadorUno = new Jugador("Pepe", 1, 10);
            tablero.establecerEntornos();
            boolean win1 = tablero.colocarFicha(0, 0, 0, jugadorUno);
            boolean win2 = tablero.colocarFicha(1, 1, 1, jugadorUno);
            boolean win3 = tablero.colocarFicha(2, 2, 2, jugadorUno);
            System.out.printf("Wins: %b, %b, %b.", win1, win2, win3);
            tablero.imprimirTablero();
            System.out.printf("\n\n");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
