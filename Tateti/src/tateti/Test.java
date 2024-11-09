package tateti;

public class Test {

    public static void main(String[] args) throws Exception {
        Tablero tablero = new Tablero(3);
        Jugador jugadorUno = new Jugador("Pepe", 5, 5, Fichas.CIRCULO, ColoresDisponibles.AZUL, null);
        Jugador jugadorDos = new Jugador("Pedro", 5, 5, Fichas.CUADRADO, ColoresDisponibles.ROJO, null);
        tablero.colocarFicha(0, 1, 1, jugadorDos);
        tablero.colocarFicha(1, 1, 1, jugadorUno);
        tablero.colocarFicha(2, 1, 1, jugadorUno);
        tablero.imprimir();
        tablero.exportar("Tabla Tablita TABLON!");
    }
}
