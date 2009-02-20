/**
 *
 */
package client.gameEngine;

import java.util.Vector;

import client.game.controller.ControllerManagerFactory;
import client.game.controller.IController;
import client.game.entity.IDynamicEntity;

/**
 * La clase <code>InputManager</code> es responsable de manejar las instancias 
 * de <code>IController</code>. Se responsabiliza de la creación, recuperación  
 * y destrucción de todas las instancias. 
 * <p>
 * <code>InputManager</code> is invocada por la Clase <code>Game</code> en cada
 * frame para hacer el update de todas las instancias de <code>IController</code>.
 * <p>
 * <p>
 * <code>InputManager</code> permite múltiples instancias del mismo tipo de
 * <code>IController</code>. 
 * <p>  
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class InputManager {
	
	private Vector<IController> controllers;
	/**
	 * Instancia de <code>InputManager</code>.
	 */
	private static InputManager instance;
		
	/**
	 * Constructor de <code>InputManager</code>.
	 */
	private InputManager() {
		controllers=new Vector<IController>();
	}
	
	/** 
	 * @return Instancia de InputManager
	 */
	public static InputManager getInstance() {		
		if (instance == null)
			instance = new InputManager();
		return InputManager.instance;		 
	}

	/**
	 * @param <code>IDynamicEntity<code> la cual se desea controlar. 
	 * @return <code>IController<code> creado.
	 */
	public IController createController(IDynamicEntity entity) {
		
		IController temp=ControllerManagerFactory.getInstance().createController(entity);
		if(!controllers.contains(temp))controllers.add(temp);
		return temp;
	}
	
	/** 
	 * Invoca al método Update de cada uno de los controles de las entidades. 
	 * @param Es el valor de la frecuencia de interpolation en segundos.
	 */
	public void update(float interpolation) {		
		// Update all controllers.
		for(IController controller : this.controllers) {
			if(controller.isActive())
				controller.update(interpolation);
		}				
	}
	
}