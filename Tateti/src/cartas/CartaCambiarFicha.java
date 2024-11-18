package cartas;

import tateti.Casillero;
import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;

public class CartaCambiarFicha extends Carta {

    /**
     * pre: jugadorActual, listaJugadores, tablero y mazo deben ser válidos
     *
     * post: Se apropia de un Casillero ocupado por la ficha de otro Jugador
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

        boolean casilleroModificado = false;
        Casillero casillero;
        while (!casilleroModificado) {
            try {
                System.out.println("Ingrese a qué casillero quiere cambiarle la ficha: ");
                casillero = this.pedirCasillero(tablero);

                if (casillero.getJugador() == null) {
                    throw new Exception("El casillero indicado debe pertenecer a alguien");
                }
                if (casillero.getJugador().getIdentificacion() == jugadorActual.getIdentificacion()) {
                    throw new Exception("No puedes cambiar tu propia ficha");
                }

                casillero.setJugador(null);
                casillero.setJugador(jugadorActual);
                System.out.printf("\n");
                System.out.println("Se cambió la ficha correctamente.");
                System.out.printf("\n");
                casilleroModificado = true;

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
        return "Carta Cambiar Ficha";
    }
}
