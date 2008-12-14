package client.game.entity;

import client.game.PersonaDae;

public class U3DPlayer extends Player {

	private PersonaDae p;
	
	public U3DPlayer(String theTipo)
	{
		super(theTipo);
	}
	
	public void setDae(PersonaDae p){
		this.p=p;
	}
	
	public void isMoving(boolean state, boolean running, boolean forward){
		p.mover(state,running, forward);
	}
}
