package tateti;

public class Test {

    public static void main(String[] args) {
        try {
            Tablero tablero = new Tablero(3, 3, 3);
            tablero.establecerEntornos();
            int rgbCircular = (255 << 16) | (64 << 8) | 64;
            int rgbCuadrado = (64 << 16) | (255 << 8) | 64;
            int rgbTriangulo = (64 << 16) | (64 << 8) | 255;
            Jugador circular = new Jugador("Pepe", 1, 10, 10, Fichas.CIRCULO, 'A', rgbCircular);
            Jugador cuadratico = new Jugador("Cuadratico", 2, 10, 10, Fichas.CUADRADO, 'B', rgbCuadrado);
            Jugador triangulado = new Jugador("Triangulado", 1, 10, 10, Fichas.TRIANGULO, 'C', rgbTriangulo);
            tablero.colocarFicha(0, 0, 0, circular);
            tablero.colocarFicha(2, 2, 0, cuadratico);
            tablero.colocarFicha(1, 1, 0, triangulado);
            tablero.exportar("Tablero Exportado");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        /*System.out.println(new File("").getAbsolutePath());
        try {
            Imagen imagen = new Imagen("C:/Users/Gacitua/Documents/UBA/Algoritmos y Estructuras de Datos/Tp-2/Tateti/src/imagenes/number_0.bmp");
            imagen.bordear(1, 64, 64, 64);
            for (int i = 1; i < 10; i++) {
                String path = "C:/Users/Gacitua/Documents/UBA/Algoritmos y Estructuras de Datos/Tp-2/Tateti/src/imagenes/number_" + i + ".bmp";
                Imagen adicional = new Imagen(path);
                adicional.recolorizar((new Random()).nextInt(255), (new Random()).nextInt(255), (new Random()).nextInt(255));
                adicional.bordear(1, 64, 64, 64);
                imagen = imagen.añadirImagenAbajo(adicional);
            }
            imagen.exportar("Combo Bordeado");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }*/
    }
}
