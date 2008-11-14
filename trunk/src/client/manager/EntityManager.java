/**
 * 
 */
package client.manager;

import java.util.HashMap;
import java.util.Set;
import client.game.entity.IEntity;

/** 
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class EntityManager {
	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private static int STATIC_ENTITY=1;
	private static int DINAMIC_ENTITY=2;
	
	private int currentId;
	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private HashMap<String,IEntity> hash;

	/**
	 * @return el currentId
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getCurrentId() {
		return currentId;
	}
	
	
	
	/**
	 * @param theCurrentId el currentId a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setCurrentId(Integer theCurrentId) {
		// begin-user-code
		currentId = theCurrentId;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private static EntityManager instance;

	

	
	/**
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static EntityManager getInstance() {
		if(instance==null) instance=new EntityManager();
		return instance;
	}

	/**
	 * @return el hash
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public HashMap getHash() {
		// begin-user-code
		return hash;
		// end-user-code
	}

	/**
	 * @param theHash el hash a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setHash(HashMap theHash) {
		// begin-user-code
		hash = theHash;
		// end-user-code
	}

	/** 
	 * @uml.annotations for <code>ientity</code>
	 *     collection_type="client.game.entity.IEntity"
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<IEntity> ientity;

	/**
	 * @return el ientity
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<IEntity> getIentity() {
		// begin-user-code
		return ientity;
		// end-user-code
	}

	/**
	 * @param theIentity el ientity a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setIentity(Set<IEntity> theIentity) {
		// begin-user-code
		ientity = theIentity;
		// end-user-code
	}

	/**
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected EntityManager() {
		currentId=0;
		hash=new HashMap<String,IEntity>();
	}

	

	/**
	 * @param entity
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public IEntity getEntity(String idEntity,int entityType) {
		String tempKey=idEntity+entityType;
		//Metodo incompleto
		return null;
	}

	/**
	 * @param entity
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void removeEntity(String idEntit) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/**
	 * @param entity
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void registerEntity(IEntity entity) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/**
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public IEntity createEntity(String entityType) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return null;
		// end-user-code
	}
}