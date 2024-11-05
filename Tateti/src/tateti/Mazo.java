package tateti;

import cartas.Carta;
import java.util.Random;
import utilidades.PilaGenerica;

public class Mazo {

    @SuppressWarnings("FieldMayBeFinal") // TODO: Si sigue dando la warning cuando esté completo, hacerlo final
    private PilaGenerica<Carta> cartas = new PilaGenerica<>();

    /**
     * pre: El largo debe ser válido,
     * post: Genera un arreglo de largo "largo" con numeros aleatorios
     * sin repetir.
     * 
     * @param largo: Debe ser >= 0.
     * @return Devuelve un arreglo con números aleatorios sin repetir.
     */
    private int[] indicesAleatorios(int largo) {
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
     * pre: -, post: Agrega las cartas mezcladas.
     * @param cartas: Es el arreglo de cartas que contendrá el mazo.
     */
    public Mazo(Carta[] cartas) {
        if (cartas == null) {
            cartas = new Carta[0];
        }

        int[] indices = indicesAleatorios(cartas.length);

        for (int indice : indices) {
            this.cartas.agregar(cartas[indice]);
        }
    }

    /**
	 * pre: -, post: Elimina la carta del tope del mazo y la devuelve.
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
