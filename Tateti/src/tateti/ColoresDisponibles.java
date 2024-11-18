package tateti;

public enum ColoresDisponibles {
    ROJO,
    VERDE,
    AZUL,
    AMARILLO,
    ROSA,
    CELESTE,
    GRIS,
    NEGRO;

    /**
     * @return Devuelve el valor RGB del color.
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
            case GRIS ->
                (127 << 16) | (127 << 8) | 127;
            case NEGRO ->
                0;
            default ->
                0;
        };
    }

    /**
     * @return Devuelve el ANSI escape code del color.
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
            case GRIS ->
                "\u001B[90m";
            case NEGRO ->
                "\u001B[30m";
            default ->
                "\u001B[0m"; // Reset
        };
    }

    /**
     * @return Devuelve el nombre del color elegido.
     */
    @Override
    public String toString() {
        return name();
    }

    /**
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
