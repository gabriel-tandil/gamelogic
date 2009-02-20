/**
  * <code>DynamicEntity</code> extends <code>Entity</code> and implements
  * <code>IDynamicEntity</code> to represent an actual dynamic entity in
  * the game world.
  */
package client.game.entity;

import java.io.IOException;

import com.jme.math.Vector3f;
import common.datatypes.Skin;

import com.jme.util.export.InputCapsule;
import com.jme.util.export.JMEExporter;
import com.jme.util.export.JMEImporter;
import com.jme.util.export.OutputCapsule;


/** 
 * @author Critian Calomino
 * @version Created: 20-11-2008
 */
public class DynamicEntity extends Entity implements IDynamicEntity {
	
	protected Vector3f position;
	
	public Vector3f getPosition() {
		return position;
	}
	
	public void setPosition(Vector3f thePosition) {
		position = thePosition;
	}
	
	/** 
	 * El angulo de la <code>DinamicEntity</code>
	 */
	protected Vector3f angle;

	/** 
	 * Retorna el angulo de la <code>DinamicEntity</code>
	 * @return angle de la DynamicEntity
	 */
	public Vector3f getAngle() {
		return angle;
	}

	/** 
	 * Aplica el angulo de la DynamicEntity
	 * @param theAngle el angulo de la DynamicEntity a aplicar.
	 */
	public void setAngle(Vector3f theAngle) {
		angle = theAngle;
	}

	/** 
	 * El skin de la <code>DinamicEntity</code>.
	 */
	protected Skin skin;

	/** 
	 * Retorna el skin de la <code>DinamicEntity</code>
	 * @return skin de la DinamicEntity.
	 */
	public Skin getSkin() {
		return skin;
	}

	/** 
	 * Aplcia el skin de la DynamicEntity
	 * @param theSkin el skin de la DynamicEntity a aplicar.
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
	 * @return mass de la DynamicEntity.
	 */
	public float getMass() {
		return mass;
	}

	/**
	 * Aplica la masa a DynamicEntity
	 * @param mass la masa de DynamicEntity a aplicar.
	 */
	public void setMass(float mass) {
		this.mass = mass;
	}

	/**
	 * El vector <code>Vector3f</code> de la <code>DynamicEntity</code>.
	 **/
	protected Vector3f force;

	/** 
	 * Retorna el <code>Vector3f</code> force de la <code>DynamicEntity</code>.
	 * @return the <code>Vector3f</code> force de la <code>DynamicEntity</code>.
	 */
	public Vector3f getForce() {
		return force;
	}

	/** 
	 * Aplica el <code>Vector3f</code> force a la <code>DynamicEntity</code>.
	 * @param theForce <code>Vector3f</code> a aplicar.
	 */
	public void setForce(Vector3f theForce) {
		force = theForce;
	}
	

	/** 
	 * El vector <code>Vector3f</code> velocity de la <code>DynamicEntity</code>.
	 */
	protected Vector3f velocity;

	/** 
	 * Retorna el <code>Vector3f</code> velocity de la <code>DynamicEntity</code>.
	 * @return <code>Vector3f</code> velocity de la <code>DynamicEntity</code>.
	 */
	public Vector3f getVelocity() {
		return velocity;
	}

	/** 
	 * Aplica un <code>Vector3f</code> velocity a la <code>DynamicEntity</code>.
	 * @param theVelocity <code>Vector3f</code> velocity a aplicar.
	 */
	public void setVelocity(Vector3f theVelocity) {
		velocity = theVelocity;
	}

	/** 
	 * El mundo actual donde esta la DynamicEntity.
	 */
	protected String actualWorld;

	/** 
	 * Retorna el mundo actual dodne esta la DynamicEntity.
	 * @return actualWorld el mundo actual donde esta la DynamicEntity.
	 */
	public String getActualWorld() {
		return actualWorld;
	}

	/** 
	 * Aplica el mundo actual de la DynamicEntity.
	 * @param theActualWorld mundo actual a aplicar a DynamicEntity.
	 */
	public void setActualWorld(String theActualWorld) {
		actualWorld = theActualWorld;
	}
	
	/** 
	 * Constructor de la DynamicEntity.
	 */
	public DynamicEntity(String theTipo) {
		super(theTipo);
	}

	/** 
	 * Permite la exportacion de datos de <code>DynamicEntity</code> a una ubicacion especifica.
	 * @param arg0 <code>JMEExporter</code> para establecer la ubicacion de los datos.
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
	 * Permite la importacion de datos de <code>DynamicEntity</code> a una ubicacion especifica.
	 * @param arg0 <code>JMEImporter</code> para establecer la ubicacion de los datos.
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
			this.mass=ic.readFloat("Mass", 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	/** 
	 * Retorna la clase de la <code>DynamicEntity</code>.
	 * @return clase de la <code>DynamicEntity</code>.
	 */
	public Class getClassTag() {
		return this.getClass();
	}

	
	/** 
	 * Retorna la identity de la DynamicEntity.
	 *  @return identity de la DynamicEntity.
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
 	* @param vector <code>Vector3f</code> velocity.
 	* */
	public void addVelocity(Vector3f vector) {
		this.velocity.addLocal(vector);
	}

	/** 
	 * Agrega la fuerza a esta entidad.
	 * @param vector la fuerza en <code>Vector3f</code>.
	 */
	public void addForce(Vector3f vector) {
		this.force.addLocal(vector);
	}
	
}