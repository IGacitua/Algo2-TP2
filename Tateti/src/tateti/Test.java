package tateti;

public class Test {

    public static void main(String[] args) throws Exception {
        Tablero tablero = new Tablero(3, 3, 3, 3);
        tablero.establecerEntornos();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (tablero.colocarFicha(i, j, k, k + 1)) {
                        System.out.printf("Win!");
                    }
                }
            }
        }
        tablero.imprimirTablero();
        System.out.printf("\n\n");
    }
}
