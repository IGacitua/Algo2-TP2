package tateti;

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
}
