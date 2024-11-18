package tateti;

import cartas.Carta;
import cartas.CartaAnularCasillero;
import cartas.CartaBloquearFicha;
import cartas.CartaBomba;
import cartas.CartaCambiarFicha;
import cartas.CartaPerderTurno;
import cartas.CartaRobarCartas;
import cartas.CartaVolverTurno;

import java.util.Random;
import utilidades.Herramientas;
import utilidades.Pila;

public class Mazo {

    public static final int CANTIDAD_TIPO_CARTAS = 7; // Cuantos tipos de Carta únicos hay
    private final Pila<Carta> cartas = new Pila<>(); // Final xq siempre apunta a la misma pila.

    /**
     * pre: Recibe la cantidad de cartas de cada tipo que añadir al mazo
     *
     * post: Inicializa el mazo agregando las cartas mezcladas.
     *
     * @param cantidadCartasPorTipo Debe ser >= 0
     * @throws Exception: Si la cantidadCartasPorTipo es < 0.
     */
    public Mazo(int cantidadCartasPorTipo) throws Exception {
        if (!Herramientas.validarNumeroPositivo(cantidadCartasPorTipo)) {
            throw new Exception("La cantidad de cartas por tipo establecidas debe ser mayor o igual a 0.");
        }
        int[] idMezclados = new int[cantidadCartasPorTipo * CANTIDAD_TIPO_CARTAS];
        for (int i = 0; i < CANTIDAD_TIPO_CARTAS; i++) {
            for (int j = 0; j < cantidadCartasPorTipo; j++) {
                idMezclados[i * cantidadCartasPorTipo + j] = i;
            }
        }
        idMezclados = mezclarArreglo(idMezclados);
        for (int id : idMezclados) {
            this.cartas.agregar(crearCartaPorId(id));
        }
    }

    /**
     * pre: El largo debe ser válido
     *
     * post: Genera un arreglo de largo "largo" con números aleatorios sin
     * repetir.
     *
     * @param largo: Debe ser >= 0.
     * @return Devuelve un arreglo con números aleatorios sin repetir.
     */
 
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
     * pre: Recibe un arreglo a mezclar
     *
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
     * pre: Recibe la ID del tipo de carta a crear
     *
     * post: Devuelve una nueva carta, en base al tipo dado
     *
     * @param id: ID de la carta a crear, debe estar entre 0 y cantidad de tipos
     * de carta
     * @return Devuelve la carta creada.
     * @throws Exception: Si el id no corresponde a ninguna carta.
     */
    private Carta crearCartaPorId(int id) throws Exception {
        switch (id) {
            case 0 -> {
                return new CartaAnularCasillero();
            }
            case 1 -> {
                return new CartaBloquearFicha();
            }
            case 2 -> {
                return new CartaPerderTurno();
            }
            case 3 -> {
                return new CartaRobarCartas();
            }
            case 4 -> {
                return new CartaBomba();
            }
            case 5 -> {
                return new CartaCambiarFicha();
            }
            case 6 -> {
            	return new CartaVolverTurno();
            }
            default -> {
                throw new Exception("El id dado no es válido.");
            }
        }
    }

    /**
     * post: Elimina la carta del tope del mazo y la devuelve.
     *
     * @return Devuelve la Carta que se eliminó.
     * @throws Exception: Si el mazo está vacío.
     */
    public Carta tomarCarta() throws Exception {
        if (this.cartas.getCantidadElementos() == 0) {
            throw new Exception("No se pueden tomar cartas porque el mazo esta vacio");
        }
        return this.cartas.quitar();
    }
}
