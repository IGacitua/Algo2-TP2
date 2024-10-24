package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import tateti.*;

public class TestTablero {
	//TODO: hacer los tests de cada ambito, tablero, jugador,carta, etc.
	//le agrego los unused asi deja de tirar los Warnigns
	@SuppressWarnings("unused")
	private Tablero tablero;
    @SuppressWarnings("unused")
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
	
	@Test
	public void testEntorno() {
		//establecer entorno deberia estar en el test de casillero o tablero
		//establecer entorno deberia ser automatico
		try {
		Tablero tablero= new Tablero(4,4,4);
		tablero.establecerEntornos();
		
		Casillero casillero1=tablero.getCasillero(1, 1, 1);
		Casillero[][][] entornoCasillero=casillero1.getEntorno();
		//verifico que alguno de los vecinos de este casillero sea nulo, lo cual deberia no ser posible al ser uno de 			los casilleros interiores
		        for (int i = 0; i < 3; i++) {
		            for (int j = 0; j < 3; j++) {
		                for (int k = 0; k < 3; k++) {
		                    if (entornoCasillero[i][j][k] == null) {   
		                    	 assertNotNull(entornoCasillero[i][j][k], 
		                                 "El vecino en la posición [" + i + "][" + j + "][" + k + "] es nulo");
		                    }
		                }
		            }
		        }
		   
		//verifico que asigne null a los vecinos que no existen
		Casillero casillero2=tablero.getCasillero(0, 0, 0);
		Casillero[][][] entornoCasillero2=casillero2.getEntorno();
		assertTrue(entornoCasillero2[0][0][0]==null);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLimitesTablero() {
		try {
		Tablero tablero= new Tablero(4,4,4);
		
		Exception exception = assertThrows(Exception.class, () -> {
	       tablero.getCasillero(-1, 0, 0);
		});
		String mensajeDeErrorRecibido=exception.getMessage();
		String mensajeDeErrorEsperado= "La posicion debe estar entre 1 y tamaño. (Es 0)";
		assertTrue(mensajeDeErrorRecibido.equals(mensajeDeErrorEsperado));
		//forma mas simple de revisar lo mismo
		assertTrue(tablero.getCasillero(1, 1, 1)!=null);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
