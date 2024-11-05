package tests;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import tateti.Casillero;
import tateti.Tablero;


public class TestTablero {
	@Test
	public void testTableroInvalido() {
		Exception exception = assertThrows(Exception.class, () -> {
	        new Tablero(2);
		});
		String mensajeDeErrorEsperado = "El tamaño del tablero debe ser mayor a 0. (El ingresado fue 2)";
	    String mensajeDeErrorRecibido = exception.getMessage();
	    System.out.println(mensajeDeErrorRecibido);
	    assertTrue(mensajeDeErrorRecibido.equals(mensajeDeErrorEsperado));
	}
	
	@Test
	public void testEntorno() {
		try {
		Tablero tablero= new Tablero(4);
		
		Casillero casillero1=tablero.getCasillero(1, 1, 1);
		Casillero[][][] entornoCasillero=casillero1.getEntorno();
		//verifica que alguno de los vecinos de este casillero sea nulo, lo cual deberia no ser posible al ser uno de los casilleros interiores
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
		Tablero tablero = new Tablero(4);
		
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

