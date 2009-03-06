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
 * Interface que define el método buildWord() el cual es llamado para construir el RootNode de la implementación.
 * Cada clase implementa ésta interface podrá variar la forma de construir el mismo por ejemplo por XML
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface IWorldBuilder {
	/** 
	 * @param node
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void buildWorld(Node node);
	
	public Vector3f getInitialPosition();
	
	public void buildLight(Node nodeRoot);
	
	public U3DChaseCamera buildCamera(DynamicView playerView);
	
	public void getTranslationPoint(Vector3f point);
	
	public void destroyWorld(Node node);
	
	public Skybox setupSky();
}