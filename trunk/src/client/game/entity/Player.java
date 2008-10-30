/**
 *<code>Player</code> extends <code>DynamicEntity</code> to define
 * the base player in the game.
 */
package client.game.entity;

import java.util.Hashtable;

/** 
 * @author Santiago Michielotto
 * @version Created: 29-10-2008
 */
public class Player extends DynamicEntity {
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
	 * Save a Stat of this <code>Player</code> whit his key.
	 * @param stat Stat to save.
	 * @param key The Key to retrieve the Stat.
	 */
	public void setStat(Object stat, String key) {
		this.stats.put(key,stat);
	}

	/** 
	 * Save a Propertie of this <code>Player</code> whit his key.
	 * @param propertie Propertie to save.
	 * @param key The Key to retrieve the Propertie.
	 */
	public void setPropertie(Object propertie, String key) {
		this.properties.put(key, propertie);
	}

	/** 
	 * Retrieve the Stat usign the key.
	 * @param key the Key to retrieve the Stat.
	 * @return the stat of this key.
	 */
	public Object getStat(String key) {
		return this.stats.get(key);
	}

	/** 
	 * Retrieve the Propertie usign the key.
	 * @param key the Key to retrieve the Propertie.
	 * @return the propertie of this key.
	 */
	public Object getProperties(String key) {
		return this.properties.get(key);
	}
}