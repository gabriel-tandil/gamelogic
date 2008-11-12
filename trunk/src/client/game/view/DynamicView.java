/**
 * 
 */
package client.game.view;

import client.game.entity.IDynamicEntity;

/** 
 * <code>DynamicView</code> extends <code>View</code> e implementa 
 * <code>IDynamicView</code> para representar una vista dinámica para su 
 * correspondiente <code>IDynamicEntity</code>. Las subclases necesitan implementar
 * la lógica específica del update.
 * 
 * @author Maria Hansen
 * @version Creation date: 29-10-2008
 */
public abstract class DynamicView extends View implements IDynamicView {
	/**
	 * Constructor de <code>DynamicView</code>.
	 * @param entity La <code>IDynamicEntity</code> que esta vista reprenta.
	 */
	public DynamicView(IDynamicEntity entity){
		super(entity);
	}

	/**
	 * @see IView#isValidView()
	 */
	public boolean isDynamicView(){
		return true;
	}

}