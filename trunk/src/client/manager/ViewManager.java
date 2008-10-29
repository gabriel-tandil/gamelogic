/**
 * 
 */
package client.manager;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.Set;
import client.game.view.IView;
import client.game.view.IDynamicView;
import client.game.entity.IEntity;
import client.game.entity.IDynamicEntity;

/** 
 * @author
 * 
 */
public abstract class ViewManager {
	/**
	 * The ViewManager instance
	 */
	private static ViewManager instance;
	/** 
	 * The IView pool
	 */
	private HashMap<IEntity, IView> views;
	/**
	 * The dirty IDynamic buffer
	 */
	private LinkedList<IDynamicView> dirty;
	
	private Set<IView> iview;
	
	/**
	 * Constructor of ViewManager
	 */
	private ViewManager(){
		this.views = new HashMap<IEntity, IView>();
		this.dirty = new LinkedList<IDynamicView>();
	}

	/**
	 * @return The ViewManager instance
	 */
	public static ViewManager getInstance(){
		if(ViewManager.instance == null){
			//instance = new ViewManager();
			//no se pueden crear instancias porque ViewManager es abstracta
		}
		return ViewManager.instance;
	}
	
	/**
	 * Update all the dirty views
	 * @param interpolation The frame rate interpolation value
	 */
	public void update(float interpolation) {
		while(!this.dirty.isEmpty()){
			this.dirty.pop().update(interpolation);
		}
	}
	
	/**
	 * Register the given view with ViewManager
	 * @param view The IView to be registered
	 */
	public boolean registerView(IView view) {
		IEntity entity = view.getEntity();
		if(this.views.containsKey(entity)){
			return false;
		}
		this.views.put(entity, view);
		return true;
	}
	
	/** 
	 * Remove the view represents the given entity
	 * @param entity
	 */
	public boolean removeView(IEntity entity) {
		IView view = this.views.remove(entity);
		if(view == null){
			return false;
		}
		//view.detachFromParent(); no existe en IView
		return true;
	}

	/** 
	 * Mark the dynamic view represents the given dynamic entity for update
	 * @param entity The IDynamicEntity has been modified
	 */
	public void markForUpdate(IDynamicEntity entity) {
		IView view = this.views.get(entity);
		if(view.isValidView()){
			this.dirty.add((IDynamicView)view);
		}
	}

	/** 
	 * Retrieve the view that represents the given entity.
	 * @param entity The IEntity that the view represents.
	 * @return The IView that represents the given entity.
	 */
	public IView getView(IEntity entity) {
		return this.views.get(entity);
		//consultar sobre lanzar excepcion ObjectNotFound
	}
	
	/** 
	 * @return The LinkedList that represents dirty views.
	 */
	public LinkedList getDirty() {
		return dirty;
	}
	

	/** 
	 * @param list The list to set
	 */
	public void setDirty(LinkedList list) {
		this.dirty = list;
	}

	/** 
	 * @return The HashMap that represents the relation IEntity, IView.
	 */
	public HashMap getViews() {
		return views;
	}

	/** 
	 * @param views The views to set.
	 */
	public void setViews(HashMap views) {
		this.views = views;
	}

	/** 
	 * @return The IView
	 */
	public Set<IView> getIview() {
		return iview;
	}
	

	/** 
	 * @param theIview The IView to set
	 */
	public void setIview(Set<IView> theIview) {
		iview = theIview;
	}


	/** 
	 * @param view
	 */
	public void addDirtyView(IView view) {
		//consultar, misma funcionalidad de markForUpdate()?
	}

	/** 
	 * @return
	 */
	public abstract IEntity createView();

}