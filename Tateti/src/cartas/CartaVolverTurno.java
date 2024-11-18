package cartas;

import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;

public class CartaVolverTurno extends Carta {

    /**
     * pre: jugadorActual, listaJugadores, tablero y mazo deben ser válidos
     *
     * post: Vuelve hacia atrás una ronda completa
     *
     * @param jugadorActual El jugador que juega la carta, no debe ser nulo.
     * @param listaJugadores La lista de jugadores que están el la partida, no
     * debe ser nula.
     * @param tablero El tablero con el que se desarrolla la partida, no debe
     * ser nulo.
     * @param mazo El mazo con el que se juega la partida, no debe ser nulo.
     * @throws Exception Si alguno de los parámetros es inválido.
     */
    @Override
    public void usar(Jugador jugadorActual, Lista<Jugador> listaJugadores, Tablero tablero, Mazo mazo) throws Exception {

        if (jugadorActual == null) {
            throw new Exception("jugadorActual no puede ser null");
        }
        if (listaJugadores == null) {
            throw new Exception("listaJugadores no puede ser null");
        }
        if (tablero == null) {
            throw new Exception("tablero no puede ser null");
        }
        if (mazo == null) {
            throw new Exception("mazo no puede ser null");
        }

        System.out.printf("\n");
        System.out.println("Se regresa atras la ronda!");
        System.out.printf("\n");
        tablero.alternarTablero();
        listaJugadores.iniciarCursor();
        while (!listaJugadores.avanzarCursor()) {
            int fichas = tablero.contarFichas(listaJugadores.obtenerCursor());
            listaJugadores.obtenerCursor().setCantidadDeFichas(listaJugadores.obtenerCursor().getCantidadDeFichasMaxima() - fichas);
        }
    }

    /**
     * @return Devuelve el nombre de la carta.
     */
    @Override
    public String toString() {
        return "Carta Volver Turno";
    }

}
