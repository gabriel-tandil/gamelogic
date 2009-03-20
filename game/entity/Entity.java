/**
 * La clase <code>Entity</code> es una clase abstracta
 * que define la entidad en el mundo del juego.
 * Una <code>Entity</code> tiene una entidad
 * y un tipo.
 * 
 * @author Cristian Calomino
 * @version Created: 20-11-2008
 */
package client.game.entity;

import java.io.IOException;

import com.jme.util.export.JMEExporter;
import com.jme.util.export.JMEImporter;

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
	public Entity(String id){
		this.setId(id);
		this.setTipo("Entity");
	}
	
	public Entity(){
	}
	
	/** 
	 * El tipo de <code>Entity</code>.
	 */
	private String tipo = "Entity";

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

	/**
	 * El mundo actual donde esta la DynamicEntity.
	 */
	protected String actualWorld;

	/**
	 * Retorna el mundo actual dodne esta la DynamicEntity.
	 * 
	 * @return actualWorld el mundo actual donde esta la DynamicEntity.
	 */
	public String getActualWorld() {
		return actualWorld;
	}

	/**
	 * Aplica el mundo actual de la DynamicEntity.
	 * 
	 * @param theActualWorld
	 *            mundo actual a aplicar a DynamicEntity.
	 */
	public void setActualWorld(String theActualWorld) {
		actualWorld = theActualWorld;
	}

	public boolean equals(IEntity e)
	{
		return e.getId().equals(this.id);
	}

	
	public int hashCode() {
		return this.id.hashCode();
	}
	
	
}