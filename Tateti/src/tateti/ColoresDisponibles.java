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
     * @return Devuelve entero del color elegido.
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
     * @return Devuelve el nombre del color elegido.
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    } //TODO: Se usa realmente en el código?

    /**
     * pre: -, post: -
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
