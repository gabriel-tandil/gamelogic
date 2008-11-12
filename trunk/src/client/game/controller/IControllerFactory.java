package client.game.controller;

import client.game.entity.IDynamicEntity;

/**
 * Define la interfaz de creación para los posteriores controles que serán
 * definidos en el Game. 
 */
public interface IControllerFactory extends IController {

	/**
	 * Este método será implementado por cada controlador definido para el game.
	 * La clase que define al controlador es la que debe conocer como crear una instancia
	 * de este. 
	 * @param entity Entidad para la cual se desea asociar el <code>Controller<code>
	 * creado.
	 * @return El <code>IControllerFactory<code> creado.
	 */
	public IControllerFactory createController(IDynamicEntity entity);	
}
