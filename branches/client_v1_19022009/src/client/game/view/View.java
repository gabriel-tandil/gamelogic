/**
 * 
 */
package client.game.view;

import com.jme.scene.Node;
import com.jme.scene.Spatial;

import client.game.entity.IEntity;

/** 
 * <code>View</code> define la representación de una <code>IEntity</code>.
 * Es una subclase de <code>Node</code> lo que hace posible adjuntar directamente 
 * una <code>View</code> a la escena gráfica para renderización.
 * 
 * @author Maria Hansen
 * @version Creation date: 29-10-2008
 */
public abstract class View extends Node implements IView {
	/** 
     * La <code>IEntity</code> que esta <code>View</code> representa.
	 */
	private IEntity entity;


	/**
     * Constructor de <code>View</code> dada una <code>IEntity</code>.
     * @param entity La <code>IEntity<code> que esta <code>View</code> representa.
	 */
	public View(IEntity entity) {
		super(entity.getId() + "_View");
		this.entity = entity;
	}
	
	/** 
	 * @see IView#attachSpatial(Spatial).
	 */
	public void attachSpatial(Spatial mesh) {
		this.attachChild(mesh);
	}

	/** 
	 * @see IView#attachToNode(Node).
	 */
	public void attachToNode(Node node) {
		node.attachChild(this);
	}

	/** 
	 * @see IView#detachFromParent().
	 */
	public Boolean detachFromParent() {
		return this.removeFromParent();
	}

	/** 
	 * @see IView#getEntity().
	 */
	public IEntity getEntity() {
		return entity;
	}

	/** 
	 * Setea esta entidad con la entidad dada.
	 * @param entity La entidad a ser seteada.
	 */
	public void setEntity(IEntity entity) {
		this.entity = entity;
	}
}