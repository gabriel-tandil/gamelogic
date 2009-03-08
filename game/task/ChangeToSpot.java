package client.game.task;

import client.spot.SpotState;

import com.jmex.game.state.GameStateManager;

public class ChangeToSpot extends ChangeStateTask {
	private String proxEstado;

	public ChangeToSpot(String proxEstado) {
		this.proxEstado = proxEstado;
	}

	public void execute() {
		((SpotState)GameStateManager.getInstance().getChild(proxEstado)).initialize();
	}
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
