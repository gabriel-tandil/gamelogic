/**
 * 
 */
package client.game.view;

import java.util.Hashtable;

import javax.swing.text.ViewFactory;

import client.game.Game;
import client.game.controller.Controller;
import client.game.entity.EntityManagerFactory;
import client.game.entity.IEntity;

/** 
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class ViewFactoryManager {
	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Hashtable<String, IViewFactory> viewFactorys;
	private static int id=0;

	private static ViewFactoryManager instance = null;
	/**
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static ViewFactoryManager getInstance() {
		if (instance == null) {
			instance = new ViewFactoryManager();
		}
		return instance;
	}

	/**
	 * @param viewFactory
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void add(IViewFactory viewFactory) {
		viewFactorys.put(viewFactory.getId(), viewFactory);
	}
}