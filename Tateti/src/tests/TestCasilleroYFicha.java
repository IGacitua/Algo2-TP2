package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tateti.*;

public class TestCasilleroYFicha {
	private Tablero tablero;
	private Jugador jugador1;
	private Jugador jugador2;
	
	@BeforeEach
	public void inicializarTableroYJugadores() {
		try {
			tablero = new Tablero(3,3,3);
			jugador1 = new Jugador("Carlos", 1, 5, 4);
			jugador2 = new Jugador("Pedro", 2, 5, 4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void colocarFicha() {
		try {
			tablero.getCasillero(0, 0, 0).estaVacio();
			assertTrue(tablero.getCasillero(0, 0, 0).estaVacio()); //sí está vacío
			tablero.colocarFicha(0, 0, 0, jugador1);
			assertFalse(tablero.getCasillero(0, 0, 0).estaVacio()); //no está vacío
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void colocarFichaEnCasilleroOcupado() {
		try {
			tablero.colocarFicha(0, 0, 0, jugador1);
			Exception exception = assertThrows(Exception.class, () -> {
				tablero.colocarFicha(0, 0, 0, jugador2);
			});
			String mensajeDeErrorEsperado = "El casillero ya está ocupado por " + tablero.getCasillero(0, 0, 0).getJugador().getNombreJugador();
		    String mensajeDeErrorRecibido = exception.getMessage();
		    assertTrue(mensajeDeErrorRecibido.equals(mensajeDeErrorEsperado));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void bloquearCasillero() {
		try {
			tablero.colocarFicha(0, 0, 0, jugador1);
			tablero.getCasillero(0, 0, 0).alternarBloqueo(); //pasa a estar bloqueado
			assertTrue(tablero.getCasillero(0, 0, 0).isBloqueado());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void colocarFichaEnTableroBloqueado() {
		try {
			tablero.colocarFicha(0, 0, 0, jugador1);
			tablero.getCasillero(0, 0, 0).alternarBloqueo(); //pasa a estar bloqueado
			Exception exception = assertThrows(Exception.class, () -> {
				tablero.colocarFicha(0, 0, 0, jugador2);
			});
			String mensajeDeErrorEsperado = "No se puede colocar una ficha en una casilla que está bloqueada.";
		    String mensajeDeErrorRecibido = exception.getMessage();
		    assertTrue(mensajeDeErrorRecibido.equals(mensajeDeErrorEsperado));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void moverFicha() {
		//TODO
	}
	
	@Test
	public void moverFichaDeUnJugadorInvalido() {
		//TODO: mejorar nombre lol, la idea es q jugador 1 ponga una
		//ficha y jugador 2 trate de moverla a ver qué pasa
	}
	
	@Test
	public void moverFichaBloqueada(){
		//TODO
	}
	
	@Test
	public void moverFichaQueNoEsta() {
		//TODO
	}
}
