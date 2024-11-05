package tateti;

public enum Fichas {
    CUADRADO,
    CIRCULO,
    TRIANGULO,
    CRUZ,
    RECTANGULO,
    ESTRELLA,
    ROMBO,
    PAUSA;

    /**
     * pre: -, post: -
     *
     * @return Devuelve un char correspondiente a la seleccion.
     */
    public char getFichaCaracter() {
        return switch (this) {
            case CUADRADO ->
                '■';
            case CIRCULO ->
                'O';
            case TRIANGULO ->
                '▲';
            case CRUZ ->
                'X';
            case RECTANGULO ->
                '▰';
            case ESTRELLA ->
                '★';
            case ROMBO ->
                '♦';
            default ->
                'P'; //P de pausa
        };
    }

    /**
     * pre: -, post: -
     *
     * @return Devuelve un String con todas los tipos de ficha listados.
     */
    public static String obtenerTiposFicha() {
        String tiposExistentes = "";
        Fichas[] fichas = Fichas.values();
        for (int i = 0; i < fichas.length; i++) {
            tiposExistentes += fichas[i].name();
            if (i < fichas.length - 1) {
                tiposExistentes += ", "; //añade una coma y un espacio entre las fichas
            }
        }
        return tiposExistentes;
    }
}
