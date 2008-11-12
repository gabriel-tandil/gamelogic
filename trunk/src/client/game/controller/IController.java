/**
 * 
 */
package client.game.controller;

import client.game.entity.IDynamicEntity;

/**
 * <code>IController</code> define la interface para todos los tipos de entity
 * controller en el sistema.
 * <p>
 * <code>IController</code> define la unidad de control de un dynamic
 * entity, la cual puede ser controlada por las entradas del usuario. Es
 * responsable de mapear la entradas del usuario a las entity y view controladas.
 * <p>
 * <code>IController</code> genera las correspondientes <code>ITask</code> cuando
 * una entrada del usuario es recibida para modificar la entidad controlada.
 * <p>
 * <code>IController</code> es actualizada (updated) por el loop update/render del
 * main del game, en cada frame, para ejecutar potenciales acciones. Esta
 * mantiene una referencia a la <code>IEntity</code> controlada.
*/

public interface IController {
	
	/**
	 * Recupera la entity controlada por este <code>IController<code>.
	 * @return Instancia de <code>IDynamicEntity</code>.
	 */
	public IDynamicEntity getEntity();

	/**
	 * @param Es el valor de la frecuencia de interpolation en segundos.
	 */
	public void update(float interpolation);
	
	/**
	 * Setea el estado, activo o no, del <code>Controller<code>.
	 * @param True si el <code>Controller<code> debería ser activado. De otra manera, False.
	 */
	public void setActive(boolean active);
	
	/**
	 * Chequea si el <code>Controller<code> esta activo o no.
	 * @return True si el <code>Controller<code> esta activo. De otra manera, False.
	 */
	public boolean isActive();
		
}