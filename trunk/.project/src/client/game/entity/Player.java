/**
 *<code>Player</code> extends <code>DynamicEntity</code> to define
 * the base player in the game.
 */
package client.game.entity;

import java.util.Hashtable;
import common.datatypes.PlayerState;
import common.datatypes.Quest;

import java.util.Set;

/** 
 * @author Cristian Calomino
 * @version Created: 20-11-2008
 */
public class Player extends DynamicEntity {
	
	/*
	* Constructor of the Player.
	*/
	public Player(String id) {
		super(id);
	}

	/** 
	 * Contains the properties of the <code>Player</code>.
	 */
	protected Hashtable properties;

	/** 
	 * Retrieve the properties of the <code>Player</code>.
	 * @return the properties of the <code>Player</code>.
	 */
	public Hashtable getProperties()  {
		return properties;
	}

	/** 
	 * Apply a <code>Hashtable<code> properties to this <code>Player</code>.
	 * @param theProperties <code>Hashtable<code> properties to aplly
	 */
	public void setProperties(Hashtable theProperties) {
		properties = theProperties;
	}

	/** 
	 * The state of this Player in the Game.
	 */
	protected PlayerState state;

	/** 
	 * Retrieve the state of this Player in the Game.
	 * @return the state of this Player in the Game.
	 */
	public PlayerState getState() {
		return state;
	}

	/** 
	 * Apply a PlayerState to this <code>Player</code>.
	 * @param theState <code>PlayerState<code> stats to aplly.
	 */
	public void setState(PlayerState theState) {
		state = theState;
	}

	/** 
	 * @uml.annotations for <code>currentQuests</code>
	 *     collection_type="Quest"
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
        //protected Set<Quest> currentQuests;

	/** 
	 * @return el currentQuests
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
//	public Set<Quest> getCurrentQuests() {
//		return currentQuests;
//	}

	/** 
	 * @param theCurrentQuests el currentQuests a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
//	public void setCurrentQuests(Set<Quest> theCurrentQuests) {
//		currentQuests = theCurrentQuests;


	/** 
	 * Contains the stats of the <code>Player</code>.
	 */
	private Hashtable stats;

	/** 
	 * Retrieve the <code>Hashtable<code> stats of this <code>Player<code>.
	 * @return the <code>Hashtable<code> stats of this <code>Player<code>.
	 */
	public Hashtable getStats() {
		return stats;
	}

	/** 
	 * Apply a <code>Hashtable<code> stats to this <code>Player</code>.
	 * @param theStats <code>Hashtable<code> stats to aplly
	 */
	public void setStats(Hashtable theStats) {
		stats = theStats;
	}

	/** 
	 * Contains the properties of the <code>Player</code>.
	 */
	private Hashtable properties;

	/** 
	 * Retrieve the properties of the <code>Player</code>.
	 * @return the properties of the <code>Player</code>.
	 */
	public Hashtable getProperties() {
		return properties;
	}

	/** 
	 * Apply a <code>Hashtable<code> properties to this <code>Player</code>.
	 * @param theProperties <code>Hashtable<code> properties to aplly
	 */
	public void setProperties(Hashtable theProperties) {
		properties = theProperties;
	}

	/** 
	 * Retrieve the Property usign the key.
	 * @param key the Key to retrieve the Propertie.
	 * @return the property of this key.
	 */
	public Object getProperty(String key) {
		return this.properties.get(key);
	}

	/** 
	 * Save a Property of this <code>Player</code> whit his key.
	 * @param propertie Property to save.
	 * @param key The Key to retrieve the Property.
	 */
	public void setProperty(Object propertie, String key) {
		this.properties.put(key, propertie);
	}

	
}