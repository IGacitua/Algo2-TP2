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
	 * pre -, post: -
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
     * pre: -, post: -
     * @return devuelve el nombre del color elegido.
     */
    @Override
    public String toString() {
        return "El color es " + name().toLowerCase();
    }
    
    /**
     * pre: -, post: -
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
