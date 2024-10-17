package tateti;

public class PilaGenerica<T> {
	//Atriibutos -------------------------------------------
	private Nodo<T> tope;
	private int longitud;
	
	//Constructor -------------------------------------------
	public PilaGenerica() {
		this.tope=null;
		this.longitud=0;
	}
	
	//Apilar ---------------------------------------------------
	public void agregar(T elemento) {
		Nodo<T> nuevo= new Nodo<T>(elemento);
		nuevo.setSiguiente(this.tope);
		this.tope=nuevo;
		this.longitud++;
	}
	
	public void agregar(Lista<T> lista) throws Exception {
		if(lista==null || 
				lista.estaVacia()) {
			throw new Exception("La lista esta vacia");
		}
		while(!lista.avanzarCursor()) {
			this.agregar(lista.obtenerCursor());;
		}
	}
	
	//getters ----------------------------------------------------
	public boolean estaVacia() {
		return this.longitud==0;
	}
	public int getCantidadElementos() {
		return this.longitud;
	}
	public T verTope() {
		T elemento =null;
		if(!this.estaVacia()) {
			elemento=this.tope.getDato();
		}
		return elemento;
	}
	//Desapilar -------------------------------------------------
	public T quitar() {
		T elemento=null;
		if(!this.estaVacia()) {
			elemento=this.tope.getDato();
			
			this.tope=this.tope.getSiguiente();
			this.longitud--;
		}
		
		return elemento;
	}
	
	//metodos de comportamiento ----------------------------------
	
	public PilaGenerica<T> invertirPila(){
		PilaGenerica<T> resultado=new PilaGenerica<T>();
		while(!this.estaVacia()) {
			resultado.agregar(this.quitar());
		}
		return resultado;
	}
	
	
}
