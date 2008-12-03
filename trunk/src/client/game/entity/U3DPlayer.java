package client.game.entity;

import java.util.Hashtable;

import com.jme.math.Vector3f;
import common.datatypes.PlayerState;
import common.datatypes.Skin;

public class U3DPlayer extends Player {

	public U3DPlayer(String theTipo)
	{
		super(theTipo);
	}
	
	private Vector3f destino;

	public Vector3f getDestino() {
		return destino;
	}

	public void setDestino(Vector3f destino) {
		this.destino = destino;
	}
}