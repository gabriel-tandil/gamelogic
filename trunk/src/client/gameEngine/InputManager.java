/**
 * 
 */
package client.gameEngine;

import java.util.Set;
import client.game.controller.IController;
import client.game.entity.IDynamicEntity;

/** 
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class InputManager {
	/** 
	 * @uml.annotations for <code>icontroller</code>
	 *     collection_type="client.game.controller.IController"
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<IController> icontroller;

	/** 
	 * @return el icontroller
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<IController> getIcontroller() {
		// begin-user-code
		return icontroller;
		// end-user-code
	}

	/** 
	 * @param theIcontroller el icontroller a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setIcontroller(Set<IController> theIcontroller) {
		// begin-user-code
		icontroller = theIcontroller;
		// end-user-code
	}

	/** 
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static InputManager getInstance() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return null;
		// end-user-code
	}

	/** 
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public IDynamicEntity getController() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return null;
		// end-user-code
	}

	/** 
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public abstract IController createController();

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void update() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}
}