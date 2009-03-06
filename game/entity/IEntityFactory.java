/**
 * Define la interfaz de creación para las posteriores entidades que serán
 * definidas en el Game. 
 */
package client.game.entity;

/** 
 * Define la interfaz de creación para las posteriores entidades que serán
 * definidas en el juego.
 * @author Santiago Michielotto
 * @version Created: 19-11-2008
 */
public interface IEntityFactory {

	/**
	 * @return El identificador de la Entidad.
	 */
	public String getId();
	
	/**
	 * Este método será implementado por cada Entidad definida para el game.
	 * La clase que define la Entidad es la que debe conocer como crear una instancia
	 * de ella misma.
	 * @return La <code>IEntity<code> creada.
	 */
	public IEntity createEntity(String idEntity);
}