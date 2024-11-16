package tateti;

public enum ColoresDisponibles {
    ROJO,
    VERDE,
    AZUL,
    AMARILLO,
    ROSA,
    CELESTE,
    BLANCO,
    NEGRO;

    /**
     * pre -, post: -
     *
     * @return Devuelve el entero del color elegido.
     */
    public int getRGB() {
        return switch (this) {
            case ROJO ->
                255 << 16;
            case VERDE ->
                255 << 8;
            case AZUL ->
                255;
            case AMARILLO ->
                (255 << 16) | (255 << 8);
            case ROSA ->
                (255 << 16) | 255;
            case CELESTE ->
                (255 << 8) | 255;
            case BLANCO ->
                (255 << 16) | (255 << 8) | 255;
            case NEGRO ->
                0;
            default ->
                0;
        };
    }

    /**
     * pre: -, post: -
     * @return Devuelve el valor para imprimir el color.
     */
    public String getPrintfColor() {
        return switch (this) {
            case ROJO ->
                "\u001B[31m";
            case VERDE ->
                "\u001B[32m";
            case AZUL ->
                "\u001B[34m";
            case AMARILLO ->
                "\u001B[33m";
            case ROSA ->
                "\u001B[35m";
            case CELESTE ->
                "\u001B[36m";
            case BLANCO ->
                "\u001B[37m";
            case NEGRO ->
                "\u001B[30m";
            default ->
                "\u001B[0m"; // Reset
        };
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve el nombre del color elegido.
     */
    @Override
    public String toString() {
        return name();
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve un String con todos los colores listados.
     */
    public static String obtenerColores() {
        String coloresExistentes = "";
        ColoresDisponibles[] colores = ColoresDisponibles.values();
        for (int i = 0; i < colores.length; i++) {
            coloresExistentes += colores[i].name();
            if (i < colores.length - 1) {
                coloresExistentes += ", "; //añade una coma y un espacio entre los colores
            }
        }
        return coloresExistentes;
    }

}
