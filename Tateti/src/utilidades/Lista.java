package utilidades;

public class Lista<T> {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------

    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private Nodo<T> primero = null;
    private int longitud = 0;
    private Nodo<T> cursor = null;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * pre: - post: Inicializa la lista.
     */
    public Lista() {
        this.primero = null;
        this.longitud = 0;
        this.cursor = null;
    }

    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    /**
     * pre: -, post: -
     *
     * @return Devuelve un booleano correspondiente a si la lista está vacía o
     * no.
     */
    public boolean esVacia() {
        return (this.longitud == 0);
    }

    /**
     * pre: -, post: -
     *
     * @param elemento: Ppuede ser de cualquier tipo.
     */
    public void agregarElemento(T elemento) {
        try {
            this.agregarElemento(this.getLongitud() + 1, elemento);
        } catch (Exception e) {
            System.out.println("Excepción imposible al agregar elemento a la Lista.");
        }
    }

    /**
     * pre: La posicion debe ser válida, post: -
     *
     * @param posicion: Debe estar dentro de la lista.
     * @param elemento: Puede ser cualquier tipo.
     * @throws Exception: Si la posicion no está entre 1 y la longitud de la
     * lista.
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
     * pre: La posicion debe ser válida, post: Elimina un elemento solo por su
     * posición.
     *
     * @param posicion: Debe estar dentro de la lista.
     * @throws Exception: Si la posicion no está entre 1 y la longitud de la
     * lista menos uno.
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
     * pre: El valor debe estar en la lista, post: Elimina un elemento por el
     * valor del mismo.
     *
     * @param valor: Puede ser de cualquier tipo.
     * @throws Exception: Si el valor no está en la lista.
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
     * pre: La posicion debe ser válida, post: -
     *
     * @param posicion: Debe ser válida.
     * @return Devuelve el dato en la posicion ingresada.
     * @throws Exception: Si la posicion no está entre 1 y la longitud de la
     * lista menos uno.
     */
    public T obtenerDato(int posicion) throws Exception {
        validarPosicionEnLista(posicion);
        return this.getNodo(posicion).getDato();
    }

    /**
     * pre: La posicion debe ser válida, post: Cambia el dato de determinada
     * posición por uno nuevo (elemento).
     *
     * @param elemento: Puede ser de cualquier tipo.
     * @param posicion: Debe ser válida.
     * @throws Exception: Si la posicion no está entre 1 y la longitud de la
     * lista menos uno.
     */
    public void cambiarDato(T elemento, int posicion) throws Exception {
        validarPosicionEnLista(posicion);
        this.getNodo(posicion).setDato(elemento);
    }

    /**
     * pre: -, post: Inicia el cursor.
     */
    public void iniciarCursor() {
        this.cursor = null;
    }

    /**
     * pre: -, post: - Avanza el cursor
     *
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
     *
     * @return Devuelve el elemento que se halla donde está el cursor.
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
     *
     * @param valor: Puede ser de cualquier tipo.
     * @return Devuelve un boolean correspondiente a si existe el valor o no.
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
     *
     * @param valor: Puede ser cualquier tipo.
     * @return Devuelve la cantidad de veces que se repite ese mismo valor en la
     * lista.
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

    /**
     * post: Copia los valores de la Lista a una nueva. Ambas listas son
     * independientes una de la otra
     *
     * @return: Devuelve la copia de la Lista
     */
    public Lista<T> copiar() {
        Lista<T> copia = new Lista<>();
        this.iniciarCursor();
        while (this.avanzarCursor()) {
            copia.agregarElemento(this.obtenerCursor());
        }
        return copia;
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * pre: -, post: -
     *
     * @return Devuelve la longitud de la lista.
     */
    public int getLongitud() {
        return this.longitud;
    }

    /**
     * pre: La posición debe ser válida, post: -
     *
     * @param posicion: Debe ser válida.
     * @return Devuelve el nodo actual donde se halla la lista.
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
     *
     * @param posicion: Debe formar parte de la lista.
     * @throws Exception: Si la posicion no está entre 1 y la longitud de la
     * lista.
     */
    private void validarPosicionParaAgregar(int posicion) throws Exception {
        if ((posicion < 1)
                || (posicion > this.longitud + 1)) {
            throw new Exception("La posicion debe estar entre 1 y tamaño + 1. (Es " + posicion + ")");
        }
    }

    /**
     * pre: -, post: -
     *
     * @param posicion: Debe ser válida.
     * @throws Exception: Si la posicion no está entre 1 y la longitud de la
     * lista menos uno.
     */
    private void validarPosicionEnLista(int posicion) throws Exception {
        if ((posicion < 1)
                || (posicion > this.longitud)) {
            throw new Exception("La posicion debe estar entre 1 y tamaño. (Es " + posicion + ")");
        }
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
