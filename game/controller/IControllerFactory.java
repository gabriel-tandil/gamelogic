package client.game.controller;

import client.game.Game;
import client.game.entity.IDynamicEntity;

/**
 * Define la interfaz de creaci�n para los diferentes {@link IController} que
 * ser�n definidos en el Game. 
 */
public interface IControllerFactory  {

	/**
 	 * Crea y retorna la instancia de {@link Controller} espec�fica para los
 	 * diferentes tipos de controlador existentes en el Game.       
 	 * 	
	 * @param entity es la {@link IDynamicEntity} asociada al {@link IController}
	 * que ser� creado.
	 * 
	 * @return la instancia de {@link IController} creada.
	 */
	public IController createController(IDynamicEntity entity);	
	
	/**
	 * Recupera el id del {@link IControllerFactory}.
	 * @return String con el valor del id.
	 */
	public String getId();

}
