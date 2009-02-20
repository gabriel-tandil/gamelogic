/**
 * 
 */
package client.manager;

import com.jme.renderer.Camera;
import com.jme.util.Timer;
import com.jmex.bui.BStyleSheet;
import com.jmex.bui.PolledRootNode;

/** 
 * @author Martin Sabatini - Sebastian Bisbal
 * @version Fecha de creaci�n: 05-11-2008
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