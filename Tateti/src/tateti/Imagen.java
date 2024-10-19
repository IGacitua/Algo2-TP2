package tateti;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Imagen {

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    BufferedImage imagen;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    //TODO pre-post
    // Crea una imagen desde un archivo
    public Imagen(String archivo) throws Exception {
        try {
            this.imagen = ImageIO.read(new File(archivo));
        } catch (IOException ex) {
            throw new Exception("Archivo invalido.");
        }
    }

    //TODO pre-post
    // Crea un Imagen desde una BufferedImage
    public Imagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //TODO pre-post
    // Convierte la imagen a un .bmp en destino
    public void exportar(String destino) throws Exception {
        try {
            File archivo = new File(destino + ".bmp");
            ImageIO.write(this.imagen, "bmp", archivo);
        } catch (IOException ex) {
            throw new Exception("Destino invalido.");
        }
    }

    //TODO pre-post
    // Convierte todos los pixeles no-vacios al valor rgb dado
    public void recolorizar(int r, int g, int b) {
        int[][] arrayAuxiliar = this.obtenerArray();
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

    //TODO pre-post
    // Convierte la imagen a un array de rgb
    private int[][] obtenerArray() {
        int[][] arrayImagen = new int[this.getAncho()][this.getAlto()];
        for (int x = 0; x < this.getAncho(); x++) {
            for (int y = 0; y < this.getAlto(); y++) {
                arrayImagen[x][y] = this.imagen.getRGB(x, y);
            }
        }
        return arrayImagen;
    }

    //TODO pre-post
    private BufferedImage obtenerImagen(int[][] array) {
        BufferedImage imagenRetorno = new BufferedImage(array.length, array[0].length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                imagenRetorno.setRGB(i, j, array[i][j]);
            }
        }
        return imagenRetorno;
    }

    //TODO pre-post
    // Añade la imagen "adicional" a la derecha de this
    public Imagen añadirImagenDerecha(Imagen adicional) throws Exception {
        if (this.getAlto() != adicional.getAlto()) {
            throw new Exception("Las imagenes deben tener la misma altura.");
        }
        int[][] arrayOriginal = this.obtenerArray();
        int[][] arrayAdicional = adicional.obtenerArray();
        int[][] arrayNuevo = new int[this.getAncho() + adicional.getAncho()][this.getAlto()];
        for (int x = 0; x < (this.getAncho() + adicional.getAncho()); x++) {
            for (int y = 0; y < this.getAlto(); y++) {
                if (x < this.getAncho()) {
                    arrayNuevo[x][y] = arrayOriginal[x][y];
                } else {
                    arrayNuevo[x][y] = arrayAdicional[x - this.getAncho()][y];
                }
            }
        }
        return new Imagen(obtenerImagen(arrayNuevo));
    }

    //TODO pre-post
    // Añade la imagen "adicional" abajo de this
    public Imagen añadirImagenAbajo(Imagen adicional) throws Exception {
        if (this.getAncho() != adicional.getAncho()) {
            throw new Exception("Las imagenes deben tener el mismo ancho.");
        }
        int[][] arrayOriginal = this.obtenerArray();
        int[][] arrayAdicional = adicional.obtenerArray();
        int[][] arrayNuevo = new int[this.getAncho()][this.getAlto() + adicional.getAlto()];
        for (int x = 0; x < this.getAncho(); x++) {
            for (int y = 0; y < (this.getAlto() + adicional.getAlto()); y++) {
                if (y < this.getAlto()) {
                    arrayNuevo[x][y] = arrayOriginal[x][y];
                } else {
                    arrayNuevo[x][y] = arrayAdicional[x][y - this.getAlto()];
                }
            }
        }
        return new Imagen(obtenerImagen(arrayNuevo));
    }

    //TODO pre-post
    public void bordear(int profundidad, int r, int g, int b) {
        int rgb = (r << 16) | (g << 8) | b;
        BufferedImage imagenAuxiliar = new BufferedImage(this.getAncho() + 2, this.getAlto() + 2, BufferedImage.TYPE_INT_RGB);
        if (profundidad > 0) {
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
            this.setImagen(imagenAuxiliar);
            this.bordear(profundidad - 1, r, g, b);
        }
    }

    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //TODO pre-post
    public int getAncho() {
        return this.imagen.getWidth();
    }

    //TODO pre-post
    public int getAlto() {
        return this.imagen.getHeight();
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------	
    //TODO pre-post
    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

}
