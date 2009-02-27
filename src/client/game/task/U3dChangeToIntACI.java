package client.game.task;

import client.game.state.U3dIntACIState;

import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;

public class U3dChangeToIntACI extends ChangeStateTask {

	public U3dChangeToIntACI(BasicGameState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		this.endState("AC1");
		GameStateManager.getInstance().deactivateAllChildren();
		GameStateManager.getInstance().activateChildNamed("AC1");
		((U3dIntACIState) GameStateManager.getInstance().getChild("AC1")).initialize();
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
