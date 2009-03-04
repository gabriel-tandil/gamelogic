/**
 * 
 */
package client.manager;

import com.jme.renderer.Camera;
import com.jme.util.Timer;
import com.jmex.bui.BStyleSheet;
import com.jmex.bui.PolledRootNode;

/** 
 * Manager que se encarga de la visualización del HUD (Head Up Display), es decir las imágenes 2D que 
 * aparecen en la pantalla mostrando los stats del jugador, los puntos, etc
 * @author Martin Sabatini - Sebastian Bisbal
 * @version Fecha de creaciï¿½n: 05-11-2008
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface IHudManager {
	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void render();

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void update();

	public void initialize(Timer timer);

	public PolledRootNode getRoot();

	public BStyleSheet getStyle();
}