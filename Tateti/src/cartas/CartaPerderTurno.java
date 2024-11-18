package cartas;

import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;

public class CartaPerderTurno extends Carta {

    /**
     * pre: jugadorActual, listaJugadores, tablero y mazo deben ser válidos,
     * post: Hace que el jugador elegido pierda el turno
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

        boolean bloqueado = false;
        this.mostrarJugadores(listaJugadores);

        while (!bloqueado) {
            try {
                System.out.println("Indique que jugador quiere bloquear (por identificacion): ");
                Jugador jugadorObjetivo = this.pedirJugadorPorIdentificacion(listaJugadores);

                if (jugadorObjetivo.getIdentificacion() == jugadorActual.getIdentificacion()) {
                    throw new Exception("No puedes autobloquearte.");
                }
                if (jugadorObjetivo.isPierdeTurno()) {
                    throw new Exception("El jugador pierde el turno.");
                }

                jugadorObjetivo.alternarPierdeTurno();
                System.out.printf("\n");
                System.out.println("El jugador " + jugadorObjetivo.getNombre() + " perdió el turno.");
                System.out.printf("\n");
                bloqueado = true;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * @return Devuelve el nombre de la carta.
     */
    @Override
    public String toString() {
        return "Carta Perder Turno";
    }

}
