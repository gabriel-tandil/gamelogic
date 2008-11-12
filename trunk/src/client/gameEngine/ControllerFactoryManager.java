package client.gameEngine;

import java.util.HashMap;

import client.game.controller.IController;
import client.game.controller.IControllerFactory;
import client.game.entity.IDynamicEntity;

/**
 * Esta clase es un singleton que contendrá los diferentes controles definidos 
 * para el <code>Game<code>. Por medio de esta se pueden agregar, crear y obtener 
 * los <code>Controllers<code> del <code>Game<code>. 
 * 
 */
public class ControllerFactoryManager {
	/**
	 * La instancia de <code>ControllerFactoryManager</code>.
	 */
	private static ControllerFactoryManager instance;
	
	/**
	 * HashMap que contiene los diferentes <code>IController<code> del Game relacionados a
	 * sus correspondientes identificadores. 
	 */
	private HashMap<String,IController> controllers;
	
	/**
	 * Constructor <code>ControllerFactoryManager</code>.
	 */
	private ControllerFactoryManager(){
		this.controllers = new HashMap<String,IController>();
	}
	
	/**
	 * Crea la instancia de <code>ControllerFactoryManager</code> por primera 
	 * y única vez.
	 * @return La instancia de <code>ControllerFactoryManager</code>.
	 */
	protected static ControllerFactoryManager getInstance() {		
		if(ControllerFactoryManager.instance == null) {
			ControllerFactoryManager.instance = new ControllerFactoryManager();			
		}
		return ControllerFactoryManager.instance;
	}
	
	/**
	 * 
	 * @param controller Es un nuevo <code>IController<code> definido para el Game.
	 * @param entity Es la entidad que será controlada por este <code>IController<code>.
	 */
	protected void addController(IController controller, IDynamicEntity entity) {
		controllers.put(entity.toString(), controller);
	}
	
	/** 
	 * @param id Es el identificador del controlador a ser creado.
	 * @return El <code>IController<code> que fue creado.
	 */
	protected IController createController(IDynamicEntity entity) {
		return ((IControllerFactory)controllers.get(entity.toString())).createController(entity);		
	}	
}
