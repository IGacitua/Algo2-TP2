package utilidades;

public class Herramientas {

    /**
     * pre: -, pos: -
     *
     * @param valor puede ser cualquier valor numérico
     * @return devuelve un booleano correspondiente a si el valor es >= 0
     */
    public static boolean validarNumeroPositivo(Integer valor) {
        return valor >= 0;
    }

    /**
     * pre: -, pos: -
     *
     * @param valor puede ser cualquier valor numérico
     * @return devuelve un booleano correspondiente a si el valor es > 0
     */
    public static boolean validarNumeroPositivoEstricto(Integer valor) {
        return valor > 0;
    }

    // TODO Pre-post
    // Devuelve el digito en la posición dada, con 0 siendo el mas pequeño
    public static int devolverDigito(int numero, int posicion) {
        return (int) ((numero / (Math.pow(10, posicion))) % 10);
    }
}
