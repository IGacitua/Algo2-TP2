package tateti;

//TODO cambiar nombre. Java ya posee una clase con este nombre
public enum Color {
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
     * @return devuelve entero del color elegido
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
     *
     * @return devuelve el nombre del color elegido.
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }

    /**
     * pre: -, post: -
     *
     * @return devuelve un String con todos los colores listados
     */
    public static String obtenerColores() {
        String coloresExistentes = "";
        Color[] colores = Color.values();
        for (int i = 0; i < colores.length; i++) {
            coloresExistentes += colores[i].name();
            if (i < colores.length - 1) {
                coloresExistentes += ", "; //añade una coma y un espacio entre los colores
            }
        }
        return coloresExistentes;
    }

}
