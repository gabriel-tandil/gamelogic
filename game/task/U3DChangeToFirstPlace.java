package client.game.task;

import client.game.state.U3dState;

import com.jmex.game.state.GameStateManager;

public class U3DChangeToFirstPlace extends ChangeStateTask {

	private String proxEstado;

	public U3DChangeToFirstPlace(String proxEstado) {
		this.proxEstado = proxEstado;
	}
	
	public void execute() 
	{
		this.endState(proxEstado);

		GameStateManager.getInstance().deactivateAllChildren();
		GameStateManager.getInstance().activateChildNamed(proxEstado);

		U3dState proximoEstado = ((U3dState) GameStateManager.getInstance()
				.getChild(proxEstado));

		proximoEstado.initialize();

	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
