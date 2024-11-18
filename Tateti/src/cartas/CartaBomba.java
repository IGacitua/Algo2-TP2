package cartas;

import tateti.Casillero;
import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;

public class CartaBomba extends Carta {

    /**
     * pre: jugadorActual, listaJugadores, tablero y mazo deben ser válidos
     *
     * post: Explota el entorno de un casillero (incluyendose a si mismo). Al
     * explotar casilleros, dejan de estar bloqueados o anulados, y se les
     * elimina la ficha que contienen
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

        boolean casilleroValido = false;
        Casillero casillero = null;
        System.out.println("Ingrese qué casillero quiere explotar: ");

        while (!casilleroValido) {
            try {
                casillero = pedirCasillero(tablero);
                casilleroValido = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.printf("\n");
        System.out.println("BOOM!");
        System.out.printf("\n");
        this.explotarEntorno(casillero);
    }

    /**
     * pre: Recibe el casillero "centro"
     *
     * post: Elimina las fichas contenidas en su entorno, incluyendose, y
     * desbloquea los casilleros bloqueados
     *
     * @param casillero No debe ser nulo.
     * @throws Exception
     */
    private void explotarEntorno(Casillero casillero) throws Exception {

        if (casillero == null) {
            throw new Exception("casillero no puede ser null");
        }

        Casillero[][][] entorno = casillero.getEntorno();

        for (Casillero[][] entorno1 : entorno) {
            for (Casillero[] entorno11 : entorno1) {
                for (Casillero entorno111 : entorno11) {
                    if (entorno111 == null) {
                        continue;
                    }
                    if (entorno111.isBloqueado()) {
                        entorno111.alternarBloqueo();
                    }
                    if (entorno111.getJugador() != null) {
                        entorno111.getJugador().aumentarFichas();
                        entorno111.setJugador(null);
                    }
                }
            }
        }

    }

    /**
     * @return Devuelve el nombre de la carta.
     */
    @Override
    public String toString() {
        return "Carta Bomba";
    }

}
