package tateti;

public class CartaBloquearFicha extends Carta {

	public CartaBloquearFicha(Tablero tablero) {
		super(tablero);
	}

	public void usar(int x, int y, int z) throws Exception {
		Casillero casillero = this.tablero.getCasillero(x, y, z); 
		if (!casillero.isVacio() &&
			!casillero.isBloqueado()) {
			casillero.alternarBloqueo();
		} else {
			throw new Exception("El casillero esta vacio o ya esta bloqueado");
		}
	}
}
