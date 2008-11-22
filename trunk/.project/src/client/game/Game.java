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
 * @author Martin Sabatini - Sebastian Bisbal
 * @version Fecha de creación: 06-11-2008 
 *  Tiene como responsabilidad, inicializar todos los manager una vez que esta conectado. 
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class Game extends BaseGame {
	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected float intervalo;

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
	private ViewManager viewManager;

	/** 
	 * @return el viewmanager
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public ViewManager getViewManager() {
		// begin-user-code
		return viewManager;
		// end-user-code
	}

	/** 
	 * @param theViewmanager el viewmanager2 a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setViewManager(ViewManager theViewmanager) {
		// begin-user-code
		viewManager = theViewmanager;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected TaskManager taskManager;

	/** 
	 * @return el taskmanager
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public TaskManager getTaskManager() {
		// begin-user-code
		return taskManager;
		// end-user-code
	}

	/** 
	 * @param theTaskmanager el taskmanager a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setTaskManager(TaskManager theTaskmanager) {
		// begin-user-code
		taskManager = theTaskmanager;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected InputManager inputManager;

	/** 
	 * @return el inputmanager
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public InputManager getInputManager() {
		// begin-user-code
		return inputManager;
		// end-user-code
	}

	/** 
	 * @param theInputmanager el inputmanager a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setInputManager(InputManager theInputmanager) {
		// begin-user-code
		inputManager = theInputmanager;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected Timer timer;

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
	 * @uml.annotations for <code>gameStateManager</code>
	 *     collection_type="com.jmex.game.state.GameStateManager"
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected GameStateManager gameStateManager;

	/** 
	 * @return el gameStateManager
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public GameStateManager getGameStateManager() {
		// begin-user-code
		return gameStateManager;
		// end-user-code
	}

	/** 
	 * @param theGameStateManager el gameStateManager a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setGameStateManager(GameStateManager theGameStateManager) {
		// begin-user-code
		gameStateManager = theGameStateManager;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected PhysicsManager physicsManager;

	/** 
	 * @return el physicsmanager
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public PhysicsManager getPhysicsManager() {
		// begin-user-code
		return physicsManager;
		// end-user-code
	}

	/** 
	 * @param thePhysicsmanager el physicsmanager a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setPhysicsManager(PhysicsManager thePhysicsmanager) {
		physicsManager = thePhysicsmanager;
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private EntityManager entityManager;

	/** 
	 * @return el entitymanager
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/** 
	 * @param theEntitymanager el entitymanager a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setEntityManager(EntityManager theEntitymanager) {
		entityManager = theEntitymanager;
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private IHudManager hudManager;

	/** 
	 * @return el ihudmanager
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public IHudManager getHudManager() {
		return hudManager;
	}

	/** 
	 * @param theIhudmanager el ihudmanager a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setHudManager(IHudManager theIhudmanager) {
		hudManager = theIhudmanager;
	}

	/** 
	 * Actualiza los Head Up Display
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public abstract void updateHuds();

	/** 
	 * inicializa la cámara, se setea una posición inicial o puede ser 
	 * asignada para que siga un determinado elemento del juego 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected abstract void initCamera();

	/** 
	 * define el comportamiento de las teclas asignadas para realizar 
	 * tareas predefinidas
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected abstract void initHotKeys();

	/** 
	 * 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected void initConverters() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 * inicializa todos lo managers 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected abstract void initManagers();

	/** 
	 * configura configuran las propiedades de la pantalla principal del juego
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected abstract void initWindow();
}