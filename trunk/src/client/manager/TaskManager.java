/**
 * 
 */
package client.manager;

import java.util.concurrent.ConcurrentLinkedQueue;
import client.game.Game;
import java.util.Set;
import client.game.task.ITask;

/** 
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class TaskManager {
	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private ConcurrentLinkedQueue listSubmitedTask;

	/** 
	 * @return el listSubmitedTask
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public ConcurrentLinkedQueue getListSubmitedTask() {
		// begin-user-code
		return listSubmitedTask;
		// end-user-code
	}

	/** 
	 * @param theListSubmitedTask el listSubmitedTask a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setListSubmitedTask(ConcurrentLinkedQueue theListSubmitedTask) {
		// begin-user-code
		listSubmitedTask = theListSubmitedTask;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private ConcurrentLinkedQueue listTaskQueue;

	/** 
	 * @return el listTaskQueue
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public ConcurrentLinkedQueue getListTaskQueue() {
		// begin-user-code
		return listTaskQueue;
		// end-user-code
	}

	/** 
	 * @param theListTaskQueue el listTaskQueue a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setListTaskQueue(ConcurrentLinkedQueue theListTaskQueue) {
		// begin-user-code
		listTaskQueue = theListTaskQueue;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Game game;

	/** 
	 * @return el game
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Game getGame() {
		// begin-user-code
		return game;
		// end-user-code
	}

	/** 
	 * @param theGame el game a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setGame(Game theGame) {
		// begin-user-code
		game = theGame;
		// end-user-code
	}

	/** 
	 * @uml.annotations for <code>itask</code>
	 *     collection_type="client.game.task.ITask"
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<ITask> itask;

	/** 
	 * @return el itask
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<ITask> getItask() {
		// begin-user-code
		return itask;
		// end-user-code
	}

	/** 
	 * @param theItask el itask a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setItask(Set<ITask> theItask) {
		// begin-user-code
		itask = theItask;
		// end-user-code
	}

	/** 
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static TaskManager getInstance() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return null;
		// end-user-code
	}

	/** 
	 * @param task
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public abstract void createTask(ITask task);

	/** 
	 * @param Task
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void enqueue(ITask Task) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 * @param task
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void submit(ITask task) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void update() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}
}