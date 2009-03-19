/**
 * 
 */
package client.game.view;

import client.game.entity.IEntity;

/** 
 * <code>IViewFactory</code> define la interface para la 
 * creación de vistas dada una <code>IEntity</code> y 
 * para retornar el identificador de la misma.
 * 
 * @author Maria Hansen, Sebastian Bisbal (Javadoc)
 * @version Creation date: 20-11-2008
 */
public interface IViewFactory {

	/** 
	 * Crea la vista correspondiente a la entidad dada.
	 * @param entity La <code>IEntity</code> que la nueva vista representa.
	 * @return IView La <code>IView</code> creada.
	 */
	public IView createView(IEntity entity);

	/** 
	 * Devuelve el id correspondiente a cada factory.
	 * @return String El Id correspondienta al factory.
	 */
	public String getId();
}