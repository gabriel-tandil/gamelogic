package client.game.view;

import client.game.entity.IDynamicEntity;
import client.game.entity.IEntity;

/**
 * <code>U3DPlayerViewFactory</code> realiza una abstraccion
 * para crear vistas de tipo <code>U3dPlayerView</code> correspondientes
 * a una entidad de tipo <code>IDynamicEntity</code>.
 * Implemnta la interfaz <code>IViewFactory</code>. Por defecto
 * define un identificador con valor "Player".
 * 
 * @author Sebastian Bisbal (javadoc)
 */
public class U3DPlayerViewFactory implements IViewFactory {

	/**
	 * Identificador de esta factoria. Contiene un valor
	 * por defecto "Player"
	 */
	private String id="Player";

	/**
	 * Crea una nueva <code>U3dPlayerView</code> para 
	 * una entidad de tipo <code>IDynamicEntity</code>
	 * 
	 * @return <code>IView</code> vista generada. 
	 */
	public IView createView(IEntity entity) {
		return new U3dPlayerView((IDynamicEntity) entity);
	}

	/**
	 * Retorna el identificador unico de la factoria.
	 * 
	 * @return <code>String</code> identificador unico 
	 */
	public String getId() {
		return id;
	}

}
