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
	 * @return el idynamicentity
	 * @generated "De UML a Java V5.0 
	 * (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public IDynamicEntity getIDynamicEntity() {
		return this.idynamicentity;
	}
	
	/**
	 * @param theIdynamicentity el idynamicentity a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setIDynamicEntity(IDynamicEntity theIdynamicentity) {
		// begin-user-code
		this.idynamicentity = theIdynamicentity;
		// end-user-code
	}
	
	/**
	 * (sin Javadoc)
	 * @see IController#update()
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void update(float interpolation) {
		this.updateLogic(interpolation);
	}	
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * (sin Javadoc)
	 * @see IController#isActive()
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public boolean isActive() {
		return this.active;
	}

}