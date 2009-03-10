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
import java.util.Iterator;
import java.util.Set;

import com.jme.scene.Node;
import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;

import client.game.entity.Entity;
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
			return hash.get(entity.getId());

	}
	
	public IEntity getEntity(String id) {
		return hash.get(id);
	}


	/**
	 * Remove the entity.
	 * @param entity The <code>IEntity<code> to be destroyed.
	 */
	public void removeEntity(IEntity entity) {
		if(entity!=null)
		{
			Node root =	((BasicGameState) GameStateManager.getInstance().getChild(entity.getActualWorld())).getRootNode();
			root.getChild(entity.getId());
		}
		hash.remove(entity.getId());
	}
	
	public void removeEntity(String id) {
		Entity e= (Entity)getEntity(id);
		/*if (e!=null)
		{
			BasicGameState actualState = (BasicGameState) GameStateManager.getInstance().getChild(e.getActualWorld());
			Node root = actualState.getRootNode();
			root.getChild(id).removeFromParent();
		}*/
		hash.remove(id);
	}

	/**
	 * Register the given entity .
	 * @param entity The <code>IEntity</code> to be registered.
	 */
	public void registerEntity(IEntity entity) {
		if(hash.containsKey(entity.getId())) {
			return;
		}
		hash.put(entity.getId(), entity);
	}

	/**
	 * Create an entity in the world.
	 * @return The newly created <code>IEntity</code>.
	 */
	public IEntity createEntity(String factoryId, String id)
	{
		if(!hash.containsKey(id))
		{
			IEntity entity = EntityManagerFactory.getInstance().create(factoryId, id);
			if(entity != null){
				registerEntity(entity);
				return entity;
			}
		}
		return null;
	}
	
	public void removeAll()
	{
		hash.clear();
	}
}
	