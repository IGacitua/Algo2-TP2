package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tateti.ColoresDisponibles;
import tateti.Fichas;
import tateti.Jugador;
import tateti.Tablero;

public class TestCasilleroYFicha {
	private Tablero tablero;
	private Jugador jugador1;
	private Jugador jugador2;
	
	@BeforeEach
	public void inicializarTableroYJugadores() {
		try {
			tablero = new Tablero(3);
			jugador1 = new Jugador("Carlos", 4, 5, Fichas.CUADRADO, ColoresDisponibles.AMARILLO);
			jugador2 = new Jugador("Pedro", 4, 5, Fichas.RECTANGULO, ColoresDisponibles.VERDE);
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
		try {
			tablero.colocarFicha(0, 2, 0, jugador1);
			tablero.moverFicha(0, 2, 1, tablero.getCasillero(0, 2, 0));
			
			assertTrue(tablero.getCasillero(0, 2, 0).estaVacio());
			assertFalse(tablero.getCasillero(0, 2, 1).estaVacio());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void moverEnDiagonal() {
		try {
			tablero.colocarFicha(0, 0, 0, jugador1);
			tablero.moverFicha(1, 1, 0, tablero.getCasillero(0, 0, 0));
			assertTrue(tablero.getCasillero(0, 0, 0).estaVacio());
			assertFalse(tablero.getCasillero(1, 1, 0).estaVacio());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void adyacenciaDeCapas() {
		try {
			tablero.colocarFicha(0, 0, 0, jugador2);
			
			//Intenta mover a capa correcta pero lugar incorrecto
			Exception exception = assertThrows(Exception.class, () -> {
				tablero.moverFicha(0, 2, 2, tablero.getCasillero(0, 0, 0));
			});
			String mensajeDeErrorEsperado = "Las casillas indicadas [0, 0, 0] (ubicación original) y [0, 2, 2] (ubicación de destino) no son adyacentes";
		    String mensajeDeErrorRecibido = exception.getMessage();
		    assertTrue(mensajeDeErrorRecibido.equals(mensajeDeErrorEsperado));
			
		    //verifica que el casilleroDestino no adyacente esté vacío
		    assertTrue(tablero.getCasillero(0, 2, 2).estaVacio());
			
		    //Intenta mover a capa incorrecta pero lugar correcto
			Exception exception2 = assertThrows(Exception.class, () -> {
				tablero.moverFicha(0, 0, 2, tablero.getCasillero(0, 0, 0));
			});
			String mensajeDeErrorEsperado2 = "Las casillas indicadas [0, 0, 0] (ubicación original) y [0, 0, 2] (ubicación de destino) no son adyacentes";
		    String mensajeDeErrorRecibido2 = exception2.getMessage();
		    assertTrue(mensajeDeErrorRecibido2.equals(mensajeDeErrorEsperado2));
		    
		    //verifica que el casilleroDestino no adyacente esté vacío
		    assertTrue(tablero.getCasillero(0, 0, 2).estaVacio());

		    //Intenta mover a capa correcta y lugar correcto
		    tablero.moverFicha(0, 0, 1, tablero.getCasillero(0, 0, 0));
		    assertTrue(tablero.getCasillero(0, 0, 0).estaVacio());
		    assertFalse(tablero.getCasillero(0, 0, 1).estaVacio());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testVictoriaEnUnaMismaCapa() {
		try {
			//En diagonal
			assertFalse(tablero.colocarFicha(0, 0, 0, jugador1)); //false: no ganó
			assertFalse(tablero.colocarFicha(1, 1, 0, jugador1)); //false: no ganó
			assertTrue(tablero.colocarFicha(2, 2, 0, jugador1)); //true: ganó

			
			//En fila (además se hacen más testeos de movimientos)
			assertFalse(tablero.moverFicha(1, 0, 0, tablero.getCasillero(1, 1, 0))); //mueve el uno de abajo a la derecha del (0,0,0), todavía no ganó
			assertFalse(tablero.moverFicha(2, 1, 0, tablero.getCasillero(2, 2, 0))); //mueve el uno de abajo a la derecha del todo hacia arriba, no ganó
			assertTrue(tablero.moverFicha(2, 0, 0, tablero.getCasillero(2, 1, 0))); //mueve el uno de la 2da fila hacia arriba, ganó
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testVictoriaEnCapasDistintas() {
		try {
			//Entre capas en fila
			assertFalse(tablero.colocarFicha(2, 0, 0, jugador2)); //coloca el dos en la ezquina derecha de la 1era fila, no ganó
			assertFalse(tablero.colocarFicha(2, 0, 1, jugador2)); //coloca el dos en la ezquina derecha de la 2era fila, no ganó
			assertTrue(tablero.colocarFicha(2, 0, 2, jugador2)); //coloca el dos en la ezquina derecha de la 3era fila, ganó

			
			//Entre capas en diagonal
			assertFalse(tablero.colocarFicha(1, 1, 1, jugador1)); //coloca el uno en el centro de la segunda capa, no ganó
			tablero.moverFicha(1, 0, 0, tablero.getCasillero(2, 0, 0)); //muevo el dos a la izquierda
			assertFalse(tablero.colocarFicha(2, 0, 0, jugador1)); //coloca el uno en el extremo derecho de arriba
																//de todo de la primera capa, no ganó
			assertTrue(tablero.colocarFicha(0, 2, 2, jugador1)); //coloca el uno en el extremo izquierdo de la última capa, ganó
			tablero.imprimir(); //TODO: borrar al final:)
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

