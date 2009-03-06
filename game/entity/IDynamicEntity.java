/**
 *<code>IDynamicEntity</code> defines the interface for all types of dynamic
 * entity in the game world.
 */
package client.game.entity;

import client.game.IPersonaje;

import com.jme.math.Vector3f;
import common.datatypes.Skin;

/** 
 * Define la interface para todos los tipos de entidades dinámicas del juego
 * @author Cristian Calomino
 * @version Created: 20-11-2008
 */
public interface IDynamicEntity extends IEntity {
	/**
	 * Setea con cero la fuerza en esta entidad.
	 */
	public void resetForce();

	/** 
	 * Retorna el <code>Vector3f</code> velocity de la <code>IDynamicEntity</code>.
	 * @return <code>Vector3f</code> velocity de la <code>IDynamicEntity</code>.
	 */
	public Vector3f getVelocity();

	/** 
	 * Retorna la masa de la <code>IDynamicEntity</code>.
	 * @return mass de la <code>IDynamicEntity</code>.
	 */
	public float getMass();

	/** 
	* Agrega el vector a la <code>IDynamicEntity</code>.
 	* @param vector <code>Vector3f</code> de la velocidad.
 	* */
	public void addVelocity(Vector3f vector);


	/**
	 * Aplica el valor de la masa a esta <code>IDynamicEntity</code>.
	 * @param par el nuevo valor de la masa.
	 * */
	public void setMass(float par);

	/** 
	 * Agrega la fuerza esta entidad <code>IDynamicEntity</code>.
	 * @param vector <code>Vector3f</code> de la fuerza.
	 */
	public void addForce(Vector3f vector);

	
	/** 
	 * Retorna la fuerza de la <code>IDynamicEntity</code>.
	 * @return <code>Vector3f</code> force de la <code>IDynamicEntity</code>.
	 */
	public Vector3f getForce();
	
	public Vector3f getPosition();
	
	public void setPosition(Vector3f thePosition);
	
	public void setPlayerAvatar(IPersonaje p);
	
	public Skin getSkin();
	
}