package tateti;

import utilidades.Teclado;

public class JuegoTateti {

    public static int aumentarContador(int contador, Menu menu) {
        int valorRetorno;
        if (contador >= menu.getCantidadDeJugadores()) {
            valorRetorno = 1;
        } else {
            valorRetorno = contador + 1;
        }
        return valorRetorno;
    }

    public static void main(String[] args) throws Exception {
        Teclado teclado = new Teclado(); // scanner
        Menu menu = new Menu(); // iniciamos menu
        Tablero tablero = menu.inicializarTablero();
        menu.inicializarJugadores(tablero.getTamaño());
        Mazo mazo = menu.inicializarMazo();

        int contadorDeTurno = 1; // Turno: Se reinicia al llegar a cantidad de jugadores
        int contadorDeRonda = 0; // Ronda: Al volver al primer jugador se aumenta
        boolean victoria = false;
        while (!victoria) {
            if (contadorDeTurno == 1) {
                contadorDeRonda++;
                tablero.exportar(("Tablero Turno ") + contadorDeRonda);
            }
            System.out.printf("Turno de jugador %d.\n", contadorDeTurno);
            Jugador jugadorActual = menu.getListaJugadores().obtenerDato(contadorDeTurno);
            if (jugadorActual.isPierdeTurno()) {
                jugadorActual.alternarPierdeTurno();
                contadorDeTurno = aumentarContador(contadorDeTurno, menu);
            } else {
                // TURNO
                menu.jugadorRobaCartas(jugadorActual, mazo); //TODO: Ver de mover a metodo aca
                menu.jugarFicha(jugadorActual, tablero);
                tablero.imprimir();
                if (menu.isVictoria()) {
                    victoria = true;
                }
                //menu.jugarCarta(jugadorActual, tablero,mazo);

                contadorDeTurno = aumentarContador(contadorDeTurno, menu);
            }
        }
        System.out.printf("Jugador %s ganó!\n", menu.getListaJugadores().obtenerDato(contadorDeTurno).getNombre());
    }
}
