package tv;

public class Televisor {

	private Canal[] canales = null;
	private int volumenActual = 0;
	private int canalActual = 1;
	private boolean silenciado = false;

	public Televisor(int cantidadCanales) throws Exception {
		this.canales = new Canal[cantidadCanales];
		for (int i = 0; i < cantidadCanales; i++) {
			this.canales[i] = new Canal(i + 1); // + 1 para que sea empezando en 1.
		}
	}

	public void subirCanal() throws Exception {
		setCanal(canalActual + 1);
		canales[canalActual - 1].changeVolumen(volumenActual);
	};

	public void bajarCanal() throws Exception {
		setCanal(canalActual - 1);
		canales[canalActual - 1].changeVolumen(volumenActual);
	};

	public void setCanal(int canal) throws Exception {
		if (canal > 0 || canal < this.canales.length) {
			this.canalActual = canal;
		} else {
			throw new Exception("Canal debe estar entre 1 y " + this.canales.length);
		}
		canales[canalActual - 1].changeVolumen(volumenActual);
	};

	public void subirVolumen() throws Exception {
		if (this.volumenActual < 100) {
			this.volumenActual += 1;
		} else {
			throw new Exception("Volumen no puede ser mayor a 100.");
		}
		canales[canalActual - 1].changeVolumen(volumenActual);
	};

	public void bajarVolumen() throws Exception {
		if (this.volumenActual > 0) {
			this.volumenActual -= 1;
		} else {
			throw new Exception("Volumen no puede ser menor a 0.");
		}
		canales[canalActual - 1].changeVolumen(volumenActual);
	};

	public void switchSilencio() {
		this.silenciado = !this.silenciado;
	};

	public boolean getSilencio() {
		return this.silenciado;
	}

	public int getCanalActual() {
		return this.canalActual;
	}

	public int getVolumenActual() {
		return this.volumenActual;
	}

	public int getCanalMaxVol() {
		int maximoEncontrado = 0;
		for (int i = 0; i < this.canales.length; i++) {
			if (this.canales[i].getVolumenMaximo() > this.canales[maximoEncontrado].getVolumenMaximo()) {
				maximoEncontrado = i;
			}
		}
		return maximoEncontrado + 1;
	}

	public int getVolumenCanal(int canal) {
		return this.canales[canal - 1].getVolumenActual();
	};
	
	public int getVolumenMaxCanal(int canal) {
		return this.canales[canal - 1].getVolumenMaximo();
	}
}
