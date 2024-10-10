package tateti;

public class Lista<T> {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------

    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private NodoLista<T> primero = null;
    private int longitud = 0;
    private NodoLista<T> cursor = null;
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    // TODO pre-post
    public Lista() {
        this.primero = null;
        this.longitud = 0;
        this.cursor = null;
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //TODO pre-post
    public boolean esVacia() {
        return (this.longitud == 0);
    }

    //TODO pre-post
    public void agregarElemento(T elemento) throws Exception {
        this.agregarElemento(this.getLongitud() + 1, elemento);
    }

    //TODO pre-post
    public void agregarElemento(int posicion, T elemento) throws Exception {
        validarPosicionParaAgregar(posicion);
        NodoLista<T> nuevo = new NodoLista<>(elemento);
        if (posicion == 1) {
            nuevo.setSiguiente(this.primero);
            this.primero = nuevo;
        } else {
            NodoLista<T> anterior = this.getNodo(posicion - 1);
            nuevo.setSiguiente(anterior.getSiguiente());
            anterior.setSiguiente(nuevo);
        }
        this.longitud++;
    }

    //TODO pre-post
    public void remover(int posicion) throws Exception {
        validarPosicionEnLista(posicion);
        this.cursor = null;
        NodoLista<T> removido;
        if (posicion == 1) {
            removido = this.primero;
            this.primero = removido.getSiguiente();
        } else {
            NodoLista<T> anterior = this.getNodo(posicion - 1);
            removido = anterior.getSiguiente();
            anterior.setSiguiente(removido.getSiguiente());
        }
        this.longitud--;
    }

    //TODO pre-post
    public void remover(T valor) throws Exception {
        cursor = this.primero;
        int posicion = 1;
        while (cursor != null) {
            if (cursor.getDato().equals(valor)) {
                remover(posicion);
                return;
            }
            cursor = cursor.getSiguiente();
            posicion++;
        }
        throw new Exception("El valor '" + valor + "' no existe");
    }

    //TODO pre-post
    public T obtener(int posicion) throws Exception {
        validarPosicionEnLista(posicion);
        return this.getNodo(posicion).getDato();
    }

    //TODO pre-post
    public void cambiar(T elemento, int posicion) throws Exception {
        validarPosicionEnLista(posicion);
        this.getNodo(posicion).setDato(elemento);
    }

    //TODO pre-post
    public void iniciarCursor() {
        this.cursor = null;
    }

    //TODO pre-post
    public boolean avanzarCursor() {
        if (this.cursor == null) {
            this.cursor = this.primero;
        } else {
            this.cursor = this.cursor.getSiguiente();
        }

        /* pudo avanzar si el cursor ahora apunta a un nodo */
        return (this.cursor != null);
    }

    //TODO pre-post
    public T obtenerCursor() {
        T elemento = null;
        if (this.cursor != null) {
            elemento = this.cursor.getDato();
        }
        return elemento;
    }

    //TODO pre-post
    public boolean existe(T valor) {
        cursor = this.primero;
        while (cursor != null) {
            if (cursor.getDato().equals(valor)) {
                return true;
            }
            cursor = cursor.getSiguiente();
        }
        return false;
    }

    //TODO pre-post
    public int contarOcurrencias(T valor) {
        cursor = this.primero;
        int cantidadDeOcurrencias = 0;
        while (cursor != null) {
            if (cursor.getDato().equals(valor)) {
                cantidadDeOcurrencias++;
            }
            cursor = cursor.getSiguiente();
        }
        return cantidadDeOcurrencias;
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //TODO pre-post
    public int getLongitud() {
        return this.longitud;
    }

    //TODO pre-post
    private NodoLista<T> getNodo(int posicion) throws Exception {
        validarPosicionEnLista(posicion);
        NodoLista<T> actual = this.primero;
        for (int i = 1; i < posicion; i++) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    //TODO pre-post
    private void validarPosicionParaAgregar(int posicion) throws Exception {
        if ((posicion < 1)
                || (posicion > this.longitud + 1)) {
            throw new Exception("La posicion debe estar entre 1 y tamaño + 1");
        }
    }

    //TODO pre-post
    private void validarPosicionEnLista(int posicion) throws Exception {
        if ((posicion < 1)
                || (posicion > this.longitud)) {
            throw new Exception("La posicion debe estar entre 1 y tamaño");
        }
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
