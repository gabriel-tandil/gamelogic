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
 * <code>ViewManager</code> es responsable del manejo de todas las 
 * <code>IView</code> que representan a las entidades. Debería ser actualizado
 * por <code>Game</code> dentro del main game loop.
 * <code>ViewManager</code> es responsable de todos los aspectos del manejo de
 * vistas incluyendo creación, destrucción y actualización.
 * <code>ViewManager</code> mantiene todas las <code>IView</code> utilizando
 * <code>IEntity</code> como la clave. Esto permite al sistema marcar una 
 * <code>IView</code> particular como sucia cuando su correspondiente
 * <code>IEntity</code> ha sido modificada.
 * <code>ViewManager</code> luego almacena todas las <code>IDynamicView</code> 
 * sucias para actualizarlas durante el siguiente ciclo.
 * 
 * @author Maria Hansen
 * @version Creation date: 29-10-2008
 */
public class ViewManager {
	/**
	 * La instancia de <code>ViewManager</code>.
	 */
	private static ViewManager instance;
	/** 
	 * El almacenamiento de <code>IView</code>.
	 */
	private HashMap<IEntity, IView> views;
	/**
	 * El almacenamietno de <code>IDynamic</code> sucias.
	 */
	private LinkedList<IDynamicView> dirty;


	/**
	 * Constructor de <code>ViewManager</code>.
	 */
	private ViewManager() {
		this.views = new HashMap<IEntity, IView>();
		this.dirty = new LinkedList<IDynamicView>();
	}

	/**
	 * Devuelve la instancia de <code>ViewManager</code>.
	 * @return La instancia de <code>ViewManager</code>.
	 */
	public static ViewManager getInstance() {
		if (ViewManager.instance == null) {
			instance = new ViewManager();
		}
		return ViewManager.instance;
	}

	/**
	 * Actualiza todas las vistas sucias.
	 * @param interpolation El valor de interpolación del frame.
	 */
	public void update(float interpolation) {
		while (!this.dirty.isEmpty()) {
			this.dirty.pop().update(interpolation);
		}
	}

	/**
	 * Registra la vista dada al <code>ViewManager</code>. Este método debería ser
	 * sólo invocado cuando la clase se carga por primera vez.
	 * @param view La <code>IView</code> a ser registrada.
	 * @return True si la vista dada es registrada exitosamente. False en caso 
	 * contrario.
	 */
	public boolean registerView(IView view) {
		IEntity entity = view.getEntity();
		if (this.views.containsKey(entity)) {
			return false;
		}
		this.views.put(entity, view);
		return true;
	}

	/** 
	 * Remueve la <code>IView</code> que representa la entidad dada.
	 * @param entity La <code>IEntity</code> que la <code>IView</code> representa.
	 * @return True si la vista fue eliminada exitosamente. False en caso contrario.
	 */
	public boolean removeView(IEntity entity) {
		IView view = this.views.remove(entity);
		if (view == null) {
			return false;
		}
		view.detachFromParent();
		return true;
	}

	/** 
	 * Marca para actualizar a la vista dinámica que representa a la entidad
	 * dinámica.
	 * @param entity La <code>IDynamicEntity</code> que ha sido modificada.
	 */
	public void markForUpdate(IDynamicEntity entity) {
		IView view = this.getView(entity);
		this.addDirtyView(view);
	}

	/** 
	 * Devuelve la vista que representa a la entidad dada.
	 * @param entity La <code>IEntity</code> que la vista representa.
	 * @return La <code>IView</code> que representa a la entidad dada.
	 */
	public IView getView(IEntity entity) {
		return this.views.get(entity);
	}

	/** 
	 * Agrega a la lista de vistas sucias la vista dada.
	 * @param view La vista a ser agregada.
	 */
	public void addDirtyView(IView view) {
		if(view.isDynamicView()) {
			this.dirty.add((IDynamicView) view);
		}
	}

	/** 
	 * Crea la vista correspondiente a la entidad dada.
	 */
	public void createView(String idEntity) { }
	
	/** 
	 * Devuelve la lista que almacena a las vistas sucias.
	 * @return La lista que almacena a las vistas sucias.
	 */
	public LinkedList getDirty() {
		return dirty;
	}

	/** 
	 * Setea la lista de vistas a ser actualizadas con la lista dada.
	 * @param list La lista a ser seteada.
	 */
	public void setDirty(LinkedList<IDynamicView> list) {
		this.dirty = list;
	}

	/** 
	 * Devuelve la tabla que representa la relacion <code>IEntity</code>,
	 * <code>IView</code>.
	 * @return La tabla que representa la relacion <code>IEntity</code>,
	 * <code>IView</code>.
	 */
	public HashMap getViews() {
		return views;
	}

	/** 
	 * Setea la tabla de vistas con la tabla dada.
	 * @param views Las vistas a ser seteadas.
	 */
	public void setViews(HashMap<IEntity, IView> views) {
		this.views = views;
	}

}