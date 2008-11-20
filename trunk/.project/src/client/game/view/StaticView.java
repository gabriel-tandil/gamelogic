/**
 * 
 */
package client.game.view;

import com.jme.scene.Node;
import com.jme.scene.Spatial;

import client.game.entity.IEntity;

/** 
 * <code>StaticView</code> extiende de <code>View</code> e implementa 
 * <code>IStaticView</code> para representar a una vista estática.
 * 
 * @author Maria Hansen
 * @version Creation date: 29-10-2008
 */
public abstract class StaticView extends View implements IStaticView {
	/**
	 * Constructor de <code>StaticView</code>
	 */
	public StaticView(){
		super();
	}

	/**
	 * Constructor de <code>StaticView</code> dada una entidad. 
	 * @param entity La <code>IEntity</code> que esta vista representa.
	 */
	public StaticView(IEntity entity){
		super(entity);
	}

	/**
	 * @see IStaticView#lock().
	 */
	public void lock(){
		super.lockMeshes();
		super.lockBounds();
		super.lockTransforms();
	}

	/**
	 * @see IView#isValidView().
	 */
	public boolean isDynamicView(){
		return false;
	}
	
	/**
	 * (sin Javadoc)
	 * @see IView#detachFromParent()
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public boolean detachFromParent() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return false;
		// end-user-code
	}
	
	/**
	 * (sin Javadoc)
	 * @see IView#attachToNode(Node parent)
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void attachToNode(Node parent) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/**
	 * (sin Javadoc)
	 * @see IView#attachSpatial(Spatial mesh)
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void attachSpatial(Spatial mesh) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

}