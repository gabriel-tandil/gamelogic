package client.game.task;

import client.game.state.U3dIntEcoState;
import client.manager.HudManager;

import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;

public class U3dChangeToIntEco extends ChangeStateTask {

	public U3dChangeToIntEco(BasicGameState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		super.execute();
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public void initTask() {
		GameStateManager.getInstance().deactivateAllChildren();
		GameStateManager.getInstance().activateChildNamed("Eco");
		((U3dIntEcoState) GameStateManager.getInstance().getChild("Eco")).initialize();
	}

}
