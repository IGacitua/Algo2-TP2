package tateti;

public class Tablero {
	// TODO validacion
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	
	//ATRIBUTOS -----------------------------------------------------------------------------------------------
	
	private Lista<Lista<Lista<Casillero>>> casilleros = null;
	private int tamañoX;
	private int tamañoY;
	private int tamañoZ;
	//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	//TODO pre-post
	public Tablero(int tamañoX, int tamañoY, int tamañoZ) throws Exception {
		// TODO validacion tamaños
		this.tamañoX = tamañoX;
		this.tamañoY = tamañoY;
		this.tamañoZ = tamañoZ;
		this.casilleros = new Lista<Lista<Lista<Casillero>>>();
		for (int x = 0; x < tamañoX; x++) {
			Lista<Lista<Casillero>> fila = new Lista<Lista<Casillero>>();
			for (int y = 0; y < tamañoY; y++) {
				Lista<Casillero> columna = new Lista<Casillero>();
				for (int z = 0; z < tamañoZ; z++) {
					columna.agregarElemento(new Casillero(x,y,z));
				}
				fila.agregarElemento(columna);
			}
			casilleros.agregarElemento(fila);
		}
	}
	
	//METODOS DE CLASE ----------------------------------------------------------------------------------------
	
	//METODOS GENERALES ---------------------------------------------------------------------------------------
	
	// TODO pre-post
	public void imprimirTablero() throws Exception {
		for (int z = 0; z < this.tamañoZ; z++) {
			System.out.printf("\n\n");
			for (int y = 0; y < this.tamañoY; y++) {
				System.out.printf("\n");
				for (int x = 0; x < this.tamañoX; x++) {
					System.out.printf("%d", getFicha(x+1,y+1,z+1).getJugador());
				}
			}
		}
	}
	
	// TODO pre-post
	public void colocarFicha(int x, int y, int z, int jugador) throws Exception {
		getFicha(x,y,z).setJugador(jugador);
	}
	
	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
	
	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	
	// TODO pre-post
	private Casillero getFicha(int x, int y, int z) throws Exception {
		return this.casilleros.obtener(x).obtener(y).obtener(z);
	}
	
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
	
}
