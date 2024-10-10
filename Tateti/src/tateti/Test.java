package tateti;

public class Test {

    public static void main(String[] args) throws Exception {
        Tablero tablero = new Tablero(9, 6, 3);
        for (int i = 0; i < 256; i++) {
            int randomPlayer = (int) (Math.random() * 5);
            int randomPosX = (int) (Math.random() * 8) + 1;
            int randomPosY = (int) (Math.random() * 6) + 1;
            int randomPosZ = (int) (Math.random() * 3) + 1;
            tablero.colocarFicha(randomPosX, randomPosY, randomPosZ, randomPlayer);
        }
        tablero.imprimirTablero();
    }
}
