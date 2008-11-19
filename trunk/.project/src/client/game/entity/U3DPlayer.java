package client.game.entity;

import java.util.Hashtable;

import com.jme.math.Vector3f;

public class U3DPlayer extends Player {

	public U3DPlayer(String avatar,Vector3f force,String id,float mass
			,Hashtable Properties,Hashtable stats,Vector3f velocity, String tipo)
	{
		this.setAvatar(avatar);
		this.setForce(force);
		this.setId(id);
		this.setMass(mass);
		this.setProperties(Properties);
		this.setStats(stats);
		this.setVelocity(velocity);
		this.setTipo(tipo);
	}
}
