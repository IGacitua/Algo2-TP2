package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
		String mensajeDeErrorEsperado = "Los tamaños del tablero deben ser mayores a 0.";
	    String mensajeDeErrorRecibido = exception.getMessage();
	    assertTrue(mensajeDeErrorRecibido.equals(mensajeDeErrorEsperado));
	}
	
	@Test
	public void testTableroInvalidoX() {
		//Si es 0 en X
		Exception exception1 = assertThrows(Exception.class, () -> {
			new Tablero(0, 5, 5);
		});
		String mensajeDeErrorEsperado1 = "Los tamaños del tablero deben ser mayores a 0.";
	    String mensajeDeErrorRecibido1 = exception1.getMessage();
	    assertTrue(mensajeDeErrorRecibido1.equals(mensajeDeErrorEsperado1));
	    
	    //Si no es cuadrado en X
  		Exception exception2 = assertThrows(Exception.class, () -> {
  			new Tablero(3, 5, 5);
  		});
  		String mensajeDeErrorEsperado2 = "El tablero debe ser cuadrado.";
  	    String mensajeDeErrorRecibido2 = exception2.getMessage();
  	    assertTrue(mensajeDeErrorRecibido2.equals(mensajeDeErrorEsperado2));
	}
	
	@Test
	public void testTableroInvalidoY() {
		//Si es 0 en Y
		Exception exception1 = assertThrows(Exception.class, () -> {
			new Tablero(5, 0, 5);
		});
		String mensajeDeErrorEsperado1 = "Los tamaños del tablero deben ser mayores a 0.";
	    String mensajeDeErrorRecibido1 = exception1.getMessage();
	    assertTrue(mensajeDeErrorRecibido1.equals(mensajeDeErrorEsperado1));
	    
	    //Si no es cuadrado en Y
  		Exception exception2 = assertThrows(Exception.class, () -> {
  			new Tablero(5, 3, 5);
  		});
  		String mensajeDeErrorEsperado2 = "El tablero debe ser cuadrado.";
  	    String mensajeDeErrorRecibido2 = exception2.getMessage();
  	    assertTrue(mensajeDeErrorRecibido2.equals(mensajeDeErrorEsperado2));
	}
	
	@Test
	public void testTableroInvalidoZ() {
		//Si es 0 en Z
		Exception exception1 = assertThrows(Exception.class, () -> {
			new Tablero(5, 5, 0);
		});
		String mensajeDeErrorEsperado1 = "Los tamaños del tablero deben ser mayores a 0.";
	    String mensajeDeErrorRecibido1 = exception1.getMessage();
	    assertTrue(mensajeDeErrorRecibido1.equals(mensajeDeErrorEsperado1));

	    //Si no es cuadrado en Z
		Exception exception2 = assertThrows(Exception.class, () -> {
			new Tablero(5, 5, 3);
		});
		String mensajeDeErrorEsperado2 = "El tablero debe ser cuadrado.";
	    String mensajeDeErrorRecibido2 = exception2.getMessage();
	    assertTrue(mensajeDeErrorRecibido2.equals(mensajeDeErrorEsperado2));
	}
}
