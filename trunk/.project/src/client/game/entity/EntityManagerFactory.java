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
	 * 
	 * @param task
	 *            Es una nueva tarea definida.
	 */
	public void add(IEntityFactory entityFactory) {
		entityFactorys.put(entityFactory.getId(), entityFactory);
	}
	/** 
	 * @param id Es el identificador de la tarea a ser creada.
	 * @return La tarea que fue creada.
	 */
	public IEntity createEntity(String id) {
		return ((IEntityFactory)entityFactorys.get(id)).createEntity(id);
	}
}