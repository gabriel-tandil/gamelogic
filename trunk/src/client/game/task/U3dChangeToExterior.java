package client.game.task;

import client.game.state.U3dExteriorState;

import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;

public class U3dChangeToExterior extends ChangeStateTask {

	public U3dChangeToExterior(BasicGameState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		GameStateManager.getInstance().deactivateAllChildren();
		GameStateManager.getInstance().activateChildNamed("Exterior");
		((U3dExteriorState) GameStateManager.getInstance().getChild(
				"Exterior")).initialize();
		super.execute();
		
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public void initTask() {

	}

}
