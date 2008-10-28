/**
 * 
 */
package client.game.entity;

import com.jme.math.Vector3f;

/** 
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface IDynamicEntity extends IEntity {
	/** 
	 * @param vector
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void addVelocity(Vector3f vector);

	/** 
	 * @param par
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setMass(Float par);

	/** 
	 * @param vector
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void addForce(Vector3f vector);
}