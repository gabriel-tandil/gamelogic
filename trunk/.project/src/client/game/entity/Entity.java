/**
 *<code>Entity</code> defines the base abstraction of an entity in the game
 * world. One <code>Entity</code> has an identity and a type 
 */
package client.game.entity;

/** 
 * @author Cristian Calomino
 * @version Created: 20-11-2008
 * 
 */
public abstract class Entity implements IEntity {
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

	/*
	 * Contructor of the Entity.
	*/
	public Entity(string theId){
		this.id=theId
	}

}