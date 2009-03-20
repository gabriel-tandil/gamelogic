package client.manager;

import com.jme.renderer.Camera;
import com.jme.util.Timer;
import com.jmex.bui.BStyleSheet;
import com.jmex.bui.PolledRootNode;

/**
 * Manager que se encarga de la visualización del HUD (Head Up Display), es
 * decir las imágenes 2D que aparecen en la pantalla mostrando los stats del
 * jugador, los puntos, etc
 * 
 * @author Martin Sabatini - Sebastian Bisbal - Gabriel Alvarez
 * @version Fecha de creaciï¿½n: 05-11-2008
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface IHudManager {
	/**
	 * actualmente no se usa en esta implementacion, se dejo porque esta
	 * definido en el diseño
	 */
	public void render();

	/**
	 * vincula el nodo raiz del hud y el del mapa al state activo actualmente y
	 * los marca para actualizar.
	 */
	public void update();

	/**
	 * inicializa la clase, construye el nodo raiz del gbui, el mapa de
	 * ventanas, configura el estilo por defecto crea la ventana de control y
	 * carga el mapa indicativo de posicion
	 * 
	 * @param timer
	 *            el timer del sistema
	 */
	public void initialize(Timer timer);

	/**
	 * 
	 * @return el nodo raiz del hud (gbui)
	 */
	public PolledRootNode getRoot();

	/**
	 * 
	 * @return el estilo por defecto
	 */
	public BStyleSheet getStyle();
}