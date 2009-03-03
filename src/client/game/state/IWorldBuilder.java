/**
 * 
 */
package client.game.state;

import com.jme.scene.Node;

/** 
 *  Interface que define el m�todo buildWord() el cual es llamado para construir el RootNode de la implementaci�n.
 *  Cada clase implementa �sta interface podr� variar la forma de construir el mismo por ejemplo por XML
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface IWorldBuilder {
	/** 
	 * @param node
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void buildWorld(Node node);
}