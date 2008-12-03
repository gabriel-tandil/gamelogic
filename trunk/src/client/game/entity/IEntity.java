/**
 * <code>IEntity</code> defines the interface for all 
 * types of entity.
 */
package client.game.entity;

import com.jme.util.export.Savable;

/** 
 * @author Santiago Michielotto
 * @version Created: 20-11-2008
 */
public interface IEntity extends Savable {
	/**
	 * Retrieve the identity(id) number of this entity.
	 * @return The integer ID number.
	 */
	public String getId();
	
	/** 
	 * Retrieve the <code>Entity</code> type.
	 * @return the <code>Entity</code> type.
	 */
	public String getTipo();
}