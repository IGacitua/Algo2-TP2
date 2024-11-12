package utilidades;

import java.util.Scanner;

public class Teclado {
    // Declarar el Scanner como atributo estático
    private static final Scanner teclado = new Scanner(System.in);
    
    /**
     * pre: -, post: Pide un nombre de usuario por consola.
     * Hasta que no se ingrese un nombre valido, no sale del método.
     * @return Devuelve el nombre ingresado por consola.
     */
    public static String pedirNombre() {
        boolean terminado = false;
        String nombre = null;
        System.out.println("Ingrese un nombre: ");
        while (!terminado) {
    		nombre = teclado.nextLine();
    		if (nombre.trim().isEmpty()) {
    			System.out.println("No ingresó un nombre válido, vuelva a intentarlo.");
    		} else {
    			terminado = true;
    			teclado.next();
    		}
    	}
        return nombre;       
    }

    /**
     * pre: -, post: Pide un número por consola.
     * Hasta que no se ingrese un número valido, no sale del método.
     * @return Devuelve el número ingresado por consola.
     */
    public static int pedirNumero() {
       boolean terminado = false;
       int numero = 0;
       System.out.println("Ingrese un número: ");
       while (!terminado) {
           if (teclado.hasNextInt()) {
               numero = teclado.nextInt();
               terminado = true; 
           } else {
               System.out.println("No ingresó un número válido, vuelva a intentarlo.");
               teclado.next(); 
           }
       }
       return numero;
   }
    
    /**
     * pre: min y max deben ser válidos,
     * post: Devuelve el numero ingresado por consola.
     * El número a ingresar debe estar entre min y max.
     * @param min: Puede ser cualquier número entero. 
     * @param max: Puede ser cualquier número entero.
     * @return Devuelve el número ingresado por consola en caso de estar entre min y max.
     * @throws Exception: Si el mínimo y el máximo son iguales.
     */
    public static int pedirNumero(int min, int max) throws Exception {
    	if (min == max) {
    		throw new Exception("El mínimo y el máximo deben ser dos números distintos.");
    	}
    	if (min > max) { //TODO: yo acá lanzaría una excepción pero idk
    		int aux=min;
    		min=max;
    		max=aux;
    	}
        boolean terminado = false;
        int numero = 0;
        System.out.println("Ingrese un número: ");
        while (!terminado) {
            if (teclado.hasNextInt()) {
                numero = teclado.nextInt();
                terminado = true; 
                if( numero>= min && numero<=max){
                    terminado=true;
                    teclado.next();
                } else {
                    System.out.println("No ingresaste un numero en el rango permitido, reintente");
                }
            } else {
                System.out.println("No ingresó un número válido, vuelva a intentarlo.");
                teclado.next(); 
            }
        }
        return numero;
     }
    
    /**
     * pre: -, post: Cierra el teclado.
     */
    public static void cerrarScanner() {
        teclado.close();
    }
}
