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
import java.util.Set;
import client.game.entity.IEntity;

/** 
 * @author Santiago Michielotto
 * @version Created: 20-11-2008
 */
public class EntityManager {

	/**
	 * The client side current entity ID number.
	 */
	private static int currentId;

	/** 
	 * Retrieve the current identity of the Entities of the Game.
	 * @return the current identity of the Entities of the Game.
	 */
	public Integer getCurrentId() {
		return currentId;
	}

	/** 
	 * @param theCurrentId to apply to the EntityManager singleton.
	 * Apply a current identity to the EntityManager singleton.
	 */
	public void setCurrentId(Integer theCurrentId) {
		currentId = theCurrentId;
	}

	/**
	 * The <code>EntityManager</code> instance.
	 */
	private static EntityManager instance;

	/**
	 * The <code>HashMap</code> of ID number and <code>IEntity</code> pair.
	 */
	private HashMap<String,IEntity> hash;

	/** 
	 * Retrieve the Entity HasMap of of the Game.
	 * @return the Entity HasMap of of the Game.
	public HashMap<String,IEntity> getHash() {
		return hash;
	}
	
	/** 
	 * @param theHash to apply to the EntityManager singleton.
	 * Apply a theHash to the EntityManager singleton.
	 */
	public void setHash(HashMap<String,IEntity> theHash) {
		hash = theHash;
	}

	/**
	 * Constructor of <code>EntityManager</code>.
	 */
	protected EntityManager() {
		this.hash=new HashMap();
	}

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
	 * @return 
	 */ 
	public IEntity getEntity(IEntity entity) {
			return hash.get(Integer.valueOf(entity.getId()));

	}

	/**
	 * Remove the entity.
	 * @param entity The <code>IEntity<code> to be destroyed.
	 */
	public void removeEntity(IEntity entity) {
		hash.remove(Integer.valueOf(entity.getId()));
	}

	/**
	 * Register the given entity .
	 * @param entity The <code>IEntity</code> to be registered.
	 */
	public void registerEntity(IEntity entity) {
		final int id = Integer.valueOf(entity.getId());
		if(hash.containsKey(id)) {
			return;
		}
		if(currentId > id) currentId = id;
		hash.put(id+"", entity);
	}

	/**
	 * Create an entity in the world.
	 * @return The newly created <code>IEntity</code>.
	 */
	public IEntity createEntity()
	{
		//************Llamar al factory
		/*IEntity entity =new Entity();
		
		this.entities.put(Integer.valueOf(id), entity);
		this.logger.fine("Created entity " + enumn.toString() + "with ID number: " + id);
		return entity;*/
		return null;
	}

}
	