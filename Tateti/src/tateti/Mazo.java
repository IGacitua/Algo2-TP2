package tateti;

import cartas.Carta;
import cartas.CartaAnularCasillero;
import cartas.CartaBloquearFicha;
import cartas.CartaBomba;
import cartas.CartaCambiarFicha;
import cartas.CartaPerderTurno;
import cartas.CartaRobarCartas;

import java.util.Random;

import utilidades.Herramientas;
import utilidades.PilaGenerica;

public class Mazo {
	@SuppressWarnings("FieldMayBeFinal") // TODO: Si sigue dando la warning cuando esté completo, hacerlo final
	private static final int CANTIDAD_TIPO_CARTAS = 6;
	private PilaGenerica<Carta> cartas = new PilaGenerica<Carta>();


    /**
     * pre: -, post: Inicializa el mazo agregando las cartas mezcladas.
     *
     * @param cartas: Es el arreglo de cartas que contendrá el mazo.
     * @throws Exception: Si la cantidadCartasPorTipo es < 0.
     */
    public Mazo(int cantidadCartasPorTipo) throws Exception {
    	if (!Herramientas.validarNumeroPositivo(cantidadCartasPorTipo)) {
    		throw new Exception("La cantidad de cartas por tipo establecidas debe ser mayor o igual a 0.");
    	}
    	int[] idMezclados = new int[cantidadCartasPorTipo*CANTIDAD_TIPO_CARTAS];
		for (int i = 0; i < CANTIDAD_TIPO_CARTAS; i++) {
			for (int j = 0; j < cantidadCartasPorTipo; j++) {
				idMezclados[i*cantidadCartasPorTipo+j] = i;
			}
		}
		idMezclados = mezclarArreglo(idMezclados);
		for (int id : idMezclados) {
				this.cartas.agregar(crearCartaPorId(id));
		}
    }
	
    /**
     * pre: El largo debe ser válido,
     * post: Genera un arreglo de largo "largo" con números aleatorios sin repetir.
     *
     * @param largo: Debe ser >= 0.
     * @return Devuelve un arreglo con números aleatorios sin repetir.
     */
    //TODO: validarían acá que largo sea >= 0? no sé si es necesario porque
    //solo la usa mezclarArreglo() y esa le pasa la longitud del arreglo
    //y el arreglo puede estar vacío o no (len 0 o len > 0), pero sí valida q no sea null.
    private int[] crearArregloConIndicesAleatorios(int largo) {
        Random generadorRandom = new Random();
        int[] resultado = new int[largo];
        int[] restante = new int[largo];

        for (int i = 0; i < largo; i++) {
            restante[i] = i;
        }
        while (largo > 0) {
            int indiceHacia = restante.length - largo;
            int indiceDesde = generadorRandom.nextInt(largo);
            resultado[indiceHacia] = restante[indiceDesde];
            for (int i = indiceDesde; i < largo - 1; i++) {
                restante[i] = restante[i + 1];
            }
            largo--;
        }
        return resultado;
    }
    
    /**
     * pre: El arreglo no debe estar vacío,
     * post: Devuelve el arreglo mezclado.
     * 
     * @param array: No debe ser nulo.
     * @throws Exception: Si el arreglo es inválido.
     */
 	private int[] mezclarArreglo(int[] array) throws Exception {
 		if (array == null) {
 			throw new Exception("El arreglo a mezclar no debe ser nulo.");
 		}
 		int[] resultado = new int[array.length]; 
 		int[] indices = crearArregloConIndicesAleatorios(array.length);
 		for (int i = 0; i < array.length; i++) {
 			resultado[indices[i]] = array[i];
 		}
 		return resultado;
 	}

 	/**
     * pre: -, post: Devuelve una nueva carta, en base al id (tipo).
     * 
     * @param id: id de la carta a crear, debe ser un válido
     * (estar entre los tipos de cartas disponibles).
     * @return Devuelve la carta creada.
     * @throws Exception: Si el id no corresponde a ninguna carta.
     */
 	private Carta crearCartaPorId(int id) throws Exception {
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
 			case 4:
 				resultado = new CartaBomba();
 				break;
 			case 5:
 				resultado = new CartaCambiarFicha();
 				break;
 			default:
 				throw new Exception("El id no es válido.");
 		}

 		return resultado;
 	}

    /**
     * pre: -, post: Elimina la carta del tope del mazo y la devuelve.
     *
     * @return Devuelve la Carta que se quitó.
     * @throws Exception: Si el mazo está vacío.
     */
    public Carta tomarCarta() throws Exception {
        if (this.cartas.getCantidadElementos() == 0) {
            throw new Exception("No se pueden tomar cartas porque el mazo esta vacio");
        }
        return this.cartas.quitar();
    }
}
