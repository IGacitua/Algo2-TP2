package tateti;

public enum Fichas {
    CUADRADO,
    CIRCULO,
    TRIANGULO,
    CRUZ,
    RECTANGULO,
    ESTRELLA,
    ROMBO,
    CORAZON;

    /**
     * @return Devuelve un char correspondiente a la Ficha.
     */
    public char getFichaCaracter() {
        return switch (this) {
            case CUADRADO ->
                '■';
            case CIRCULO ->
                '⬤';
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
            case CORAZON ->
                '♥';
        };
    }

    /**
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

    /**
     * @return Devuelve el nombre de la ficha elegida.
     */
    @Override
    public String toString() {
        return name();
    }
}
