/**
 * 
 */
package client.manager;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.Set;
import client.game.view.IView;
import client.game.entity.IEntity;
import client.game.entity.IDynamicEntity;

/** 
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class ViewManager {
	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private LinkedList lista;

	/** 
	 * @return el lista
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public LinkedList getLista() {
		// begin-user-code
		return lista;
		// end-user-code
	}

	/** 
	 * @param theLista el lista a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setLista(LinkedList theLista) {
		// begin-user-code
		lista = theLista;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private HashMap hash;

	/** 
	 * @return el hash
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public HashMap getHash() {
		// begin-user-code
		return hash;
		// end-user-code
	}

	/** 
	 * @param theHash el hash a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setHash(HashMap theHash) {
		// begin-user-code
		hash = theHash;
		// end-user-code
	}

	/** 
	 * @uml.annotations for <code>iview</code>
	 *     collection_type="client.game.view.IView"
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<IView> iview;

	/** 
	 * @return el iview
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<IView> getIview() {
		// begin-user-code
		return iview;
		// end-user-code
	}

	/** 
	 * @param theIview el iview a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setIview(Set<IView> theIview) {
		// begin-user-code
		iview = theIview;
		// end-user-code
	}

	/** 
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static ViewManager getInstance() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return null;
		// end-user-code
	}

	/** 
	 * @param view
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void addDirtyView(IView view) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 * @param entity
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public IView getView(IEntity entity) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return null;
		// end-user-code
	}

	/** 
	 * @param entity
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void markForUpdate(IDynamicEntity entity) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public abstract IEntity createView();

	/** 
	 * @param entity
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void removeView(IEntity entity) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 * @param view
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void registerView(IView view) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void update() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}
}