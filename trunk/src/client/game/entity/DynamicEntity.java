/**
 * 
 */
package client.game.entity;

import com.jme.math.Vector3f;
import com.jme.util.export.JMEExporter;
import com.jme.util.export.JMEImporter;

/** 
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class DynamicEntity extends Entity implements IDynamicEntity {
	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Float mass;

	/** 
	 * @return el mass
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Float getMass() {
		// begin-user-code
		return mass;
		// end-user-code
	}

	/** 
	 * @param theMass el mass a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setMass(Float theMass) {
		// begin-user-code
		mass = theMass;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Vector3f force;

	/** 
	 * @return el force
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Vector3f getForce() {
		// begin-user-code
		return force;
		// end-user-code
	}

	/** 
	 * @param theForce el force a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setForce(Vector3f theForce) {
		// begin-user-code
		force = theForce;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Vector3f velocity;

	/** 
	 * @return el velocity
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Vector3f getVelocity() {
		// begin-user-code
		return velocity;
		// end-user-code
	}

	/** 
	 * @param theVelocity el velocity a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setVelocity(Vector3f theVelocity) {
		// begin-user-code
		velocity = theVelocity;
		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see Savable#write(JMEExporter arg0)
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void write(JMEExporter arg0) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see Savable#read(JMEImporter arg0)
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void read(JMEImporter arg0) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see Savable#getClassTag()
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Class getClassTag() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return null;
		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see IEntity#getId()
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Integer getId() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return null;
		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see IDynamicEntity#addVelocity(Vector3f vector)
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void addVelocity(Vector3f vector) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see IDynamicEntity#addForce(Vector3f vector)
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void addForce(Vector3f vector) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}
}