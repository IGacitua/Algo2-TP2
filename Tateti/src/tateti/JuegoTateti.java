package tateti;

import utilidades.Herramientas;

public class JuegoTateti {

	/**
	 * pre: contador y menu deben ser válidos,
	 * post: Devuelve un valor aumentado en uno.
	 * @param contador Debe ser >= 0.
	 * @param menu No debe ser nulo.
	 * @return Devuelve el contador aumentado en uno.
	 * @throws Exception Si el contador es negativo.
	 * @throws Exception Si el menu es nulo.
	 */
    public static int aumentarContador(int contador, Menu menu) throws Exception {
    	if (menu == null) {
    		throw new Exception("El menu no puede ser nulo.");
    	}
    	if (!Herramientas.validarNumeroPositivo(contador)) {
    		throw new Exception("El contador no puede ser negativo.");
    	}
        int valorRetorno;
        if (contador >= menu.getCantidadDeJugadores()) {
            valorRetorno = 1;
        } else {
            valorRetorno = contador + 1;
        }
        return valorRetorno;
    }

    public static void main(String[] args) {
        System.out.println("El directorio donde está trabajando es " + System.getProperty("user.dir") + ". Allí se guardará el tablero.");
        Menu menu = new Menu(); // iniciamos menu
        Tablero tablero;
        Mazo mazo;
        try {
            tablero = menu.inicializarTablero();
            menu.inicializarJugadores(tablero.getTamaño());
            menu.imprimirJugadoresPorPantalla();
            System.out.print("\n");
            mazo = menu.inicializarMazo();
        } catch (Exception e) {
            // Todo esta validado de tal forma que no deberían haber excepciones.
            System.out.println("Excepción al intentar inicializar el tablero, los jugadores o el mazo.");
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
                    System.out.printf("\n");
                    menu.jugarFicha(jugadorActual, tablero);
                    
                    System.out.printf("\n");
                    if (menu.esVictoria()) {
                        victoria = true;
                        tablero.exportar("Tablero Final");
                    } else {
                        contadorDeTurno = aumentarContador(contadorDeTurno, menu);
                        menu.jugarCarta(jugadorActual, tablero,mazo);
                    }
                    tablero.imprimir();
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
