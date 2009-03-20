package client.game.controller;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

import com.sun.org.apache.bcel.internal.generic.RET;

import client.game.Game;
import client.game.entity.Entity;
import client.game.entity.IDynamicEntity;
import client.game.task.ITask;
import client.game.task.ITaskFactory;
import client.game.task.TaskManagerFactory;

/** 
 * Clase singleton. Funciona como un administrador de los Factories para los 
 * distintos {@link IController} utilizados por el Game. Ésta permite agregar dichos 
 * Factories y crear, mediante su, utilización, distintas instancias de 
 * {@link IController}.
 * 
 * @author Leo López
 * @version Creation date: 20-11-2008
 */
public class ControllerManagerFactory {
	/**
	 * {@link HashMap} que almacena el par Id - {@link IControllerFactory}. Para los  
	 * diferentes {@link IControllerFactory} utilizados por el Game.  
	 */
	private HashMap<String,IControllerFactory> controllers;	

	/**
	 * Instancia de {@link ControllerManagerFactory}.
	 */
	private static ControllerManagerFactory instance = null;
	
	/**
	 * Constructor de {@link ControllerManagerFactory} utilizado por 
	 * {@link ControllerManagerFactory#getInstance()}.
	 */
	private ControllerManagerFactory(){
		this.controllers = new HashMap<String,IControllerFactory>();
	}
	
	/**
	 * Crea la instancia de {@link ControllerManagerFactory} por primera y 
	 * única vez, y la retorna. Si ya estuviese creada sólo la devuelve.
	 * 
	 * @return instancia de {@link ControllerManagerFactory}.
	 */
	public static ControllerManagerFactory getInstance() {		
		if(instance == null) {
			instance = new ControllerManagerFactory();			
		}
		return instance;
	}
	
	/**
	 * Agrega un nuevo {@link IControllerFactory} 
	 * @param controller es la instancia de {@link IControllerFactory} que
	 * será agregada.
	 */
	public void add(IControllerFactory controller) {
		String id = controller.getId();
		if (!this.controllers.containsKey(id))
		controllers.put(controller.getId(), controller);
	}
	
	/** 
 	 * Retorna la instancia de {@link IController} asociado a entity. 
 	 * Creada, utilizando el correspondiente {@link IControllerFactory}.
 	 * 
	 * @param entity es la instancia de {@link IDynamicEntity} para la 
	 * cual se creara el {@link IController} asociado.
	 * @return la instancia de {@link IController} creada. 
	 */
	public IController createController(IDynamicEntity entity) {
		
		return ((IControllerFactory)controllers.get(entity.getTipo() + "ControllerFactory")).createController(entity);
	}
	
}