package tateti;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import utilidades.Herramientas;

//TODO documentar las funciones
public class Imagen {

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    BufferedImage imagen;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * pre: El archivo debe ser válido,
     * post: Crea una imagen desde un archivo.
     * @param archivo: No debe ser nulo y debe representar la ruta
     * relativa del archivo.
     * @throws Exception
     */
    public Imagen(String archivo) throws Exception {
    	try { //se puede sacar el try/catch? no lo quise sacar por las dudas, si se puede saquenlo xfi :)
	        if ((archivo == null) || (archivo.trim().isEmpty())) {
	            throw new Exception("El archivo debe tener nombre");
	        }
	        this.imagen = ImageIO.read(new File(archivo));
        } catch (IOException e) {
            throw new Exception("Archivo invalido.");
        }
    }

    /**
     * pre: La imagen debe ser válida,
     * post: Crea una imagen desde una BufferedImage.
     * @param imagen: No debe ser nula.
     * @throws Exception 
     */
    // Crea un Imagen desde una BufferedImage
    public Imagen(BufferedImage imagen) throws Exception {
    	if (imagen == null) {
    		throw new Exception("La imagen no puede ser nula");
    	}
        this.imagen = imagen;
    }

    /**
     * pre: El ancho y alto deben ser válidos,
     * post: Crea una imagen blanca con las dimensiones dadas.
     * @param ancho, @param alto: Deben ser > 0.
     * @throws Exception 
     */
    public Imagen(int ancho, int alto) throws Exception {
    	if (!Herramientas.validarNumeroPositivoEstricto(ancho)) {
    		throw new Exception("El ancho debe ser mayor a cero");
    	}
    	if (!Herramientas.validarNumeroPositivoEstricto(alto)) {
    		throw new Exception("El alto debe ser mayor a cero");
    	}
        int rgb = (255 << 16) | (255 << 8) | 255;
        this.imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                this.imagen.setRGB(i, j, rgb);
            }
        }
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    /**
     * pre: El destino debe ser válido,
     * post: Convierte la imagen a un .bmp en destino.
     * @param destino: No debe ser nulo.
     * @throws Exception
     */
    public void exportar(String destino) throws Exception {
        try {
        	if ((destino == null) || (destino.trim().isEmpty())) {
	            throw new Exception("El archivo debe tener nombre");
	        }
            File archivo = new File(destino + ".bmp");
            ImageIO.write(this.imagen, "bmp", archivo);
        } catch (IOException e) {
            throw new Exception("Destino invalido.");
        }
    }

    /**
     * pre: r,g,b deben ser válidos,
     * post: Convierte todos los pixeles no-vacios al valor rgb dado.
     * @param r, @param g, @param b: No deben ser < 0 ni > a 255.
     * @throws Exception 
     */
    public void recolorizar(int r, int g, int b) throws Exception {
    	if ((!Herramientas.validarNumeroPositivo(r)) || (r > 255) ||
    			(!Herramientas.validarNumeroPositivo(g)) || (g > 255) || 
    			(!Herramientas.validarNumeroPositivo(b)) || (b > 255)){
    		throw new Exception("r,g,b deben estar entre 0 y 255 incluisive.");
    	}
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

    /**
     * pre: rgb debe ser válido,
     * post: Convierte todos los pixeles no-vacios al valor rgb dado.
     * @param rgb: No debe ser < 0 ni > a 16777215.
     * @throws Exception 
     */
    public void recolorizar(int rgb) throws Exception {
    	if ((!Herramientas.validarNumeroPositivo(rgb)) || (rgb > 16777215)) {
    		throw new Exception("rgb debe estar entre 0 y 16777214 inclusive.");
    	}
        int[][] arrayAuxiliar = this.obtenerArray();
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
     * pre: -, post: Convierte la imagen a un array de rgb.
     * @return Devuelve un arreglo de rgb correspondiente a la imagen.
     */
    private int[][] obtenerArray() {
        int[][] arrayImagen = new int[this.getAncho()][this.getAlto()];
        for (int x = 0; x < this.getAncho(); x++) {
            for (int y = 0; y < this.getAlto(); y++) {
                arrayImagen[x][y] = this.imagen.getRGB(x, y);
            }
        }
        return arrayImagen;
    }

    /**
     * pre: El array debe ser válido,
     * post: Devuelve la imagen.
     * @param array: No debe estar vacío.
     * @return Devuelve la imagen del tipo BufferedImage.
     * @throws Exception 
     */
    private BufferedImage obtenerImagen(int[][] array) throws Exception {
    	if ((array.length <= 0) || (array == null)) {
    		throw new Exception("El arreglo debe existir y tener una longitud mayor a cero.");
    	}
        BufferedImage imagenRetorno = new BufferedImage(array.length, array[0].length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                imagenRetorno.setRGB(i, j, array[i][j]);
            }
        }
        return imagenRetorno;
    }

    /**
     * pre: adicional debe ser válida,
     * post: Añade una imagen adicional con la misma altura exacta a
     * la derecha de la imagen original, y devuelve la imagen compuesta.
     * @param adicional: No debe ser nula.
     * @return Devuelve una imagen compuesta (la original más la añadida a su derecha).
     * @throws Exception
     */
    public Imagen añadirImagenDerecha(Imagen adicional) throws Exception {
    	if (adicional == null) {
    		throw new Exception("La imagen no puede ser nula.");
    	}
        if (this.getAlto() != adicional.getAlto()) {
            this.exportar("Imagen Principal Error");
            adicional.exportar("Imagen Adicional Error");
            throw new Exception("Las imagenes deben tener el mismo alto. Img1 tiene altura " + this.getAlto() + ". Img2 tiene altura " + adicional.getAlto());
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

    /**
     * pre: adicional debe ser válida,
     * post: Añadir imagen abajo añade una imagen con el mismo ancho exacto
     * abajo de la imagen original, y devuelve la imagen compuesta.
     * @param adicional: No debe ser nula.
     * @return Devuelve una imagen compuesta (la original más la añadiad abajo).
     * @throws Exception
     */
    public Imagen añadirImagenAbajo(Imagen adicional) throws Exception {
    	if (adicional == null) {
    		throw new Exception("La imagen no puede ser nula.");
    	}
        if (this.getAncho() != adicional.getAncho()) {
            this.exportar("Imagen Principal Error");
            adicional.exportar("Imagen Adicional Error");
            throw new Exception("Las imagenes deben tener el mismo ancho. Img1 tiene ancho " + this.getAncho() + ". Img2 tiene ancho " + adicional.getAncho());
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

    /**
     * pre: profundidad, r,g,b deben ser válidos,
     * post: Genera un borde con los colores r,g,b separados.
     * @param profundidad: //TODO: no sé qué poner
     * @param r, @param g, @param b: No deben ser <= 0 ni > a 255.
     * @throws Exception 
     */
    public void bordear(int profundidad, int r, int g, int b) throws Exception {
    	if ((!Herramientas.validarNumeroPositivoEstricto(r)) || (r > 255) ||
    			(!Herramientas.validarNumeroPositivoEstricto(g)) || (g > 255) || 
    			(!Herramientas.validarNumeroPositivoEstricto(b)) || (b > 255)){
    		throw new Exception("r,g,b deben estar entre 0 y 255 incluisive.");
    	}
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
            this.bordear(profundidad - 1, rgb);
        }
    }

    /**
     * pre: profundidad y rgb deben ser válidos,
     * post: Genera un borde con el rgb completo.
     * @param profundidad: //TODO: no sé qué poner
     * @param rgb: No debe ser < 0 ni > a 16777215.
     * @throws Exception 
     */
    public void bordear(int profundidad, int rgb) throws Exception {
    	if ((!Herramientas.validarNumeroPositivo(rgb)) || (rgb > 16777215)) {
    		throw new Exception("rgb debe estar entre 0 y 16777214 inclusive.");
    	}
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
            this.bordear(profundidad - 1, rgb);
        }
    }

    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * pre: -, post: -
     * @return Devuelve el ancho de la imagen.
     */
    public int getAncho() {
        return this.imagen.getWidth();
    }

    /**
     * pre: -, post: -
     * @return Devuelve el alto de la imagen.
     */
    public int getAlto() {
        return this.imagen.getHeight();
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------	
    /**
     * pre: imaggen debe ser válida,
     * post: Setea la imagen a una nueva.
     * @param imagen: No debe ser nula.
     * @throws Exception 
     */
    public void setImagen(BufferedImage imagen) throws Exception {
    	if (imagen == null) {
    		throw new Exception("La imagen no puede ser nula");
    	}
        this.imagen = imagen;
    }

}
