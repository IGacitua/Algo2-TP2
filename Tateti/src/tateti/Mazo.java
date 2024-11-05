package tateti;

import java.util.Random;

import cartas.Carta;
import cartas.CartaAnularCasillero;
import cartas.CartaBloquearFicha;
import cartas.CartaPerderTurno;
import cartas.CartaRobarCartas;
import utilidades.PilaGenerica;

public class Mazo {
	
	private static final int CANTIDAD_TIPO_CARTAS = 4;

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
	
	// TODO
	private int[] mezclarInt(int[] array) {
		
		int[] resultado = new int[array.length]; 
		int[] indices = indicesAleatorios(array.length);
		
		for (int i = 0; i < array.length; i++) {
			resultado[indices[i]] = array[i];
		}
		
		return resultado;
	}
	
	// TODO
	private Carta crearCartaPorId(int id) {
		
		Carta resultado = null;
		
		switch (id) {
			case 0:
				resultado = new CartaAnularCasillero();
				break;
			case 1:
				resultado = new CartaBloquearFicha();
				break;
			case 2:
				resultado = new CartaPerderTurno();
				break;
			case 3:
				resultado = new CartaRobarCartas();
				break;
		}
		
		return resultado;
	}
	
	/**
     * pre: -, post: -
     * Crea el mazo con las cartas mezcladas
     *
     * @param cantidadCartasPorTipo: cantidad de cartas que se van a crear de cada tipo
     */
	public Mazo(int cantidadCartasPorTipo) {
		
		int[] idMezclados = new int[cantidadCartasPorTipo*CANTIDAD_TIPO_CARTAS];
		
		for (int i = 0; i < CANTIDAD_TIPO_CARTAS; i++) {
			for (int j = 0; j < cantidadCartasPorTipo; j++) {
				idMezclados[i*cantidadCartasPorTipo+j] = i;
			}
		}
		
		idMezclados = mezclarInt(idMezclados);
		
		for (int id : idMezclados) {
			this.cartas.agregar(crearCartaPorId(id));
		}
	}
	
	/*
	 * pre: -, post: -
	 * Elimina la carta del tope del mazo y la devuelve
	 * 
	 * @throws Exception 
	 */
	public Carta tomarCarta() throws Exception {
		if (this.cartas.getCantidadElementos() == 0) {
			throw new Exception("No se pueden tomar cartas porque el mazo esta vacio");
		}
		return this.cartas.quitar();
	}
	
}
