package client.game.view;

import client.game.entity.IEntity;

/**
 * <code>U3dBuildingView</code> representa la vista de un edificio 
 * en el juego.
 * Extiende de <code>StaticView</code> ya que los edificios son
 * entidades estaticas del juego que no cambian a lo largo del
 * mismo. Con esto se logra trabar la geometría, límites y 
 * transformación del edificio para poder optimizar el motor 
 * del juego a la hora de actualizar los mismos.
 * 
 * @author Sebastian Bisbal (javaDoc)
 */
public class U3dBuildingView extends StaticView {
	
	/**
     * Constructor de <code>U3dBuildingView</code> dada una entidad. 
     * @param entity La <code>IEntity</code> que esta vista representa.
     */
	public U3dBuildingView(IEntity entity) {
		super(entity);
	}

}
