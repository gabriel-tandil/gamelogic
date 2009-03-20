/**
 * La clase <code>IEntity</code> define 
 * la interface de todas las entitades
 *
 * @author Santiago Michielotto
 * @version Created: 20-11-2008
 */
package client.game.entity;

import com.jme.util.export.Savable;

public interface IEntity extends Savable {
	/**
	 * Retorna la identity de esta entidad
	 * @return ID number.
	 */
	public String getId();
	
	/** 
	 * Retorna el tipo de la <code>Entity</code>.
	 * @return tipo de <code>Entity</code>.
	 */
	public String getTipo();
	
	public String getActualWorld();
	
	public void setActualWorld(String theActualWorld);
}