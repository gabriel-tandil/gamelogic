/**
 * 
 */
package client.game.entity;


/** 
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class EntityManagerFactory {
	
	private static EntityManagerFactory instance=null;
	
	/**
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected EntityManagerFactory() {
		// begin-user-code
		// TODO Apéndice de constructor generado automáticamente
		// end-user-code
	}

	/**
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static EntityManagerFactory getInstance() {
		if(EntityManagerFactory.instance == null) {
			EntityManagerFactory.instance = new EntityManagerFactory();			
		}
		return EntityManagerFactory.instance;
	}

	/** 
	 * @param id Es el identificador de la tarea a ser creada.
	 * @param c el class de la clase que quiero instanciar
	 * @param game el juego
	 * @return La entidad que fue creada.
	 */
	@SuppressWarnings("unchecked")
	public Entity createEntity(Class<Entity> c, String id) {
		Entity e=null;
		Class<Entity> claseEntity;
	try {
		claseEntity=(Class<Entity>) Class.forName(c.getName());
		e=(Entity)claseEntity.newInstance();
		e.initializeEntity(id);
	} catch (Exception ex) {
		// TODO Auto-generated catch block
//		logger.log.Level.FATAL,"Error al crear la instancia de la clase "+c.getName());

	}
	return e;
	}
}