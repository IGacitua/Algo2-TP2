package tateti;

public class Nodo<T> {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	
	//ATRIBUTOS -----------------------------------------------------------------------------------------------
	private T dato;
	private Nodo<T> siguiente = null;
	//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	/**
	 * pre: -, post: Inicializa el nodo
	 * @param dato: puede ser cualquier tipo
	 */
	Nodo(T dato){
		this.dato = dato;
		this.siguiente = null;
	}

	//METODOS DE CLASE ----------------------------------------------------------------------------------------
	
	//METODOS GENERALES ---------------------------------------------------------------------------------------
	
	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
	
	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	
	/**
	 * pre: -, post: -
	 * @return devuelve el dato
	 */
	public T getDato() {
		return dato;
	}
	
	/**
	 * pre: -, post: -
	 * @return devuelve el siguiente nodo
	 */
	public Nodo<T> getSiguiente() {
		return siguiente;
	}
	
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
	
	/**
	 * pre: -, post: establece el dato
	 * @param dato: puede ser cualquier tipo
	 */
	public void setDato(T dato) {
		this.dato = dato;
	}
	
	/**
	 * pre: -, post: -
	 * @param siguiente: debe ser un nodo de cualquier dato
	 */
	public void setSiguiente(Nodo<T> siguiente) {
		this.siguiente = siguiente;
	}
}
