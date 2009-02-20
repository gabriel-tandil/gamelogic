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
	 * La identity de la <code>Entity</code>.
	 */
	protected String id;

	/** 
	 * Retorna la identity de la entidad <code>Entity</code>.
	 * @return la identity de <code>Entity</code>.
	 */
	public String getId() {
		return id;
	}

	/** 
	 * Setea la id de la <code>Entity</code>.
	 * @param theId la id a setear en <code>Entity</code>.
	 */
	public void setId(String theId) {
		id = theId;
	}

	/**
	 * Contructor de la Entity.
	*/
	public Entity(String theTipo){
		this.tipo=theTipo;
	}
	
	/** 
	 * El tipo de <code>Entity</code>.
	 */
	private String tipo;

	/** 
	 * Retorna el tipo de <code>Entity</code>. 
	 * @return tipo de <code>Entity</code>.
	 */
	public String getTipo() {
		return tipo;
	}
	
	/** 
	 * Setea el tipo de <code>Entity</code>.
	 * @param theTipo el tipo a setear a <code>Entity</code>
	 */
	public void setTipo(String theTipo) {
		tipo = theTipo;
	}


}