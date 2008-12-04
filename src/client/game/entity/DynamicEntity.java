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
	
	/** 
	 * The poition of the DinamicEntity
	 */
	protected Vector3f position;

	/** 
	 * Retrieve el position of the DinamicEntity.
	 * @return el position of the DinamicEntity.
	 */
	public Vector3f getPosition() {
		return position;
	}

	/** 
	 * Apply a position to DinamicEntity
	 * @param thePosition the position to apply.
	 */
	public void setPosition(Vector3f thePosition) {
		position = thePosition;
	}

	/** 
	 * The angle of the DynamicEntity
	 */
	protected Vector3f angle;

	/** 
	 * Retrieve the angle of the DynamicEntity.
	 * @return the angle of the DynamicEntity
	 */
	public Vector3f getAngle() {
		return angle;
	}

	/** 
 	 * Apply an angle to this DynamicEntity.
	 * @param theAngle the angle of the DynamicEntity to apply.
	 */
	public void setAngle(Vector3f theAngle) {
		angle = theAngle;
	}

	/** 
	 * The skin of this DinamicEntity.
	 */
	protected Skin skin;

	/** 
	 * Retrieve the skin of this DinamicEntity.
	 * @return the skin of this DinamicEntity.
	 */
	public Skin getSkin() {
		return skin;
	}

	/** 
 	 * Apply a skin to this DynamicEntity.
	 * @param theSkin the skin of the DynamicEntity to apply.
	 */
	public void setSkin(Skin theSkin) {
		skin = theSkin;
	}

	/** 
	 * The mass value of this <code>DynamicEntity</code>.
	 */
	protected Float mass;

	/**
	 * Retrieve the mass of this DynamicEntity.
	 * @return the mass of this DynamicEntity.
	 */
	public float getMass() {
		return mass;
	}

	/**
	 * Apply a mass to this DynamicEntity.
	 * @param mass the mass of the DynamicEntity to apply.
	 */
	public void setMass(float mass) {
		this.mass = mass;
	}

	/**
	 * The <code>Vector3f</code> force currently in effect.
	 **/
	protected Vector3f force;

	/** 
	 * Retrieve the <code>Vector3f</code> force of this <code>DynamicEntity</code>.
	 * @return the <code>Vector3f</code> force of this <code>DynamicEntity</code>.
	 */
	public Vector3f getForce() {
		return force;
	}

	/** 
	 * Apply a <code>Vector3f</code> force to this <code>DynamicEntity</code>.
	 * @param theForce <code>Vector3f</code> force to apply.
	 */
	public void setForce(Vector3f theForce) {
		force = theForce;
	}
	

	/** 
         * The current <code>Vector3f</code> velocity of this <code>DynamicEntity</code>.
	 */
	protected Vector3f velocity;

	/** 
	 * Retrieve the <code>Vector3f</code> velocity of this <code>DynamicEntity</code>.
	 * @return <code>Vector3f</code> velocity of this <code>DynamicEntity</code>.
	 */
	public Vector3f getVelocity() {
		return velocity;
	}

	/** 
	 * Apply a <code>Vector3f</code> velocity to this <code>DynamicEntity</code>.
	 * @param theVelocity <code>Vector3f</code> velocity to apply.
	 */
	public void setVelocity(Vector3f theVelocity) {
		velocity = theVelocity;
	}

	/** 
	 * the actual word where is the DynamicEntity.
	 */
	protected String actualWorld;

	/** 
	 * Retrieve the actual word where is the DynamicEntity.
	 * @return the actual word where is the DynamicEntity.
	 */
	public String getActualWorld() {
		return actualWorld;
	}

	/** 
	 * Apply a Actual Word to this DynamicEntity.
	 * @param theActualWorld to apply of this DynamicEntity.
	 */
	public void setActualWorld(String theActualWorld) {
		actualWorld = theActualWorld;
	}
	
	/** 
	 * Constructor of the DynamicEntity.
	 */
	public DynamicEntity(String theTipo) {
		super(theTipo);
	}

	/** 
	 * Allow export the <code>DynamicEntity</code> data to a specific location.
	 * @param arg0 <code>JMEExporter</code> to set the save location of data.
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
	 * Allow import the <code>DynamicEntity</code> data from a specific location.
	 * @param arg0 <code>JMEImporter</code> to set the save location of data.
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
	 * Retrieve the Java Class of this <code>DynamicEntity</code>.
	 * @return the Java Class of this <code>DynamicEntity</code>.
	 */
	public Class getClassTag() {
		return this.getClass();
	}

	
	/** 
	 *  Retrieve the identity of this DynamicEntity.
	 *  @return the identity of this DynamicEntity.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Clear the force acting on this DynamicEntity.
	 */
	public void resetForce() {
		this.force.zero();
	}

	/**
	* Retrieve the current velocity of this dynamic entity.
 	* @param vector <code>Vector3f</code> velocity.
 	* */
	public void addVelocity(Vector3f vector) {
		this.velocity.addLocal(vector);
	}

	/** 
	 * Add the given force to this entity.
	 * @param vector The force in <code>Vector3f</code> form.
	 */
	public void addForce(Vector3f vector) {
		this.force.addLocal(vector);
	}
	
}