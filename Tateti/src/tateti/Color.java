package tateti;

public enum Color {
	ROJO,
	VERDE,
	AZUL,
	AMARILLO,
	ROSA,
	CELESTE,
	BLANCO;
	
	/**
	 * pre -, pos: -
	 * @return devuelve entero del color elegido
	 */
	public int getRGB() {
		switch (this) {
		case ROJO:
			return (255 << 16);
		case VERDE:
			return (255 << 8);
		case AZUL:
			return 255;
		case AMARILLO:
			return (255 << 16) | (255 << 8);
		case ROSA:
			return (255 << 16) | 255;
		case CELESTE:
			return (255 << 8) | 255;
		case BLANCO:
			return (255 << 16) | (255 << 8) | 255;
		default:
			return 0; // Negro
		}
	}
	
    /**
     * pre: -, pos: -
     * @return el nombre del color elegido.
     */
    @Override
    public String toString() {
        return "El color es " + name().toLowerCase();
    }
	
}

