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
	 * @return devuelve un char correspondiente a la seleccion
	 */
	public char getFichaCaracter() {
		switch (this) {
		case CUADRADO:
			return '■';
		case CIRCULO:
			return 'O';
		case TRIANGULO:
			return '▲';
		case CRUZ:
			return 'X';
		case RECTANGULO:
			return '█'; //TODO: CAMBIAR. Pensé en poner este: ▬ pero me parece que es muy similar al cuadrado
		case ESTRELLA:
			return '★';
		case ROMBO:
			return '♦';
		default:
			return 'P'; //P de pausa
		}
	}
	
	/**
     * pre: -, post: -
     * @return devuelve un String con todas los tipos de ficha listados
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
