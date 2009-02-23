/**
 * 
 */
package client.game.entity;

import java.util.HashMap;

/**
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
		String id = entityFactory.getId();
		if (!this.entityFactorys.containsKey(id))
		entityFactorys.put(entityFactory.getId(), entityFactory);
	}
	/** 
	 * Crea una Entity en el mundo
	 * @param id el identificador de la entidad a ser creada.
	 * @return IEntity La entidad creada.
	 */
	public IEntity create(String id, String idEntity) {
		return ((IEntityFactory)entityFactorys.get(id)).createEntity(idEntity);
	}
}