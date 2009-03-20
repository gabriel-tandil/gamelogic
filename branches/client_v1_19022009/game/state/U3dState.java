package client.game.state;

import com.jme.math.Vector3f;

/**
 * Esta clase abstracta hereda de <code>WorldGameState</code> y de ella van 
 * a heredar las clases que representan los diferentes estados del juego.
 *  
 *
 */
public abstract class U3dState extends WorldGameState {

	/**
	 * Constructor de la clase. Setea el nombre del estado.
	 * @param name el nombre que se le va a dar al estado.
	 */
	public U3dState(String name) {
		super(name);
	}

	/**
	 * Este metodo debera ser implementado por toda clase que extienda de esta.
	 * Se encarga de inicializar todo lo necesario para el estado
	 * correspondiente. 
	 */
	public abstract void initialize();
	
	/**
	 * Este metodo debera ser implementado por toda clase que extienda de esta.
	 * Se encarga de actualizar la camara de manera que siempre apunte al
	 * personaje y verifica las posibles colisiones de esta con los distintos
	 * objetos del mundo.
	 */
	public abstract void updateCamera();
	
	
	/**
	 * Este metodo debera ser implementado por toda clase que extienda de esta.
	 * @return la traslacion, el <code>Vector3f</code> que se establecera como nueva posicion del mundo.
	 */
	public abstract Vector3f getTranslation();
	
}
