package client.game.controller;

import client.game.entity.IDynamicEntity;

/**
 * {@link Controller} implementa {@link IController} para definir la
 * abstracción básica del controlador de una entidad. Este es responsable de monitorear
 * y procesar las entradas del usuario. Genera las correspondientes {@link ITask}
 * en respuesta a los eventos de entrada.<br> 
 * {@link Controller} es, además, responsable de monitorear el estado de la
 * {@link IDynamicEntity}, la cual este maneja, para generar las correspondientes
 *  {@link ITask} durante el ciclo de update. Esto permite al {@link Controller}
 *  modificar la {@link IDynamicEntity}, continuamente, en cada una de las iteraciones 
 *  del update.
 *  
 *  @author Leo López
 * */
public abstract class Controller implements IController{
	
	/**
	 * La correspondiente {@link IDynamicEntity} manejada por el {@link Controller}
	 */
	private IDynamicEntity idynamicentity;
	
	/**
	 * Este flag indica si el {@link Controller} se encuentra activo o no.
	 */
	private boolean active;
			
	/**
	 * Constructor de {@link Controller}
	 * @param entity la {@link IDynamicEntity} controlada.
	 */
	public Controller(IDynamicEntity entity) {	
		if(entity == null) throw new IllegalArgumentException("Null entity.");
		this.idynamicentity = entity;	
	}
		
	/**
	 * Realiza el update de la logica de este controlador.<br>
	 * Creará las correspondientes {@link ITask} que llevarán
	 * a cabo el comportamiento respectivo de la {@link IDynamicEntity}.
	 * @param interpolation es el valor de la frecuencia de interpolacion 
	 * en segundos.
	 */
	public abstract void updateLogic(float interpolation);
	
	/**
	 * Obtiene la instancia de {@link IDynamicEntity} manejada por
	 * el {@link Controller}.
	 * @return una instancia de {@link IDynamicEntity}.
	 */
	public IDynamicEntity getIDynamicEntity() {
		return this.idynamicentity;
	}
	
	/**
	 * Setea la {@link IDynamicEntity} manejada por el {@link Controller}.
	 * @param theIdynamicentity es el valor para setear la instancia de 
	 * {@link IDynamicEntity}.
	 */
	public void setIDynamicEntity(IDynamicEntity theIdynamicentity) {
		this.idynamicentity = theIdynamicentity;
	}
	
	/**
	 * Lleva a cabo el {@link Controller#updateLogic(float)}.
	 * @param interpolation es el valor de la frecuencia de interpolacion
	 * en segundos.
	 */
	public void update(float interpolation) {
		this.updateLogic(interpolation);
	}	
	

	/**
	 * Setea el valor para active.
	 * @param active, el valor a setear.
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * Retorna el valor del flag active, indicando si el {@link Controller}
	 * se encuentra activo o no.
	 * @return boolean, valor que indica si el {@link Controller} se 
	 * encuentra activo o no.
	 */
	public boolean isActive() {
		return this.active;
	}

}