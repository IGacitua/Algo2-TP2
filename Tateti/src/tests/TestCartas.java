package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartas.Carta;
import cartas.CartaAnularCasillero;
import cartas.CartaBloquearFicha;
import cartas.CartaBomba;
import cartas.CartaCambiarFicha;
import cartas.CartaPerderTurno;
import cartas.CartaRobarCartas;
import tateti.Colores;
import tateti.Fichas;
import tateti.Jugador;
import tateti.Mazo;
import tateti.Tablero;
import utilidades.Lista;

public class TestCartas {
	private Tablero tablero;
	private Jugador jugadorTests;
	private Jugador jugadorUsarCartasVSEl;
	private Lista<Jugador> listaJugadores;
	private Mazo mazo;
	
	@BeforeEach
	public void inicializarMenu() {
        try {
        	listaJugadores = new Lista<Jugador>();
    		Lista<Carta> cartas = new Lista<Carta>();
    		jugadorTests = new Jugador("Juancito", 4, 3, Fichas.CIRCULO, Colores.AMARILLO, cartas);
	        jugadorUsarCartasVSEl = new Jugador("Pedrito", 4, 3, Fichas.CORAZON, Colores.ROJO, null);
    		listaJugadores.agregarElemento(jugadorTests);
			listaJugadores.agregarElemento(jugadorUsarCartasVSEl);
        	mazo = new Mazo(4);
	        tablero = new Tablero(3);
	        tablero.colocarFicha(0, 0, 0, jugadorTests);
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	@Test
	public void testCartaAnularCasillero() {
		
		System.out.println("TEST CARTA ANULAR CASILLERO");
		System.out.println("Ingrese 2 en cada coordenada pedida.");
		try {
			
			Lista<Carta> cartas = jugadorTests.getCartas();
			Carta carta1 = new CartaAnularCasillero();
			cartas.agregarElemento(carta1);
			cartas.iniciarCursor();

			while (cartas.avanzarCursor()) {
				Carta carta = cartas.obtenerCursor();
				assertTrue(carta.toString().equals("Carta Anular Casillero"));
				//Verifica que la carta esté en la lista.
				
				carta.usar(jugadorTests, listaJugadores, tablero, mazo);
	            Exception exception = assertThrows(Exception.class, () -> {
	            	tablero.colocarFicha(1, 1, 1, jugadorTests);
	            });
	            String mensajeDeErrorEsperado = "No se puede colocar una ficha en una casilla que está bloqueada.";
	            String mensajeDeErrorRecibido = exception.getMessage();
	            assertTrue(mensajeDeErrorRecibido.equals(mensajeDeErrorEsperado));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBloquearFicha() {

		System.out.println("TEST CARTA BLOQUEAR FICHA");
		System.out.println("Ingrese 2 en cada coordenada pedida.");
		try {
			Lista<Carta> cartas = jugadorTests.getCartas();
			Carta carta1 = new CartaBloquearFicha();
			tablero.colocarFicha(1, 1, 1, jugadorUsarCartasVSEl);
			
			cartas.agregarElemento(carta1);
			cartas.iniciarCursor();
			while (cartas.avanzarCursor()) {
				Carta carta = cartas.obtenerCursor();
				assertTrue(carta.toString().equals("Carta Bloquear Ficha"));
				//Verifica que la carta esté en la lista.
				
				carta.usar(jugadorTests, listaJugadores, tablero, mazo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCartaBomba() {
		
		System.out.println("TEST CARTA BOMBA");
		System.out.println("Ingrese 1 en cada coordenada pedida, verá el tablero antes y después de la bomba.");
		try {
			Lista<Carta> cartas = jugadorTests.getCartas();
			tablero.colocarFicha(1, 0, 0, jugadorTests);
			tablero.colocarFicha(0, 1, 0, jugadorTests);
			System.out.println("Tablero antes del uso de la carta:");
			tablero.imprimir();
			
			Carta carta1 = new CartaBomba();
			cartas.agregarElemento(carta1);
			cartas.iniciarCursor();
			while (cartas.avanzarCursor()) {
				Carta carta = cartas.obtenerCursor();
				assertTrue(carta.toString().equals("Carta Bomba"));
				//Verifica que la carta esté en la lista.
				
				carta.usar(jugadorTests, listaJugadores, tablero, mazo);
			}
			System.out.println("Tablero luego del uso de la carta (no habrá fichas):");
			tablero.imprimir();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCartaCambiarFicha() {
		
		System.out.println("TEST CARTA CAMBIAR FICHA");
		System.out.println("Ingrese 2 en cada coordenada pedida, verá el tablero antes y después del cambio.");
		try {
			Lista<Carta> cartas = jugadorTests.getCartas();
			tablero.colocarFicha(1, 1, 1, jugadorUsarCartasVSEl);
			System.out.println("Tablero antes del uso de la carta:");
			tablero.imprimir();
			
			Carta carta1 = new CartaCambiarFicha();
			cartas.agregarElemento(carta1);
			cartas.iniciarCursor();
			while (cartas.avanzarCursor()) {
				Carta carta = cartas.obtenerCursor();
				assertTrue(carta.toString().equals("Carta Cambiar Ficha"));
				//Verifica que la carta esté en la lista.
				
				carta.usar(jugadorTests, listaJugadores, tablero, mazo);
			}
			System.out.println("Tablero luego del uso de la carta (todas los casilleros tomados ahora son círculo):");
			tablero.imprimir();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCartaPerderTurno() {
		
		System.out.println("TEST CARTA PERDER TURNO");
		System.out.println("Ingrese 2 en lo pedido por consola.");
		try {
			Lista<Carta> cartas = jugadorTests.getCartas();
			Carta carta1 = new CartaPerderTurno();
			cartas.agregarElemento(carta1);
			int cursor = 1;
			listaJugadores.iniciarCursor();
	        while (listaJugadores.avanzarCursor()) {
	            Jugador jugadorActual = listaJugadores.obtenerCursor();
	            jugadorActual.setIdentificacion(cursor);
	            cursor++;
	        }
	        cartas.iniciarCursor();
	        while (cartas.avanzarCursor()) {
				Carta carta = cartas.obtenerCursor();
				assertTrue(carta.toString().equals("Carta Perder Turno"));
				//Verifica que la carta esté en la lista.
				
				carta.usar(jugadorTests, listaJugadores, tablero, mazo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRobarCartas() {
		System.out.println("TEST CARTA ROBAR DOS CARTAS");
		try {
			Lista<Carta> cartas = jugadorTests.getCartas();
			Carta carta1 = new CartaRobarCartas();
			cartas.agregarElemento(carta1);
			
			assertTrue(jugadorTests.getCantidadDeCartas() == 1);
			assertTrue(jugadorTests.getCartasMaximas() == 3);
			//Verifico que tenga solo una carta y que el máximo sean 3.
			
			cartas.iniciarCursor();
	        while (cartas.avanzarCursor()) {
				Carta carta = cartas.obtenerCursor();
				assertTrue(carta.toString().equals("Carta Robar 2 Cartas"));
				//Verifica que la carta esté en la lista.
				carta.usar(jugadorTests, listaJugadores, tablero, mazo);
				break;
			}
	        assertTrue(jugadorTests.getCantidadDeCartas()==2);
	        //Verifica que haya descartado la carta que usó y haya agarrado dos.
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
