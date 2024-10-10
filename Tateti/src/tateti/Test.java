package tateti;

public class Test {

    public static void main(String[] args) throws Exception {
        Tablero tablero = new Tablero(9, 6, 3);
        tablero.colocarFicha(2, 2, 2, 5);
        tablero.getFicha(2, 2, 2).establecerEntorno(tablero);
        //tablero.imprimirTablero();
    }
}
