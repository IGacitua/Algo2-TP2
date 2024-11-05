package utilidades;

public class Herramientas {
    /**
     * pre: -, post: -
     * @param valor: Puede ser cualquier valor numérico.
     * @return Devuelve un booleano correspondiente a si el valor es >= 0.
     */
    public static boolean validarNumeroPositivo(Integer valor) {
        return valor >= 0;
    }

    /**
     * pre: -, post: -
     * @param valor: Puede ser cualquier valor numérico.
     * @return Devuelve un booleano correspondiente a si el valor es > 0.
     */
    public static boolean validarNumeroPositivoEstricto(Integer valor) {
        return valor > 0;
    }

    /**
     * pre: -, post: -
     * @param numero: Puede ser cualquier dígito.
     * @param posicion: Puede ser cualquier dígito.
     * @return Devuelve el dígito en la posición dada, con 0 siendo el más pequeño.
     */
    public static int devolverDigito(int numero, int posicion) {
        return (int) ((numero / (Math.pow(10, posicion))) % 10);
    }
    
    /**
     * pre: -, post: -
     * @param valor: Puede ser cualquier valor.
     * @return Devuelve un boolean correspondiente a si el RGB es
     * válido o no, siendo false si no está entre los valores válidos.
     */
    public static boolean validarRGB(int valor) {
        int rojo = (valor >> 16) & 0xFF;
        int verde = (valor >> 8) & 0xFF;
        int azul = valor & 0xFF;

        return (rojo >= 0 && rojo <= 255) &&
               (verde >= 0 && verde <= 255) &&
               (azul >= 0 && azul <= 255);
    }
}

