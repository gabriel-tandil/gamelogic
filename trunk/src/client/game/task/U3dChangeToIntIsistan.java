package client.game.task;

import client.game.state.U3dIntIsistanState;

import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;

public class U3dChangeToIntIsistan extends ChangeStateTask {

	public U3dChangeToIntIsistan(BasicGameState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		GameStateManager.getInstance().deactivateAllChildren();
		GameStateManager.getInstance().activateChildNamed("Isi");
		((U3dIntIsistanState) GameStateManager.getInstance().getChild("Isi")).initialize();
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
