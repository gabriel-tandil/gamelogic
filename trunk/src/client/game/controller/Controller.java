/**
 * 
 */
package client.game.controller;

import client.game.entity.IDynamicEntity;

/**
 * <code>Controller</code> implementa <code>IController</code> para definir la
 * abstracción básica del controlador de una entidad. Este es responsable de monitorear
 * y procesar las entradas del usuario. Genera las correspondientes <code>ITask</code>
 * en respuesta a los eventos de entrada.
 * <p>
 * <code>Controller</code> es, además, responsable de monitorear el estado de la
 * <code>IDynamicEntity</code> que este controla para generar las correspondientes
 *  <code>ITask</code> durante el ciclo de update. Esto permite al <code>Controller</code>
 *  modificar la <code>IDynamicEntity</code> continuamente a través de las distintas
 *  iteraciones del update.
 * */
public abstract class Controller implements IControllerFactory {
	
	/**
	 * La <code>IDynamicEntity<code> controlada por este <code>Controller<code>.
	 */
	private IDynamicEntity entity;
	
	/**
	 * Este flag indica si el control esta activo o no.
	 */
	private boolean active;
			
	/**
	 * Constructor de <code>Controller</code>.
	 * @param La <code>IDynamicEntity</code> controlada.
	 */
	public Controller(IDynamicEntity entity) {	
		if(entity == null) throw new IllegalArgumentException("Null entity.");
		this.entity = entity;	
	}
		
	/**
	 * Hace el update de la logica de este controlador.
	 * @param Es el valor de la frecuencia de interpolation en segundos.
	 */
	public abstract void updateLogic(float interpolation);
	
	public IDynamicEntity getEntity() {
		return this.entity;
	}

	public void update(float interpolation) {
		this.updateLogic(interpolation);
	}	
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isActive() {
		return this.active;
	}
}