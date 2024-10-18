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
    public Imagen(String archivo) throws Exception {
        try {
            this.imagen = ImageIO.read(new File(archivo));
        } catch (IOException ex) {
            throw new Exception("Archivo invalido.");
        }
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
}
