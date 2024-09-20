package tv;

public class Canal {
	private int numeroCanal;
	private int volumenMaximo;
	private int volumenActual;

	public Canal(int numeroCanal) throws Exception {
		if (Tools.checkPositiveStrict(numeroCanal)) {
			throw new Exception("Numero de canal invalido.");
		}
		this.numeroCanal = numeroCanal;
	}

	/*
	 * PRE POST
	 */
	public int getVolumenActual() {
		return volumenActual;
	}

	/*
	 * PRE POST
	 */
	public void setVolumenActual(int volumenActual) throws Exception {
		if (Tools.checkPositive(volumenActual) || volumenActual > 100) {
			throw new Exception("Volumen debe estar entre 0 y 100.");
		}
		this.volumenActual = volumenActual;
	}

	/*
	 * PRE POST
	 */
	public int getNumeroCanal() {
		return numeroCanal;
	}

	/*
	 * PRE POST
	 */
	public int getVolumenMaximo() {
		return volumenMaximo;
	}
	
	public void changeVolumen(int volumen) {
		if (volumen > this.volumenMaximo) {
			this.volumenMaximo = volumen;
		}
		this.volumenActual = volumen;
	}
}
