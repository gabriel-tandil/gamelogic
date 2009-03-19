/**
 * 
 */
package client.game.view;

import java.util.Hashtable;
import client.game.entity.IEntity;

/** 
 * <code>ViewFactoryManager</code> crea y maneja las <code>IView</code>
 * correspondientes a la <code>IEntity</code>.
 * 
 * @author Maria Hansen, Sebastian Bisbal (javadoc)
 * @version Creation date: 20-11-2008
 */
public class ViewFactoryManager {
	/** 
	 * La instancia de <code>ViewFactoryManager</code>
	 */
	private static ViewFactoryManager instance;
	
	/** 
	 * El almacenamiento de <code>IViewFactory</code>.
	 */
	private Hashtable<String, IViewFactory> viewfactoryhash;
	
	/**
	 * Constructor de <code>ViewFactoryManager</code>.
	 */
	protected ViewFactoryManager() {
		this.viewfactoryhash = new Hashtable<String, IViewFactory>();
	}

	/** 
     * Devuelve la instancia de <code>ViewFactoryManager</code>.
     * @return La instancia de <code>ViewFactoryManager</code>.
	 */
	public static ViewFactoryManager getInstance() {
		if(ViewFactoryManager.instance == null) {
			instance = new ViewFactoryManager();
		}
		return instance;
	}

	/** 
	 * Crea la vista correspondiente a la entidad dada recuperando el factory
	 * adecuado. 
	 * @param entity La <code>IEntity</code> que la nueva vista representa.
	 * @return IView La vista creada.
	 */
	public IView createView(IEntity entity) {
		String id;
		IViewFactory factory;
		IView view = null;
		
		if(entity != null) {
			id = entity.getTipo();
			if(this.viewfactoryhash.containsKey(id)) {
				factory = this.viewfactoryhash.get(id);
				view = (IView)factory.createView(entity);
			}
		}
		return view;
	}

	/** 
	 * Regista la factory dada al <code>ViewFactoryManager</code>.
	 * @param viewFactory La <code>IViewFactory</code> a ser registrada.
	 */
	public void add(IViewFactory viewFactory) {
		String id = viewFactory.getId();
		if (!this.viewfactoryhash.containsKey(id))
			this.viewfactoryhash.put(id, viewFactory);
	}

	/** 
	 * Devuelve la tabla que almacena los factorys.
	 * @return La tabla que almacena los factorys.
	 */
	public Hashtable getViewfactoryhash() {
		return viewfactoryhash;
	}

	/** 
	 * Setea la tabla de factorys con la tabla dada.
	 * @param theViewfactoryhash Las factorys a ser seteadas.
	 */
	public void setViewfactoryhash(Hashtable<String, IViewFactory> theViewfactoryhash) {
		viewfactoryhash = theViewfactoryhash;
	}
}