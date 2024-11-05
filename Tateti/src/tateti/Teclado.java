package tateti;

import java.util.Scanner;

public class Teclado {
    // Declarar el Scanner como atributo estático
    private static final Scanner teclado = new Scanner(System.in);
    
    /**
     * pre: Iniciar previamente el teclado
     * post: Pide por consola un nombre de usuario, hasta que no ponga un nombre valido, no sale del metodo
     * @return un String con el nombre ingresado por consola 
     */
    public static String pedirNombre() {
        boolean terminado=false;
        String nombre=null;
        System.out.println("Ingrese un nombre:");
        
        while(!terminado) {
        	try {
        		nombre=teclado.nextLine();
        		
        		if(nombre.trim().isEmpty()) {
        			throw new Exception("nombre invalido, reintente");
        		}else {
        			terminado=true;
        			teclado.next();
        		}
        		
        	}catch(Exception e) {
        		
        	}
        }
        return nombre;       
    }

    /**
     * pre: iniciar previamente el teclado
     * post: devuelve un numero entero ingresado por consola, hasta que lo ingresado sea un numero entero valido sigue
     *		 pidiendo numeros
     * @return
     */
    public static int pedirNumero() {
       boolean terminado=false;
       int numero=0;
       System.out.println("Ingrese un numero por consola");
       
       while(!terminado) {
    	   try {
    		   numero=teclado.nextInt();
    		   terminado=true;
    		   teclado.next();
    	   }catch(Exception e) {
    		   System.out.println("No ingresaste un numero entero por consola, reintente");
    		   teclado.next();
    	   }
       }
       return numero;
    }

    public static int pedirNumero(int min,int max) {
        boolean terminado=false;
        int numero=0;
        System.out.println("Ingrese un numero por consola");
        
        while(!terminado) {
            try {
                numero=teclado.nextInt();
                if(numero>= min && numero<=max){
                    terminado=true;
                    teclado.next();
                } else {
                    System.out.println("No ingresaste un numero en el rango permitido, reintente");
                }
            }catch(Exception e) {
                System.out.println("No ingresaste un numero entero por consola, reintente");
                teclado.next();
            }
        }
        return numero;
     }
    
    /**
     * pre: abrir previamente el teclado
     * post: cierra el teclado y no permite ingresar mas inputs
     */
    public static void cerrarScanner() {
        teclado.close();
    }
}
