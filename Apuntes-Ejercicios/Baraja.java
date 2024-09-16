package EjerciciosTDANivel3;

import java.util.Random;

public class Baraja {
	
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	//se puede usar tambien con una constante en lugar de 48
	private Carta[] cartas= new Carta[48];
	
	//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	/*
	 *pre:-
	 *post: Crea una baraja con 12 cartas de cada palo con valores del 1 al 12 
	 */
	public Baraja() {
		int j=0;
		for (Palo palo: Palo.values()) {
			  for(int i=0;i<12;i++) {
				  try {
				  Carta carta=new Carta(palo,i+1);
				  cartas[j]=carta;
				  j++;
				  }catch(Exception e){
					  System.out.println("Error imposible");
				  }
			  }
			}
	}
	
	//METODOS DE Clase -------------------------------------------------------------------------------
	
	/**
	 * pre: - 
	 * post: Imprime la baraja
	 */
	public void mostrarBaraja() {
		for(int i=0;i<48;i++) {
			System.out.println(cartas[i].toString());
		}
	}
	
	/**
	 * pre: 
	 * post: Devuelve la baraja con la misma cantidad de cartas pero desordenada
	 */
	public void barajar() {
		int maximo=47;
		Random r=new Random();
		for(int i=0;i<100;i++) {
			Carta cartaAuxiliar;
			int posicion1=r.nextInt(maximo);
			int posicion2=r.nextInt(maximo);
			cartaAuxiliar=this.cartas[posicion1];
			this.cartas[posicion1]=this.cartas[posicion2];
			this.cartas[posicion2]=cartaAuxiliar;
		}
	}
	
	/**
	 * pre: un jugador ya creado y un numero entero
	 * @param Jugador, un jugador con la capacidad de recibir una carta 
	 * @param cantidadCartas, un entero que represente la cantidad de cartas que recibira el jugador
	 * @throws Exception si la baraja esta vacia, si el jugador no puede recibir mas cartas o si la cantidad de cartas es 		igual o menor a 0
	 */
	public void repatirCartas(Jugador jugador, int cantidadCartas) throws Exception {
		if(cantidadCartas<=0) {
			throw new Exception("no puedes recibir:"+cantidadCartas);
		}
		for(int i=0;i<cantidadCartas;i++) {
			if(this.cartas[0]==null) {
				throw new Exception("Maso sin cartas");
			}
			jugador.recibirCarta(this.cartas[0]);
			eliminarCarta(0);
		}
	}
	
	/**
	 * pre: un numero entero
	 * @param posicion, un entero que representa la posicion de la carta a eliminar
	 * @throws Exception si la carta que se quiere eliminar es nula
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
	 * pre: una Carta ya creada
	 * @param Carta, carta que quieres recibir
	 * @throws Exception si la baraja esta llena (tecnicamente imposible)
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
	
	//-------------------------------------------------------------------------------------------------
	//TODO: main de testeo
}
