/**
 * 
 */
package client.game.controller;

import client.game.entity.IDynamicEntity;

/**
 * <code>Controller</code> implementa <code>IController</code> para definir la
 * abstracciónn básica del controlador de una entidad. Este es responsable de monitorear
 * y procesar las entradas del usuario. Genera las correspondientes <code>ITask</code>
 * en respuesta a los eventos de entrada.
 * <p>
 * <code>Controller</code> es, además, responsable de monitorear el estado de la
 * <code>IDynamicEntity</code> que este controla para generar las correspondientes
 *  <code>ITask</code> durante el ciclo de update. Esto permite al <code>Controller</code>
 *  modificar la <code>IDynamicEntity</code> continuamente a través de las distintas
 *  iteraciones del update.
 * */
public abstract class Controller implements IController{
	
	/**
	 * La <code>IDynamicEntity<code> controlada por este <code>Controller<code>.
	 */
	private IDynamicEntity idynamicentity;
	
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
		this.idynamicentity = entity;	
	}
		
	/**
	 * Hace el update de la logica de este controlador.
	 * @param Es el valor de la frecuencia de interpolation en segundos.
	 */
	public abstract void updateLogic(float interpolation);
	
	/**
	 * Retorna la idynamicentity
	 * @return el idynamicentity
	 */
	public IDynamicEntity getIDynamicEntity() {
		return this.idynamicentity;
	}
	
	/**
	 * Setea la idynamicentity con la theIdynamicentity pasada como parametro
	 * @param theIdynamicentity el idynamicentity a establecer
	 */
	public void setIDynamicEntity(IDynamicEntity theIdynamicentity) {
		this.idynamicentity = theIdynamicentity;
	}
	
	/**
	 * Aplica el update
	 */
	public void update(float interpolation) {
		this.updateLogic(interpolation);
	}	
	

	/**
	 * Setea el flag active con la varibale pasada como parametro
	 * @param active el flag a establecer
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * Retorna el flag active, muestra si se encuentra activo o no
	 * @return active, flag muestra si se encuentra activo o no
	 */
	public boolean isActive() {
		return this.active;
	}

}