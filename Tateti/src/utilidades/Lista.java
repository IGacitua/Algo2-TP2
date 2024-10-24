package utilidades;

public class Lista<T> {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------

    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private Nodo<T> primero = null;
    private int longitud = 0;
    private Nodo<T> cursor = null;
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre: - post: -
     * Inicializa la lista
     */
    public Lista() {
        this.primero = null;
        this.longitud = 0;
        this.cursor = null;
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    /**
     * pre: -, post: -
     * @return Devuelve un booleano correspondiente a si la lista está vacía o no
     */
    public boolean esVacia() {
        return (this.longitud == 0);
    }

    /**
     * pre: -, post: -
     * @param elemento: puede ser de cualquier tipo
     * @throws Exception
     */
    public void agregarElemento(T elemento) throws Exception {
        this.agregarElemento(this.getLongitud() + 1, elemento);
    }

    /**
     * pre: la posicion debe ser válida, post: -
     * @param posicion: debe estar dentro de la lista
     * @param elemento: puede ser cualquier tipo
     * @throws Exception
     */
    public void agregarElemento(int posicion, T elemento) throws Exception {
        validarPosicionParaAgregar(posicion);
        Nodo<T> nuevo = new Nodo<>(elemento);
        if (posicion == 1) {
            nuevo.setSiguiente(this.primero);
            this.primero = nuevo;
        } else {
            Nodo<T> anterior = this.getNodo(posicion - 1);
            nuevo.setSiguiente(anterior.getSiguiente());
            anterior.setSiguiente(nuevo);
        }
        this.longitud++;
    }

    /**
     * pre: la posicion debe ser válida, post: -
     * Elimina un elemento solo por su posición
     * @param posicion: debe estar dentro de la lista
     * @throws Exception
     */
    public void remover(int posicion) throws Exception {
        validarPosicionEnLista(posicion);
        this.cursor = null;
        Nodo<T> removido;
        if (posicion == 1) {
            removido = this.primero;
            this.primero = removido.getSiguiente();
        } else {
            Nodo<T> anterior = this.getNodo(posicion - 1);
            removido = anterior.getSiguiente();
            anterior.setSiguiente(removido.getSiguiente());
        }
        this.longitud--;
    }

    /**
     * pre: el valor debe estar en la lista, post: -
     * Elimina un elemento por el valor del mismo
     * @param valor: puede ser de cualquier tipo
     * @throws Exception
     */
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

    /**
     * pre: la posicion debe ser válida, post: -
     * @param posicion: debe ser válida
     * @return Devuelve el dato en la posicion ingresada
     * @throws Exception
     */
    public T obtenerDato(int posicion) throws Exception {
        validarPosicionEnLista(posicion);
        return this.getNodo(posicion).getDato();
    }

    /**
     * pre: la posicion debe ser válida, post: -
     * Cambia el dato de determinada posición por uno nuevo (elemento)
     * @param elemento: puede ser de cualquier tipo
     * @param posicion: debe ser válida
     * @throws Exception
     */
    public void cambiarDato(T elemento, int posicion) throws Exception {
        validarPosicionEnLista(posicion);
        this.getNodo(posicion).setDato(elemento);
    }

    /**
     * pre: -, post: -
     * Inicia el cursor
     */
    public void iniciarCursor() {
        this.cursor = null;
    }

    /**
     * pre: -, post: -
     * Avanza el cursor
     * @return Devuelve un boolean correspondiente a si pudo avanzar o no
     */
    public boolean avanzarCursor() {
        if (this.cursor == null) {
            this.cursor = this.primero;
        } else {
            this.cursor = this.cursor.getSiguiente();
        }

        /* pudo avanzar si el cursor ahora apunta a un nodo */
        return (this.cursor != null);
    }

    /**
     * pre: -, post: -
     * @return Devuelve el elemento que se halla donde está el cursor
     */
    public T obtenerCursor() {
        T elemento = null;
        if (this.cursor != null) {
            elemento = this.cursor.getDato();
        }
        return elemento;
    }

    /**
     * pre: -, post: -
     * @param valor: puede ser de cualquier tipo
     * @return Devuelve un boolean correspondiente a si existe el
     * valor o no
     */
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

    /**
     * pre: -, post: -
     * @param valor: puede ser cualquier tipo
     * @return Devuelve la cantidad de veces que se repite
     * ese mismo valor en la lista
     */
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
    /**
     * pre: -, post: -
     * @return Devuelve la longitud de la lista
     */
    public int getLongitud() {
        return this.longitud;
    }

    /**
     * pre: la posición debe ser válida, post: -
     * @param posicion: debe ser válida
     * @return Devuelve el nodo actual donde se halla la lista
     * @throws Exception
     */
    private Nodo<T> getNodo(int posicion) throws Exception {
        validarPosicionEnLista(posicion);
        Nodo<T> actual = this.primero;
        for (int i = 1; i < posicion; i++) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    /**
     * pre: -, post: -
     * @param posicion: debe formar parte de la lista
     * @throws Exception
     */
    private void validarPosicionParaAgregar(int posicion) throws Exception {
        if ((posicion < 1)
                || (posicion > this.longitud + 1)) {
            throw new Exception("La posicion debe estar entre 1 y tamaño + 1. (Es " + posicion + ")");
        }
    }

    /**
     * pre: -, post: -
     * @param posicion: debe ser válida
     * @throws Exception
     */
    private void validarPosicionEnLista(int posicion) throws Exception {
        if ((posicion < 1)
                || (posicion > this.longitud)) {
            throw new Exception("La posicion debe estar entre 1 y tamaño. (Es " + posicion + ")");
        }
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
