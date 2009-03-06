/**
 * Define la interfaz de creaci�n para las posteriores entidades que ser�n
 * definidas en el Game. 
 */
package client.game.entity;

/** 
 * Define la interfaz de creaci�n para las posteriores entidades que ser�n
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
	 * Este m�todo ser� implementado por cada Entidad definida para el game.
	 * La clase que define la Entidad es la que debe conocer como crear una instancia
	 * de ella misma.
	 * @return La <code>IEntity<code> creada.
	 */
	public IEntity createEntity(String idEntity);
}