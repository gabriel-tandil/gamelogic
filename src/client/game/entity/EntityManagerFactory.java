/**
 * 
 */
package client.game.entity;

import java.util.HashMap;

/**
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class EntityManagerFactory {

	private static EntityManagerFactory instance = null;
	private HashMap<String, IEntityFactory> entityFactorys;

	/**
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected EntityManagerFactory() {
		this.entityFactorys = new HashMap<String, IEntityFactory>();
	}

	/**
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static EntityManagerFactory getInstance() {
		if (EntityManagerFactory.instance == null) {
			EntityManagerFactory.instance = new EntityManagerFactory();
		}
		return EntityManagerFactory.instance;
	}

	/**
	 * Add a Entity Type can be create.
	 * @param entityFactory the Entity Type to add.
	 *            
	 **/
	public void add(IEntityFactory entityFactory) {
		entityFactorys.put(entityFactory.getId(), entityFactory);
	}
	/** 
	 * Create a Entity in the world.
	 * @param id the identifier of the Entity to be created.
	 * @return The IEntity created.
	 */
	public IEntity create(String id) {
		return ((IEntityFactory)entityFactorys.get(id)).createEntity();
	}
}