package tateti;

import java.util.Scanner;
import java.io.IOException;

public class Teclado {
    // Declarar el Scanner como atributo estático
    private static final Scanner teclado = new Scanner(System.in);

    // Método para pedir el nombre
    public static String pedirNombre() {
        System.out.print("Por favor, ingrese el nombre del jugador: ");
        return scanner.nextLine();
    }

    // Otros métodos que también utilizan el Scanner
    public static int pedirNumero() {
        System.out.print("Por favor, ingrese un número: ");
        return scanner.nextInt();
    }

    // Método para cerrar el Scanner al final del programa
    public static void cerrarScanner() {
        scanner.close();
    }
}
