/**
 * 
 */
package client.game.state;

import client.game.input.U3DChaseCamera;
import client.game.view.DynamicView;

import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.Skybox;

/**
 * <code>XMLWorldBuilder</code> Tarea encargada
 * Interface que define el método buildWord() el cual es llamado para construir el RootNode de la implementación.
 * Cada clase implementa ésta interface podrá variar la forma de construir el mismo por ejemplo por XML
 * @author Luciano Doglioli
 * @version 1.0
 */
public interface IWorldBuilder {
	/**
	* Crea el mundo y lo agrega a node
	* @param node <code>Node</code> el nodo contenedor del modelo que se carga.
	*/
	public void buildWorld(Node node);
	/**
	 * Establece la posicion inicial del jugador dentro del mundo.
	 */
	public Vector3f getInitialPosition();
	/**
	 * Crea las luces y las agrega al mundo
	 * @param nodeRoot <code>Node</code> el nodo contenedor del modelo que se carga.
	 */
	public void buildLight(Node nodeRoot);
	/**
	 * Crea la Camara que sigue al personaje.
	 * @param playerView <code>DynamicView</code> vista del jugador a la cual se agrega la camara.
	 */
	public U3DChaseCamera buildCamera(DynamicView playerView);
	/**
	 * Retorna la posicion del modelo.
	 * @param point <code>Vector3f</code> posicion dle modelo.
	 */
	public void getTranslationPoint(Vector3f point);
	/**
	 * Elimina el modelo de la memoria.
	 * @param node <code>Node</code> nodo que se destruye.
	 */
	public void destroyWorld(Node node);
	/**
	 * Configuracion del cielo del modelo.
	 */
	public Skybox setupSky();
}