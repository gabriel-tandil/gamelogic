/**
 * 
 */
package client.game.controller;

import java.util.Hashtable;
import java.util.Set;

import client.game.Game;
import client.game.entity.Entity;
import client.game.entity.IDynamicEntity;

/** 
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class ControllerManagerFactory {
	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Hashtable atributo1;

	/**
	 * @return el atributo1
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Hashtable getAtributo1() {
		// begin-user-code
		return atributo1;
		// end-user-code
	}

	/**
	 * @param theAtributo1 el atributo1 a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setAtributo1(Hashtable theAtributo1) {
		// begin-user-code
		atributo1 = theAtributo1;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private static ControllerManagerFactory instance;

	
	

	/**
	 * @param theInstance el instance a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static void setInstance(ControllerManagerFactory theInstance) {
		// begin-user-code
		instance = theInstance;
		// end-user-code
	}

	/** 
	 * @uml.annotations for <code>icontrollerfactory</code>
	 *     collection_type="client.game.controller.IControllerFactory"
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<IControllerFactory> icontrollerfactory;

	/**
	 * @return el icontrollerfactory
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<IControllerFactory> getIcontrollerfactory() {
		// begin-user-code
		return icontrollerfactory;
		// end-user-code
	}

	/**
	 * @param theIcontrollerfactory el icontrollerfactory a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setIcontrollerfactory(
			Set<IControllerFactory> theIcontrollerfactory) {
		// begin-user-code
		icontrollerfactory = theIcontrollerfactory;
		// end-user-code
	}

	/**
	 * @param Parámetro1
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void add(IControllerFactory Parámetro1) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 * @param id Es el identificador de la tarea a ser creada.
	 * @param c el class de la clase que quiero instanciar
	 * @param game el juego
	 * @return El controller que fue creado.
	 */
	@SuppressWarnings("unchecked")
	protected Controller createController(Class<Controller> c, Game game,String id) {
		//return ((ITaskFactory)tasks.get(id)).createTask(id, game);		
	
		Controller co=null;
		Class<Controller> claseController;
	try {
		claseController=(Class<Controller>) Class.forName(c.getName());
		co=(Controller)claseController.newInstance();
		co.initializeController(id, game);
	} catch (Exception ex) {
		// TODO Auto-generated catch block
//		logger.log.Level.FATAL,"Error al crear la instancia de la clase "+c.getName());

	}
	return co;
	}
	/**
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static ControllerManagerFactory getInstance() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return null;
		// end-user-code
	}
}