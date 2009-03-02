/**
  * <code>EntityManager</code> es responsable del manejo de todos los
  *  <code>IEntity</code> en el juego.
  * <p>
  * <code>EntityManager</code>es responsable de todos los aspectos del "management" de las entidades
  *  incluyendo la creación, recuperación y destrucción de una entidad
  * <p>
  * <code>EntityManager</code>mantiene todas las entidades por su número ID. Esto permite 
  * que haya múltiples entidades con el mismo tipo.
  * <p>
  * <code>EntityManager</code> es un Singleton.
  */
package client.manager;

import java.util.HashMap;
import java.util.Set;

import client.game.entity.EntityManagerFactory;
import client.game.entity.IEntity;
import client.game.view.IView;
import client.game.view.ViewFactoryManager;

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
	public IEntity createEntity(String factoryId)
	{
		IEntity entity = EntityManagerFactory.getInstance().create(factoryId);
		if(entity != null){
//			registerEntity(entity);
			return entity;
		}
		return null;
	}

}
	