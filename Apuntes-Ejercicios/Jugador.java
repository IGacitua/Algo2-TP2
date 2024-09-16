package EjerciciosTDANivel3;

public class Jugador {
	
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	//se puede usar tambien con una constante en lugar de 48
	private Carta[] cartas=new Carta[47]; 
	
	//CONSTRUCTORES -------------------------------------------------------------------------------------------
	/**
	 * pre: -
	 * post: Devuelve un jugador con una mano vacia
	 */
	public Jugador() {
		for(int i=0;i<47;i++) {
			cartas[i]=null;
		}
	}
	
	//METODOS DE Clase -------------------------------------------------------------------------------
	
	/**
	 * pre: una carta que quiere recibir
	 * @param Carta, la carta que quiere ser recibida
	 * @throws Exception
	 */
	public void recibirCarta(Carta carta) throws Exception {
		boolean cartaRecivida=false;
		int i=0;
		while(!(cartaRecivida) && i<this.cartas.length) {
			if(cartas[i]==null) {
				this.cartas[i]=carta;
				cartaRecivida=true;
			}
			i++;
		}
		if(!(cartaRecivida)) {
			throw new Exception("No se pudo recibir la carta");
		}
	}
	
	/**
	 * 
	 * @param posicionCarta, un entero que represente la posicion de la carta que quiere jugar
	 * @param Baraja, la baraja con la que se esta jugando
	 * @throws Exception si la carta que se quiere jugar no existe (null), si posicion no es un numero natural (mayor a 0), 			si el jugador ya no puede recibir mas cartas
	 */
	public void jugarCarta(int posicionCarta,Baraja baraja) throws Exception {
		baraja.recibirCarta(this.cartas[posicionCarta]);
		eliminarCarta(posicionCarta);
		
	}
	
	/**
	 * pre: un int, que sea la posicion de una carta dentro de tu baraja
	 * @param posicion, un numero entero que sea la posicion de la carta a eliminar
	 * @throws Exception si la carta en esa posicion no existe
	 */
	private void eliminarCarta(int posicion) throws Exception {
		if(this.cartas[posicion]==null) {
			throw new Exception("Posicion nula");
		}
		int i=posicion;
		while(this.cartas[i]!=null && i<this.cartas.length-1){
			this.cartas[i]=this.cartas[i+1];
			i++;
		}
		if(i==this.cartas.length-1) {
			this.cartas[i+1]=null;
		}
	}
	
	
	/**
	 * pre: -
	 * post: devuelve tu puntuacion actual (la suma del valor de tus cartas actuales)
	 * @return un numero entero que representa la suma de los valores de las cartas de tu mano actual
	 * @throws Exception si no posees cartas en tu mano actual
	 */
	public int verPuntuacion() throws Exception {
		if(this.cartas[0]==null) {
			throw new Exception("No poses cartas en tu mano actual");
		}
		int resultado=0;
		int i=0;
		while(i<this.cartas.length && this.cartas[i]==null) {
			resultado+=this.cartas[i].getValor();
			i++;
		}
		return resultado;
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//TODO: main de testeo
}
