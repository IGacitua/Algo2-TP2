package tateti;

public class NodoLista<T> {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	
	//ATRIBUTOS -----------------------------------------------------------------------------------------------
	private T dato;
	private NodoLista<T> siguiente = null;
	//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	// TODO pre-post
	NodoLista(T dato){
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
	public NodoLista<T> getSiguiente() {
		return siguiente;
	}
	
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
	
	// TODO pre-post
	public void setDato(T dato) {
		this.dato = dato;
	}
	
	// TODO pre-post
	public void setSiguiente(NodoLista<T> siguiente) {
		this.siguiente = siguiente;
	}
}