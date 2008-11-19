/**
 * 
 */
package client.game.view;

import java.util.Hashtable;

import client.game.Game;
import client.game.controller.Controller;
import client.game.entity.IEntity;

/** 
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class ViewFactoryManager {
	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Hashtable<IEntity, View> viewfactoryhash;
	private static int id=0;
	/** 
	 * @param id Es el identificador de la tarea a ser creada.
	 * @param c el class de la clase que quiero instanciar
	 * @param game el juego
	 * @return El controller que fue creado.
	 */
	@SuppressWarnings("unchecked")
	protected View createView(Class<View> c, Game game,String id) {
		//return ((ITaskFactory)tasks.get(id)).createTask(id, game);		
	
		View v=null;
		Class<View> claseView;
	try {
		claseView=(Class<View>) Class.forName(c.getName());
		v=(View)claseView.newInstance();
		v.initializeView(id, game);
	} catch (Exception ex) {
		// TODO Auto-generated catch block
//		logger.log.Level.FATAL,"Error al crear la instancia de la clase "+c.getName());

	}
	return v;
	}

	/**
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static ViewFactoryManager getInstance() {
		return null;
	}

	/**
	 * @param viewFactory
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void add(IViewFactory viewFactory) {

	}
}