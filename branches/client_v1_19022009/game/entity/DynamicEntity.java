/**
 * <code>DynamicEntity</code> extends <code>Entity</code> and implements
 * <code>IDynamicEntity</code> to represent an actual dynamic entity in
 * the game world.
 */
package client.game.entity;

import java.io.IOException;

import client.game.IPersonaje;
import client.game.state.U3dState;
import client.game.task.TaskManagerFactory;
import client.game.task.U3DAddDynamicEntityTask;
import client.game.task.U3DAddDynamicEntityTaskFactory;
import client.game.task.U3DAddPlayerTask;
import client.game.task.U3DAddPlayerTaskFactory;
import client.manager.TaskManager;
import client.manager.ViewManager;

import com.jme.math.Quaternion;
import com.jme.math.Vector3f;

import common.datatypes.PlayerState;
import common.datatypes.Skin;

import com.jme.scene.Node;
import com.jme.scene.Spatial;
import com.jme.util.export.InputCapsule;
import com.jme.util.export.JMEExporter;
import com.jme.util.export.JMEImporter;
import com.jme.util.export.OutputCapsule;
import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;

/**
 * @author Critian Calomino
 * @version Created: 20-11-2008
 */
public class DynamicEntity extends Entity implements IDynamicEntity {

	public void init(Vector3f force, float mass, Vector3f velocity,
			float angle, String actualWorld, Skin theSkin, Vector3f thePosition) {

		TaskManagerFactory.getInstance().add(
				new U3DAddDynamicEntityTaskFactory());
		U3DAddDynamicEntityTask task = (U3DAddDynamicEntityTask) TaskManager
				.getInstance().createTask("8");
		Node root = ((BasicGameState) GameStateManager.getInstance().getChild(
				actualWorld)).getRootNode();
		task.initTask(root, this, angle, position);
		TaskManager.getInstance().enqueue(task);

		this.setAngle(angle);
		this.setActualWorld(actualWorld);
		this.setForce(force);
		this.setMass(mass);
		this.setVelocity(velocity);
		this.setSkin(theSkin);
		this.setPosition(thePosition);
	}

