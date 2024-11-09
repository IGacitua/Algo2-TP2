package tateti;

import utilidades.Lista;

public class JuegoTateti{
    public static void main(String[] args) throws Exception{
    	//ahora el teclado se abre asi
    	Teclado teclado =new Teclado();
    	
        //iniciamos los objetos
    	Lista<Jugador> jugadores = new Lista<>();
    	Mazo mazo = new Mazo(null);
    	Menu menu = new Menu(jugadores);
    	//iniciamos mazo
    	menu.generarMazoAleatorio(mazo,menu.limiteCartas());
    	//obtenemos los jugadores
    	menu.cargarJugadores();
    	
        //obtenemos el parametro para el tablero
        System.out.println("Elija la cantidad de casillas NxN que tendra el tablero: ");
        int tamañoTablero = Teclado.pedirNumero(1, 99);       
        Tablero tablero = new Tablero(tamañoTablero);

        
        

        //turnos
        
        
        
    }
}