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
			tablero.establecerEntornos();
			jugador1 = new Jugador("Carlos", 1, 5, 4, Fichas.CIRCULO, 'O', 1);
			jugador2 = new Jugador("Pedro", 2, 5, 4, Fichas.CRUZ, 'X', 2);
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
	public void moverFichaEnCadaDireccionAdyacente() {
		try {
			//mueve a la derecha
			tablero.colocarFicha(0, 0, 0, jugador1);
			tablero.moverFicha(1, 0, 0, tablero.getCasillero(0, 0, 0));  //TODO: no sé si soy yo el problema
																		//pero siento que está raro
			assertTrue(tablero.getCasillero(0, 0, 0).estaVacio());
			assertFalse(tablero.getCasillero(1, 0, 0).estaVacio());
			
			//mueve hacia abajo
			tablero.moverFicha(1, 1, 0, tablero.getCasillero(1, 0, 0));
			assertTrue(tablero.getCasillero(1, 0, 0).estaVacio());
			assertFalse(tablero.getCasillero(1, 1, 0).estaVacio());

			//mueve hacia la izquierda
			tablero.moverFicha(0, 1, 0, tablero.getCasillero(1, 1, 0));
			assertTrue(tablero.getCasillero(1, 1, 0).estaVacio());
			assertFalse(tablero.getCasillero(0, 1, 0).estaVacio());

			//mueve hacia arriba (vuelve a posición original)
			tablero.moverFicha(0, 0, 0, tablero.getCasillero(0, 1, 0));
			assertTrue(tablero.getCasillero(0, 1, 0).estaVacio());
			assertFalse(tablero.getCasillero(0, 0, 0).estaVacio());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void moverFichaALugarNoAdyacente() {
		try {
			tablero.colocarFicha(0, 0, 0, jugador1);
			Exception exception = assertThrows(Exception.class, () -> {
				tablero.moverFicha(2, 0, 0, tablero.getCasillero(0, 0, 0));
			});
			String mensajeDeErrorEsperado = "Las casillas indicadas [0, 0, 0] (ubicación original) y [2, 0, 0] (ubicación de destino) no son adyacentes";
		    String mensajeDeErrorRecibido = exception.getMessage();
		    assertTrue(mensajeDeErrorRecibido.equals(mensajeDeErrorEsperado));

		    //verifica que la ficha sigue en la ubicacionOriginal
			assertFalse(tablero.getCasillero(0, 0, 0).estaVacio());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void moverFichaDeUnJugadorInvalido() {
		//TODO: mejorar nombre lol, la idea es q jugador 1 ponga una
		//ficha y jugador 2 trate de moverla a ver qué pasa
	}
	
	@Test
	public void moverFichaBloqueada(){
		try {
			tablero.colocarFicha(0, 0, 0, jugador1);
			tablero.getCasillero(0, 0, 0).alternarBloqueo();
			Exception exception = assertThrows(Exception.class, () -> {
				tablero.moverFicha(1, 0, 0, tablero.getCasillero(0, 0, 0));
			});
			String mensajeDeErrorEsperado = "No se puede mover una ficha que se encuentra bloqueada en un casillero.";
		    String mensajeDeErrorRecibido = exception.getMessage();
		    assertTrue(mensajeDeErrorRecibido.equals(mensajeDeErrorEsperado));
		    
		    //verificar que el casillero al que se quiso mover porque era un movimiento inválido está vacío
		    assertTrue(tablero.getCasillero(1, 0, 0).estaVacio());
		    //verificar que la ficha sigue estando en el casillero original bloqueado
		    assertFalse(tablero.getCasillero(0, 0, 0).estaVacio());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void moverFichaQueNoEsta() {
		try {
			//verifica que no hay ninguna ficha
			assertTrue(tablero.getCasillero(0, 0, 0).estaVacio());
			Exception exception = assertThrows(Exception.class, () -> {
				tablero.moverFicha(1, 0, 0, tablero.getCasillero(0, 0, 0));
			});
			String mensajeDeErrorEsperado = "No se puede mover una ficha que no está en la ubicación original.";
		    String mensajeDeErrorRecibido = exception.getMessage();
		    assertTrue(mensajeDeErrorRecibido.equals(mensajeDeErrorEsperado));
		    
		    //verifica que el casilleroOriginal siga vacío
		    assertTrue(tablero.getCasillero(0, 0, 0).estaVacio());
		    
		    //verifica que de todas formas no haya aparecido una ficha en el casilleroDestino
		    assertTrue(tablero.getCasillero(1, 0, 0).estaVacio());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void moverADondeYaHayFicha() {
		try {
		tablero.colocarFicha(0, 0, 0, jugador1);
		tablero.colocarFicha(1, 0, 0, jugador2);
		Exception exception = assertThrows(Exception.class, () -> {
			tablero.moverFicha(1, 0, 0, tablero.getCasillero(0, 0, 0));
		});
		String mensajeDeErrorEsperado = "El casillero [1, 0, 0] (ubicacion de destino) ya tiene una ficha colocada.";
	    String mensajeDeErrorRecibido = exception.getMessage();
	    assertTrue(mensajeDeErrorRecibido.equals(mensajeDeErrorEsperado));
		
	    //verifica que el jugador del casillero al que se quiso mover siga siendo el mismo
		assertTrue(tablero.getCasillero(1,0,0).getJugador().getNombreJugador().equals(jugador2.getNombreJugador()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void moverALosExtremos() {
		try {
			//Intenta mover a la izquierda cuando estoy en el borde izquierdo
			tablero.colocarFicha(0, 0, 0, jugador1);
			Exception exception1 = assertThrows(Exception.class, () -> {
				tablero.moverFicha(-1, 0, 0, tablero.getCasillero(0, 0, 0));
			});
			String mensajeDeErrorEsperado1 = "La posición del casillero debe ser válida.";
		    String mensajeDeErrorRecibido1 = exception1.getMessage();
		    assertTrue(mensajeDeErrorRecibido1.equals(mensajeDeErrorEsperado1));
		    
		    //Intenta mover la ficha hacia arriba cuando estoy en el borde de arriba
		    Exception exception2 = assertThrows(Exception.class, () -> {
		    	tablero.moverFicha(0, -1, 0, tablero.getCasillero(0, 0, 0));
			});
			String mensajeDeErrorEsperado2 = "La posición del casillero debe ser válida.";
		    String mensajeDeErrorRecibido2 = exception2.getMessage();
		    assertTrue(mensajeDeErrorRecibido2.equals(mensajeDeErrorEsperado2));
		    
		    //Intenta mover a la derecha cuando estoy en el borde derecho
		    tablero.colocarFicha(2, 2, 2, jugador2);
		    Exception exception3 = assertThrows(Exception.class, () -> {
		    	tablero.moverFicha(3, 2, 2, tablero.getCasillero(2, 2, 2));
			});
			String mensajeDeErrorEsperado3 = "La posicion debe estar entre 1 y tamaño. (Es 4)";
		    String mensajeDeErrorRecibido3 = exception3.getMessage();
		    assertTrue(mensajeDeErrorRecibido3.equals(mensajeDeErrorEsperado3));
		    
		    //Intenta mover la ficha hacia abajo cuando estoy en el borde de abajo
		    Exception exception4 = assertThrows(Exception.class, () -> {
		    	tablero.moverFicha(2, 2, 3, tablero.getCasillero(2, 2, 2));
			});
			String mensajeDeErrorEsperado4 = "La posicion debe estar entre 1 y tamaño. (Es 4)";
		    String mensajeDeErrorRecibido4 = exception4.getMessage();
		    assertTrue(mensajeDeErrorRecibido4.equals(mensajeDeErrorEsperado4));
		    
		    //verifica que después de todos los intentos las fichas sigan en sus casilleroOriginal
		    assertFalse(tablero.getCasillero(2, 2, 2).estaVacio());
		    assertFalse(tablero.getCasillero(0, 0, 0).estaVacio());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void moverDeUnaCapaAOtra() {
		//TODO: TERMINAR CUANDO SE ARREGLE LO DE MOVER FICHA
		try {
			tablero.colocarFicha(0, 2, 0, jugador1);
			//tablero.moverFicha(0, 0, 1, tablero.getCasillero(0, 2, 0));
			//Las casillas indicadas [0, 2, 0] (ubicación original) y [0, 0, 1] (ubicación de destino) no son adyacentes
			tablero.colocarFicha(0, 0, 1, jugador1);
			tablero.imprimir();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
