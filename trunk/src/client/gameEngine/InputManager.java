/**
 *
 */
package client.gameEngine;

import java.util.HashMap;

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
	
	/**
	 * <code>HashMap</code> con la <code>IDynamicEntity</code> y el 
	 * <code>IController</code> que llevará a cabo el control de la misma.
	 */
	private final HashMap<IDynamicEntity, IController> controllers;	
	
	/**
	 * Instancia de <code>InputManager</code>.
	 */
	private static InputManager instance;
		
	/**
	 * Constructor de <code>InputManager</code>.
	 */
	private InputManager() {
		controllers = new HashMap<IDynamicEntity,IController>();	
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
	 * En caso de no existir un determinado <code>IController<code> asociado
	 * a esta <code>IDynamicEntity<code>, crea uno y lo retorna. 
	 * @param La entidad <code>IDynamicEntity</code> que esta siendo controlada.
	 * @return El <code>IController</code> que controla la <code>IDynamicEntity</code>
	 * pasada por parámetro. 
	 */
	public IController getIController(IDynamicEntity entity) {
		IController controller = this.controllers.get(entity);
		if(controller == null) controller = this.createController(entity);
		return controller;				
	}

	/**
	 * @param <code>IDynamicEntity<code> la cual se desea controlar. 
	 * @return <code>IController<code> creado.
	 */
	public IController createController(IDynamicEntity entity) {		
		return ControllerFactoryManager.getInstance().createController(entity);
	}
	
	/** 
	 * Invoca al método Update de cada uno de los controles de las entidades. 
	 * @param Es el valor de la frecuencia de interpolation en segundos.
	 */
	public void update(float interpolation) {		
		// Update all controllers.
		for(IController controller : this.controllers.values()) {
			if(controller.isActive())
				controller.update(interpolation);
		}				
	}
}