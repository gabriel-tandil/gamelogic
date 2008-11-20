/**
 * Esta clase es un singleton que contendrá las diferentes entidades definidas 
 * para el <code>Game<code>. Por medio de esta se pueden agregar, crear y obtener 
 * las entidades del <code>Game<code>. 
 * 
 */
package client.game.entity;

import java.util.Hashtable;
import java.util.Set;

/** 
 * @author Santiago Michielotto
 * @version Created: 19-11-2008
 */
public class EntityManagerFactory {
	/**
	 * La instancia de <code>EntityManagerFactory</code>.
	 */
	private static EntityManagerFactory instance;
	
	/**
	 * HashMap que contiene las diferentes <code>IEntityFactory<code> del Game relacionadas a
	 * sus correspondientes identificadores. 
	 */
	private Hashtable<String,IEntityFactory> has;

	/** 
	 * Retorna el Hashtable de <code>IEntityFactory<code>.
	 * @return el Hashtable de <code>IEntityFactory<code>.
	 */
	public Hashtable<String,IEntityFactory>  getHas() {
		return has;
	}

	/** 
	 * Aplica un theHas al singleton
	 * @param theHas el Hashtable a establecer
	 */
	public void setHas(Hashtable<String,IEntityFactory>  theHas) {
		has = theHas;
	}

	/**
	 * Constructor <code>EntityManagerFactory</code>. Crea la instancia de has.
	 */
	protected EntityManagerFactory() {
		this.has = new Hashtable<String,IEntityFactory>();
	}

	/**
	 * Crea la instancia de <code>EntityManagerFactory</code> por primera 
	 * y única vez.
	 * @return La instancia de <code>EntityManagerFactory</code>.
	 */
	public static EntityManagerFactory getInstance() {
		if(EntityManagerFactory.instance == null) {
			EntityManagerFactory.instance = new EntityManagerFactory();			
		}
		return EntityManagerFactory.instance;
	}

	/** 
	 * Agrega una IEntityFactory para el Game.
	 */
	public void add(IEntityFactory entity) {
		has.put(entity.getId(), entity);
	}

	/** 
	 * Crea una IEntity para el Game.
	 * @return IEntity Creada
	 */
	public IEntity create(String id) {
		return ((IEntityFactory)has.get(id)).createEntity();
	}
}