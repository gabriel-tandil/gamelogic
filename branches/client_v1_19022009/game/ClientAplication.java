package client.game;

import com.jme.app.AbstractGame.ConfigShowMode;



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
		game.setConfigShowMode(ConfigShowMode.AlwaysShow);
		game.start();
	}
}
