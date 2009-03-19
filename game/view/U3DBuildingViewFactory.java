package client.game.view;

import client.game.entity.IEntity;

/**
 * <code>U3DBuildingViewFactory</code> realiza una abstraccion
 * para crear vistas de tipo <code>U3DBuildingView</code> 
 * correspondientes a una entidad de tipo <code>IEntity</code>.
 * Implemnta la interfaz <code>IViewFactory</code>. Por defecto
 * define un identificador con valor "Entity".
 * 
 * @author Sebastian Bisbal (javadoc)
 */
public class U3DBuildingViewFactory implements IViewFactory {
	/**
	 * Identificador de esta factoria. Contiene un valor
	 * por defecto "Entity"
	 */
	private String id="Entity";

	/**
	 * Crea una nueva <code>U3DBuildingView</code> para 
	 * una entidad de tipo <code>IEntity</code>
	 * 
	 * @return <code>IView</code> vista generada. 
	 */
	public IView createView(IEntity entity) {
		return new U3dBuildingView(entity);
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
