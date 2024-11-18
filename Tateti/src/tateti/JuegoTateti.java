package tateti;

import utilidades.Herramientas;

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

    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Menu menu = new Menu(); // iniciamos menu
        Tablero tablero;
        Mazo mazo;
        try {
            tablero = menu.inicializarTablero();
            menu.inicializarJugadores(tablero.getTamaño());
            mazo = menu.inicializarMazo();
        } catch (Exception e) {
            // Todo esta validado de tal forma que no deberían haber excepciones.
            System.out.println("Excepcion imposible al empezar el juego!");
            return; // Se sale del programa
        }

        int contadorDeTurno = 1; // Turno: Se reinicia al llegar a cantidad de jugadores
        int contadorDeRonda = 0; // Ronda: Al volver al primer jugador se aumenta
        boolean victoria = false;
        try {
            while (!victoria) {
                if (contadorDeTurno == 1) {
                    contadorDeRonda++;
                    tablero.setTableroAuxiliar(tablero.copiarTablero());
                    tablero.exportar(("Tablero Turno ") + contadorDeRonda);
                }
                Jugador jugadorActual = menu.getListaJugadores().obtenerDato(contadorDeTurno);
                if (jugadorActual.isPierdeTurno()) {
                    jugadorActual.alternarPierdeTurno();
                    contadorDeTurno = aumentarContador(contadorDeTurno, menu);
                } else {
                    // TURNO
                    menu.jugadorRobaCartas(jugadorActual, mazo);
                    menu.jugarFicha(jugadorActual, tablero);
                    tablero.imprimir();
                    if (menu.isVictoria()) {
                        victoria = true;
                        tablero.exportar("Tablero Final");
                    } else {
                        contadorDeTurno = aumentarContador(contadorDeTurno, menu);
                        //menu.jugarCarta(jugadorActual, tablero,mazo);
                    }
                }
            }
            Jugador ganador = menu.getListaJugadores().obtenerDato(contadorDeTurno);
            System.out.printf("Jugador %s%s ", ganador.getColor().getPrintfColor(), ganador.getNombre());
            Herramientas.reiniciarColor();
            System.out.printf("ganó!\n");
        } catch (Exception e) {
            // Todo esta validado de tal forma que no deberían haber excepciones.
            System.out.println("Excepción imposible durante el juego!");
        }
    }
}
