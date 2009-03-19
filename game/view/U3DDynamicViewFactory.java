package client.game.view;

import client.game.entity.IDynamicEntity;
import client.game.entity.IEntity;

/**
 * <code>U3DDynamicViewFactory</code> realiza una abstraccion
 * para crear vistas de tipo <code>U3DDynamicView</code> correspondientes
 * a una entidad de tipo <code>IDynamicEntity</code>.
 * Implemnta la interfaz <code>IViewFactory</code>. Por defecto
 * define un identificador con valor "DynamicEntity".
 * 
 * @author Sebastian Bisbal (javadoc)
 */
public class U3DDynamicViewFactory implements IViewFactory {

	/**
	 * Crea una nueva <code>U3DDynamicView</code> para 
	 * una entidad de tipo <code>IDynamicEntity</code>
	 * 
	 * @return <code>IView</code> vista generada. 
	 */
	public IView createView(IEntity entity) {
		return new U3DDynamicView((IDynamicEntity) entity);
	}
	
	/**
	 * Identificador de esta factoria. Contiene un valor
	 * por defecto "DynamicEntity"
	 */
	private String id="DynamicEntity";

	/**
	 * Retorna el identificador unico de la factoria.
	 * 
	 * @return <code>String</code> identificador unico 
	 */
	public String getId() {
		return id;
	}

}