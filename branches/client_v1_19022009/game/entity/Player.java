/**
 *<code>Player</code> extends <code>DynamicEntity</code> to define
 * the base player in the game.
 */
package client.game.entity;

import java.util.HashMap;
import java.util.Hashtable;

import client.game.task.TaskManagerFactory;
import client.game.task.U3DAddPlayerTask;
import client.game.task.U3DAddPlayerTaskFactory;
import client.manager.EntityManager;
import client.manager.TaskManager;

import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;
import common.datatypes.PlayerState;
import common.datatypes.Skin;

/** 
 * Extiende de DynamicEntity para definir el jugador base en el juego.
 * @author Cristian Calomino
 * @version Created: 20-11-2008
 */
public class Player extends DynamicEntity {
	
	/**
	* Constructor de la clase Player.
	*/
	public Player(String id) {
		this.setId(id);
		this.setTipo("Player");
	}
	
	/**
	 * Inicializa la clase Player con sus parametros
	 * @param id identificador
	 * @param force vector de la fuerza de la entidad
	 * @param mass vector de la masa de la entidad
	 * @param Properties
	 * @param stats
	 * @param velocity
	 * @param angle
	 * @param actualWorld
	 * @param theSkin
	 * @param theState
	 */
	public void initPlayer(Vector3f force,float mass,HashMap<String,Object> Properties,
			Hashtable<String,Object> stats,Vector3f velocity, float  angle, String actualWorld,
			Skin theSkin, PlayerState theState, Vector3f thePosition) {

		TaskManagerFactory.getInstance().add(new U3DAddPlayerTaskFactory());
		U3DAddPlayerTask task = (U3DAddPlayerTask) TaskManager
		.getInstance().createTask("8");
		Node root =	((BasicGameState) GameStateManager.getInstance().getChild(actualWorld)).getRootNode();
		task.initTask(root, this, angle, thePosition, actualWorld);
		TaskManager.getInstance().enqueue(task);
		
		
		this.setAngle(angle);
		this.setActualWorld(actualWorld);
		this.setForce(force);
		this.setMass(mass);
		this.setVelocity(velocity);
		this.setSkin(theSkin);
		this.setPosition(thePosition);
		this.setProperties(Properties);
		this.setStats(stats);
		this.setState(theState);
	}

	/** 
	 * Contiene las propiedades de un <code>Player</code>.
	 */
	protected HashMap properties;

	/** 
	 * Retorna las propiedades de un <code>Player</code>.
	 * @return properties Propiedades de un <code>Player</code>.
	 */
	public HashMap getProperties()  {
		return properties;
	}

	/** 
	 * Aplica las propiedades <code>Hashtable<code> al <code>Player</code>.
	 * @param theProperties propiedades <code>Hashtable<code> a aplicar.
	 */
	public void setProperties(HashMap theProperties) {
		properties = theProperties;
	}
	
	/** 
	 * Contiene el estado del <code>Player</code>.
	 */
	private Hashtable<String,Object> stats;

	/** 
	 * Retorna el estado <code>Hashtable<code> del <code>Player<code>.
	 * @return stats estado <code>Hashtable<code> del <code>Player<code>.
	 */
	public Hashtable<String,Object> getStats() {
		return stats;
	}

	/** 
	 * Aplica un estado <code>Hashtable<code> al jugador.
	 * @param theStats estado <code>Hashtable<code> a aplicar.
	 */
	public void setStats(Hashtable<String,Object> theStats) {
		stats = theStats;
	}


	/** 
	 * Retorna las propiedades usando una clave key
	 * @param key La clave para retornar la porpiedad.
	 * @return Object propiedad a ser retornada segun la clave.
	 */
	public Object getProperty(String key) {
		return this.properties.get(key);
	}


	/** 
	 * Guarda una propiedad del <code>Player</code> con su clave key
	 * @param propertie Propiedad a ser guardada. 
	 * @param key clave para retornar la propiedad.
	 */
	public void setProperty(Object propertie, String key) {
		this.properties.put(key, propertie);
	}

	
}