package client.game.task;

import client.game.state.U3dExteriorState;
import client.manager.U3dHudManager;

import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;

public class U3dCargandoTask extends ChangeStateTask {

	public U3dCargandoTask(BasicGameState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		U3dHudManager.getInstance().setCargando();
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
