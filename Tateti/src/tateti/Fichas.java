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
}
