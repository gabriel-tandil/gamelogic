package client.game.task;

import client.minigame.MiniGameState;

import com.jmex.game.state.GameStateManager;

public class ChangeToGame extends ChangeStateTask {
	private String proxEstado;

	public ChangeToGame(String proxEstado) {
		this.proxEstado = proxEstado;
	}

	public void execute() {
		((MiniGameState)GameStateManager.getInstance().getChild(proxEstado)).initialize();
	}
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
