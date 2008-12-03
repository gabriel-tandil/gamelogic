/**
 * 
 */
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
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class ControllerManagerFactory {
	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	/**
	 * HashMap que contiene las diferentes <code>controllers<code> del Game relacionadas a
	 * sus correspondientes identificadores. 
	 */
	private HashMap<String,IControllerFactory> controllers;	

private static ControllerManagerFactory instance=null;
	
	/**
	 * Constructor <code>TaskFactoryManager</code>.
	 */
	private ControllerManagerFactory(){
		this.controllers = new HashMap<String,IControllerFactory>();
	}
	
	/**
	 * Crea la instancia de <code>TaskFactoryManager</code> por primera 
	 * y �nica vez.
	 * @return La instancia de <code>TaskFactoryManager</code>.
	 */
	public static ControllerManagerFactory getInstance() {		
		if(instance == null) {
			instance = new ControllerManagerFactory();			
		}
		return instance;
	}
	
	/**
	 * 
	 * @param task Es una nueva tarea definida para el Game.
	 */
	public void add(IControllerFactory controller) {
		controllers.put(controller.getId(), controller);
	}
	
	/** 
	 * @param id Es el identificador de la tarea a ser creada.
	 * @return La tarea que fue creada.
	 */
	public IController createController(IDynamicEntity entity) {
		
		return ((IControllerFactory)controllers.get(entity.getTipo())).createController(entity);
	}
	
}