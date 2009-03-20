package client.game.controller;

import client.game.entity.IDynamicEntity;

/**
 * Implementa {@link IControllerFactory}.<br> 
 * Es el Factory de {@link ExternPlayerController} administrado por 
 * {@link ControllerManagerFactory}. Sólo se encargará de crear y 
 * devolver una instancia de {@link ExternPlayerController}. 
 * */
public class ExternPlayerControllerFactory implements IControllerFactory {
	
	/**
	 * Identificador. 
	 * */
	private String id = "DynamicEntityControllerFactory"; 
		
	/**
 	 * Crea y retorna la instancia de {@link ExternPlayerController}.      
 	 * 	
	 * @param entity es la {@link IDynamicEntity} asociada a 
	 * {@link ExternPlayerController}.
	 * 
	 * @return la instancia de {@link ExternPlayerController} creada.
	 */
	public IController createController(IDynamicEntity entity) {
		return new ExternPlayerController(entity);
	}

	/**
	 * Recupera el id del {@link ExternPlayerControllerFactory}.
	 * @return String con el valor del id.
	 */
	public String getId() {
		return id;
	}

}
