package utilidades;

public class PilaGenerica<T> {
	//ATRIBUTOS -------------------------------------------
	private Nodo<T> tope;
	private int longitud;
	
	//CONSTRUCTOR -------------------------------------------
	/**
	 * pre: -, post: inicializa la pila
	 */
	public PilaGenerica() {
		this.tope=null;
		this.longitud=0;
	}
	
	//Apilar ---------------------------------------------------
	/**
	 * pre: -, post: añade un nuevo elemento a la pila
	 * @param elemento: puede ser cualquier tipo
	 */
	public void agregar(T elemento) {
		Nodo<T> nuevo= new Nodo<T>(elemento);
		nuevo.setSiguiente(this.tope);
		this.tope=nuevo;
		this.longitud++;
	}
	
	/**
	 * pre: -, post: añade un nuevo elemento a la lista
	 * @param lista: no debe estar vacía
	 * @throws Exception
	 */
	public void agregar(Lista<T> lista) throws Exception {
		if(lista==null || 
				lista.esVacia()) {
			throw new Exception("La lista esta vacia");
		}
		while(!lista.avanzarCursor()) {
			this.agregar(lista.obtenerCursor());;
		}
	}
	
	//GETTERS ----------------------------------------------------
	/**
	 * pre: -, post: verifica si la pila está vacía
	 * @return devuelve un booleano correspondiente a si la pila está vacía o no
	 */
	public boolean estaVacia() {
		return this.longitud==0;
	}
	
	/**
	 * pre: -, post: -
	 * @return devuelve la cantidad de elementos de la pila
	 */
	public int getCantidadElementos() {
		return this.longitud;
	}
	
	/**
	 * pre: no debe estar vacía, post: -
	 * @return devuelve el elemento correspondiente al tope de la pila
	 */
	public T verTope() {
		T elemento =null;
		if(!this.estaVacia()) {
			elemento=this.tope.getDato();
		}
		return elemento;
	}
	
	//Desapilar -------------------------------------------------
	/**
	 * pre: no debe estar vacía, post: -
	 * @return devuelve el elemento quitado de la pila
	 */
	public T quitar() {
		T elemento=null;
		if(!this.estaVacia()) {
			elemento=this.tope.getDato();
			
			this.tope=this.tope.getSiguiente();
			this.longitud--;
		}
		
		return elemento;
	}
	
	//MÉTODOS DE COMPORTAMIENTO ----------------------------------
	/**
	 * pre. no debe estar vacía, post: -
	 * @return devuelve la pila invertida
	 */
	public PilaGenerica<T> invertirPila(){
		PilaGenerica<T> resultado=new PilaGenerica<T>();
		while(!this.estaVacia()) {
			resultado.agregar(this.quitar());
		}
		return resultado;
	}
	
	
}
