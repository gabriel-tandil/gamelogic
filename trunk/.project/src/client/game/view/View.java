/**
 * 
 */
package client.game.view;

import com.jme.scene.Node;

import client.game.Game;
import client.game.entity.IEntity;
import com.jme.scene.Spatial;

/** 
 * <code>View</code> define la representación de una <code>IEntity</code>.
 * Es una subclase de <code>Node</code> lo que hace posible adjuntar directamente 
 * una <code>View</code> a la escena gráfica para renderización.
 * 
 * @author Maria Hansen
 * @version Creation date: 29-10-2008
 */
public abstract class View extends Node implements IView, IViewFactory {
	/** 
	 * La <code>IEntity</code> que esta <code>View</code> representa.
	 */
	protected IEntity entity;


	/**
	 * Constructor de <code>View</code>.
	 */
	public View(){
		super();
	}

	/**
	 * Constructor de <code>View</code> dada una <code>IEntity</code>.
	 * @param entity Le <code>IEntity<code> que esta <code>View</code> representa.
	 */
	public View(IEntity entity) {
		super(entity.getId().toString() + "_View");
		this.entity = entity;
	}

	/** 
	 * @see IView#attachSpatial(Spatial).
	 */
	public void attachSpatial(Spatial mesh) {
		this.attachChild(mesh);
	}

	/** 
	 * @see IView#attachTo(Node).
	 */
	public void attachTo(Node parent) {
		parent.attachChild(this);
	}

	/** 
	 * @see IView#detachFromParent().
	 */
	public boolean detachFromParent() {
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
	public void setEntity(IEntity theentity) {
		this.entity = theentity;
	}

	public void initializeView(String id, Game game) {
		// TODO Auto-generated method stub
		
	}

}