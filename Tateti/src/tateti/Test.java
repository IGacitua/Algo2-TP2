package tateti;

public class Test {

    public static void main(String[] args) throws Exception {
        Tablero tablero = new Tablero(9, 6, 3);
        tablero.colocarFicha(1, 1, 1, 5);
        tablero.imprimirTablero();
    }
}
