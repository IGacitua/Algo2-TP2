package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import tateti.*;

public class TestTablero {
	//TODO: hacer los tests de cada ambito, tablero, jugador,carta, etc.
	private Tablero tablero;
	
	@BeforeEach
	public void incializarTablero() throws Exception {
		Tablero tablero = new Tablero(3,3,3,3);
	}
	
	@Test
	public void Test() {
		try {
            
            Jugador jugadorUno = new Jugador("Pepe", 1, 10, 3);
            Jugador jugadorDos = new Jugador(2, 10, 3);
            tablero.establecerEntornos();
            tablero.colocarFicha(0, 0, 0, jugadorUno);
            tablero.colocarFicha(1, 0, 0, jugadorUno);
            tablero.colocarFicha(2, 1, 0, jugadorUno);
            tablero.moverFicha(2, 0, 0, tablero.getCasillero(2, 1, 0));
            tablero.colocarFicha(0, 1, 0, jugadorDos);
            tablero.moverFicha(1, 0, 0, tablero.getCasillero(0, 1, 0));
            tablero.imprimirTablero();
        } catch (Exception e) {
            System.out.println(e);
        }
	}
}
