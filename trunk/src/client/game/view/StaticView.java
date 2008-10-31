/**
 * 
 */
package client.game.view;

import client.game.entity.IEntity;

/** 
 * <code>StaticView</code> extiende de <code>View</code> e implementa 
 * <code>IStaticView</code> para representar a una vista estática.
 * 
 * @author Maria Hansen
 * @version Creation date: 29-10-2008
 */
public class StaticView extends View implements IStaticView {
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
	public boolean isValidView(){
		return false;
	}

}