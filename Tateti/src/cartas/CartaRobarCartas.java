package cartas;

import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;

public class CartaRobarCartas extends Carta {

    private final static int CANTIDAD_A_ROBAR = 2;

    /**
     * Misma documentación de Carta. post: Roba dos cartas del mazo. Si
     * robándolas supera el máximo de cartas, no roba ninguna y se pierde la
     * carta.
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

        try {
            if (jugadorActual.getCantidadDeCartas() + CANTIDAD_A_ROBAR > jugadorActual.getCartasMaximas()) {
                throw new Exception("No se puede utilizar la carta en este jugador porque excede su maximo");
            }
            System.out.printf("\n");
            System.out.println(jugadorActual.getNombre() + " robó dos cartas correctamente.");
            System.out.printf("\n");
            jugadorActual.descartarCarta(2);
            jugadorActual.robarCartas(CANTIDAD_A_ROBAR, mazo);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve el nombre de la carta.
     */
    @Override
    public String toString() {
        return "Carta Robar " + CANTIDAD_A_ROBAR + " Cartas";
    }
}
