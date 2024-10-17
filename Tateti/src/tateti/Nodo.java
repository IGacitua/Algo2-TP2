package tateti;

public class Nodo<T> {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	
	//ATRIBUTOS -----------------------------------------------------------------------------------------------
	private T dato;
	private Nodo<T> siguiente = null;
	//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	// TODO pre-post
	Nodo(T dato){
		this.dato = dato;
		this.siguiente = null;
	}

	//METODOS DE CLASE ----------------------------------------------------------------------------------------
	
	//METODOS GENERALES ---------------------------------------------------------------------------------------
	
	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
	
	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	
	// TODO pre-post
	public T getDato() {
		return dato;
	}
	
	// TODO pre-post
	public Nodo<T> getSiguiente() {
		return siguiente;
	}
	
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
	
	// TODO pre-post
	public void setDato(T dato) {
		this.dato = dato;
	}
	
	// TODO pre-post
	public void setSiguiente(Nodo<T> siguiente) {
		this.siguiente = siguiente;
	}
}