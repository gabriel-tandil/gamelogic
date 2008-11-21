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
	 * @see IView#isDynamicView().
	 */
	public boolean isDynamicView(){
		return false;
	}
}