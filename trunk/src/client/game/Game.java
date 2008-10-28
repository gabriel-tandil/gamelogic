/**
 * 
 */
package client.game;

import com.jme.app.BaseGame;
import java.util.Set;
import client.game.state.IGameState;
import client.manager.ViewManager;
import client.manager.TaskManager;
import client.gameEngine.InputManager;
import com.jme.util.Timer;
import com.jmex.game.state.GameStateManager;
import client.gameEngine.PhysicsManager;
import client.manager.EntityManager;
import client.manager.IHudManager;

/** 
 *  Tiene como responsabilidad, inicializar todos los manager una vez que esta conectado. 
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class Game extends BaseGame {
	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Float intervalo;

	/** 
	 * @return el intervalo
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Float getIntervalo() {
		// begin-user-code
		return intervalo;
		// end-user-code
	}

	/** 
	 * @param theIntervalo el intervalo a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setIntervalo(Float theIntervalo) {
		// begin-user-code
		intervalo = theIntervalo;
		// end-user-code
	}

	/** 
	 * @uml.annotations for <code>game</code>
	 *     collection_type="client.game.state.IGameState"
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<IGameState> game;

	/** 
	 * @return el game
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<IGameState> getGame() {
		// begin-user-code
		return game;
		// end-user-code
	}

	/** 
	 * @param theGame el game a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setGame(Set<IGameState> theGame) {
		// begin-user-code
		game = theGame;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private ViewManager viewmanager2;

	/** 
	 * @return el viewmanager2
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public ViewManager getViewmanager2() {
		// begin-user-code
		return viewmanager2;
		// end-user-code
	}

	/** 
	 * @param theViewmanager2 el viewmanager2 a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setViewmanager2(ViewManager theViewmanager2) {
		// begin-user-code
		viewmanager2 = theViewmanager2;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private TaskManager taskmanager;

	/** 
	 * @return el taskmanager
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public TaskManager getTaskmanager() {
		// begin-user-code
		return taskmanager;
		// end-user-code
	}

	/** 
	 * @param theTaskmanager el taskmanager a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setTaskmanager(TaskManager theTaskmanager) {
		// begin-user-code
		taskmanager = theTaskmanager;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private InputManager inputmanager;

	/** 
	 * @return el inputmanager
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public InputManager getInputmanager() {
		// begin-user-code
		return inputmanager;
		// end-user-code
	}

	/** 
	 * @param theInputmanager el inputmanager a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setInputmanager(InputManager theInputmanager) {
		// begin-user-code
		inputmanager = theInputmanager;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Timer timer;

	/** 
	 * @return el timer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Timer getTimer() {
		// begin-user-code
		return timer;
		// end-user-code
	}

	/** 
	 * @param theTimer el timer a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setTimer(Timer theTimer) {
		// begin-user-code
		timer = theTimer;
		// end-user-code
	}

	/** 
	 * @uml.annotations for <code>gamestatemanager</code>
	 *     collection_type="com.jmex.game.state.GameStateManager"
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<GameStateManager> gamestatemanager;

	/** 
	 * @return el gamestatemanager
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<GameStateManager> getGamestatemanager() {
		// begin-user-code
		return gamestatemanager;
		// end-user-code
	}

	/** 
	 * @param theGamestatemanager el gamestatemanager a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setGamestatemanager(Set<GameStateManager> theGamestatemanager) {
		// begin-user-code
		gamestatemanager = theGamestatemanager;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private PhysicsManager physicsmanager;

	/** 
	 * @return el physicsmanager
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public PhysicsManager getPhysicsmanager() {
		// begin-user-code
		return physicsmanager;
		// end-user-code
	}

	/** 
	 * @param thePhysicsmanager el physicsmanager a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setPhysicsmanager(PhysicsManager thePhysicsmanager) {
		// begin-user-code
		physicsmanager = thePhysicsmanager;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private EntityManager entitymanager;

	/** 
	 * @return el entitymanager
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public EntityManager getEntitymanager() {
		// begin-user-code
		return entitymanager;
		// end-user-code
	}

	/** 
	 * @param theEntitymanager el entitymanager a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setEntitymanager(EntityManager theEntitymanager) {
		// begin-user-code
		entitymanager = theEntitymanager;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private IHudManager ihudmanager;

	/** 
	 * @return el ihudmanager
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public IHudManager getIhudmanager() {
		// begin-user-code
		return ihudmanager;
		// end-user-code
	}

	/** 
	 * @param theIhudmanager el ihudmanager a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setIhudmanager(IHudManager theIhudmanager) {
		// begin-user-code
		ihudmanager = theIhudmanager;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public abstract void updateHuds();

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected abstract void initCamera();

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected abstract void initHotKeys();

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected void initConverters() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected abstract void initManagers();

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected abstract void initWindow();
}