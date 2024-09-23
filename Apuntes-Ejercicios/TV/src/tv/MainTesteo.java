package tv;

public class MainTesteo {

    public static void main(String[] args) throws Exception {
        //Crea un televisor con 140 canales
        Televisor televisor = new Televisor(140);

        //Prueba setear un canal e ir subiendo y bajando de canal y volumen
        televisor.canalEstablecer(4);
        System.out.println("El canal actual es " + televisor.canalActualObtener() + " y el volumen actual es " + televisor.volumenActualObtener());
        for (int i = 0; i < 50; i++) {
            televisor.volumenSubir();
        }
        System.out.println("El volumen del canal actual es " + televisor.getVolumenCanal(televisor.canalActualObtener()));
        System.out.println("Ahora el volumen actual es " + televisor.volumenActualObtener());

        for (int i = 0; i < 12; i++) {
            televisor.canalSubir();
            televisor.volumenBajar();
        }
        System.out.println("");
        System.out.println("Subí el canal varias veces y bajé el volumen, ahora el canal al que se cambió es el " + televisor.canalActualObtener() + " y el volumen es " + televisor.getVolumenCanal(televisor.canalActualObtener()));
        System.out.println("");

        //Prueba el silenciar el televisor
        System.out.println("¿El Televisor está silenciado? -> " + televisor.silencioObtener());
        televisor.silencioAlternar();
        System.out.println("¿Y ahora? -> " + televisor.silencioObtener());
        System.out.println("Entonces el volumen actual es: " + televisor.volumenActualObtener());
        televisor.silencioAlternar();
        System.out.println("Ya no está silenciado, entonces su volumen es: " + televisor.volumenActualObtener());
        System.out.println("");

        //Más pruebas con bucles
        for (int i = 0; i < 100; i++) {
            televisor.canalSubir();
        }
        while (televisor.canalActualObtener() > 99) {
            televisor.canalBajar();
        }
        System.out.println("El canal al que se cambió es el " + televisor.canalActualObtener());
        System.out.println("");

        //Prueba al silenciar, subir el volumen y desilenciar
        System.out.println("El volumen es " + televisor.volumenActualObtener());
        televisor.silencioAlternar();
        televisor.volumenSubir();
        System.out.println("Tras silenciar y subir el volumen, debería desilenciarse y subir uno. Entonces el volumen es: " + televisor.volumenActualObtener());
        System.out.println("");

        //Prueba de excepciones
        //Intentar un canal inválido
        try {
            televisor.canalEstablecer(1000);
        } catch (Exception e) {
            System.out.println("Error al cambiar de canal: " + e.getMessage());
        }

        //Subir demasiadas veces un canal
        try {
            for (int i = 0; i < 10000; i++) {
                televisor.canalSubir();
            }
        } catch (Exception e) {
            System.out.println("Error al subir el canal: " + e.getMessage());
        }

        //Subir el volumen demasiado
        try {
            for (int i = 0; i < 300; i++) {
                televisor.volumenSubir();
            }
        } catch (Exception e) {
            System.out.println("Error al subir el volumen: " + e.getMessage());
        }

        //Bajar el volumen demasiado
        try {
            for (int i = 0; i < 300; i++) {
                televisor.volumenBajar();
            }
        } catch (Exception e) {
            System.out.println("Error al bajar el volumen: " + e.getMessage());
        }
    }

}
