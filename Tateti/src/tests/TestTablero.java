package tateti.tests;

import org.junit.jupiter.api.BeforeEach;

import tateti.*;

public class TestTablero {
	private Tablero tablero;
	
	@BeforeEach
	public void incializarTablero() throws Exception {
		tablero = new Tablero(3,3,1,3);
	}
}
