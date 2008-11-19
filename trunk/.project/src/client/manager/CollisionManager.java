/**
 * 
 */
package client.manager;

import java.util.Set;
import client.game.state.IAccessPoint;
import client.game.state.AccessPoint;
import com.jme.scene.Spatial;
import com.jme.math.Ray;
import com.jme.math.Vector3f;

/** 
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class CollisionManager {
	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private static CollisionManager collisionManager;

	/**
	 * @return el collisionManager
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static CollisionManager getCollisionManager() {
		// begin-user-code
		return collisionManager;
		// end-user-code
	}

	/**
	 * @param theCollisionManager el collisionManager a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static void setCollisionManager(CollisionManager theCollisionManager) {
		// begin-user-code
		collisionManager = theCollisionManager;
		// end-user-code
	}

	/** 
	 * @uml.annotations for <code>iaccesspoint</code>
	 *     collection_type="Object"
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<Object> iaccesspoint;

	/**
	 * @return el iaccesspoint
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<Object> getIaccesspoint() {
		// begin-user-code
		return iaccesspoint;
		// end-user-code
	}

	/**
	 * @param theIaccesspoint el iaccesspoint a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setIaccesspoint(Set<Object> theIaccesspoint) {
		// begin-user-code
		iaccesspoint = theIaccesspoint;
		// end-user-code
	}

	/** 
	 * @uml.annotations for <code>iaccesspoint2</code>
	 *     collection_type="client.game.state.IAccessPoint"
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<IAccessPoint> iaccesspoint2;

	/**
	 * @return el iaccesspoint2
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<IAccessPoint> getIaccesspoint2() {
		// begin-user-code
		return iaccesspoint2;
		// end-user-code
	}

	/**
	 * @param theIaccesspoint2 el iaccesspoint2 a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setIaccesspoint2(Set<IAccessPoint> theIaccesspoint2) {
		// begin-user-code
		iaccesspoint2 = theIaccesspoint2;
		// end-user-code
	}

	/**
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected CollisionManager() {
		// begin-user-code
		// TODO Apéndice de constructor generado automáticamente
		// end-user-code
	}

	/**
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static CollisionManager getInstace() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return null;
		// end-user-code
	}

	/**
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public AccessPoint checkOverAccessPoint() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return null;
		// end-user-code
	}

	/**
	 * @param local
	 * @param parent
	 * @param ray
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected Vector3f getIntersection(Boolean local, Spatial parent, Ray ray) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return null;
		// end-user-code
	}

	/**
	 * @param root
	 * @param zFin
	 * @param yFin
	 * @param xFin
	 * @param zInicio
	 * @param yInicio
	 * @param xInicio
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Vector3f getDestination(Spatial root, Float zFin, Float yFin,
			Float xFin, Float zInicio, Float yInicio, Float xInicio) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return null;
		// end-user-code
	}
}