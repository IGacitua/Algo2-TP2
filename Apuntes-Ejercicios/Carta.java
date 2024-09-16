package EjerciciosTDANivel3;

public class Carta {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	//los palos son enums, verificar el apartado palo.java
	private Palo palo=null;
	private int valor=0;
	
	//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	public Carta(Palo palo,int valor) throws Exception {
		if(valor>12 || valor<1) {
			throw new Exception("Valor de carta no valido");
		}
		if(palo==null) {
			throw new Exception("Palo de carta no valido");
		}
		this.palo=palo;
		this.valor=valor;
	}

	//METODOS GENERALES ---------------------------------------------------------------------------------------
	//Sobreescribimos el metodo heredad de Object, nos permite imprimir el valor y palo de una carta en lugar del puntero 			a esta carta
	 @Override
	 public String toString() {
		 String mensaje="Palo: " +this.palo+ " Valor: "+this.valor;
		 return mensaje;
	 }
	 
	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	 /**
	  * pre:
	  * @return el palo de esta carta
	  */
	public Palo getPalo() {
		return this.palo;
	}
	
	/**
	 * pre: - 
	 * @return el valor de esta carta
	 */
	public int getValor() {
		return this.valor;
	}
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------
	//en este caso no vamos a setear valores a una carta, los cuales estan en una baraja ya establecida
	
}