	protected Vector3f position;

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f thePosition) {
		position = thePosition;
		Node view = ((Node) ViewManager.getInstance().getView(this));
		if (view != null) {
			view.setLocalTranslation(thePosition);
			ViewManager.getInstance().markForUpdate(this);
		}
	}

	private IPersonaje p;

	public void setPlayerAvatar(IPersonaje p) {
		this.p = p;
	}

	public void isMoving(boolean state, boolean running, boolean forward) {
		if (p != null)
			p.mover(state, running, forward);
	}

	/**
	 * El angulo de la <code>DinamicEntity</code>
	 */
	protected float angle;

	/**
	 * Retorna el angulo de la <code>DinamicEntity</code>
	 * 
	 * @return angle de la DynamicEntity
	 */
	public float getAngle() {
		return angle;
	}

	/**
	 * Aplica el angulo de la DynamicEntity
	 * 
	 * @param theAngle
	 *            el angulo de la DynamicEntity a aplicar.
	 */
	public void setAngle(float theAngle) {
		angle = theAngle;
		Node view = ((Node) ViewManager.getInstance().getView(this));
		if (view != null) {
			float[] angles = new float[3];
			Vector3f ltras = view.getLocalTranslation();
			view.getLocalRotation().toAngles(angles);
			view.getLocalRotation().fromAngles(angles[0], angles[1] + angle,
					angles[2]);
			view.setLocalTranslation(ltras);
			ViewManager.getInstance().markForUpdate(this);
		}
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
	}

	/**
	 * El skin de la <code>DinamicEntity</code>.
	 */
	protected Skin skin;

	/**
	 * Retorna el skin de la <code>DinamicEntity</code>
	 * 
	 * @return skin de la DinamicEntity.
	 */
	public Skin getSkin() {
		return skin;
	}

	/**
	 * Aplcia el skin de la DynamicEntity
	 * 
	 * @param theSkin
	 *            el skin de la DynamicEntity a aplicar.
	 */
	public void setSkin(Skin theSkin) {
		skin = theSkin;
	}

	/**
	 * La masa de la <code>DynamicEntity</code>.
	 */
	protected Float mass;

	/**
	 * Retorna la masa de la DynamicEntity
	 * 
	 * @return mass de la DynamicEntity.
	 */
	public float getMass() {
		return mass;
	}

	/**
	 * Aplica la masa a DynamicEntity
	 * 
	 * @param mass
	 *            la masa de DynamicEntity a aplicar.
	 */
	public void setMass(float mass) {
		this.mass = mass;
	}

	/**
	 * El vector <code>Vector3f</code> de la <code>DynamicEntity</code>.
	 */
	protected Vector3f force;

	/**
	 * Retorna el <code>Vector3f</code> force de la <code>DynamicEntity</code>.
	 * 
	 * @return the <code>Vector3f</code> force de la
	 *         <code>DynamicEntity</code>.
	 */
	public Vector3f getForce() {
		return force;
	}

	/**
	 * Aplica el <code>Vector3f</code> force a la <code>DynamicEntity</code>.
	 * 
	 * @param theForce
	 *            <code>Vector3f</code> a aplicar.
	 */
	public void setForce(Vector3f theForce) {
		force = theForce;
	}

	/**
	 * El vector <code>Vector3f</code> velocity de la
	 * <code>DynamicEntity</code>.
	 */
	protected Vector3f velocity;

	/**
	 * Retorna el <code>Vector3f</code> velocity de la
	 * <code>DynamicEntity</code>.
	 * 
	 * @return <code>Vector3f</code> velocity de la <code>DynamicEntity</code>.
	 */
	public Vector3f getVelocity() {
		return velocity;
	}

	/**
	 * Aplica un <code>Vector3f</code> velocity a la
	 * <code>DynamicEntity</code>.
	 * 
	 * @param theVelocity
	 *            <code>Vector3f</code> velocity a aplicar.
	 */
	public void setVelocity(Vector3f theVelocity) {
		velocity = theVelocity;
	}
	/**
	 * Constructor de la DynamicEntity.
	 */
	public DynamicEntity(String id) {
		this.setId(id);
		this.setTipo("DynamicEntity");
	}
	
	public DynamicEntity() {
	}

	/**
	 * Permite la exportacion de datos de <code>DynamicEntity</code> a una
	 * ubicacion especifica.
	 * 
	 * @param arg0
	 *            <code>JMEExporter</code> para establecer la ubicacion de los
	 *            datos.
	 */
	public void write(JMEExporter arg0) {
		OutputCapsule oc = arg0.getCapsule(this);
		try {
			oc.write(this.getId(), "ID", null);
			oc.write(this.force.x, "ForceX", 0);
			oc.write(this.force.y, "ForceY", 0);
			oc.write(this.force.z, "ForceZ", 0);
			oc.write(this.velocity.x, "VelocityX", 0);
			oc.write(this.velocity.y, "VelocityY", 0);
			oc.write(this.velocity.z, "VelocityZ", 0);
			oc.write(this.mass, "Mass", 0);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Permite la importacion de datos de <code>DynamicEntity</code> a una
	 * ubicacion especifica.
	 * 
	 * @param arg0
	 *            <code>JMEImporter</code> para establecer la ubicacion de los
	 *            datos.
	 */
	public void read(JMEImporter arg0) {
		InputCapsule ic = arg0.getCapsule(this);
		try {
			this.setId(ic.readString("ID", null));
			this.force.setX(ic.readFloat("ForceX", 0));
			this.force.setY(ic.readFloat("ForceY", 0));
			this.force.setZ(ic.readFloat("ForceZ", 0));
			this.velocity.setX(ic.readFloat("VelocityX", 0));
			this.velocity.setY(ic.readFloat("VelocityY", 0));
			this.velocity.setZ(ic.readFloat("VelocityZ", 0));
			this.mass = ic.readFloat("Mass", 0);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Retorna la clase de la <code>DynamicEntity</code>.
	 * 
	 * @return clase de la <code>DynamicEntity</code>.
	 */
	public Class getClassTag() {
		return this.getClass();
	}

	/**
	 * Retorna la identity de la DynamicEntity.
	 * 
	 * @return identity de la DynamicEntity.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Setea el vector force con cero en la DynamicEntity.
	 */
	public void resetForce() {
		this.force.zero();
	}

	/**
	 * Retorna la velocidad actual de la DynamicEntity.
	 * 
	 * @param vector
	 *            <code>Vector3f</code> velocity.
	 */
	public void addVelocity(Vector3f vector) {
		this.velocity.addLocal(vector);
	}

	/**
	 * Agrega la fuerza a esta entidad.
	 * 
	 * @param vector
	 *            la fuerza en <code>Vector3f</code>.
	 */
	public void addForce(Vector3f vector) {
		this.force.addLocal(vector);
	}

}