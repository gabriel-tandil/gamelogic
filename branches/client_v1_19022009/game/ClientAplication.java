package client.game;



/**
 * Esta clase es la encargada de inicializar el juego
 * creando una instancia de U3dgame y llamando al método 
 * start de la misma
 * @author kike
 *
 */

public class ClientAplication {
/**
 * método principal de la aplicación
 * @param args
 */

	public static void main(String[] args) {
		U3dgame game = new U3dgame();
		game.start();
	}
}
