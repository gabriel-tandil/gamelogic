/**
 * 
 */
package client.game.controller;

import client.game.entity.IDynamicEntity;

/**
 * <code>IController</code> define la interface para los distintos controles del 
 * sistema. Esta mantiene una referencia a la {@link IEntity} controlada.<br> 
 * {@link IController} define la unidad de control de una {@link IDynamicEntity}
 * la cual puede ser controlada por las entradas del usuario. Es responsable de 
 * mapear la entradas del usuario a las {@link IDynamicEntity} controladas.<br>
 * {@link IController} da soporte para generar las correspondientes {@link ITask}
 * cuando una entrada del usuario sea recibida con el objetivo de modificar la 
 * entidad controlada. <br>
 * {@link IController} sera actualizada (updated) por el loop update/render del
 * main del game, en cada frame, para llevar a cabo potenciales acciones.
*/

public interface IController {
	
	/**
	 * Recupera la entity controlada por este {@link IController}.
	 * @return Instancia de <code>IDynamicEntity</code>.
	 */
	public IDynamicEntity getIDynamicEntity();

	/**
	 * Actualiza este {@link IController}.
	 * @param interpolation es el valor de la frecuencia de interpolacion en segundos.
	 */
	public void update(float interpolation);
	
	/**
	 * Setea el estado, activo o no, del {@link Controller}.
	 * @param True si el {@link Controller} debería ser activado. De otra manera, False.
	 */
	public void setActive(boolean active);
	
	/**
	 * Chequea si el {@link Controller} esta activo o no.
	 * @return True si el {@link Controller} esta activo. De otra manera, False.
	 */
	public boolean isActive();
		
}