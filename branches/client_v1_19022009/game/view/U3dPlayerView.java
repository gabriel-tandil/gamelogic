package client.game.view;

import client.game.entity.DynamicEntity;
import client.game.entity.IDynamicEntity;
import client.game.entity.Player;

/**
 * <code>U3dPlayerView</code> define la vista qu representa un player
 * a traves de una <code>IDynamicEntity</code> dentro del mundo virtual.
 * 
 * @author Sebastian Bisbal (javadoc)
 *
 */
public class U3dPlayerView extends DynamicView {
	
	/**
	 * Constructor de <code>U3dPlayerView</code>
	 * @param entity La <code>IDynamicEntity</code> que esta vista reprenta.
	 */
	public U3dPlayerView(IDynamicEntity entity) {
		super(entity);
	}

	/**
	 * Actualiza la posicion en el mundo del player a traves 
	 * de la <code>IDynamicEntity</code> que <code>U3dPlayerView</code>
	 * representa.
	 * 
	 */
	public void update(float interpolation) {
		this.localTranslation = ((Player)this.getEntity()).getPosition();
		this.updateWorldTranslation();
	}

}
