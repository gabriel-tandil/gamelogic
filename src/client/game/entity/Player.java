/**
 *<code>Player</code> extends <code>DynamicEntity</code> to define
 * the base player in the game.
 */
package client.game.entity;

import java.util.Hashtable;

import client.game.task.ITask;

import com.jme.math.Vector3f;
import common.datatypes.PlayerState;
import common.datatypes.Skin;
import common.messages.MessageFactory;
import common.messages.notify.MsgChangePlayerState;
import common.messages.notify.MsgRotate;

/** 
 * @author Cristian Calomino
 * @version Created: 20-11-2008
 */
public class Player extends DynamicEntity {
	
	/**
	* Constructor de la clase Player.
	*/
	public Player(String theTipo) {
		super(theTipo);
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
	public void initPlayer(String id,Vector3f force,float mass,Hashtable<String,Object> Properties,
			Hashtable<String,Object> stats,Vector3f velocity, Vector3f  angle, String actualWorld, Skin theSkin, PlayerState theState) {
		this.setId(id);
		this.setAngle(angle);
		this.setActualWorld(actualWorld);
		this.setForce(force);
		this.setMass(mass);
		this.setProperties(Properties);
		this.setStats(stats);
		this.setVelocity(velocity);
		this.setSkin(theSkin);
		this.setState(theState);
	}

	/** 
	 * Contiene las propiedades de un <code>Player</code>.
	 */
	protected Hashtable properties;

	/** 
	 * Retorna las propiedades de un <code>Player</code>.
	 * @return properties Propiedades de un <code>Player</code>.
	 */
	public Hashtable getProperties()  {
		return properties;
	}

	/** 
	 * Aplica las propiedades <code>Hashtable<code> al <code>Player</code>.
	 * @param theProperties propiedades <code>Hashtable<code> a aplicar.
	 */
	public void setProperties(Hashtable theProperties) {
		properties = theProperties;
	}


	/** 
	 * El estado del jugador en el juego. 
	 */
	protected PlayerState state;


	/** 
	 * Retorna el estado del <code>Player</code> en el juego
	 * @return state estado del <code>Player</code>.
	 */
	public PlayerState getState() {
		return state;
	}

	/** 
	 * Aplica un estado PlayerState al <code>Player</code>.
	 * @param theState estado <code>PlayerState<code> a aplicar.
	 */
	public void setState(PlayerState theState) {
		state = theState;
		
		//mensaje al servidor
		MsgChangePlayerState msg = MessageFactory.getInstance().createMessage(MsgTypes.MSG_CHANGE_PLAYER_STATE_SEND_TYPE);
		msg.setIdPlayer(this.getId());
		msg.setNewState(theState);
		ITask task = TaskCommFactory.getInstance().createTask(msg);
		TaskManager.getInstance().submit(task);	
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