package tateti;

public class Test {

    public static void main(String[] args) {
        try {
            Tablero tablero = new Tablero(3, 3, 3);
            tablero.establecerEntornos();
            tablero.colocarFicha(1, 1, 0, new Jugador(10, 9, 1));
            tablero.exportar();
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
