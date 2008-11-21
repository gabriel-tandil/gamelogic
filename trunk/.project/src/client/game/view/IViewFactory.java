/**
 * 
 */
package client.game.view;

import client.game.Game;
import client.game.entity.IEntity;

/** 
 * <code>IViewFactory</code> define la interface para la creación de vistas dada
 * una <code>IEntity</code>.
 * 
 * @author Maria Hansen
 * @version Creation date: 20-11-2008
 */
public interface IViewFactory {
	//no va!
	/** 
	 * 
	 */
	ViewFactoryManager viewfactorymanager = null;

	/** 
	 * @param entity
	 * @return IView La <code>IView</code> creada.
	 */
	public Object createView(IEntity entity);

	/** 
	 * Devuelve el id correspondiente a cada factory.
	 * @return String El Id correspondienta al factory.
	 */
	public String getId();
}