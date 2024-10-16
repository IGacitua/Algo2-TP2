package tateti;

public class Test {

    public static void main(String[] args) {
        try {
            Tablero tablero = new Tablero(3, 3, 3, 3);
            tablero.establecerEntornos();
            boolean win1 = tablero.colocarFicha(0, 0, 0, 2);
            boolean win2 = tablero.colocarFicha(1, 1, 1, 2);
            boolean win3 = tablero.colocarFicha(2, 2, 2, 2);
            System.out.printf("Wins: %b, %b, %b.", win1, win2, win3);
            tablero.imprimirTablero();
            System.out.printf("\n\n");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
