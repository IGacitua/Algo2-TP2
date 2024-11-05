package utilidades;

public class Nodo<T> {
	//ATRIBUTOS -----------------------------------------------------------------------------------------------
	private T dato;
	private Nodo<T> siguiente = null;
	//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	/**
	 * pre: -, post: Inicializa el nodo.
	 * @param dato: Puede ser cualquier tipo.
	 */
	Nodo(T dato){
		this.dato = dato;
		this.siguiente = null;
	}
	
	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	
	/**
	 * pre: -, post: -
	 * @return Devuelve el dato.
	 */
	public T getDato() {
		return dato;
	}
	
	/**
	 * pre: -, post: -
	 * @return Devuelve el siguiente nodo.
	 */
	public Nodo<T> getSiguiente() {
		return siguiente;
	}
	
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
	
	/**
	 * pre: -, post: Establece el dato.
	 * @param dato: Puede ser cualquier tipo.
	 */
	public void setDato(T dato) {
		this.dato = dato;
	}
	
	/**
	 * pre: -, post: -
	 * @param siguiente: Debe ser un nodo de cualquier dato.
	 */
	public void setSiguiente(Nodo<T> siguiente) {
		this.siguiente = siguiente;
	}
}

