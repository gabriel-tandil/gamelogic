/**
 *<code>IDynamicEntity</code> defines the interface for all types of dynamic
 * entity in the game world.
 */
package client.game.entity;

import com.jme.math.Vector3f;

/** 
 * @author Santiago Michielotto
 * @version Created: 29-10-2008
 */
public interface IDynamicEntity extends IEntity {
	
	/**
	* Retrieve the current velocity of this <code>IDynamicEntity</code>.
 	* @param vector <code>Vector3f</code> velocity.
 	* */
	public void addVelocity(Vector3f vector);

	/**
	 * Set the mass of this dynamic entity.
	 * @param par The new float mass value to be set.
	 * */
	public void setMass(float par);

	/** 
	 *Add the given force to this entity.
	 * @param vector The force in <code>Vector3f</code> form.
	 */
	public void addForce(Vector3f vector);
	
	/** 
	 * Retrieve the <code>Vector3f</code> velocity of this <code>IDynamicEntity</code>.
	 * @return <code>Vector3f</code> velocity of this <code>IDynamicEntity</code>.
	 */
	public Vector3f getVelocity();
	
	/**
	 * Clear the force acting on this entity.
	 */
	public void resetForce();
	
	/** 
	 * Retrieve the <code>Vector3f</code> force of this <code>IDynamicEntity</code>.
	 * @return the <code>Vector3f</code> force of this <code>IDynamicEntity</code>.
	 */
	public Vector3f getForce();
	
	/** 
	 * Retrieve the mass value of this <code>IDynamicEntity</code>.
	 * @return the mass value of this <code>IDynamicEntity</code>.
	 */
	public float getMass(); 
	
	public String getAvatar();

	public void setAvatar(String avatar);
}