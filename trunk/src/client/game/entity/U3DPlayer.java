package client.game.entity;

import client.game.IPersonaje;
/**
 * clase que representa a un jugador particular del juego
 * 
 *
 */
public class U3DPlayer extends Player {

	private IPersonaje p;
	
	public U3DPlayer(String theTipo)
	{
		super(theTipo);
	}
	
	public void setPlayerAvatar(IPersonaje p){
		this.p=p;
	}
	
	public void removeAvatar(){
		this.p.clearAll();
		this.p = null;
	}
	
	public void isMoving(boolean state, boolean running, boolean forward){
		p.mover(state,running, forward);
	}
}
