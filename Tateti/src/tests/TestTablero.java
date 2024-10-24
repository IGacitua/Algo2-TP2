package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import tateti.*;

public class TestTablero {
	//TODO: hacer los tests de cada ambito, tablero, jugador,carta, etc.
	private Tablero tablero;
    private Jugador jugador;
	
	@Test
	public void testTableroMenorACero() {
		Exception exception = assertThrows(Exception.class, () -> {
	        new Tablero(0, 0, 0);
		});
		String mensajeDeErrorEsperado = "Los tamaños del tablero y la condición de victoria deben ser mayores a 0.";
	    String mensajeDeErrorRecibido = exception.getMessage();
	    assertTrue(mensajeDeErrorRecibido.equals(mensajeDeErrorEsperado));
	}
	
	@Test
	public void testTableroInvalidoX() {
		Exception exception = assertThrows(Exception.class, () -> {
			new Tablero(0, 5, 5);
		});
		String mensajeDeErrorEsperado = "Los tamaños del tablero y la condición de victoria deben ser mayores a 0.";
	    String mensajeDeErrorRecibido = exception.getMessage();
	    assertTrue(mensajeDeErrorRecibido.equals(mensajeDeErrorEsperado));
	}
	
	@Test
	public void testTableroInvalidoY() {
		Exception exception = assertThrows(Exception.class, () -> {
			new Tablero(5, 0, 5);
		});
		String mensajeDeErrorEsperado = "Los tamaños del tablero y la condición de victoria deben ser mayores a 0.";
	    String mensajeDeErrorRecibido = exception.getMessage();
	    assertTrue(mensajeDeErrorRecibido.equals(mensajeDeErrorEsperado));
	}
	
	@Test
	public void testTableroInvalidoZ() {
		Exception exception = assertThrows(Exception.class, () -> {
			new Tablero(5, 5, 0);
		});
		String mensajeDeErrorEsperado = "Los tamaños del tablero y la condición de victoria deben ser mayores a 0.";
	    String mensajeDeErrorRecibido = exception.getMessage();
	    assertTrue(mensajeDeErrorRecibido.equals(mensajeDeErrorEsperado));
	}
	
	/*@Test
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
            tablero.imprimir();
        } catch (Exception e) {
            System.out.println(e);
        }
	}*/
}
