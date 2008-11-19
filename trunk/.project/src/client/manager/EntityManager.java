/**
  * <code>EntityManager</code> is responsible for
  * managing all the <code>IEntity</code> in the game world.
  * <p>
  * <code>EntityManager</code> is responsible for all aspects of entity management
  * including entity creation, retrieving and destruction.
  * <p>
  * <code>EntityManager</code> maintains all the entities by their ID number. This
  * allows multiple entities with the same type.
  * <p>
  * <code>EntityManager</code> is a Singleton.
  */
package client.manager;

import java.util.HashMap;
import java.util.logging.Logger;

import client.game.entity.EntityManagerFactory;
import client.game.entity.IEntity;

/** 
 * @author Santiago Michielotto
 * @version Created: 29-10-2008
 */
public class EntityManager {
	
	/**
	 * The <code>Logger</code> instance.
	 */
	protected final Logger logger;
	
	/**
	 * Constructor of <code>EntityManager</code>.
	 */
	protected EntityManager() {
		this.hash=new HashMap<String, IEntity>();
		this.logger = Logger.global;
	}
	
	/**
	 * The <code>EntityManager</code> instance.
	 */
	private static EntityManager instance;
	
	/**
	 * The client side current entity ID number.
	 */
	private static int currentId;

	/**
	 * The <code>HashMap</code> of ID number and <code>IEntity</code> pair.
	 */
	private HashMap<String, IEntity> hash;

	/** 
	 * Retrieve the Singleton instance EntityManager.
	 * @return the instance of the <code>EntityManager<code>.
	 */
	public static EntityManager getInstance() {
		if(EntityManager.instance == null) {
			EntityManager.instance = new EntityManager();
		}
		return EntityManager.instance;
	}

	/** 
	 * Retrieve a especific <code>IEntity<code> of the game.
	 * @param entity The <code>IEntity<code> to be retrieved.
	 */ 
	public IEntity getEntity(IEntity entity) {
			return hash.get(entity.getId());
	}

	/**
	 * Remove the entity.
	 * @param entity The <code>IEntity<code> to be destroyed.
	 */
	public void removeEntity(IEntity entity) {
		hash.remove(Integer.valueOf(entity.getId()));
		if(entity == null) 
		{
			this.logger.fine("Entity to remove is null.");
			return;
		}
		this.logger.fine("Destroyed entity with ID number: " + entity.getId());
	}

	/**
	 * Register the given entity .
	 * @param entity The <code>IEntity</code> to be registered.
	 */
	public void registerEntity(IEntity entity) {
		final String id = entity.getId();
		if(hash.containsKey(id)) {
			this.logger.fine("ID: " + id + " already in use");
			return;
		}
		if(currentId > Integer.valueOf(id)) currentId = Integer.valueOf(id);
		hash.put(id, entity);
	}

	/**
	 * Create an entity in the world.
	 * @return The newly created <code>IEntity</code>.
	 */
	public IEntity createEntity(Class c)
	{
		IEntity temp=EntityManagerFactory.getInstance().createEntity(c, String.valueOf(currentId++));
		this.hash.put(temp.getId(), temp);
		this.logger.fine("Created entity " + temp.toString() + "with ID number: " + temp.getId());
		return temp;
	}
}