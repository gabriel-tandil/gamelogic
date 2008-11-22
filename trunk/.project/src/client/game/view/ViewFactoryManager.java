/**
 * 
 */
package client.game.view;

import java.util.Hashtable;

import client.game.entity.IEntity;

/** 
 * @author Maria Hansen
 * @version Creation date: 20-11-2008
 */
public class ViewFactoryManager {
	/**
	 * La instancia de <code>ViewFactoryManager</code>
	 */
	private static ViewFactoryManager instance = null;

	/** 
	 * 
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
	 * 
	 */
	public IView createView(IEntity entity) {
		String id;
		IViewFactory factory;
		IView view = null;
		
		if(entity != null) {
			id = entity.getId();
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
	 * 
	 */
	public Hashtable getViewfactoryhash() {
		return viewfactoryhash;
	}

	/** 
	 * 
	 */
	public void setViewfactoryhash(Hashtable<String, IViewFactory> theViewfactoryhash) {
		viewfactoryhash = theViewfactoryhash;
	}
}