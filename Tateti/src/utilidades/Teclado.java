package utilidades;

import java.util.Scanner;

public class Teclado {
    private static final Scanner teclado = new Scanner(System.in);
    
    /**
     * pre: -, post: Pide un String por consola.
     * Hasta que no se ingrese un nombre valido, no sale del método.
     * @param mensaje Puede especificarse un mensaje al pedir el String, pero
     * en caso de que no se necesite, puede ser nulo o vacío y se lo reemplaza
     * por "Ingrese una cadena de texto". 
     * @return Devuelve la entrada ingresada por consola.
     */
    public static String pedirString(String mensaje) {
        if ((mensaje.trim().isEmpty()) || (mensaje == null)) {
            mensaje = "Ingrese una cadena de texto";
        }
        boolean terminado = false;
        String entrada = null;
        while (!terminado) {
            System.out.print(mensaje + ": ");
            entrada = teclado.nextLine();
            if (entrada.trim().isEmpty()) {
                System.out.println("No ingresó un valor válido, vuelva a intentarlo.");
            } else {
                terminado = true;
            }
        }
        return entrada;
    }
    
    /**
     * pre: -, post: Pide un número por consola.
     * Hasta que no se ingrese un número valido, no sale del método.
     * @param mensaje Puede especificarse un mensaje al pedir el número, pero
     * en caso de que no se necesite, puede ser nulo o vacío y se lo reemplaza
     * por "Ingrese un número".
     * @return Devuelve el número ingresado por consola.
     */
    public static int pedirNumero(String mensaje) {
    	if ((mensaje.trim().isEmpty()) || (mensaje == null)) {
    		mensaje = "Ingrese un número";
    	}
        boolean terminado = false;
        int numero = 0;
        while (!terminado) {
        	System.out.print(mensaje + ": ");
            if (teclado.hasNextInt()) {
                numero = teclado.nextInt();
                terminado = true; 
            } else {
                System.out.println("No ingresó un número válido, vuelva a intentarlo.");
                teclado.next();  //Limpia el buffer
            }
        }
        teclado.nextLine();
        return numero;
    }
    
    /**
     * pre: min y max deben ser válidos,
     * post: Devuelve el numero ingresado por consola.
     * El número a ingresar debe estar entre min y max, inclusive para ambos.
     * @param mensaje Puede especificarse un mensaje al pedir el número, pero
     * en caso de que no se necesite, puede ser nulo o vacío y se lo reemplaza
     * por "Ingrese un número".
     * @param min Puede ser cualquier número entero. 
     * @param max Puede ser cualquier número entero.
     * @return Devuelve el número ingresado por consola en caso de estar entre min y max.
     */
    public static int pedirNumeroEntreIntervalo(String mensaje, int min, int max) {
    	if ((mensaje.trim().isEmpty()) || (mensaje == null)) {
    		mensaje = "Ingrese un número";
    	}
        if (min > max) { //Si min es mayor que max, intercambia los valores
            int aux = min;
            min = max;
            max = aux;
        }
        boolean terminado = false;
        int numero = 0;
        while (!terminado) {
        	System.out.print(mensaje + " (entre " + min + " y " + max + "): ");
            if (teclado.hasNextInt()) {
                numero = teclado.nextInt();
                if (numero >= min && numero <= max) {
                	terminado = true;
                	//teclado.next();
                } else {
                    System.out.println("El número ingresado no está dentro del rango permitido, vuelva a intentarlo.");
                }
            } else {
                System.out.println("No ingresó un número válido, vuelva a intentarlo.");
                teclado.next();  //Limpia el buffer
            }
        }
        teclado.nextLine();
        return numero;
    }
    
    /**
     * pre: -, post: Cierra el teclado.
     */
    public void cerrarScanner() {
        teclado.close();
    }
}
