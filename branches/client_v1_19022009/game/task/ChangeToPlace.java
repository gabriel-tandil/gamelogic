package client.game.task;

import client.game.state.U3dState;
import client.gameEngine.InputManager;
import client.manager.EntityManager;
import client.manager.ViewManager;

import com.jme.input.KeyInput;
import com.jmex.game.state.GameStateManager;

public class ChangeToPlace extends ChangeStateTask {
	private String proxEstado;

	public ChangeToPlace(String proxEstado) {
		this.proxEstado = proxEstado;
	}

	public void execute() {
		this.endState(proxEstado);
		GameStateManager.getInstance().deactivateAllChildren();
		GameStateManager.getInstance().activateChildNamed(proxEstado);
		
		//Ver bien que remover!!!		
		EntityManager.getInstance().removeAll();
		ViewManager.getInstance().removeAll();
		
		InputManager.getInstance().removeAll();
		KeyInput.get().removeListeners();
		
		((U3dState)GameStateManager.getInstance().getChild(proxEstado)).
			initialize();
	}
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
