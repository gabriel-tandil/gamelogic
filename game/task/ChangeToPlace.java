package client.game.task;

import client.game.state.U3dState;
import com.jmex.game.state.GameStateManager;

public class ChangeToPlace extends ChangeStateTask {
	private String proxEstado;

	public ChangeToPlace(String proxEstado) {
		this.proxEstado = proxEstado;
	}

	public void execute() {
		GameStateManager.getInstance().deactivateAllChildren();
		GameStateManager.getInstance().activateChildNamed(proxEstado);
		((U3dState)GameStateManager.getInstance().getChild(proxEstado)).
			initialize();
	}
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
