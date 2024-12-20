package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import cartas.Carta;
import tateti.Colores;
import tateti.Fichas;
import tateti.Jugador;
import tateti.Mazo;
import utilidades.Lista;

public class TestJugadorYCartas {
	@Test
	public void testInicializarNombreInvalido() {
		Exception exception = assertThrows(Exception.class, () -> {
	        new Jugador(" ", 4, 5, Fichas.CIRCULO, Colores.AMARILLO, null);
		});
		String mensajeDeErrorEsperado = "El nombre no es válido";
	    String mensajeDeErrorRecibido = exception.getMessage();
	    assertTrue(mensajeDeErrorRecibido.equals(mensajeDeErrorEsperado));
	}
	
	@Test
	public void testInicializarCantidadesInvalidas() {
		Exception exception1 = assertThrows(Exception.class, () -> {
	        new Jugador("Pedro", 0, 5, Fichas.CIRCULO, Colores.AMARILLO, null);
		});
		String mensajeDeErrorEsperado1 = "La cantidad de fichas debe ser positiva";
	    String mensajeDeErrorRecibido1 = exception1.getMessage();
	    assertTrue(mensajeDeErrorRecibido1.equals(mensajeDeErrorEsperado1));
	    
	    Exception exception2 = assertThrows(Exception.class, () -> {
	    	new Jugador("Pedro", 1, -1, Fichas.CIRCULO, Colores.AMARILLO, null);
	    });
	    String mensajeDeErrorEsperado2 = "El valor de cartas máximas debe ser mayor o igual que 0";
	    String mensajeDeErrorRecibido2 = exception2.getMessage();
	    assertTrue(mensajeDeErrorRecibido2.equals(mensajeDeErrorEsperado2));
	}
	
	@Test
	public void testEnumFichas() {
		Exception exception = assertThrows(Exception.class, () -> {
	        new Jugador("Pedro", 4, 5, null, Colores.AMARILLO, null);
		});
		String mensajeDeErrorEsperado = "La ficha debe existir y estar dentro de las siguientes opciones: CUADRADO, CIRCULO, TRIANGULO, CRUZ, RECTANGULO, ESTRELLA, ROMBO, CORAZON";
	    String mensajeDeErrorRecibido = exception.getMessage();
	    assertTrue(mensajeDeErrorRecibido.equals(mensajeDeErrorEsperado));
	}
	
	@Test
	public void testEnumColor() {
		Exception exception = assertThrows(Exception.class, () -> {
	        new Jugador("Pedro", 4, 5, Fichas.CIRCULO, null, null);
		});
		String mensajeDeErrorEsperado = "El color debe existir estar dentro de las siguientes opciones: ROJO, VERDE, AZUL, AMARILLO, ROSA, CELESTE, GRIS, NEGRO";
	    String mensajeDeErrorRecibido = exception.getMessage();
	    assertTrue(mensajeDeErrorRecibido.equals(mensajeDeErrorEsperado));
	}
	
	@Test
	public void testRobarCartas() {
		Lista<Carta> cartas = new Lista<Carta>();
		try {
			Jugador jugador = new Jugador("Juancito", 4, 3, Fichas.CIRCULO, Colores.AMARILLO, cartas); //3: cantCartasMax
			Mazo mazo = new Mazo(4); //El mazo tendrá 4 cartas de cada tipo.
			assertTrue(jugador.getCantidadDeCartas() == 0);
			jugador.robarCartas(2, mazo);
			assertTrue(jugador.getCantidadDeCartas() == 2); //Verifica que tenga dos cartas
			
			System.out.println(jugador.getCartasMaximas());
			
			//Intenta sumar dos cartas más cuando ya tiene dos y el máximo es tres
			Exception exception1 = assertThrows(Exception.class, () -> {
				jugador.robarCartas(2, mazo);
			});
			String mensajeDeErrorEsperado1 = "No se puede agregar esa cantidad de cartas ya que el máximo es 3 y ya se tienen 2";
		    String mensajeDeErrorRecibido1 = exception1.getMessage();
		    assertTrue(mensajeDeErrorRecibido1.equals(mensajeDeErrorEsperado1));
			
			//Intenta robar cero cartas
		    Exception exception2 = assertThrows(Exception.class, () -> {
		    	jugador.robarCartas(0, mazo);
			});
			String mensajeDeErrorEsperado2 = "Se debe robar por lo menos una carta";
		    String mensajeDeErrorRecibido2 = exception2.getMessage();
		    assertTrue(mensajeDeErrorRecibido2.equals(mensajeDeErrorEsperado2));
		    
		    //Intenta robar sin un mazo del cual hacerlo
		    Exception exception3 = assertThrows(Exception.class, () -> {
		    	jugador.robarCartas(1, null);
			});
			String mensajeDeErrorEsperado3 = "No se indicó un mazo del que robar";
		    String mensajeDeErrorRecibido3 = exception3.getMessage();
		    assertTrue(mensajeDeErrorRecibido3.equals(mensajeDeErrorEsperado3));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
