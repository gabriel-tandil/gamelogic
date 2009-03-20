package client.game.controller;

import client.game.entity.IDynamicEntity;

/**
 * Implementa {@link IControllerFactory}.<br> 
 * Es el Factory de {@link PlayerControllerFactory} administrado por 
 * {@link ControllerManagerFactory}. Sólo se encargará de crear y 
 * devolver una instancia de {@link PlayerControllerFactory}. 
 * */
public class PlayerControllerFactory implements IControllerFactory {

	/**
	 * Identificador. 
	 * */
	private String id = "PlayerControllerFactory"; 
	
	/**
 	 * Crea y retorna la instancia de {@link PlayerController}.      
 	 * 	
	 * @param entity es la {@link IDynamicEntity} asociada a 
	 * {@link PlayerController}.
	 * 
	 * @return la instancia de {@link PlayerController} creada.
	 */
	public IController createController(IDynamicEntity entity) {
		return new PlayerController(entity);
	}

	/**
	 * Recupera el id del {@link PlayerControllerFactory}.
	 * @return String con el valor del id.
	 */
	public String getId() {
		return id;
	}

}
