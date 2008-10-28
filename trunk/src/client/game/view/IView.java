/**
 * 
 */
package client.game.view;

import com.jme.util.export.Savable;
import com.jme.scene.Spatial;
import com.jme.scene.Node;
import client.game.entity.IEntity;

/** 
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface IView extends Savable {
	/** 
	 * @param Parámetro
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void attachSpatial(Spatial Parámetro);

	/** 
	 * @param Parámetro
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void attachTo(Node Parámetro);

	/** 
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public IEntity getEntity();
}