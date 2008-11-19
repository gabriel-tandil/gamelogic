/**
 *<code>Entity</code> defines the base abstraction of an entity in the game
 * world. One <code>Entity</code> has an identity and a type 
 */
package client.game.entity;


/** 
 * @author Santiago Michielotto
 * @version Created: 28-10-2008
 * 
 */
public abstract class Entity implements IEntity, IEntityFactory {
	/** 
	 * The type of this <code>Entity</code>.
	 */
	private String tipo;

	/** 
	 * Retrieve the <code>Entity</code> type.
	 * @return the <code>Entity</code> type.
	 */
	public String getTipo() {
		return tipo;
	}
	
	/** 
	 * Set the <code>Entity</code> type.
	 * @param theTipo the <code>Entity</code> type required.
	 */
	public void setTipo(String theTipo) {
		tipo = theTipo;
	}

	/**
	 * The identity number of this <code>Entity</code>.
	 */
	private String id;

	/** 
	 * Retrieve the identity(id) number of this <code>Entity</code>.
	 * @return The identity of this <code>Entity</code>.
	 */
	public String getId() {
		return id;
	}

	/** 
	 * Set the <code>Entity</code> id.
	 * @param theId the identity(id) number of the <code>Entity</code>.
	 */
	public void setId(String theId) {
		id = theId;
	}
	public void initializeEntity(String id){
	//hacer	
	}

}