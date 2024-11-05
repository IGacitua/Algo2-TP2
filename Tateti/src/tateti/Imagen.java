package tateti;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import utilidades.Herramientas;

public class Imagen {

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    BufferedImage imagen;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * pre: Recibe una ruta al archivo con la imagen .bmp
     *
     * post: Crea la Imagen
     *
     * @param archivo: Debe ser una ruta a un archivo .bmp
     * @throws Exception: Si la ruta dada o el archivo en ésta no son válidos.
     */
    public Imagen(String archivo) throws Exception {
        if ((archivo == null) || (archivo.trim().isEmpty())) {
            throw new Exception("La ruta dada no es válida.");
        }
        this.imagen = ImageIO.read(new File(archivo));
    }

    /**
     * pre: Recibe una BufferedImage
     *
     * post: Crea la Imagen
     *
     * @param imagen: No debe ser null
     * @throws Exception
     */
    public Imagen(BufferedImage imagen) throws Exception {
        if (imagen == null) {
            throw new Exception("La imagen dada es nula.");
        }
        this.imagen = imagen;
    }

    /**
     * pre: Recibe un ancho y un alto
     *
     * post: Crea una imagen en blanco de las dimensiones dadas.
     *
     * @param ancho: Debe ser > 0
     * @param alto: Debe ser > 0
     *
     * @throws Exception: Cuando alguno de los parametros no es mayor a 0
     */
    public Imagen(int ancho, int alto) throws Exception {
        if (!Herramientas.validarNumeroPositivoEstricto(ancho)) {
            throw new Exception("El ancho debe ser mayor a cero. (Es " + ancho + ")");
        }
        if (!Herramientas.validarNumeroPositivoEstricto(alto)) {
            throw new Exception("El alto debe ser mayor a cero. (Es " + alto + ")");
        }
        int rgb = Color.BLANCO.getRGB();
        this.imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                this.imagen.setRGB(i, j, rgb);
            }
        }
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    /**
     * pre: Recibe un nombre de archivo. (Sin extensión)
     *
     * post: Convierte la imagen a un .bmp en destino.
     *
     * @param destino: No debe ser nulo.
     * @throws Exception: Cuando el nombre de archivo dado no es válido
     */
    public void exportar(String destino) throws Exception {
        if ((destino == null) || (destino.trim().isEmpty())) {
            throw new Exception("El archivo debe tener nombre.");
        }
        File archivo = new File(destino + ".bmp");
        ImageIO.write(this.imagen, "bmp", archivo);
    }

    /**
     * pre: Recibe color separado en R G B
     *
     * post: Convierte todos los pixeles no-vacios al valor RGB dado.
     *
     * @param r: Debe estar entre 0 y 255, inclusive.
     * @param g: Debe estar entre 0 y 255, inclusive.
     * @param b: Debe estar entre 0 y 255, inclusive.
     * @throws Exception: Si el valor RGB dado es inválido.
     */
    public void recolorizar(int r, int g, int b) throws Exception {
        if (!Herramientas.validarNumeroPositivo(r) || r > 255
                || !Herramientas.validarNumeroPositivo(g) || g > 255
                || !Herramientas.validarNumeroPositivo(b) || b > 255) {
            throw new Exception("R G B deben estar entre 0 y 255 inclusive. (Dados " + r + " " + g + " " + b + ")");
        }
        int[][] arrayAuxiliar = this.obtenerMatriz(); // Para poder leer que pixeles están vacíos comodamente
        int rgb = (r << 16) | (g << 8) | b;
        for (int i = 0; i < this.getAncho(); i++) {
            for (int j = 0; j < this.getAlto(); j++) {
                if (arrayAuxiliar[i][j] != -1) {
                    // -1 es un pixel vacio
                    imagen.setRGB(i, j, rgb);
                }
            }
        }
    }

    /**
     * pre: Recibe color RGB
     *
     * post: Convierte todos los pixeles no-vacios al valor RGB dado.
     *
     * @param RGB: Debe estar entre 0x0 y 0xFFFFFF, inclusive.
     * @throws Exception: Si el valor RGB dado es inválido.
     */
    public void recolorizar(int rgb) throws Exception {
        if ((!Herramientas.validarNumeroPositivo(rgb)) || (rgb > 16777215)) {
            throw new Exception("RGB debe estar entre 0 y 16.777.214 inclusive. (Dado " + rgb + ")");
        }
        int[][] arrayAuxiliar = this.obtenerMatriz(); // Para poder leer que pixeles están vacíos comodamente
        for (int i = 0; i < this.getAncho(); i++) {
            for (int j = 0; j < this.getAlto(); j++) {
                if (arrayAuxiliar[i][j] != -1) {
                    // -1 es un pixel vacio
                    imagen.setRGB(i, j, rgb);
                }
            }
        }
    }

    /**
     * post: Convierte la imagen a una matriz RGB.
     *
     * @return Devuelve una matriz de pixeles correspondiente a la imagen.
     */
    private int[][] obtenerMatriz() {
        int[][] matrizImagen = new int[this.getAncho()][this.getAlto()];
        for (int x = 0; x < this.getAncho(); x++) {
            for (int y = 0; y < this.getAlto(); y++) {
                matrizImagen[x][y] = this.imagen.getRGB(x, y);
            }
        }
        return matrizImagen;
    }

    /**
     * pre: Recibe una matriz de pixeles
     *
     * post: Devuelve una BufferedImage en base a la matriz dada.
     *
     * @param matriz: Debe ser una matriz de pixeles válida.
     * @return Devuelve la imagen del tipo BufferedImage.
     * @throws Exception: Si la matriz dada no es válida.
     */
    private BufferedImage obtenerImagen(int[][] matriz) throws Exception {
        if (matriz.length == 0) {
            throw new Exception("La matriz dada es de longitud 0.");
        }
        BufferedImage imagenRetorno = new BufferedImage(matriz.length, matriz[0].length, BufferedImage.TYPE_INT_RGB);
        // matriz.length es ancho, matriz[0].length es alto.
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                imagenRetorno.setRGB(i, j, matriz[i][j]);
            }
        }
        return imagenRetorno;
    }

    /**
     * pre: Recibe una imagen de la misma altura a esta.
     *
     * post: Añade la imagen adicional a la derecha de esta imagen. No modifica
     * ninguna de las imagenes.
     *
     * @param adicional: Debe existir, y ser de la misma altura que esta imagen
     *
     * @return Devuelve una imagen compuesta. Con la imagen adicional a la
     * derecha de esta, sin separación.
     * @throws Exception: Si la imagen adicional es nula, o no tiene la misma
     * altura.
     */
    public Imagen añadirImagenDerecha(Imagen adicional) throws Exception {
        if (adicional == null) {
            throw new Exception("La imagen no puede ser nula.");
        }
        if (this.getAlto() != adicional.getAlto()) {
            throw new Exception("Las imagenes deben tener el mismo alto. Imagen original tiene altura " + this.getAlto() + ". Imagen adicional tiene altura " + adicional.getAlto());
        }
        // Convierte ambas imagenes a matrices.
        int[][] matrizImagenOriginal = this.obtenerMatriz();
        int[][] matrizImagenAdicional = adicional.obtenerMatriz();
        // Crea la matriz de la nueva imagen
        int[][] matrizImagenNueva = new int[this.getAncho() + adicional.getAncho()][this.getAlto()];
        for (int x = 0; x < (this.getAncho() + adicional.getAncho()); x++) {
            for (int y = 0; y < this.getAlto(); y++) {
                if (x < this.getAncho()) {
                    // Caso: Lado izquierdo
                    matrizImagenNueva[x][y] = matrizImagenOriginal[x][y];
                } else {
                    // Caso: Lado derecho
                    matrizImagenNueva[x][y] = matrizImagenAdicional[x - this.getAncho()][y];
                }
            }
        }
        // Crea la Imagen en base a la matriz, y devuelve
        return new Imagen(obtenerImagen(matrizImagenNueva));
    }

    /**
     * pre: Recibe una imagen del mismo ancho a esta.
     *
     * post: Añade la imagen adicional abajo de esta imagen. No modifica ninguna
     * de las imagenes.
     *
     * @param adicional: Debe existir, y ser del mismo ancho que esta imagen
     *
     * @return Devuelve una imagen compuesta. Con la imagen adicional abajo de
     * esta, sin separación.
     * @throws Exception: Si la imagen adicional es nula, o no tiene el mismo
     * ancho
     */
    public Imagen añadirImagenAbajo(Imagen adicional) throws Exception {
        if (adicional == null) {
            throw new Exception("La imagen no puede ser nula.");
        }
        if (this.getAncho() != adicional.getAncho()) {
            throw new Exception("Las imagenes deben tener el mismo ancho. Imagen original tiene ancho " + this.getAncho() + ". Imagen adicional tiene ancho " + adicional.getAncho());
        }
        // Convierte ambas imagenes a matrices.
        int[][] matrizImagenOriginal = this.obtenerMatriz();
        int[][] matrizImagenAdicional = adicional.obtenerMatriz();
        int[][] matrizImagenNueva = new int[this.getAncho()][this.getAlto() + adicional.getAlto()];
        for (int x = 0; x < this.getAncho(); x++) {
            for (int y = 0; y < (this.getAlto() + adicional.getAlto()); y++) {
                if (y < this.getAlto()) {
                    // Caso: Lado superior
                    matrizImagenNueva[x][y] = matrizImagenOriginal[x][y];
                } else {
                    // Caso: Lado inferior
                    matrizImagenNueva[x][y] = matrizImagenAdicional[x][y - this.getAlto()];
                }
            }
        }
        // Crea la Imagen en base a la matriz, y devuelve
        return new Imagen(obtenerImagen(matrizImagenNueva));
    }

    /**
     * pre: Recibe el grosor de los bordes, y el color separado en R G B
     *
     * post: Modifica la imagen, añadiendole un borde del grosor y color dados.
     *
     * @param grosor: Cuantos pixeles de ancho es el borde.
     *
     * @param r: Debe estar entre 0 y 255, inclusive.
     * @param g: Debe estar entre 0 y 255, inclusive.
     * @param b: Debe estar entre 0 y 255, inclusive.
     * @throws Exception: Si alguno de los valores RGB es invalido.
     */
    public void bordear(int grosor, int r, int g, int b) throws Exception {
        // Debido a la recursión, no hay validacion para borde = 0, simplemente no crea borde.
        if (!Herramientas.validarNumeroPositivoEstricto(r) || r > 255
                || !Herramientas.validarNumeroPositivoEstricto(g) || g > 255
                || !Herramientas.validarNumeroPositivoEstricto(b) || b > 255) {
            throw new Exception("R G B deben estar entre 0 y 255 inclusive. (Dados " + r + " " + g + " " + b + ")");
        }
        int rgb = (r << 16) | (g << 8) | b;
        BufferedImage imagenAuxiliar = new BufferedImage(this.getAncho() + 2, this.getAlto() + 2, BufferedImage.TYPE_INT_RGB);
        if (grosor > 0) {
            // Recursión hasta que grosor = 0
            for (int x = 0; x < imagenAuxiliar.getWidth(); x++) {
                for (int y = 0; y < imagenAuxiliar.getHeight(); y++) {
                    if (x == 0 || y == 0) {
                        // Borde Superior / Borde Izquierdo
                        imagenAuxiliar.setRGB(x, y, rgb);
                    } else if (x == imagenAuxiliar.getWidth() - 1 || y == imagenAuxiliar.getHeight() - 1) {
                        // Borde Inferior / Borde Derecho
                        imagenAuxiliar.setRGB(x, y, rgb);
                    } else {
                        imagenAuxiliar.setRGB(x, y, this.imagen.getRGB(x - 1, y - 1)); // +1 Por los bordes superior e izquierdo
                    }
                }
            }
            this.setImagen(imagenAuxiliar); // Modifica la imagen actual
            this.bordear(grosor - 1, rgb); // Llama a una función que es idéntica pero con RGB combinados.
        }
    }

    /**
     * pre: Recibe el grosor de los bordes, y el color RGB
     *
     * post: Modifica la imagen, añadiendole un borde del grosor y color dados.
     *
     * @param grosor: Cuantos pixeles de ancho es el borde.
     *
     * @param RGB: Debe estar entre 0x0 y 0xFFFFFF, inclusive.
     * @throws Exception: Si alguno de los valores RGB es invalido.
     */
    public void bordear(int profundidad, int rgb) throws Exception {
        // Debido a la recursión, no hay validacion para borde = 0, simplemente no crea borde.
        if ((!Herramientas.validarNumeroPositivo(rgb)) || (rgb > 16777215)) {
            throw new Exception("RGB debe estar entre 0 y 16.777.214 inclusive. (Dado " + rgb + ")");
        }
        BufferedImage imagenAuxiliar = new BufferedImage(this.getAncho() + 2, this.getAlto() + 2, BufferedImage.TYPE_INT_RGB);
        if (profundidad > 0) {
            // Recursión hasta que grosor = 0
            for (int x = 0; x < imagenAuxiliar.getWidth(); x++) {
                for (int y = 0; y < imagenAuxiliar.getHeight(); y++) {
                    if (x == 0 || y == 0) {
                        // Borde Superior / Borde Izquierdo
                        imagenAuxiliar.setRGB(x, y, rgb);
                    } else if (x == imagenAuxiliar.getWidth() - 1 || y == imagenAuxiliar.getHeight() - 1) {
                        // Borde Inferior / Borde Derecho
                        imagenAuxiliar.setRGB(x, y, rgb);
                    } else {
                        imagenAuxiliar.setRGB(x, y, this.imagen.getRGB(x - 1, y - 1)); // +1 Por los bordes superior e izquierdo
                    }
                }
            }
            this.setImagen(imagenAuxiliar); // Modifica la imagen actual
            this.bordear(profundidad - 1, rgb); // Vuelve a llamarse a si misma
        }
    }

    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * @return Devuelve el ancho de la imagen.
     */
    public int getAncho() {
        return this.imagen.getWidth();
    }

    /**
     * @return Devuelve el alto de la imagen.
     */
    public int getAlto() {
        return this.imagen.getHeight();
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------	
    /**
     * pre: Recibe una BufferedImage
     *
     * post: Reemplaza la imagen por la dada
     *
     * @param imagen: No debe ser nula.
     * @throws Exception: Si la imagen dada es nula.
     */
    public void setImagen(BufferedImage imagen) throws Exception {
        if (imagen == null) {
            throw new Exception("La imagen no puede ser nula");
        }
        this.imagen = imagen;
    }

}
