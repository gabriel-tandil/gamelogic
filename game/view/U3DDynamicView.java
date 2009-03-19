package client.game.view;

import client.game.entity.DynamicEntity;
import client.game.entity.IDynamicEntity;

/**
 * <code>U3DDynamicView</code> extiende de <code>DynamicView</code>
 * define la vista que representan <code>IDynamicEntity</code> 
 * en el mundo del juego.
 * Permite a la vista ser modificada su posicion en 
 * el mundo mediante el método update.
 * 
 * @author Sebastian Bisbal (javadoc)
 */
public class U3DDynamicView extends DynamicView {

	/**
	 * Constructor de <code>U3DDynamicView</code>
	 * @param entity La <code>IDynamicEntity</code> que esta vista reprenta.
	 */
	public U3DDynamicView(IDynamicEntity entity) {
		super(entity);
	}

	/**
	 * Actualiza la posicion en el mundo de la <code>IDynamicEntity</code>
	 * que <code>U3DDynamicView</code> representa.
	 * 
	 */
	public void update(float interpolation) {
		this.localTranslation = ((DynamicEntity)this.getEntity()).getPosition();
		this.updateWorldTranslation();
		
	}
}