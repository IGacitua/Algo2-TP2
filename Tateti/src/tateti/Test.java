package tateti;

public class Test {

    public static void main(String[] args) throws Exception {
        Tablero tablero = new Tablero(3, 3, 3, 3);
        //tablero.establecerEntornos();
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                for (int k = 1; k <= 3; k++) {
                    tablero.colocarFicha(i, j, k, k);
                }
            }
        }
        tablero.imprimirTablero();
        System.out.printf("\n\nA");
        tablero.getFicha(2, 2, 2).establecerEntorno(tablero);
    }
}
