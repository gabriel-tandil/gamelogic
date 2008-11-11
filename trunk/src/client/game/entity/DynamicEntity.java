/**
  * <code>DynamicEntity</code> extends <code>Entity</code> and implements
  * <code>IDynamicEntity</code> to represent an actual dynamic entity in
  * the game world.
  */
package client.game.entity;

import java.io.IOException;

import com.jme.math.Vector3f;
import com.jme.util.export.InputCapsule;
import com.jme.util.export.JMEExporter;
import com.jme.util.export.JMEImporter;
import com.jme.util.export.OutputCapsule;

/** 
 * @author Santiago Michielotto
 * @version Created: 29-10-2008
 */
public class DynamicEntity extends Entity implements IDynamicEntity {
	/** 
	 * The mass value of this <code>DynamicEntity</code>.
	 */
	private float mass;

	/** 
	 * Retrieve the mass value of this <code>DynamicEntity</code>.
	 * @return the mass value of this <code>DynamicEntity</code>.
	 */
	public float getMass() {
		return mass;
	}

	/** 
	 * Assign a mass value to the <code>DynamicEntity</code>.
	 * @param theMass value to assign.
	 */
	public void setMass(float theMass) {
		mass = theMass;
	}

	/**
	 * The <code>Vector3f</code> force currently in effect.
	 * */
	private Vector3f force;

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
	private Vector3f velocity;

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
	 * Allow export the <code>DynamicEntity</code> data to a specific location.
	 * @param arg0 <code>JMEExporter</code> to set the save location of data.
	 */
	public void write(JMEExporter arg0) throws IOException {
		OutputCapsule oc = arg0.getCapsule(this);
		oc.write(this.getId(), "ID", null);
		oc.write(this.getTipo(),"Type",null);
		oc.write(this.force.x, "ForceX", 0);
		oc.write(this.force.y, "ForceY", 0);
		oc.write(this.force.z, "ForceZ", 0);
		oc.write(this.velocity.x, "VelocityX", 0);
		oc.write(this.velocity.y, "VelocityY", 0);
		oc.write(this.velocity.z, "VelocityZ", 0);
		oc.write(this.mass, "Mass", 0);
	}

	/** 
	 * Allow import the <code>DynamicEntity</code> data from a specific location.
	 * @param arg0 <code>JMEImporter</code> to set the save location of data.
	 */
	public void read(JMEImporter arg0)throws IOException {
		InputCapsule ic = arg0.getCapsule(this);
		this.setId(ic.readString("ID", null));
		this.setTipo(ic.readString("Type", null));
		this.force.setX(ic.readFloat("ForceX", 0));
		this.force.setY(ic.readFloat("ForceY", 0));
		this.force.setZ(ic.readFloat("ForceZ", 0));
		this.velocity.setX(ic.readFloat("VelocityX", 0));
		this.velocity.setY(ic.readFloat("VelocityY", 0));
		this.velocity.setZ(ic.readFloat("VelocityZ", 0));
		this.mass=ic.readFloat("Mass", 0);
	}

	/** 
	 * Retrieve the Java Class of this <code>DynamicEntity</code>.
	 * @return the Java Class of this <code>DynamicEntity</code>.
	 */
	public Class getClassTag() {
		return this.getClass();
	}

	/**
	* Retrieve the current velocity of this dynamic entity.
 	* @param vector <code>Vector3f</code> velocity.
 	* */
	public void addVelocity(Vector3f vector) {
		this.velocity.addLocal(velocity);
	}

	/** 
	 * Add the given force to this entity.
	 * @param vector The force in <code>Vector3f</code> form.
	 */
	public void addForce(Vector3f vector) {
		this.force.addLocal(force);
	}
	
	/**
	 * Clear the force acting on this entity.
	 */
	public void resetForce() {
		this.force.zero();
	}
}