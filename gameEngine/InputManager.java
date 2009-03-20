/**
 *
 */
package client.gameEngine;

import java.util.HashMap;
import java.util.Iterator;

import client.game.controller.ControllerManagerFactory;
import client.game.controller.IController;
import client.game.entity.IDynamicEntity;
import client.game.entity.IEntity;

/**
 * La clase <code>InputManager</code> es responsable de manejar las instancias
 * de <code>IController</code>. Se responsabiliza de la creación, recuperación y
 * destrucción de todas las instancias.
 * <p>
 * <code>InputManager</code> is invocada por la Clase <code>Game</code> en cada
 * frame para hacer el update de todas las instancias de
 * <code>IController</code>.
 * <p>
 * <code>InputManager</code> permite múltiples instancias del mismo tipo de
 * <code>IController</code>.
 * <p>
 * 
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class InputManager {

	/**
	 * mapa que mantiene los controladores manejados
	 */
	private HashMap<IEntity, IController> controllers;

	/**
	 * Instancia de <code>InputManager</code>.
	 */
	private static InputManager instance;

	/**
	 * Constructor de <code>InputManager</code>.
	 */
	private InputManager() {
		controllers = new HashMap<IEntity, IController>();
	}

	/**
	 * devuelve la instancia unica del <code>InputManager</code>
	 * 
	 * @return Instancia de InputManager
	 */
	public static InputManager getInstance() {
		if (instance == null)
			instance = new InputManager();
		return InputManager.instance;
	}

	/**
	 * crea un controlador para una entidad dinamica y lo agrega al mapa de
	 * controladores manejados
	 * 
	 * @param <code>IDynamicEntity<code> la cual se desea controlar.
	 * @return <code>IController<code> creado.
	 */
	public IController createController(IDynamicEntity entity) {

		IController temp = ControllerManagerFactory.getInstance()
				.createController(entity);
		if (!this.controllers.containsKey(entity))
			this.controllers.put(entity, temp);
		return temp;
	}

	/**
	 * quita un controlador del mapa de controladores manejados
	 * 
	 * @param <code>IDynamicEntity<code> la cual se desea controlar.
	 * @return <code>IController<code> creado.
	 */
	public void removeController(IDynamicEntity entity) {
		if (this.controllers.containsKey(entity))
			this.controllers.remove(entity);
	}

	/**
	 * Invoca al método Update de cada uno de los controles de las entidades.
	 * 
	 * @param Es
	 *            el valor de la frecuencia de interpolation en segundos.
	 */
	public void update(float interpolation) {
		for (IController controller : this.controllers.values()) {
			if (controller.isActive())
				controller.update(interpolation);
		}
	}

	/***
	 * desactiva todos los controladores manejados para que no sean tenidos en
	 * cuanta en la actualizacion
	 */
	public void desactivateAllControllers() {
		for (IController controller : this.controllers.values()) {
			controller.setActive(false);
		}
	}

	/**
	 * limpia el arreglo de controladores manejados
	 */
	public void removeAll() {
		desactivateAllControllers();
		controllers.clear();
	}
}