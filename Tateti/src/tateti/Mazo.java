package tateti;

import java.util.Random;

public class Mazo {

	private PilaGenerica<Carta> cartas = new PilaGenerica<Carta>();
	
	/**
     * pre: -, post: -
     * Genera un array de largo "largo" con numeros aleatorios sin repetir
     */
	private int[] indicesAleatorios(int largo) {
		
		Random generadorRandom = new Random();
		int[] resultado = new int [largo];
		int[] restante = new int[largo];
		
		for (int i = 0; i < largo; i++) {
			restante[i] = i;
		}

		while (largo > 0){
			int indiceHacia = restante.length-largo;
			int indiceDesde = generadorRandom.nextInt(largo);
			resultado[indiceHacia] = restante[indiceDesde];
			for (int i = indiceDesde; i < largo-1; i++) {
				restante[i] = restante[i+1];
			}
			largo--;
		}
		
		return resultado;
	}
	
	/**
     * pre: -, post: -
     * Agrega las cartas mezcladas
     *
     * @param cartas: array de cartas que contendra el mazo
     */
	public Mazo(Carta[] cartas) {

		int[] indices = indicesAleatorios(cartas.length);
		
		for (int indice : indices) {
			this.cartas.agregar(cartas[indice]);
		}
	}
	
	/*
	 * pre: -, post: -
	 * Elimina la carta del tope del mazo y la devuelve
	 * Devuelve null si no hay cartas
	 */
	public Carta tomarCarta() {
		return this.cartas.quitar();
	}
	
}
