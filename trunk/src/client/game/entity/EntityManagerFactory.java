/**
 * 
 */
package client.game.entity;

import java.util.HashMap;

/**
 * Fábrica que se encarga de generar entidades para los tipos de entidades, 
 * cada una de estas distintas Factory son seteadas o agregadas al momento de iniciar la aplicación. 
 * @author Mara
 */
public class EntityManagerFactory {

	/**
	 * La instancia de <code>EntityManagerFactory</code>
	 */
	private static EntityManagerFactory instance = null;
	
	/**
	 * HashMap que contiene las diferentes <code>entityFactorys<code> relacionadas a
	 * sus correspondientes identificadores. 
	 */
	private HashMap<String, IEntityFactory> entityFactorys;

	/**
	 * Constructor <code>EntityManagerFactory</code>.
	 */
	protected EntityManagerFactory() {
		this.entityFactorys = new HashMap<String, IEntityFactory>();
	}

	/**
	 * Crea la instancia de <code>EntityManagerFactory</code> por primera 
	 * y única vez.
	 * @return La instancia de <code>EntityManagerFactory</code>.
	 */
	public static EntityManagerFactory getInstance() {
		if (EntityManagerFactory.instance == null) {
			EntityManagerFactory.instance = new EntityManagerFactory();
		}
		return EntityManagerFactory.instance;
	}

	/**
	 * Agrega un <code>IEntityFactory</code> 
	 * @param entityFactory <code>IEntityFactory</code> a ser agregado.
	 */
	public void add(IEntityFactory entityFactory) {
		entityFactorys.put(entityFactory.getId(), entityFactory);
	}
	/** 
	 * Crea una Entity en el mundo
	 * @param id el identificador de la entidad a ser creada.
	 * @return IEntity La entidad creada.
	 */
	public IEntity create(String id) {
		return ((IEntityFactory)entityFactorys.get(id)).createEntity();
	}
}